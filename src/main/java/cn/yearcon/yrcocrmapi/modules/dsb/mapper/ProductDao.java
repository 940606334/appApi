package cn.yearcon.yrcocrmapi.modules.dsb.mapper;

import cn.yearcon.yrcocrmapi.modules.dsb.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {


    List<Product> findList(@Param("productNo") String productNo);
    List<Product> findListByIdAndWebid(
            @Param("id") Integer productId,
            @Param("webid") Integer webid);
}
