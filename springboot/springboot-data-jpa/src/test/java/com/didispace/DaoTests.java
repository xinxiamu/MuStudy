package com.didispace;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didispace.domain.p.User;
import com.didispace.domain.p.UserDetails;
import com.didispace.domain.p.UserDetailsRepository;
import com.didispace.domain.p.UserRepository;
import com.didispace.domain.s.MessageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class DaoTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Before
	public void setUp() {
	}

	@Test
	public void save() {
		// userRepository.save(new User("mm", 19));

		UserDetails userDetails = new UserDetails();
		userDetails.setEmail("555555555@qq.com");
		userDetails.setMobile("99999999");
		userDetails.setUser(userRepository.findOne(1L));
		userDetailsRepository.save(userDetails);
	}

	@Test
	public void test() throws Exception {
		User user = userRepository.findOne(2L);
		List<UserDetails> a = userDetailsRepository.findByUserId(1L);
		List<UserDetails> b = userDetailsRepository.findByUser(user);
		User u = a.get(0).getUser();
		User ub = b.get(0).getUser();
		System.out.println("----a:" + a.get(0).getEmail() + "---user:");
		System.out.println("----b:" + b);
		System.out.println(user.getName());
	}

	@Test
	public void find() throws Exception {
		// User a = userRepository.findByName("aaa");
		// System.out.println("---user:" + a.getName());

//		List<User> a = userRepository.findByAge(22);
//		System.out.println(a.size());
		
		List<String> list = null;
		if (list.isEmpty()) {
			System.out.println(12);
		} else {
			System.out.println(13);
		}
	}

}
