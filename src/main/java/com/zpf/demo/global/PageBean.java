package com.zpf.demo.global;

import java.util.List;

public class PageBean<T> {
    public long pageNum;
    public long pageSize;
    public long totalPageNum;
    public List<T> dataList;

}
