package com.chq.example.entity;

import java.io.Serializable;

/**
 * 接收数据列表分页查询数据
 * Created by CHQ on 2017/8/2.
 */
public class Request implements Serializable {

    private int limit;

    private int offset;

    private String order;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
