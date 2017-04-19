package co.jufeng.service.user;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.jufeng.api.user.IApiAuthorizedService;
import co.jufeng.core.lang.ServiceException;
import co.jufeng.dao.user.IApplicationDAO;
import co.jufeng.model.web.ApiAuthorized;
import co.jufeng.service.BaseService;
import co.jufeng.string.StringUtil;

@Service
public class ApiAuthorizedService extends BaseService implements IApiAuthorizedService {

	private static final long serialVersionUID = 3639524096108908036L;
	
	@Resource
	IApplicationDAO applicationDAO;
	
	@Transactional("txAccessorWrite")
	@Override
	public Serializable add(String jsonString) {
		try {
			ApiAuthorized apiAuthorized = this.toJavaObject(jsonString, ApiAuthorized.class);
			
			Long userId = apiAuthorized.getUserId();
			if(StringUtil.isEmpty(userId)){
				throw new ServiceException(MISSING_PARAMETER.concat("userId"));
			}
			
			String serviceName = apiAuthorized.getServiceName();
			if(StringUtil.isEmpty(serviceName)){
				throw new ServiceException(MISSING_PARAMETER.concat("serviceName"));
			}
			
			String serviceMethod = apiAuthorized.getServiceMethod();
			if(StringUtil.isEmpty(serviceMethod)){
				throw new ServiceException(MISSING_PARAMETER.concat("serviceMethod"));
			}
			
			
			boolean isEmptyAppName = applicationDAO.getAccessorWrite().isEmpty(ApiAuthorized.class, "serviceMethod", serviceMethod);
			if(!isEmptyAppName){
				throw new ServiceException("Application serviceMethod already exists");
			} 
			
			return (Long)applicationDAO.getAccessorWrite().save(apiAuthorized);
		} catch (Exception e) {
            if(e instanceof ServiceException){
            	throw new ServiceException(e);
            	
            }else{
            	throw new ServiceException(EXCEPTION_MESSAGE);
            }
		}
	}

	@Transactional("txAccessorWrite")
	@Override
	public boolean delete(String jsonString) {
		try{
			ApiAuthorized apiAuthorized = this.toJavaObject(jsonString, ApiAuthorized.class);
			Long id = apiAuthorized.getId();
			
			if(StringUtil.isEmpty(id)){
				throw new ServiceException(MISSING_PARAMETER.concat("id"));
			}
			
			apiAuthorized = applicationDAO.getAccessorWrite().getById(id, ApiAuthorized.class);
			return applicationDAO.getAccessorWrite().delete(apiAuthorized);
		} catch (Exception e) {
			if (e instanceof ServiceException) {
				throw new ServiceException(e);

			} else {
				throw new ServiceException(EXCEPTION_MESSAGE);
			}
		}
	}

}
