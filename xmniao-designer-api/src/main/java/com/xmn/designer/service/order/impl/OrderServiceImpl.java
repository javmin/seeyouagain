package com.xmn.designer.service.order.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmn.designer.base.GlobalConfig;
import com.xmn.designer.base.Page;
import com.xmn.designer.constants.DesignerConsts;
import com.xmn.designer.dao.customize.OrderMaterialCustomizeCarouselDao;
import com.xmn.designer.dao.customize.OrderMaterialCustomizeDao;
import com.xmn.designer.dao.material.*;
import com.xmn.designer.dao.order.OrderDao;
import com.xmn.designer.entity.customize.OrderMaterialCustomize;
import com.xmn.designer.entity.material.Material;
import com.xmn.designer.entity.material.MaterialAttrGroup;
import com.xmn.designer.entity.material.MaterialCategory;
import com.xmn.designer.entity.material.OrderMaterial;
import com.xmn.designer.entity.order.DesignerOrder;
import com.xmn.designer.entity.order.Order;
import com.xmn.designer.exception.CustomException;
import com.xmn.designer.service.order.OrderService;
import com.xmn.designer.service.postage.PostageService;
import com.xmn.designer.utils.MakeOrderNum;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GlobalConfig globalConfig;

	@Autowired
	private MakeOrderNum makeOrderNum;


	@Autowired
	private MaterialAttrGroupDao materialAttrGroupDao;

	@Autowired
	private OrderMaterialDao orderMaterialDao;

	@Autowired
	private OrderMaterialCustomizeCarouselDao orderMaterialCustomizeCarouselDao;

	@Autowired
	private MaterialDao  materialDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderMaterialCustomizeDao orderMaterialCustomizeDao;
	
	@Autowired
	private MaterialCategoryDao categoryDao;

	
	@Autowired
	private PostageService postageService;

    @Autowired
	private OrderMaterialCarouselDao orderMaterialCarouselDao;

