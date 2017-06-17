package com.ymu.pattern.observer;

import java.util.ArrayList;
import java.util.List;

import com.ymu.pattern.observer.structure.observer.IObserver;
import com.ymu.pattern.observer.structure.observer.impl.HaiGui;
import com.ymu.pattern.observer.structure.observer.impl.UniversityStudent;
import com.ymu.pattern.observer.structure.subject.impl.SeekJobCenterSubject;

/**
 * <p>
 * 利用观察者模式。
 * </p>
 * <p>
 * 演示了一个大学生和一个归国留学者成为求职中心的观察者；
 * </p>
 * <p>
 * 当求职中心有新的人才需求信息时，大学生和归国留学者将得到通知
 * </P>
 * 
 * @author Administrator
 *
 */
public class ObserverMain {

	public static void main(String[] args) {
		SeekJobCenterSubject seekJobCenter = new SeekJobCenterSubject(); // 具体主题，求职中心
		IObserver zhangLiSt = new UniversityStudent(seekJobCenter, "F:a.txt"); // 具体观察者zhangLiSt
		IObserver wangTaoHg = new HaiGui(seekJobCenter, "F:b.txt"); // 具体观察者wangTaoHg，一名海归

		List<IObserver> observers = new ArrayList<>();
		observers.add(zhangLiSt);
		observers.add(wangTaoHg);

		seekJobCenter.giveMess("腾辉公司需要1个java程序员。"); // 具体主题给出新的就业信息
		seekJobCenter.notifyAllObserver(); // 并通知所有的观察者

		seekJobCenter.giveMess("林安公司需要5个文员");
		seekJobCenter.notifyAllObserver();

		seekJobCenter.giveMess("林安公司需要9个电工");
		seekJobCenter.notifyAllObserver();

		seekJobCenter.giveMess("林安公司需要9个电工"); // 信息不是新的
		seekJobCenter.notifyAllObserver(); // 观察者不会执行更新操作

		seekJobCenter.giveMess("你好，大学生，面试啦");
		seekJobCenter.notifyObserver(zhangLiSt);

		seekJobCenter.giveMess("妹子好呀");
		seekJobCenter.notifyObserver(observers);

	}
}
