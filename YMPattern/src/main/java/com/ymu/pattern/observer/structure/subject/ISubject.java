package com.ymu.pattern.observer.structure.subject;

import java.util.List;

import com.ymu.pattern.observer.structure.observer.IObserver;


/**
 * 观察者模式的主题接口。
 * 规定具体主题需要实现的添加、删除观察者以及通知
 * 观察者更新数据的方法。
 * @author Administrator
 *
 */
public interface ISubject {
	
	/**
	 * 添加观察者。
	 * @param obs
	 */
	public void addObserver(IObserver obs);
	
	/**
	 * 删除观察者。
	 * @param obs
	 */
	public void deleteObserver(IObserver obs);
	
	/**
	 * 如果有新的消息，通知所有观察者更新信息。
	 */
	public void notifyAllObserver();
	
	/**
	 * 如果有新消息，通知观察者。
	 * @param obs
	 */
	public void notifyObserver(IObserver obs);
	
	public void notifyObserver(List<IObserver> obss);
		
}
