package co.jufeng.service.user;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:applicationContext.xml")  
public class StartServiceTest extends AbstractJUnit4SpringContextTests {
	
	@Test
	public void start() throws Exception{
		System.out.println("业务支撑系统启动完成...");
//		System.in.read();
	}
}
