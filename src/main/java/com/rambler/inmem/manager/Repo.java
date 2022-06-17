package com.rambler.inmem.manager;

import com.github.benmanes.caffeine.cache.Cache;

public class Repo {

    Cache cache;
    String name;
    Long createdDate;

    public Repo(String name, Cache cache) {
        this.cache = cache;
        this.name = name;
        this.createdDate = System.currentTimeMillis();
    }
}