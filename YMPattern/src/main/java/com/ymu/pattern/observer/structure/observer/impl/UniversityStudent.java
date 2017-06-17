package com.ymu.pattern.observer.structure.observer.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.ymu.pattern.observer.structure.observer.IObserver;
import com.ymu.pattern.observer.structure.subject.ISubject;

/**
 * 普通大学生具体观察者类。
 * 
 * @author Administrator
 * 
 */
public class UniversityStudent implements IObserver {
	
	private ISubject mSubject;  //具体主题的引用
	
	private File mFile;
	
	/**
	 * 构造函数
	 * @param subject 该观察者要观察的主题。
	 * @param fileName
	 */
	public UniversityStudent(ISubject subject,String fileName) {
		mSubject = subject;
		//使当前实例成为subject所引用的具体主题的观察者
		mSubject.addObserver(this); 
		mFile = new File(fileName);
	}

	/**
	 * 该观察者收到信息，将信息写入到一个可读写的随机文本中。
	 */
	@Override
	public void hearTelephone(String hearMess) {
		RandomAccessFile out = null;
		try {
			out = new RandomAccessFile(mFile, "rw");
			out.seek(out.length());
			byte[] b = hearMess.getBytes("utf-8");
			out.write(b);
			System.out.print("我是一个大学生,");
			System.out.println("我向文件" + mFile.getName() + "写入如下内容：");
			System.out.println(hearMess);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
