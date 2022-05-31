package com.rambler.inmem.utils;

import com.github.benmanes.caffeine.cache.Cache;

import java.lang.instrument.Instrumentation;

public class ObjectSizeUtils {
    private static Instrumentation instrumentation;

    public static Long getObjectSize(Cache cache) {
        return -1l;
    }
}
