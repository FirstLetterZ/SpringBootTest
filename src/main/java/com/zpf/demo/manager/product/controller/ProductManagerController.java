package com.zpf.demo.manager.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zpf.demo.global.ErrorCode;
import com.zpf.demo.global.PageBean;
import com.zpf.demo.manager.product.entity.ProductEntity;
import com.zpf.demo.manager.product.service.ProductManageService;
import com.zpf.demo.global.ResponseBean;
import com.zpf.demo.tools.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

@RestController
public class ProductManagerController {
    @Autowired
    private ProductManageService manageService;

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public ResponseBean<Boolean> delProductInfo(ProductEntity info) {
        ResponseBean<Boolean> result = new ResponseBean<>();
        result.data = false;
        if (info == null || info.getId() <= 0) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        if (info.getName() == null || info.getName().length() < 1) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        if (info.getDescription() == null || info.getDescription().length() < 1) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        if (info.getTopList() == null || info.getTopList().size() < 1) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        if (info.getDetailList() == null || info.getDetailList().size() < 1) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        if (info.getOriginalPrice() == null || BigDecimal.ZERO.compareTo(info.getOriginalPrice()) >= 0) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(info.getId());
        productEntity.setName(info.getName());
        productEntity.setDescription(info.getDescription());
        productEntity.setDetailList(info.getDetailList());
        productEntity.setTopList(info.getTopList());
        productEntity.setOriginalPrice(info.getOriginalPrice());
        int handelResult = -1;
        try {
            QueryWrapper<ProductEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("id", info.getId());
            handelResult = manageService.getBaseMapper().update(productEntity, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (handelResult <= 0) {
            result.setErrInfo(ErrorCode.UPDATE_FAIL);
        } else {
            result.data = true;
        }
        return result;
    }

    @RequestMapping(value = "/delProduct", method = RequestMethod.POST)
    public ResponseBean<Boolean> delProductInfo(Long id) {
        ResponseBean<Boolean> result = new ResponseBean<>();
        result.data = false;
        if (id == null || id <= 0) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        int delResult = -1;
        try {
            QueryWrapper<ProductEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("id", id);
            delResult = manageService.getBaseMapper().delete(wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (delResult < 0) {
            result.setErrInfo(ErrorCode.REMOVE_FAIL);
        } else {
            result.data = true;
        }
        return result;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ResponseBean<Boolean> addProductInfo(@RequestBody ProductEntity info) {
        ResponseBean<Boolean> result = new ResponseBean<>();
        result.data = false;
        if (info == null) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        if (info.getName() == null || info.getName().length() < 1) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        if (info.getDescription() == null || info.getDescription().length() < 1) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        if (info.getTopList() == null || info.getTopList().size() < 1) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        if (info.getDetailList() == null || info.getDetailList().size() < 1) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        if (info.getOriginalPrice() == null || BigDecimal.ZERO.compareTo(info.getOriginalPrice()) >= 0) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(SnowflakeIdWorker.get().nextId());
        productEntity.setName(info.getName());
        productEntity.setDescription(info.getDescription());
        productEntity.setDetailList(info.getDetailList());
        productEntity.setTopList(info.getTopList());
        productEntity.setOriginalPrice(info.getOriginalPrice());
        if (info.getSalePrice() == null || BigDecimal.ZERO.compareTo(info.getSalePrice()) >= 0) {
            productEntity.setSalePrice(info.getOriginalPrice());
        } else {
            productEntity.setSalePrice(info.getSalePrice());
        }
        productEntity.setCreateTime(new Date());
        productEntity.setActiveTime(new Date());
        int insertResult = -1;
        try {
            insertResult = manageService.getBaseMapper().insert(productEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (insertResult > 0) {
            result.data = true;
        } else {
            result.setErrInfo(ErrorCode.SAVE_FAIL);
        }
        return result;
    }

    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public ResponseBean<PageBean<ProductEntity>> queryProducts(HttpServletRequest request) {
        ResponseBean<PageBean<ProductEntity>> result = new ResponseBean<>();
        Page<ProductEntity> pageBean = new Page<>();
        intPageInfo(request, pageBean);
        if (pageBean.getCurrent() > 0) {
            QueryWrapper<ProductEntity> wrapper = new QueryWrapper<>();
            wrapper.orderByAsc("id");
            IPage<ProductEntity> selectResult = manageService.getBaseMapper().selectPage(pageBean, wrapper);
            PageBean<ProductEntity> pageDetail = new PageBean<>();
            pageDetail.pageNum = selectResult.getCurrent();
            pageDetail.pageSize = selectResult.getSize();
            pageDetail.totalPageNum = selectResult.getPages();
            pageDetail.dataList = selectResult.getRecords();
            result.data = pageDetail;
        } else {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
    public ResponseBean<ProductEntity> queryProductDetail(@RequestParam long id) {
        ResponseBean<ProductEntity> result = new ResponseBean<>();
        if (id > 0) {
            QueryWrapper<ProductEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("id", id);
            try {
                ProductEntity selectResult = manageService.getBaseMapper().selectOne(wrapper);
                if (selectResult != null && !StringUtils.isEmpty(selectResult.getName())) {
                    result.code = 200;
                    result.data = selectResult;
                } else {
                    result.setErrInfo(ErrorCode.RESULT_EMPTY);
                }
            } catch (Exception e) {
                result.setErrInfo(ErrorCode.QUERY_FAIL);
                e.printStackTrace();
            }
        } else {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
        }
        return result;
    }

    private Page<?> intPageInfo(HttpServletRequest request, Page<?> pageBean) {
        String pageNum = request.getParameter("pageNum");

        int realNum = -1;
        if (pageNum != null) {
            try {
                realNum = Integer.parseInt(pageNum);
                if (realNum < 1) {
                    realNum = 1;
                }
            } catch (Exception e) {
                //
            }
        }
        pageBean.setCurrent(realNum);
        int realSize = 20;
        String pageSie = request.getParameter("pageSize");
        if (pageSie != null) {
            try {
                realSize = Integer.parseInt(pageSie);
                if (realSize < 1) {
                    realSize = 1;
                }
            } catch (Exception e) {
                //
            }
        }
        pageBean.setSize(realSize);
        return pageBean;
    }


}
