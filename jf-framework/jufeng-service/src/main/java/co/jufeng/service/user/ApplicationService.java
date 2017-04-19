package co.jufeng.service.user;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.jufeng.api.user.IApplicationService;
import co.jufeng.core.lang.ServiceException;
import co.jufeng.dao.user.IApplicationDAO;
import co.jufeng.model.web.Application;
import co.jufeng.service.BaseService;
import co.jufeng.string.StringUtil;
import co.jufeng.util.MD5EncryptUtil;

@Service
public class ApplicationService extends BaseService implements IApplicationService {

	private static final long serialVersionUID = 4368463112198695753L;

	@Resource
	IApplicationDAO applicationDAO;
	
	@Transactional("txAccessorWrite")
	@Override
	public Serializable add(String jsonString) {
		try {
			Application application = this.toJavaObject(jsonString, Application.class);
			
			Long userId = application.getUserId();
			if(StringUtil.isEmpty(userId)){
				throw new ServiceException(MISSING_PARAMETER.concat("userId"));
			}
			
			String appName = application.getAppName();
			if(StringUtil.isEmpty(appName)){
				throw new ServiceException(MISSING_PARAMETER.concat("appName"));
			}
			
			boolean isEmptyAppName = applicationDAO.getAccessorWrite().isEmpty(Application.class, "appName", appName);
			if(!isEmptyAppName){
				throw new ServiceException("Application appName already exists");
			} 
			
			Long appId = System.currentTimeMillis();
			application.setAppId(appId);
			String appSecret = MD5EncryptUtil.getMd5(userId + appName);
			application.setAppSecret(appSecret);
			Long id = (Long)applicationDAO.getAccessorWrite().save(application);
			return id;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional("txAccessorWrite")
	@Override
	public boolean delete(String jsonString) {
		try{
			Application application = this.toJavaObject(jsonString, Application.class);
			Long id = application.getId();
			
			if(StringUtil.isEmpty(id)){
				throw new ServiceException(MISSING_PARAMETER.concat("id"));
			}
			
			application = applicationDAO.getAccessorWrite().getById(id, Application.class);
			return applicationDAO.getAccessorWrite().delete(application);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
