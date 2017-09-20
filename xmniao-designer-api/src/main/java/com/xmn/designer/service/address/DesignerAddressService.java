package com.xmn.designer.service.address;

import java.util.List;

import com.xmn.designer.entity.address.Area;
import com.xmn.designer.entity.address.DesigberAddress;
import com.xmn.designer.entity.postage.PostageConditions;

public interface DesignerAddressService {
	/**
	 * 
	 * 方法描述：获取地址列表 创建人：jianming 创建时间：2016年11月14日 下午5:44:00
	 * 
	 * @param sessionToken
	 * @return
	 */
	List<DesigberAddress> list(String sessionToken);
	
	/**
	 * 
	 * 方法描述：获取默认地址
	 * 创建人：jianming  
	 * 创建时间：2016年11月14日 下午6:05:43   
	 * @param sessionToken
	 * @return
	 */
	DesigberAddress getDefaultAddress(String sessionToken);
	
	/**
	 * 
	 * 方法描述：添加收货地址
	 * 创建人：jianming  
	 * 创建时间：2016年11月15日 下午2:19:02   
	 * @param desigberAddress
	 * @param sessionToken 
	 */
	void add(DesigberAddress desigberAddress, String sessionToken);
	
	/**
	 * 
	 * 方法描述：修改收货地址
	 * 创建人：jianming  
	 * 创建时间：2016年11月15日 下午4:37:22   
	 * @param desigberAddress
	 * @param sessionToken
	 */
	void edit(DesigberAddress desigberAddress, String sessionToken);
	
	/**
	 * 
	 * 方法描述：删除收货地址
	 * 创建人：jianming  
	 * 创建时间：2016年11月15日 下午4:42:01   
	 * @param id
	 * @param sessionToken
	 */
	void delete(Integer id, String sessionToken);
	
	/**
	 * 
	 * 方法描述：获取字级区域信息
	 * 创建人：jianming  
	 * 创建时间：2016年11月15日 下午5:32:28   
	 * @param pid
	 * @param sessionToken 
	 * @return
	 */
	List<Area> listArea(Integer pid);
	
	/**
	 * 
	 * 方法描述：获取指定商户指定区域运费信息
	 * 创建人：jianming  
	 * 创建时间：2016年11月21日 下午3:11:18   
	 * @param materialId
	 * @param areaId
	 * @return
	 */
	PostageConditions getCondition(Long materialId, Integer areaId);
	
	/**
	 * 
	 * 方法描述：根据名字搜索区域
	 * 创建人：jianming  
	 * 创建时间：2016年11月23日 上午10:11:26   
	 * @param areaName
	 * @return
	 */
	Area seachAreaName(String areaName);

}
