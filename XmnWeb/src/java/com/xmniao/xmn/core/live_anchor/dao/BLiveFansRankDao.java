package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRank;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：BLiveFansRankDao
 * 
 * 类描述： 直播粉丝级别Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-20 上午11:06:22 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BLiveFansRankDao extends BaseDao<BLiveFansRank>{
	
	/**
	 * 
	 * 方法描述：根据Id删除纪录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19下午5:51:30 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="burs")
    int deleteById(Long id);

	/**
	 * 
	 * 方法描述：新增粉丝级别 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19下午5:51:30 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="burs")
    void add(BLiveFansRank record);

	/**
	 * 
	 * 方法描述：获取纪录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19下午5:51:30 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="burs")
    BLiveFansRank getFansRank(Long id);
	
	/**
	 * 
	 * 方法描述：获取纪录列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19下午5:51:30 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="burs")
    List<BLiveFansRank> getList(BLiveFansRank record);
	
	/**
	 * 
	 * 方法描述：获取纪录数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19下午5:51:30 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="burs")
	Long count(BLiveFansRank record);

    /**
     * 更新粉丝级别纪录
     */
	@DataSource(value="burs")
    Integer update(BLiveFansRank record);
	
	/**
	 * 
	 * 方法描述：同步粉丝级别 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-3下午4:09:07 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="burs")
	Integer syncRankNo(BLiveFansRank record);

	@DataSource(value="burs")
    List<BLiveFansRank> selectByRankType(@Param("rankType") Integer rankType);
	
	/**
	 * 方法描述：根据V客编号查询SAAS，主播数量 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月31日上午10:09:24 <br/>
	 * @param vkeUid
	 * @return
	 */
	@DataSource(value="burs")
	BLiveFansRank getQuotaByVkeUid(@Param("uid") Integer vkeUid);
}