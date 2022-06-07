package com.rambler.inmem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class AnyRepoApplicationTests {

	@Test
	void put() {
		TestRepo repo=new TestRepo("test",5,10);
		repo.put("testKey","testValue");
		Assert.isTrue(repo.get("testKey").equals("testValue"),"Asserting failed for put message");
	}

}
