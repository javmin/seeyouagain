package com.xmniao.xmn.core.vstar.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.vstar.entity.TVstarPlayerInfo;
import com.xmniao.xmn.core.vstar.entity.TVstarSellerInfo;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarPlayerInfoMapper
 * 
 * 类描述： 星食尚大赛选手信息表DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-2 上午11:56:39 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TVstarPlayerInfoDao extends BaseDao<TVstarPlayerInfo> {
	
	/**
	 * 
	 * 方法描述：获取排名统计列表<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-19上午9:31:02 <br/>
	 * @param playerReq
	 * @return
	 */
	@DataSource(value="slave")
	List<TVstarPlayerInfo> getRankList(TVstarPlayerInfo playerReq);
	
	/**
	 * 
	 * 方法描述：获取排名统计选手总数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-19上午9:31:38 <br/>
	 * @param playerReq
	 * @return
	 */
	@DataSource(value="slave")
	long getRankCount(TVstarPlayerInfo playerReq);
	
	/**
	 * 
	 * 方法描述：根据UID查询签约商家数量<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-8下午6:31:06 <br/>
	 * @param param
	 * @return
	 */
	@DataSource(value="slave")
	List<Map<String,Object>> getSignedSellerNum(Map<String,Object> param);
	
	/**
	 * 
	 * 方法描述：根据UID查询签约商家列表信息<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-8下午6:31:06 <br/>
	 * @param param
	 * @return
	 */
	@DataSource(value="slave")
	List<TVstarSellerInfo> getSellerListByUid(TVstarSellerInfo param);
	
	
	/**
	 * 
	 * 方法描述：根据UID统计签约商家数量<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-8下午6:31:06 <br/>
	 * @param param
	 * @return
	 */
	@DataSource(value="slave")
	long getSellerCountByUid(TVstarSellerInfo param);
	
	
	/**
	 * 
	 * 方法描述：根据商家ID集合sellerIdList获取商家流水 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-9上午9:57:44 <br/>
	 * @param param
	 * @return
	 */
	@DataSource(value="slave")
	List<Map<String,Object>> getSellerEarningsBySellerIdList(Map<String,Object> param);
	
	/**
	 * 
	 * 方法描述：根据选手UID集合获取粉丝数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-15上午10:26:56 <br/>
	 * @return
	 */
	@DataSource(value="slave")
	List<Map<String,Object>> getVstarFansByUidList(Map<String,Object> param);
	
}