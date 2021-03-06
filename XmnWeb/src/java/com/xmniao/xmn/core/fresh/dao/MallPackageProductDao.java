package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.MallPackageProduct;

public interface MallPackageProductDao extends BaseDao<MallPackageProduct>{
    int deleteByPrimaryKey(Long id);

    int insert(MallPackageProduct record);

    int insertSelective(MallPackageProduct record);

    MallPackageProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallPackageProduct record);

    int updateByPrimaryKey(MallPackageProduct record);

	/**
	 * 方法描述：
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月8日下午4:37:23 <br/>
	 * @param intIds
	 * @return
	 */
	List<MallPackageProduct> getByIds(@Param("ids")List<Integer> intIds);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月9日下午2:39:06 <br/>
	 * @param productIds
	 * @param id
	 */
	void updatePackageIdByIds(@Param("productIds")List<String> productIds,@Param("id") Long id);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月9日下午4:39:50 <br/>
	 * @param id
	 * @return
	 */
	List<MallPackageProduct> getListByPackageId(Long id);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月14日下午6:05:18 <br/>
	 * @param codeId
	 * @param pvIds
	 * @return
	 */
	int countPvIds(@Param("codeId")Long codeId, @Param("pvIds")String pvIds);
}