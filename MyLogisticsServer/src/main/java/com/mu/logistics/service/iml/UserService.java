package com.mu.logistics.service.iml;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mu.common.exception.BaseException;
import com.mu.common.utils.MapUtils;
import com.mu.logistics.dao.iml.UserDao;
import com.mu.logistics.model.iwl.User;
import com.mu.logistics.service.IUserService;

@Service
public class UserService implements IUserService {

	@Resource
	private UserDao userDao;

	@Override
	public int[] a88UserMoveToIwl() throws BaseException {
		List<Map<String, Object>> userList = userDao.getA88AllUser();
		for (Map<String, Object> map : userList) {
			System.out.println(map.toString());

			User user = new User();
			user.setId((Long) map.get("wj_ID"));
			user.setCreatetime((Date) map.get("createtime"));
			user.setMoblieAccount(map.get("wj_Mobile").toString());
			user.setRealName((String)map.get("link"));
			user.setPassword((String) MapUtils.getMapValusT(map, "wj_UserPwd",
					""));
			user.setUserName(map.get("wj_User").toString());
			user.setUserAddress(MapUtils.getMapValusT(map, "wj_Address", "")
					.toString());
			user.setUserSex(MapUtils.getMapValusT(map, "wj_Sex", "").toString());
			user.setFromSystem("a88");
			int wj_type = (Integer) MapUtils.getMapValusT(map, "wj_Type", 1);
			user.setUserType_id(wj_type);

			// 进一步过滤手机号码,符合正则，是是一位的手机号码
			if (user.getMoblieAccount().trim().length() < 11) {
				continue; // 长度小于11位的不要
			}
			if (user.getMoblieAccount().trim().charAt(0) == '0') {
				user.setMoblieAccount(user.getMoblieAccount().trim()
						.substring(1));// 前面有0的去掉
			}
			if (user.getMoblieAccount().trim().contains("-")
					|| user.getMoblieAccount().trim().contains("——")) {
				continue;
			}
			if (!user.getMoblieAccount().trim().matches("^[0-9]+$")) { // 要求全数字
				continue;
			}
			if (user.getMoblieAccount().trim().length() != 11) {
				continue;
			}

			// 插入
			try {
				long id = userDao.add(user);
				System.out.println(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	@Override
	public void a88UserMoveToIwlEveryday() throws BaseException {
		// 获取iwl.user表最大id
		Long userMaxId = userDao.springJdbcDao.getMaxId(userDao.accesser
				.getJdbcTemplateFactory().getJdbcTemplateC3p0Iwl(), "user");
		System.out.println("原来最大id" + String.valueOf(userMaxId));
		
		//在a88查出大于该最大id的数据集
		List<Map<String, Object>> list = userDao.getA88UsersBigId(userMaxId);
		
		if (list.isEmpty()) {
			return;
		}
		
		//把这些数据集插入到iwl.user表，完成数据同步
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());

			User user = new User();
			user.setId((Long) map.get("wj_ID"));
			user.setCreatetime((Date) map.get("createtime"));
			user.setMoblieAccount(map.get("wj_Mobile").toString());
			user.setRealName((String)map.get("link"));
			user.setPassword((String) MapUtils.getMapValusT(map, "wj_UserPwd",
					""));
			user.setUserName(map.get("wj_User").toString());
			user.setUserAddress(MapUtils.getMapValusT(map, "wj_Address", "")
					.toString());
			user.setUserSex(MapUtils.getMapValusT(map, "wj_Sex", "").toString());
			user.setFromSystem("a88");
			int wj_type = (Integer) MapUtils.getMapValusT(map, "wj_Type", 1);
			user.setUserType_id(wj_type);

			// 进一步过滤手机号码,符合正则，是是一位的手机号码
			if (user.getMoblieAccount().trim().length() < 11) {
				continue; // 长度小于11位的不要
			}
			if (user.getMoblieAccount().trim().charAt(0) == '0') {
				user.setMoblieAccount(user.getMoblieAccount().trim()
						.substring(1));// 前面有0的去掉
			}
			if (user.getMoblieAccount().trim().contains("-")
					|| user.getMoblieAccount().trim().contains("——")) {
				continue;
			}
			if (!user.getMoblieAccount().trim().matches("^[0-9]+$")) { // 要求全数字
				continue;
			}
			if (user.getMoblieAccount().trim().length() != 11) {
				continue;
			}

			// 插入
			try {
				long id = userDao.add(user);
				System.out.println(id);
			} catch (Exception e) {
				
			}
		}
	}

}