//	@Autowired
//	private ExpressInfo expressInfo;

	private static final ObjectMapper MAPPER= new ObjectMapper();
	
	@Override
	public MaterialAttrGroup getMaterialAttr(Long materialAttrId) {
		return materialAttrGroupDao.selectByPrimaryKey(materialAttrId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public OrderMaterial placeOrder(MaterialAttrGroup materialAttrGroup,Order order,Integer num,Integer areaId) {
		try {
			String type = order.getType();
			if(type!=DesignerConsts.ORDER_TYPE_MATERIAL){
				throw new RuntimeException("生成订单传递类型错误");
			}
				BigDecimal condition = postageService.calculateCondittion(materialAttrGroup.getMaterialId(), areaId,num);
				String orderNum = makeOrderNum.makeOrderNum();
				order.setFreight(condition);
				order.setType(DesignerConsts.ORDER_TYPE_MATERIAL);
				order.setStatus(DesignerConsts.ORDER_STATUS_NO_PAYMENT);
				order.setOrderNo(orderNum);
				order.setCreateTime(new Date());
				order.setVersionLock(0L);
				order.setBalance(new BigDecimal(0));
				Material material = materialDao.selectByPrimaryKey(materialAttrGroup.getMaterialId());
				order.setOrderAmount(material.getSalePrice().add(materialAttrGroup.getAmount()).multiply(new BigDecimal(num)));
				orderDao.insert(order);
				MaterialCategory materialCategory = categoryDao.selectByPrimaryKey(material.getCategoryId());
				OrderMaterial orderMaterial = new OrderMaterial();
				orderMaterial.setMaterialId(material.getId());	//物料订单
				orderMaterial.setBasePrice(material.getSalePrice());
				orderMaterial.setSalePrice(orderMaterial.getBasePrice().add(materialAttrGroup.getAmount()));
				orderMaterial.setTitle(material.getTitle());
				orderMaterial.setRemark(material.getRemark());
				orderMaterial.setOrderNo(orderNum);
				orderMaterial.setNums(num);
				orderMaterial.setMaterialAttrGroupVal(materialAttrGroup.getMaterialAttrVals());
				orderMaterial.setCategoryName(materialCategory.getName());
				orderMaterialDao.insert(orderMaterial);
				return orderMaterial;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public OrderMaterialCustomize placeOrderCustomize(Order order, OrderMaterialCustomize orderMaterialCustomize){
		String type = order.getType();
		if(DesignerConsts.ORDER_TYPE_CUSTOMIZE!=type){
			throw new RuntimeException("生成订单传递类型错误");
		}
		String orderNum = makeOrderNum.makeOrderNum();
		order.setStatus(DesignerConsts.ORDER_STATUS_NO_PAYMENT);
		order.setOrderNo(orderNum);
		order.setCreateTime(new Date());
		order.setFreight(new BigDecimal(0));
		order.setOrderAmount(new BigDecimal(0));
		order.setBalance(new BigDecimal(0));
		order.setVersionLock(0L);
		order.setType(DesignerConsts.ORDER_TYPE_CUSTOMIZE);
		orderDao.insert(order);
		orderMaterialCustomize.setOrderNo(orderNum);
		orderMaterialCustomizeDao.insertSelective(orderMaterialCustomize);
		return orderMaterialCustomize;
	}

	@Override
	public HashMap<String, Object> queryOrderList(Order order, Page page) {
		List<DesignerOrder> designerOrderList = new ArrayList<>();

		int count = orderDao.countOrder(order);

		// 查询订单列表 根据条件查询
		List<Order> orderList = orderDao.selectBySelective(order,page);



		// 遍历Order列表
		for (Order ol : orderList) {
			// 获取订单号
			String orderNo = ol.getOrderNo();

			// 如果订单类型是 平面物料查询d_order_material
			if (DesignerConsts.ORDER_TYPE_MATERIAL.equals(ol.getType())) {

				// 根据订单号查询平面订单
				OrderMaterial orderMateria = orderMaterialDao.selectByOrderNo(orderNo);

				DesignerOrder designerOrder = new DesignerOrder(ol, orderMateria);
				designerOrderList.add(designerOrder);
			}

			// 如果订单类型是 定制物料查询 d_order_material_customize
			if (DesignerConsts.ORDER_TYPE_CUSTOMIZE.equals(ol.getType())) {
				// 根据订单号(order_no)查询定制物料
				OrderMaterialCustomize customizeOrder = orderMaterialCustomizeDao.selectByOrderNo(orderNo);

                customizeOrder.setSpecJson(setCustomizeHigh(customizeOrder.getSpecJson()));
				// 封装响应数据
				DesignerOrder designerOrder = new DesignerOrder(ol, customizeOrder);

				designerOrderList.add(designerOrder);
			}
		}


		HashMap<String, Object> result = new HashMap<>();
		result.put("count", count);
		result.put("list", designerOrderList);
		return result;
	}


	// 获取 定制物料 详情
	@Override
	public DesignerOrder queryCustomizeDetail(String orderNo,Long uid) throws CustomException {
        // 根据 order_no和uid 查询 order表
		Order order = orderDao.selectByOrderNum(orderNo, uid);

		// 查询结果为空,抛出异常
		if (order == null) {
			throw new CustomException("订单号无效");
		}

		// 根据order_no 查询订单详情
		OrderMaterialCustomize orderMaterialCustomize = orderMaterialCustomizeDao.selectByOrderNo(order.getOrderNo());
		orderMaterialCustomize.setSpecJson(setCustomizeHigh(orderMaterialCustomize.getSpecJson()));

		// 通过DesignerOrder封装数据,返回给Controller
		DesignerOrder designerOrder = new DesignerOrder(order, orderMaterialCustomize);

		return designerOrder;
	}

	// 获取 平面物料 订单详情
	@Override
	public DesignerOrder queryMatrialDetail(String orderNo, Long id) throws CustomException {
		// 查询订单表
		Order order = orderDao.selectByOrderNum(orderNo, id);
		if (order == null) {
			throw new CustomException("订单号无效");
		}

		// 查询平面物料订单
		OrderMaterial orderMaterial = orderMaterialDao.selectByOrderNum(order.getOrderNo());

        // 返回响应参数
		DesignerOrder designerOrder = new DesignerOrder(order, orderMaterial);
		return designerOrder;
	}


	/**
	 * 确认订单
	 */
	@Transactional
	@Override
	public void confirmOrder(Order order) throws CustomException {
		// 确认订单是否正确
        order = orderDao.selectByOrderNoAndUid(order);
		if (order == null) {
			throw new CustomException("订单号有误");
		}

		// 更新订单状态
		Order record = new Order();
		record.setId(order.getId());
		record.setStatus(DesignerConsts.ORDER_STATUS_FINISHED);
		orderDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 删除订单
	 */
	@Transactional
	@Override
	public void deleteOrder(Order order) throws CustomException {
		order = orderDao.selectByOrderNoAndUid(order);
		if (order == null) {
			throw new CustomException("订单号无效");
		}

		// 更新订单状态为 : 已删除
		Order record = new Order();
		record.setId(order.getId());
		record.setStatus(DesignerConsts.ORDER_STATUS_DELETED);
		orderDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 取消订单
	 */
	@Transactional
	@Override
	public void cancelOrder(Order order) throws CustomException {
		// 封装更新数据
		Order record = new Order();
		record.setCancelReason(order.getCancelReason());
        record.setStatus(DesignerConsts.ORDER_STATUS_CANCELED);

		// 根据OrderNo查询订单
		order = orderDao.selectByOrderNoAndUid(order);
		if (order == null) {
			throw new CustomException("订单号有误");
		}

		// 更新订单状态
		record.setId(order.getId());
		orderDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 提醒发货
	 * @param order
	 */
	@Override
	@Transactional
	public Integer remindShipment(Order order) throws CustomException {

		// 提醒供应商发货
		order = orderDao.selectByOrderNoAndUid(order);
		if (order == null) {
			throw new CustomException("订单号无效!");
		}

		if (order.getRemindExpress() == 0) {
			Order record = new Order();
			record.setId(order.getId());
			record.setRemindExpress(DesignerConsts.ORDER_REMIND_REMINDED);
            orderDao.updateByPrimaryKeySelective(record);
            return DesignerConsts.ORDER_REMIND_NO_REMINDED;
		}else {
            return order.getRemindExpress();
		}
	}

	/**
	 * 查询物流信息
	 */
	@Override
	public Object queryExpInfo(Order order) throws IOException, CustomException {
		HashMap<String, Object> resultMap = new HashMap<>();


		order = orderDao.selectByOrderNoAndUid(order);
		if (order == null) {
			throw new CustomException("订单号无效");
		}

		// 请求物流
		Document expInfoDoc = Jsoup.connect(globalConfig.getExpInfoApiUrl())
				.data("appkey", globalConfig.getExpInfoApiAppkey())	// 获取api 必须的key
				.data("type", "auto")			// 设置快递公司, auto api自动识别快递公司
				.data("number", order.getCourierNumber())	// 设置快递单号
                .timeout(100000)					// 设置超时时间
				.get();
        // 解析物流接口 响应参数
		JSONObject jsonObject = JSONObject.fromObject(expInfoDoc.text());
		logger.info("物流接口返回数据:"+jsonObject);

        resultMap.put("number",order.getCourierNumber());
        resultMap.put("type", order.getPostCompany());

		if (!("0".equals(jsonObject.get("status")))) {
//			throw new CustomException("物流查询失败!");
            resultMap.put("list",new ArrayList<>());
            resultMap.put("deliverystatus","1");
			return  resultMap;
		}
		JSONObject result = jsonObject.getJSONObject("result");
		resultMap.put("list",result.get("list"));
		resultMap.put("deliverystatus",result.get("deliverystatus"));

		// 返回响应内容
		return resultMap;

	}

	@Override
	public OrderMaterial getOrderMeterialByOrderNum(String orderNum) {
		return orderMaterialDao.selectByOrderNum(orderNum);
	}

	@Override
	public Order getOrderByOrderNum(String orderNum, Long outId) {
		return orderDao.selectByOrderNum(orderNum, outId);
	}


	@Override
	public OrderMaterialCustomize getOrderMeterialCustomizeByOrderNum(String orderNum) {
		return orderMaterialCustomizeDao.selectByOrderNo(orderNum);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void payOrder(Order order1) {
		orderDao.updatePayStatus(order1);
	}



	/**
	 * 设置高亮显示 字段
	 * @param specJson
	 * @return
	 */
	public String setCustomizeHigh(String specJson){
		JSONArray specArray = JSONArray.fromObject(specJson);
		ArrayList<HashMap<String, Object>> specList = new ArrayList<>();
		// 检测APP端上传数据完整性, 重新生成JSON数据
		for (int i = 0; i < specArray.size(); i++) {
			HashMap<String, Object> sepcMap = new HashMap<>();
			JSONObject jsonObject = specArray.getJSONObject(i);
			Object id = jsonObject.get("id");
			Object name = jsonObject.get("name");
			Object vals = jsonObject.get("vals");

			if(id==null || name==null || vals==null){
				throw new RuntimeException();
			}
			// 设置高亮显示的字段
			if(globalConfig.getHighName().equals(name.toString())){
				sepcMap.put("highlight",true);
			}else {
				sepcMap.put("highlight",false);
			}
			sepcMap.put("id",id);
			sepcMap.put("name",name);
			sepcMap.put("vals",vals);
			specList.add(sepcMap);

		}
		return com.alibaba.fastjson.JSONObject.toJSONString(specList);
	}
}
