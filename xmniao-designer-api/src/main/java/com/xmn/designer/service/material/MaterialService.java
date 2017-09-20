package com.xmn.designer.service.material;

import java.util.List;
import java.util.Map;

import com.xmn.designer.entity.material.Material;
import com.xmn.designer.entity.material.MaterialAttr;
import com.xmn.designer.entity.material.MaterialAttrGroup;
import com.xmn.designer.entity.material.MaterialCarousel;
import com.xmn.designer.entity.order.Order;


/**
 * 平面物料服务类
 * 
 * @author zhouxiaojian
 * @date 2016/11/18
 */
public interface MaterialService {

    public Map<String, Object> category(String type, Long id,String key);
    
    /**
     * 基础物料搜索接口
     */
    public  Map<String, Object> search(String keys, Integer page, Integer pageSize) ;

    /**
     * 平面物料列表接口
     * 
     * @param material
     * @return
     */
    public Map<String, Object> list(Material material);

    /**
     * 平面物料详情
     * 
     * @param id
     * @param deliveryCityNo
     * @return
     */
    public Material detail(Long id, String deliveryCityNo);

    /**
     * 基础物料订单规格选择接口
     * 
     * @param id
     * @return
     */
    public List<MaterialAttr> materialAttr(Long id);

    /**
     * 基础物料编辑接口
     * 
     * @param materialId
     * @return
     */
    public List<MaterialCarousel> edit(Long materialId);

    /**
     * 创建订单
     * 
     * @param id
     * @param materialAttrId
     * @param materialAttrVal
     * @param nums
     * @param shippingAddress
     * @param remark
     * @param dataList
     * @return
     */
    public Map<String, Object> createOrder(MaterialAttrGroup materialAttrGroup ,Order order ,String dataList,Integer areaId,Integer nums);
    /**
     * 订单详情
     */
    public Map<String, Object> orderDetail(MaterialAttrGroup materialAttrGroup ,Integer areaId,Integer nums);



}
