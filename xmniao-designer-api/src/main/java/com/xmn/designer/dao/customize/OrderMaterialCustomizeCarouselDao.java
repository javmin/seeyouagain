package com.xmn.designer.dao.customize;

import com.xmn.designer.entity.customize.OrderMaterialCustomizeCarousel;

import java.util.List;

public interface OrderMaterialCustomizeCarouselDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderMaterialCustomizeCarousel record);

    int insertSelective(OrderMaterialCustomizeCarousel record);

    OrderMaterialCustomizeCarousel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderMaterialCustomizeCarousel record);

    int updateByPrimaryKey(OrderMaterialCustomizeCarousel record);

    List<OrderMaterialCustomizeCarousel> selectByCustomizeMaterialId(Long id);

    List<String> selectImageByCustomizeMaterialId(Long id);
}