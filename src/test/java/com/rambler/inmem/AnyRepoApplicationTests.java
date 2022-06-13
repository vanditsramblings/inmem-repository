package com.rambler.inmem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

class AnyRepoApplicationTests {

	@Test
	void put() {
		TestRepo repo=new TestRepo("test",60000,10);
		repo.put("testKey","testValue");
		Assert.isTrue("testValue".equals(repo.get("testKey")),"Assertion failed for put message");
	}

	@Test
	void putWithTtl(){
		TestRepo repo=new TestRepo("test",5,10);
		repo.put("testKey","testValue");
		Assert.isNull(repo.get("testKey"),"Assertion failed for put with ttl message");
	}


}
