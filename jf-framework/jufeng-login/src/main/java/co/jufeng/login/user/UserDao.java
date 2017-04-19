package co.jufeng.login.user;

public interface UserDao {
	
	public String getUserSessionAttributeName();

	public UserEntity getUserByUid(String uid);
}
