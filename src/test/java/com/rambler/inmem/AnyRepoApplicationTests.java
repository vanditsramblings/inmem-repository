package com.rambler.inmem;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class AnyRepoApplicationTests {

    @Test
    void put() {
        TestRepo repo = new TestRepo("test1", 60000, 1000);
        repo.put("testKey", "testValue");
        Assert.isTrue("testValue".equals(repo.get("testKey")), "Assertion failed for put message");
    }

    @Test
    void putWithTtl() throws InterruptedException {
        TestRepo repo = new TestRepo("test2", 1, 1000);
        repo.put("testKey", "testValue");
        Thread.sleep(2000);
        Assert.isNull(repo.get("testKey"), "Assertion failed for put with ttl message");
    }


    @Test
    void overwriteValue() {
        TestRepo repo = new TestRepo("test3", 1000);
        repo.put("testKey1", "testValue1");
        repo.put("testKey1", "testValue2");
        Assert.isTrue("testValue2".equalsIgnoreCase(repo.get("testKey1")), "Assertion failed for overwrite value in cache");
    }

    @Test
    void getAll() {
        TestRepo repo = new TestRepo("test4", 1000);
        repo.put("testKey1", "testValue1");
        repo.put("testKey2", "testValue2");
        repo.put("testKey3", "testValue3");
        Assert.isTrue(repo.getAll().size() == 3, "Assertion failed for getAll cache");
    }


    @Test
    void delete() {
        TestRepo repo = new TestRepo("test5", 1000);
        repo.put("testKey", "testValue");
        repo.delete("testKey");
        Assert.isNull(repo.get("testKey"), "Assertion failed for delete value in cache");
    }

    @Test
    void putIfAbsent() {
        TestRepo repo = new TestRepo("test6", 1000);
        repo.put("testKey", "testValue");
        repo.putIfAbsent("testKey", "testValue2");
        Assert.isTrue("testValue".equals(repo.get("testKey")), "Assertion failed for putIfAbsent in cache");
    }


}
