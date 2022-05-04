package com.rambler.inmem.repository.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AbstractGenericKeyValueRepository<Key,Val> implements GenericKeyValueRepository<Key,Val>{

    @Value("${repo.cache.max.allowed.size:10000000}")
    protected int maxSize;

    @Value("${repo.cache.max.default.size:10000}")
    protected int defaultSize;

    @Value("${repo.cache.max.allowed.ttl:10000000}")
    protected int maxTtl;

    @Value("${repo.cache.max.default.ttl:10000000}")
    protected int defaultTtl;

    private Cache<Key,Val> cache;


    /**
     * Registry to hold all caches
     */
    private final Map<String, Cache<Key,Val>> cacheRegistry=new HashMap<>();


    public AbstractGenericKeyValueRepository(String name){
        init(name,defaultTtl,defaultSize);
    }

    public AbstractGenericKeyValueRepository(String name,int ttl){
        init(name,ttl,defaultSize);
    }

    public AbstractGenericKeyValueRepository(String name,int ttl,int size){
        init(name,ttl,size);
    }

    /**
     * Initializes caffeine cache instance with provided attributes
     * @param name : Name of the cache
     * @param ttl : Time to live for a single entry in the cache
     * @param size : Maximum allowed entries in the cache
     */
    private void init(String name,int ttl,int size){
        cache=Caffeine.newBuilder()
                .maximumSize(size>maxSize?maxSize:size)
                .expireAfterAccess(ttl>maxTtl?maxTtl:ttl, TimeUnit.MILLISECONDS)
                .build();
    }


    /**
     * Fetches value for the provided key
     *
     * @param key : The lookup key
     * @return the value associated to the provided key. `null` if not found.
     */
    @Override
    public Val get(Key key) {
        return (Val) cache.getIfPresent(key);
    }

    /**
     * Fetches value for the provided key
     *
     * @param keys : A set of keys to be looked up
     * @return a map key-value pairs.Associated value for the key will be `null` if not found.
     */
    @Override
    public Map<Key, Val> get(Set<Key> keys) {
        return (Map<Key, Val>) cache.getAllPresent(keys);
    }

    /**
     * Fetches all the values for the underlying entity
     *
     * @param size : A set of keys to be looked up
     * @return a map key-value pairs.Associated value for the key will be `null` if not found.
     */
    @Override
    public Map<Key, Val> getAll(int size) {
        throw new UnsupportedOperationException("This is not supported now");
    }

    /**
     * Fetches all the values for the underlying entity
     *
     * @return a map key-value pairs.
     */
    @Override
    public Map<Key, Val> getAll() {
        return (Map<Key, Val>) cache.asMap();
    }

    /**
     * Returns all the keys for the entity
     *
     * @return A set of all the keys in the repo
     */
    @Override
    public Set<Key> keys() {
        return cache.asMap().keySet();
    }

    /**
     * Returns all the keys for the entity , fetched in batches for better performance
     *
     * @param batchSize : size of a single batch
     * @return A set of all the keys in the repo
     */
    @Override
    public Set<Key> keys(int batchSize) {
        throw new UnsupportedOperationException("This is not supported");
    }

    /**
     * Adds a new entry in the store ,  overwrites existing if already exist
     *
     * @param key   : Key
     * @param value : Value
     * @return : true denotes a successful operation , false denotes a failure
     */
    @Override
    public boolean put(Key key, Val value) {
        cache.put(key,value);
        return true;
    }

    /**
     * Adds a new entry in the store only if it does not exist already
     *
     * @param key   : Key
     * @param value : Value
     * @return : true denotes a successful put , false if key already exist
     */
    @Override
    public boolean putIfAbsent(Key key, Val value) {
        if(cache.getIfPresent(key)==null){
            return put(key,value);
        }else
            return false;
    }

    /**
     * Adds a new entry in the store ,overwrites existing if already exist
     *
     * @param entries : A map of key values to be inserted into store
     * @return : true denotes a successful operation , false denotes a failure
     */
    @Override
    public boolean put(Map<Key, Val> entries) {
        cache.putAll(entries);
        return true;
    }

    /**
     * Removes the key from the store
     *
     * @param key : key to be removed
     * @return true if successful , false if key does not exist
     */
    @Override
    public boolean delete(Key key) {
        cache.invalidate(key);
        return true;
    }

    /**
     * Removes the key from the store
     *
     * @param keys : keys to be removed
     * @return List of booleans , each entry denoting : true if successful , false if key does not exist
     */
    @Override
    public boolean delete(Set<Key> keys) {
        cache.invalidateAll(keys);
        return true;
    }
}
