package co.jufeng.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import co.jufeng.BaseAction;
import co.jufeng.core.factory.SpringBeanFactory;
import co.jufeng.core.lang.ServiceException;
import co.jufeng.string.StringUtil;
import co.jufeng.util.MD5EncryptUtil;
import co.jufeng.util.encrypt.base64.Base64Util;
import co.jufeng.web.ApiRspResultVO;
import co.jufeng.web.servlet.bind.annotation.RestController;

@RestController
public class ApiAction extends BaseAction {
	
	//http://www.jufeng.co:8081/jufeng-web/api.do?action=helloWorldService.sayHello&jsonString=eyJ1c2VyTmFtZSI6ImFkbWluIn0=
	//http://www.jufeng.co:8081/jufeng-web/api.do?action=userService.getById&jsonString=eyJpZCI6MX0=
	//http://www.jufeng.co:8081/jufeng-web/api.do?action=userService.add&jsonString=eyJ1c2VyTmFtZSI6ImFkbWluIiwgInBhc3N3b3JkIjoiYTEyMzQ1NjciLCAibW9iaWxlIjoiMTM4MjIxMTkyMDMifQ==
	//{"userName":"jufeng","password":"123456"}eyJ1c2VyTmFtZSI6Imp1ZmVuZyIsInBhc3N3b3JkIjoiMTIzNDU2In0
	public ApiRspResultVO index(ApiRspResultVO apiRspResultVO, String jsonString) {
		try {
			String methodIdentifier = this.getParameter().getString("action");
			if(StringUtil.isEmpty(methodIdentifier)){
				apiRspResultVO.setDescription(this.getI18nString("apiActionEmpty"));
				apiRspResultVO.setIsSuccess(false);
				return apiRspResultVO;
			}

			if(methodIdentifier.indexOf(".") < 0){
				apiRspResultVO.setDescription(this.getI18nString("interfaceNameNot"));
				apiRspResultVO.setIsSuccess(false);
				return apiRspResultVO;
			}
			
			
			String[] methodIdentifiers = methodIdentifier.split("\\.");
			String apiName = methodIdentifiers[0];
			boolean isEmptyApi = SpringBeanFactory.isEmpty(apiName);
			if(!isEmptyApi){
				apiRspResultVO.setDescription(this.getI18nString("apiNot"));
				apiRspResultVO.setIsSuccess(false);
				return apiRspResultVO;
			}
			
			Object obj = SpringBeanFactory.getBean(apiName);
			Class<?> clazz = obj.getClass(); 
			String methodName = methodIdentifiers[1];
			
			Method method = null;
			Object result = null;
			if(StringUtil.isEmpty(jsonString)){
				method = clazz.getDeclaredMethod(methodName);
				result = method.invoke(obj); 
			}else{
				jsonString = Base64Util.getDecoderBase64(jsonString);
				if(StringUtil.isEmpty(jsonString)){
					apiRspResultVO.setDescription(this.getI18nString("base64ParamatersError"));
					apiRspResultVO.setIsSuccess(false);
					return apiRspResultVO;
				}
				System.out.println("jsonString >>> " + jsonString);
				method = clazz.getDeclaredMethod(methodName, String.class);
				result = method.invoke(obj, jsonString); 
			}
			
			System.out.println("result     >>> " + result);
			if(result != null){
				apiRspResultVO.setData(result);
				apiRspResultVO.setDescription(apiName.concat(".").concat(methodName));
			}else{
				apiRspResultVO.setDescription(this.getI18nString("dataNot"));
				apiRspResultVO.setIsSuccess(false);
			}
			
			
		} catch (NoSuchBeanDefinitionException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
			Throwable cause = e.getCause();
            if(cause == null){
            	apiRspResultVO.setDescription(this.getI18nString("interfaceNameNot"));
    			apiRspResultVO.setIsSuccess(false);
            }else if(cause instanceof ServiceException){
                System.out.println("ok,we catch ServiceException:" + (ServiceException) cause);
                apiRspResultVO.setDescription(cause.getMessage().replace("co.jufeng.core.lang.ServiceException: ", ""));
                apiRspResultVO.setIsSuccess(false);
            }else{
            	apiRspResultVO.setDescription(this.getI18nString("internalError"));
                apiRspResultVO.setIsSuccess(false);
            }
            
		}
		return apiRspResultVO;
	}
	
	public static void main(String[] args) {
		String appIdDb = "1479706022778";//AppID 
		String appSecretDb = "4d31731564c0464f533d1dbb2c36dc59";//AppSecret (应用程序的秘密)
		String jsonString = "{\"id\":1}";
		jsonString = jsonString.concat(appIdDb);
		jsonString = jsonString.concat(appSecretDb);
		String token = MD5EncryptUtil.getMd5(jsonString);
				
		System.out.println(jsonString);
		
		System.out.println("token=" + token);
		System.out.println(appIdDb.length());
		System.out.println(System.currentTimeMillis());
		Long times = System.currentTimeMillis();
		System.out.println(times);
		System.out.println("" + times.toString().length());
		//c2f5b7b0861656dd4d30f72d1b6483ad
		System.out.println(appSecretDb.length() + "\tdddd");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
