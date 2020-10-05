package com.zpf.demo.manager.product.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zpf.demo.manager.product.entity.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductManagerRepository extends BaseMapper<ProductEntity> {
}
