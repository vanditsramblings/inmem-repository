package com.rambler.inmem.manager;

import com.github.benmanes.caffeine.cache.Cache;

import java.util.HashMap;

/**
 * A static registry for all caches present.
 * Exposes various methods that will provide insight into caches like :
 * --Total caches
 * --Cache sizes
 * --Cache memory Usages
 */
public class RepositoryManager {

    /**
     *
     */
    public static HashMap<String, Cache> registry=new HashMap<>();

    public static void register(String name,Cache cache){
        registry.put(name,cache);
    }

}
