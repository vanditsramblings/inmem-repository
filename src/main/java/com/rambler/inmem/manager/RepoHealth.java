package com.rambler.inmem.manager;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.rambler.inmem.utils.ObjectSizeUtils;

public class RepoHealth {

    private final long objectSize;
    Repo repo;
    private final  Long size;
    CacheStats stats;

    public RepoHealth(Repo e, boolean evaluateDefaultSize) {
        this.repo = e;
        this.size = e.cache.estimatedSize();
        this.stats = e.cache.stats();

        if(evaluateDefaultSize){
            this.objectSize= ObjectSizeUtils.getObjectSize(e.cache);
        }else {
            this.objectSize=-1l;
        }

    }

}
