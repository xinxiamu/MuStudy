//package co.jufeng.service.user;
//
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.hibernate.SessionFactory;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.transaction.annotation.Transactional;
//
//import co.jufeng.accessor.IAccessor;
//import co.jufeng.api.user.IUserService;
//import co.jufeng.hibernate.HibernateAccessor;
//import co.jufeng.jdbctemplate.JdbcTemplateAccessor;
//import co.jufeng.model.web.User;
//import co.jufeng.redis.RedisTemplate;
//import co.jufeng.service.BaseTest;
//
//public class CopyOfStartServiceTest extends BaseTest {
//	
//	private RedisTemplate redisTemplate;
//	
//	private IUserService userService;
//
//	private JdbcTemplateAccessor jdbcTemplateAccessor;
//	List<User> list = new ArrayList<User>();
//
//	private HibernateAccessor hibernateAccessor;
//	
//	@Before
//	public void init(){
//		redisTemplate = (RedisTemplate) this.getContext().getBean("redisTemplate");
//		userService =  (IUserService) this.getContext().getBean("userService");
//		System.out.println(userService);
//		jdbcTemplateAccessor = (JdbcTemplateAccessor)this.getContext().getBean("jdbcTemplateAccessor");
//		hibernateAccessor = (HibernateAccessor) this.getContext().getBean("hibernateAccessor");
//		
//		
//		for (int i = 0; i < 100; i++) {
//			User user = new User();
//			user.setUserName("jufeng");
//			user.setPassword("a123456");
//			list.add(user);
//		}
//	}
//	//netstat -noap | grep 8080 | grep tcp
//	@SuppressWarnings("unchecked")
//	@Test
////	@Transactional("transactionManagerHibernateWrite")
//	public void start() throws Exception{
//		System.out.println("业务支撑系统启动完成...");
//		System.out.println("测试Redis\t" + redisTemplate.get("key"));
//		String jsonString = "{\"userName\":\"jufeng\", \"password\":\"a1234567\"}";
//		System.out.println(userService.add(jsonString));
//		Date a = new Date();
//		list = (List<User>) jdbcTemplateAccessor.save(list);
//		Date b = new Date();
//		long interval = (b.getTime() - a.getTime()) / 1000;
//		System.out.println(list.size() + "个对象保存用时" + interval + "秒");
//		System.out.println("成功保存完" + list.size() + "条");
//		jsonString = "{\"id\":1}";
//		System.out.println(userService.getById(jsonString));
//		
//		System.out.println(hibernateAccessor);
////		System.in.read();
//	}
//}
