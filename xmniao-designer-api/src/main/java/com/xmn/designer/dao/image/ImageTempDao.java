package com.xmn.designer.dao.image;


import com.xmn.designer.entity.image.ImageTemp;

public interface ImageTempDao {
    int deleteByPrimaryKey(Long id);

    int insert(ImageTemp record);

    int insertSelective(ImageTemp record);

    ImageTemp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImageTemp record);

    int updateByPrimaryKey(ImageTemp record);

    int deleteByUri(String uri);
}