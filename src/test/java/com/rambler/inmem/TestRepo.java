package com.rambler.inmem;

import com.rambler.inmem.exception.RepositoryException;
import com.rambler.inmem.repository.impl.GenericKeyValueRepository;

public class TestRepo extends GenericKeyValueRepository<String, String> {

    public TestRepo(String name, int ttl, int size) throws RepositoryException {
        super(name, ttl, size);
    }

    public TestRepo(String name, int size) throws RepositoryException {
        super(name, size);
    }
}
