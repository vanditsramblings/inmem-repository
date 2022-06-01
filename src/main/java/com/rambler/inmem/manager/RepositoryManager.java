package com.rambler.inmem.manager;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.rambler.inmem.utils.ObjectSizeUtils;
import org.springframework.beans.factory.annotation.Value;

import java.lang.instrument.Instrumentation;
import java.util.*;

/**
 * A static registry for all caches present.
 * Exposes various methods that will provide insight into caches like :
 * --Total caches
 * --Cache sizes
 * --Cache memory Usages
 */
public class RepositoryManager {

    @Value("${cache.health.evaluateObjectSize:false}")
    protected static boolean evaluateDefaultSize;

    /**
     * Registry to keep track for all keyValue repository
     * Will be leveraged to provide monitoring metrics.
     */
    public static HashSet<Repo> registry = new HashSet<>();

    public static void register(String name, Cache cache) {
        registry.add(new Repo(name, cache));
    }


    public static List<RepoHealth> health() {
        List<RepoHealth> health = new ArrayList<>();
        for (Repo e : registry) {
            health.add(new RepoHealth(e,evaluateDefaultSize));
        }
        return health;
    }

    public static class Repo {

        Cache cache;
        String name;
        Long createdDate;

        public Repo(String name, Cache cache) {
            this.cache = cache;
            this.name = name;
            this.createdDate = System.currentTimeMillis();
        }
    }

    public static class RepoHealth {

        private final long objectSize;
        Repo repo;
        private final  Long size;
        CacheStats stats;

        public RepoHealth(Repo e, boolean evaluateDefaultSize) {
            this.repo = e;
            this.size = e.cache.estimatedSize();
            this.stats = e.cache.stats();

            if(evaluateDefaultSize){
                this.objectSize=ObjectSizeUtils.getObjectSize(e.cache);
            }else {
                this.objectSize=-1l;
            }

        }

    }


}
