package com.mfo.creditservice.payloads;

import java.util.List;

public class PaginatedResponsePayload<T> {

    private int page;
    private int perPage;
    private long count;
    private List<T> list;
    private String error;

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public long getCount() {
        return count;
    }

    public List<T> getList() {
        return list;
    }

    public String getError() {
        return error;
    }

    public PaginatedResponsePayload<T> setErrorPayload(String error) {
        this.error = error;
        this.page = 0;
        this.perPage = 0;
        this.count = 0;
        this.list = null;
        return this;
    }

    public PaginatedResponsePayload<T> setDataPayload(List<T> list, int page, int perPage, long count) {
        this.error = null;
        this.page = page;
        this.perPage = perPage;
        this.count = count;
        this.list = list;
        return this;
    }

}
