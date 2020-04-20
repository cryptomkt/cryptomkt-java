package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Pagination implements Serializable {

    @JsonProperty("previous")
    private int previous;

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("page")
    private int page;

    @JsonProperty("next")
    private int next;

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "previous=" + previous +
                ", page=" + page +
                ", next=" + next +
                ", limit=" + limit +
                '}';
    }
}
