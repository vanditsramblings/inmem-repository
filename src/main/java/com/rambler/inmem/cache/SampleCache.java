package com.rambler.inmem.cache;

import com.rambler.inmem.entity.SampleEntity;
import com.rambler.inmem.repository.impl.GenericKeyValueRepository;

/**
 * A sanmple entity to illustrate repository usage
 * @author vandit
 */
public class SampleCache extends GenericKeyValueRepository<String, SampleEntity> {

    public SampleCache(String name, int ttl, int size) {
        super(name, ttl, size);
    }
}
