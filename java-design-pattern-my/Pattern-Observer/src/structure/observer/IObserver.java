package structure.observer;

/**
 * 观察者接口。
 * 规定具体观察者用来更新数据的方法。
 * @author Administrator
 *
 */
public interface IObserver {
	
	/**
	 * 通知所有具体观察者来更新数据。
	 * 这里模拟是接电话。
	 * @param hearMess
	 */
	public void hearTelephone(String hearMess);
		
}
