package co.jufeng.accessor;
import java.util.Properties;

import co.jufeng.string.StringUtil;
import co.jufeng.util.MD5EncryptUtil;

import com.alibaba.druid.util.DruidPasswordCallback;

@SuppressWarnings("serial")
public class DBPasswordCallback extends DruidPasswordCallback {

	@Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String pwd = properties.getProperty("password");
        if (StringUtil.isNotBlank(pwd)) {
            try {
                String password =  MD5EncryptUtil.getDecrypt(pwd);
                setPassword(password.toCharArray());
            } catch (Exception e) {
                setPassword(pwd.toCharArray());
            }
        }
    }
}