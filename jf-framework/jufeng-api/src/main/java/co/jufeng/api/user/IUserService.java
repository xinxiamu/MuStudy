package co.jufeng.api.user;

import java.io.Serializable;

import co.jufeng.api.IService;
import co.jufeng.api.annotation.Api;
import co.jufeng.api.annotation.Param;
import co.jufeng.model.web.User;

/**
 * User API "signMethod":"5690dddfa28ae085d23518a035707282"
 * @author jufeng
 *
 */
@Api("userService")
public interface IUserService extends IService{
	
	/**
	 * Add new User
	 * @param jsonString＝{"userName":"admin", "password":"a1234567"}
	 * @return Primary key
	 */
	Serializable add(@Param("jsonString") String jsonString);
	
	/**
	 * update User
	 * @param jsonString＝{"userName":"admin", "password":"123456"}
	 * @return Primary key
	 */
	boolean update(@Param("jsonString") String jsonString);
	
	
	/**
	 * query Primary key User
	 * @param jsonString＝{"id":1,"redisTimeout":10}
	 * @return 
	 */
	User getById(@Param("jsonString") String jsonString);
	
	/**
	 * query userName User
	 * @param jsonString＝{"userName":"admin","redisTimeout":10}
	 * @return 
	 */
	User getByName(@Param("jsonString") String jsonString);
	
	/**
	 * isEmptyUserName boolean
	 * @param jsonString {"userName":"admin"}
	 * @return 
	 */
	boolean isEmptyUserName(@Param("jsonString") String jsonString);
	
	/**
	 * isEmptyMobile boolean
	 * @param jsonString {"mobile":"13822119203"}
	 * @return 
	 */
	boolean isEmptyMobile(@Param("jsonString") String jsonString);
	
}
