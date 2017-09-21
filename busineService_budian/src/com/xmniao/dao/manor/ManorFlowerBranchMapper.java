package com.xmniao.dao.manor;

import com.xmniao.domain.manor.ManorFlowerBranch;
import com.xmniao.domain.manor.ManorFlowerCount;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface ManorFlowerBranchMapper {
    int deleteByPrimaryKey(String id);

    int insert(ManorFlowerBranch record);

    int insertSelective(ManorFlowerBranch record);

    ManorFlowerBranch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ManorFlowerBranch record);

    int updateByPrimaryKey(ManorFlowerBranch record);

    /** 批量插入数据 */
    void insertBatch(@Param("branchList") ArrayList<ManorFlowerBranch> branchList);

    /** 批量插入 branch关系链表初始数据 */
    void insertInitChainBatch(@Param("branchList") ArrayList<ManorFlowerBranch> branchList);

    /** 批量迁移 关系链 */
    void insertMigrateChain(@Param("branch") ManorFlowerBranch branch, @Param("parentBranch") ManorFlowerBranch parentBranch);

    /** 查询location下 最走下的叶子节点 */
    ManorFlowerBranch selectLowestByUidAndLocation(@Param("parentUid") Integer parentUid, @Param("location") Integer location);

    /** 查询所有下级branch
     * @param uid*/
    List<ManorFlowerBranch> selectAllSubBranchByUid(@Param("uid") Integer uid);

    /** 批量更新bracn */
    void updateBatch(@Param("updateList") List<ManorFlowerBranch> updateList, @Param("zid") String zid, @Param("parentBranch") ManorFlowerBranch parentBranch, @Param("subUid") Integer subUid);

    /** 根据uid和location查询用户节点 */
    ManorFlowerBranch selectByUidAndLocation(@Param("uid") int uid, @Param("location") Integer location);

    /** 根据用户uid 查询该用户的 3个初始节点 */
    List<ManorFlowerBranch> selectByUid(@Param("uid") Integer uid);

    /** 统计花朵数量 */
    List<ManorFlowerCount> countFlowerByNode(@Param("branch") ManorFlowerBranch branch);

    /** 按花朵类型统计分支节点下所有的未枯萎花朵 */
    ManorFlowerCount countFlowerByTypeInBranch(@Param("branch") ManorFlowerBranch branch,
                                               @Param("types") int[] types,
                                               @Param("shareTypes") int[] shareTypes,
                                               @Param("perishType") int perishType);

    /** 按花朵类型统计分支节点下自己种值未枯萎花朵 */
    ManorFlowerCount countFlowerByTypeAndFloristInBranch(@Param("branch") ManorFlowerBranch branch,
                                                         @Param("types") int[] types,
                                                         @Param("shareTypes") int[] shareTypes,
                                                         @Param("perishType") int perishType, @Param("floristUid") Integer floristUid);

    /** 按花田统计自己种植的 所有类型花 */
    int countFloristUidInBranch(@Param("branch") ManorFlowerBranch branch);

	 /**
	 * 方法描述：迁移关系链时修改zid
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月28日下午6:24:08 <br/>
	 * @param oldZid 
	 * @param newZid 
	 * @param manorFlowerBranchs
	 * @return
	 */
	int updateZidMigrate(@Param("oldZid")String oldZid, @Param("newZid")String newZid,@Param("manorFlowerBranchs") List<ManorFlowerBranch> manorFlowerBranchs);

	 /**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月28日下午6:39:19 <br/>
	 * @param bs
	 * @param id
	 * @return
	 */
	int updateParentId(@Param("manorFlowerBranchs")List<ManorFlowerBranch> manorFlowerBranchs,@Param("parentId") String parentId);

	 /**
	 * 方法描述：修改level的偏差
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月29日上午9:49:09 <br/>
	 * @param xiajikeng
	 * @param i
	 * @return
	 */
	int updateLevelAdd(@Param("manorFlowerBranchs")List<ManorFlowerBranch> manorFlowerBranchs,@Param("levelDeviation") int levelDeviation);
}