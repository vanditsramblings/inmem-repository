package com.rambler.inmem.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A generic contract for KeyValue type repository implementation
 *
 * Provides various behavior to operate upon a KeyValue store
 *
 * @param <Key> Key Type
 * @param <Value> Value Type
 *
 * @author vandit
 */
public interface GenericKeyValueRepository<Key,Value> extends GenericRepository {

    /**
     * Fetches value for the provided key
     *
     * @param key : The lookup key
     * @return the value associated to the provided key. `null` if not found.
     */
    Value get(Key key);

    /**
     * Fetches value for the provided key
     *
     * @param keys : A set of keys to be looked up
     * @return a map key-value pairs.Associated value for the key will be `null` if not found.
     */
    Map<Key,Value> get(Set<Key> keys);

    /**
     * Fetches all the values for the underlying entity
     *
     * @param size : A set of keys to be looked up
     * @return a map key-value pairs.Associated value for the key will be `null` if not found.
     */
    Map<Key,Value> getAll(int size);

    /**
     * Fetches all the values for the underlying entity
     *
     * @return a map key-value pairs.
     */
    Map<Key,Value> getAll();

    /**
     * Returns all the keys for the entity
     *
     * @return A set of all the keys in the repo
     */
    Set<Key> keys();

    /**
     * Returns all the keys for the entity , fetched in batches for better performance
     *
     * @param batchSize : size of a single batch
     * @return A set of all the keys in the repo
     */
    Set<Key> keys(int batchSize);

    /**
     * Adds a new entry in the store ,  overwrites existing if already exist
     * @param key : Key
     * @param value : Value
     * @return : true denotes a successful operation , false denotes a failure
     */
    boolean put(Key key,Value value);


    /**
     * Adds a new entry in the store only if it does not exist already
     * @param key : Key
     * @param value : Value
     * @return : true denotes a successful put , false if key already exist
     */
    boolean putIfAbsent(Key key,Value value);


    /**
     * Adds a new entry in the store ,overwrites existing if already exist
     * @param entries : A map of key values to be inserted into store
     *
     * @return : true denotes a successful operation , false denotes a failure
     */
    boolean put(Map<Key,Value> entries);


    /**
     * Removes the key from the store
     *
     * @param key : key to be removed
     * @return true if successful , false if key does not exist
     */
    boolean delete(Key key);

    /**
     * Removes the key from the store
     *
     * @param keys : keys to be removed
     * @return List of booleans , each entry denoting : true if successful , false if key does not exist
     */
    List<Boolean> delete(Set<Key> keys);

}