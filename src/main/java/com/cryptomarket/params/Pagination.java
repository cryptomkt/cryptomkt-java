package com.cryptomarket.params;

public class Pagination {
    public Sort sort;
    public By by;
    public String from;
    public String till;
    public Integer limit;
    public Integer offset;


    public Pagination(Builder builder) {
        if (builder.sort != null) sort = builder.sort;
        if (builder.by != null) by = builder.by;
        if (builder.from != null) from = builder.from;
        if (builder.till != null) till = builder.till;
        if (builder.limit != null) limit = builder.limit;
        if (builder.offset != null) offset = builder.offset;
    }

    public static class Builder {

        private Sort sort;
        private By by;
        private String from;
        private String till;
        private Integer limit;
        private Integer offset;

        /**
         * 
         * @param sort A Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). sorting direction.
         */
        public Builder sort(Sort sort) {
            this.sort = sort;
            return this;
        }

        /**
         * 
         * @param by A By enum type. By.Id or By.Timestamp, field to use for sorting
         */
        public Builder by(By by) {
            this.by = by;
            return this;
        }
        
        /**
         * 
         * @param from Initial value of the queried interval. As id or as timestamp
         */
        public Builder from(String from) {
            this.from = from;
            return this;
        }

        /**
         * 
         * @param till Last value of the queried interval. As id or as timestamp
         */
        public Builder till(String till) {
            this.till = till;
            return this;
        }

        /**
         * 
         * @param limit elements per query. Defaul is 100. Max is 1000
         */
        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /**
         * 
         * @param offset Default is 0. Max is 100000
         */
        public Builder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public Pagination build() {
            return new Pagination(this);
        }
    }

}
