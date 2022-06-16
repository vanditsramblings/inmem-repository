package com.rambler.inmem;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class AnyRepoApplicationTests {

	@Test
	void put() {
		TestRepo repo=new TestRepo("test1",60000,1000);
		repo.put("testKey","testValue");
		Assert.isTrue("testValue".equals(repo.get("testKey")),"Assertion failed for put message");
	}

	@Test
	void putWithTtl() throws InterruptedException {
		TestRepo repo=new TestRepo("test2",5,1000);
		repo.put("testKey","testValue");
		Thread.sleep(1000);
		Assert.isNull(repo.get("testKey"),"Assertion failed for put with ttl message");
	}


	@Test
	void overwriteValue()  {
		TestRepo repo=new TestRepo("test3",1000);
		repo.put("testKey1","testValue1");
		repo.put("testKey1","testValue2");
		Assert.isTrue("testValue2".equalsIgnoreCase(repo.get("testKey1")),"Assertion failed for overwrite value in cache");
	}


}
