package com.xmn.designer.service.order;

import com.xmn.designer.base.Page;
import com.xmn.designer.entity.customize.OrderMaterialCustomize;
import com.xmn.designer.entity.material.MaterialAttrGroup;
import com.xmn.designer.entity.material.OrderMaterial;
import com.xmn.designer.entity.order.DesignerOrder;
import com.xmn.designer.entity.order.Order;
import com.xmn.designer.exception.CustomException;

import java.io.IOException;
import java.util.HashMap;

public interface OrderService {

	/**
	 *
	 * 方法描述：获取物料链
	 * 创建人：jianming
	 * 创建时间：2016年11月16日 上午11:46:34
	 * @param MaterialAttrId
	 * @return  
	 */
	MaterialAttrGroup getMaterialAttr(Long materialAttrId);

	/**
	 * 
	 * 方法描述：生成物料订单
	 * 创建人：jianming  
	 * 创建时间：2016年11月17日 下午8:14:47   
	 * @param materialAttrGroup  物料链
	 * @param order  用户,收货地址内容
	 * @param num  购买数量
	 * @param areaId 省id
	 * @return 订单Id
	 */
	OrderMaterial placeOrder(MaterialAttrGroup materialAttrGroup,Order order,Integer num,Integer areaId);

	/**
	 *
	 * 方法描述：根据订单号查询物料订单
	 * 创建人：jianming
	 * 创建时间：2016年11月16日 下午5:38:16
	 * @param orderNum
	 * @return
	 */
	OrderMaterial getOrderMeterialByOrderNum(String orderNum);

	/**
	 *
	 * 方法描述：根据订单号查询订单
	 * 创建人：jianming
	 * 创建时间：2016年11月16日 下午6:00:28
	 * @param orderNum
	 * @param outId
	 * @return
	 */
	Order getOrderByOrderNum(String orderNum, Long outId);

	
	/**
	 * 
	 * 方法描述：自设计订单生成
	 * 创建人：jianming  
	 * 创建时间：2016年11月17日 下午8:12:46   
	 * @param order    用户,收货地址内容
	 * @param orderMaterialCustomize  物料定制信息
	 * @return  订单Id
	 */
	OrderMaterialCustomize placeOrderCustomize(Order order, OrderMaterialCustomize orderMaterialCustomize);
	
	/**
	 * 
	 * 方法描述：根据订单号获取订制物料订单标题
	 * 创建人：jianming  
	 * 创建时间：2016年11月18日 上午9:35:52   
	 * @param orderNum
	 * @return
	 */
	OrderMaterialCustomize getOrderMeterialCustomizeByOrderNum(String orderNum);


	/**
	 * 获取订单列表, 并根据条件筛选
	 */
	HashMap<String, Object> queryOrderList(Order order, Page page);

	/**
	 * 查询 定制订单 详情
	 * @return
	 */
    DesignerOrder queryCustomizeDetail(String orderNo,Long uid) throws CustomException;

	/**
	 * 查询 平面物料订单 详情
	 */
	DesignerOrder queryMatrialDetail(String orderNo, Long id) throws CustomException;

	/**
	 * 确认订单
	 */
	void confirmOrder(Order order) throws CustomException;

	/**
	 * 删除订单
	 */
    void deleteOrder(Order order) throws CustomException;

	/**
	 * 取消订单
	 */
	void cancelOrder(Order order) throws CustomException;

	/**
	 * 提醒发货
	 * @param order
	 */
	Integer remindShipment(Order order) throws CustomException;

	/**
	 * 查询物流信息
	 */
	Object queryExpInfo(Order order) throws IOException, CustomException;

	/**
	 *
	 * 方法描述：修改支付状态
	 * 创建人：jianming
	 * 创建时间：2016年11月22日 下午4:01:54
	 * @param order1
	 */
	void payOrder(Order order1);

	/**
	 * 设置高亮显示 字段
	 * @param specJson
	 * @return
	 */
	String setCustomizeHigh(String specJson);
}
