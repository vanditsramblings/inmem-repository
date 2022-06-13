package com.rambler.inmem.cache;

import com.rambler.inmem.entity.SampleEntity;
import com.rambler.inmem.repository.impl.GenericKeyValueRepository;

/**
 * A sample entity to illustrate repository usage
 * @author vandit
 */
public class SampleCacheRepository extends GenericKeyValueRepository<String, SampleEntity> {

    public SampleCacheRepository(String name, int ttl, int size) {
        super(name, ttl, size);
    }
}
