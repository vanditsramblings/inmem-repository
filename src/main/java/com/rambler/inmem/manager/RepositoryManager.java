package com.rambler.inmem.manager;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * A static registry for all caches present.
 * Exposes various methods that will provide insight into caches like :
 * --Total caches
 * --Cache sizes
 * --Cache memory Usages
 */
public class RepositoryManager {

    /**
     * Registry to keep track for all keyValue repository
     * Will be leveraged to provide monitoring metrics.
     */
    public static HashSet<Repo> registry=new HashSet<>();

    public static void register(String name,Cache cache){
        registry.add(new Repo(name,cache));
    }


    public static List<RepoHealth> health(){
        List<RepoHealth> health=new ArrayList<>();
        for(Repo e:registry){
            health.add(new RepoHealth(e));
        }
        return health;
    }

    public static class Repo{

        Cache cache;
        String name;
        Long createdDate;

        public Repo(String name,Cache cache){
            this.cache=cache;
            this.name=name;
            this.createdDate=System.currentTimeMillis();
        }
    }

    public static class RepoHealth{

        Repo repo;
        Long size;
        CacheStats stats;

        public RepoHealth(Repo e) {
            this.repo=e;
            this.size=e.cache.estimatedSize();
            this.stats=e.cache.stats();
        }
    }
}
