package com.zpf.demo.manager.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpf.demo.manager.product.entity.ProductEntity;
import com.zpf.demo.manager.product.repository.ProductManagerRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductManageService extends ServiceImpl<ProductManagerRepository, ProductEntity> {

}
