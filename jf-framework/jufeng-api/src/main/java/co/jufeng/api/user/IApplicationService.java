package co.jufeng.api.user;

import java.io.Serializable;

import co.jufeng.api.IService;
import co.jufeng.api.annotation.Api;
import co.jufeng.api.annotation.Param;

/**
 * App Apply 
 * @author jufeng
 *
 */
@Api("applicationService")
public interface IApplicationService extends IService{
	
	/**
	 * Add new Application
	 * @param jsonString={"userId":1, "appName":"The official website"}
	 * @return Primary key
	 */
	Serializable add(@Param("jsonString") String jsonString);
	
	/**
	 * Delete Application
	 * @param jsonString={"id":1}
	 * @return true success false fail 
	 */
	boolean delete(@Param("jsonString") String jsonString);
	
}
