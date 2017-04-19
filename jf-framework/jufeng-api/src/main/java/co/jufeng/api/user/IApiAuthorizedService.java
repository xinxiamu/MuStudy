package co.jufeng.api.user;

import java.io.Serializable;

import co.jufeng.api.IService;
import co.jufeng.api.annotation.Api;
import co.jufeng.api.annotation.Param;

/**
 * Api Authorized
 * @author jufeng
 *
 */
@Api("apiAuthorizedService")
public interface IApiAuthorizedService extends IService{
	
	/**
	 * Add new ApiAuthorized
	 * @param jsonString={"userId":1, "serviceName":"helloWorldService", "serviceMethod":"sayHello"}
	 * @return Primary key
	 */
	Serializable add(@Param("jsonString") String jsonString);
	
	/**
	 * Delete ApiAuthorized
	 * @param jsonString={"id":1}
	 * @return true success false fail 
	 */
	boolean delete(@Param("jsonString") String jsonString);
	
}
