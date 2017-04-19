package co.jufeng.service;


import java.io.Serializable;

import javax.annotation.Resource;

import co.jufeng.core.lang.ServiceException;
import co.jufeng.json.JSONObject;
import co.jufeng.redis.RedisTemplate;
import co.jufeng.string.StringUtil;
import co.jufeng.util.MD5EncryptUtil;

public class BaseService implements Serializable{

	private static final long serialVersionUID = -868343363742415194L;
	
	public static String MISSING_PARAMETER = "Missing parameter ";
	
	public static String EXCEPTION_MESSAGE = "Please contact the administrator QQ:375273058";
	
	@Resource(name = "redisTemplate")
	private RedisTemplate redisTemplate;

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/*////access token(访问令牌) 
	protected boolean apiAuthorized(String jsonString){
		try {																		
			String appIdDb = "wx09eceabbbf327258";//AppID 
			String appSecretDb = "4d31731564c0464f533d1dbb2c36dc59";//AppSecret (应用程序的秘密)
			JSONObject jsonObject = this.parseObject(jsonString);
			Iterator<Object> it = jsonObject.keySet().iterator();
			String token1 = jsonObject.getString("token");
			System.out.println("客户端传来的token=" + token1);
			String str = null;
			while (it.hasNext()) {
				Object key = it.next();
				Object value = jsonObject.get(key);
				System.out.println("key=" + key + " value=" + value);
				str += key.toString().concat(value.toString());
			}
			str.concat(appIdDb);
			str.concat(appSecretDb);
			System.out.println(str);
//			String appId = jsonObject.getString("appId");
//			String appSecret = jsonObject.getString("appSecret");
//			System.out.println(appId);
//			System.out.println(appSecret);
//			String appId = this.getRedisTemplate().get("appId");
//			String appSecret = this.getRedisTemplate().get("appId");
			String token = MD5EncryptUtil.getMd5(str);
			System.out.println("token=" + token);
			if(token.equals(token1)){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw new ServiceException("Not authorized by");
		}
	}*/
	
	
////access token(访问令牌) 
	protected boolean apiAuthorized(String jsonString){
		try {																		
			String appIdDb = "1479706022778";//AppID 
			String appSecretDb = "4d31731564c0464f533d1dbb2c36dc59";//AppSecret (应用程序的秘密)
			JSONObject jsonObject = this.parseObject(jsonString);
			String tokenClient = jsonObject.getString("token");
			if(StringUtil.isEmpty(tokenClient)){
				return false;
			}
			jsonObject.remove("token");
			jsonString = jsonObject.toString();
			jsonString = jsonString.concat(appIdDb);
			jsonString = jsonString.concat(appSecretDb);
			String token = MD5EncryptUtil.getMd5(jsonString);
			if(token.equals(tokenClient)){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			throw new ServiceException("Token invalid");
		}
	}

	protected JSONObject parseObject(String jsonString){
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.parseObject(jsonString);
			return jsonObject;
		} catch (Exception e) {
			throw new ServiceException("JSON string to JSONObject error " + jsonString);
		}
	}
	
	protected <T> T toJavaObject(String jsonString, Class<T> beanClass){
		try {
			return JSONObject.toJavaObject(jsonString, beanClass);
		} catch (Exception e) {
			throw new ServiceException("JSON jsonString to Object error " + jsonString);
		}
	}
	
	protected void throwServiceException(Exception e){
		 if(e instanceof ServiceException){
         	throw new ServiceException(e);
         	
         }else{
         	throw new ServiceException(EXCEPTION_MESSAGE);
         }
	}

}
