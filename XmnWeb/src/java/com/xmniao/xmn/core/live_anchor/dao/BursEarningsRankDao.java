package com.xmniao.xmn.core.live_anchor.dao;

import com.xmniao.xmn.core.live_anchor.entity.BursEarningsRank;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * 项目名称：XmnWeb_412
 * 
 * 类名称：BursEarningsRankDao
 * 
 * 类描述： 会员活动收益等级DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-4-8 下午4:58:23 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BursEarningsRankDao {
	
	/**
	 * 
	 * 方法描述：根据主键删除纪录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-8下午4:58:53 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="burs")
    int deleteById(Long id);

	/**
	 * 
	 * 方法描述：新增纪录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-8下午4:59:08 <br/>
	 * @param record
	 * @return
	 */
    @DataSource(value="burs")
    int add(BursEarningsRank record);

    /**
     * 
     * 方法描述：根据主键查询记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-4-8下午4:59:25 <br/>
     * @param id
     * @return
     */
    @DataSource(value="burs")
    BursEarningsRank selectById(Long id);
    
    /**
     * 
     * 方法描述：根据bean对象查询记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-4-8下午4:59:25 <br/>
     * @param id
     * @return
     */
    @DataSource(value="burs")
    BursEarningsRank selectByBean(BursEarningsRank record);

    /**
     * 
     * 方法描述：更新记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-4-8下午4:59:41 <br/>
     * @param record
     * @return
     */
    @DataSource(value="burs")
    int update(BursEarningsRank record);


	@DataSource(value="burs")
	List<HashMap<String, String>> selectRankUsers(@Param("rankid") Integer rankid, @Param("selectUserList") ArrayList<HashMap<String, String>> selectUserList);

	@DataSource(value="burs")
	int countRankUsers(@Param("rankid") Integer rankid, @Param("selectUserList") ArrayList<HashMap<String, String>> selectUserList);

	@DataSource(value="burs")
	Map<String,Object> selectRankInfo(@Param("uid") Integer uid );
}