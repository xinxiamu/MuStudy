package structure.subject.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import structure.observer.IObserver;
import structure.subject.ISubject;

/**
 * 具体主题类。
 * 模拟求职中心。维护一个字符串，表示求职中心的求职信息。
 * 当求职信息发生变化时，遍历具体观察者列表，通知所有具体观察者。
 * 
 *  * @author Administrator
 *
 */
public class SeekJobCenterSubject implements ISubject {
	
	private String mess; //求职中心的通知信息
	
	private List<IObserver> observersManageList; //观察者维护列表
	
	private boolean chang ; //标志通知信息是否改变，改变则通知观察者
	
	public SeekJobCenterSubject() {
		this.mess = "";
		this.observersManageList = new ArrayList<IObserver>();
		this.chang = false;
	}

	@Override
	public void addObserver(IObserver obs) {
		if (observersManageList != null) {
			observersManageList.add(obs);
		}
	}

	@Override
	public void deleteObserver(IObserver obs) {
		if (observersManageList != null) {
			observersManageList.remove(obs);
		}
	}

	@Override
	public void notifyAllObserver() {
		//如果mess改变，通知所有观察者
		if (chang) { 
			Iterator<IObserver> iterator = observersManageList.iterator();
			while (iterator.hasNext()) {
				IObserver observer = iterator.next();
				observer.hearTelephone(mess);  //让观察者接电话
				
			}
		}
		chang = false;
	}
	
	/**
	 * 给出求职中心的求职信息。并判断信息是否已经
	 * 改变，修改状态。
	 * @param str
	 */
	public void giveMess(String str) {
		if (str.equals(mess)) {
			chang = false;
		} else {
			mess = str;
			chang = true;
		}
	}

	@Override
	public void notifyObserver(IObserver obs) {
		obs.hearTelephone(mess);
	}

	@Override
	public void notifyObserver(List<IObserver> obss) {
		for (IObserver iObserver : obss) {
			iObserver.hearTelephone(mess);
		}
	}
}
