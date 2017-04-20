package com.didispace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.didispace.rabbit.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=HelloApplication.class)
public class HelloApplicationTests {

	@Autowired
	private Sender sender;

	@Test
	public void hello() throws Exception {
//		sender.send();
//		sender.sendMumu();
		sender.sendMainQ();
	}

}
