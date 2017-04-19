package co.jufeng.service.user;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.jufeng.accessor.EntityFactory;
import co.jufeng.api.user.IUserService;
import co.jufeng.core.lang.ServiceException;
import co.jufeng.dao.user.IUserDAO;
import co.jufeng.hibernate.criterion.impl.Eq;
import co.jufeng.hibernate.criterion.impl.UniqueResult;
import co.jufeng.json.JSONObject;
import co.jufeng.model.web.User;
import co.jufeng.model.web.UserDetails;
import co.jufeng.service.BaseService;
import co.jufeng.string.StringUtil;
import co.jufeng.util.MD5EncryptUtil;

@Service
public class UserService extends BaseService implements IUserService{
	
	private static final long serialVersionUID = 5588235268328152260L;
	
	@Resource
	IUserDAO userDAO;
	
	@Transactional("txAccessorWrite")
	@Override
	public Serializable add(String jsonString) {
		try {
			User user = this.toJavaObject(jsonString, User.class);
			
			String userName = user.getUserName();
			String passwrod = user.getPassword();
			JSONObject jsonObject = this.parseObject(jsonString);
			String mobile = jsonObject.getString("mobile");
			if(StringUtil.isEmpty(mobile)){
				throw new ServiceException(MISSING_PARAMETER.concat("mobile"));
			}
			
			if(StringUtil.isEmpty(userName)){
				throw new ServiceException(MISSING_PARAMETER.concat("userName"));
			}
			
			if(StringUtil.isEmpty(passwrod)){
				throw new ServiceException(MISSING_PARAMETER.concat("passwrod"));
			}
			
			boolean isEmptyUserName = userDAO.getAccessorWrite().isEmpty(User.class, "userName", userName);
			boolean isEmptyMobile = userDAO.getAccessorWrite().isEmpty(UserDetails.class, "mobile", mobile);
			if(!isEmptyUserName){
				throw new ServiceException("User name already exists");
			} 
			if(!isEmptyMobile){
				throw new ServiceException("Mobile phone already exists");
				
			} 
			String md5 = MD5EncryptUtil.getMd5KL(user.getPassword());
			user.setPassword(md5);
			Long userId = (Long)userDAO.getAccessorWrite().save(user);
			
			UserDetails userDetails = new UserDetails();
			userDetails.setUserId(userId);
			userDetails.setMobile(mobile);
			userDAO.getAccessorWrite().save(userDetails);
			return userId;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Transactional(value = "txAccessorWrite")
	@Override
	public boolean update(String jsonString) {
		try {
			User user = JSONObject.toJavaObject(jsonString, User.class);
			Long id = user.getId();
			if(id == null || id == 0){
				throw new ServiceException("用户主键不能为空");
			}
			String md5 = MD5EncryptUtil.getMd5KL(user.getPassword());
			user.setPassword(md5);
			return userDAO.getAccessorWrite().update(user);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(value = "txAccessorRead", readOnly = true)
	@Override
	public User getById(String jsonString) {
		try {
			if(!apiAuthorized(jsonString)){
				throw new ServiceException("Token invalid");
			}
			
			JSONObject jsonObject = this.parseObject(jsonString);
			Long id = jsonObject.getLong("id");
			System.out.println(id);
			if(id <= 0){
				throw new ServiceException("id必填参数");
			}
			String str = this.getRedisTemplate().get("key");
			System.out.println("redis..." + str);
			return userDAO.getAccessorRead().getById(id, User.class);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(value = "txAccessorRead", readOnly = true)
	@Override
	public User getByName(String jsonString) {
		try {
			JSONObject jsonObject = this.parseObject(jsonString);
			String userName = jsonObject.getString("userName");
			User user = (User)userDAO.getAccessorRead().get(EntityFactory.add(User.class), 
					Eq.add("userName", userName), 
					UniqueResult.add(true));
			return user;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(value = "txAccessorRead", readOnly = true)
	@Override
	public boolean isEmptyUserName(String jsonString) {
		try {
			JSONObject jsonObject = this.parseObject(jsonString);
			return userDAO.getAccessorRead().isEmpty(User.class, "userName", jsonObject.getString("userName"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(value = "txAccessorRead", readOnly = true)
	@Override
	public boolean isEmptyMobile(String jsonString) {
		try {
			JSONObject jsonObject = this.parseObject(jsonString);
			String mobile = jsonObject.getString("mobile");
			if(mobile == null){
				throw new ServiceException("手机号码不能为空");
			}
			return userDAO.getAccessorRead().isEmpty(UserDetails.class, "mobile", mobile);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	

}
