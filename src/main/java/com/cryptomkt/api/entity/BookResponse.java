package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookResponse extends Response{

    @JsonProperty("data")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setTickers(List<Book> books) {
        this.books = books;
    }
}
