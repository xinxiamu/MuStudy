package structure.observer.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import structure.observer.IObserver;
import structure.subject.ISubject;

/**
 * 海归人员具体观察这类。
 * 
 * @author Administrator
 * 
 */
public class HaiGui implements IObserver {

	private ISubject subject; // 具体主题的引用

	private File mFile;

	/**
	 * 构造函数
	 * 
	 * @param subject
	 *            该观察者要观察的主题。
	 * @param fileName
	 */
	public HaiGui(ISubject subject, String fileName) {
		this.subject = subject;
		// 使当前实例成为subject所引用的具体主题的观察者
		subject.addObserver(this);
		mFile = new File(fileName);
	}

	@Override
	public void hearTelephone(String hearMess) {
		boolean bo = hearMess.contains("java程序员") || hearMess.contains("软件");
		RandomAccessFile out = null;
		if (bo) {
			try {
				out = new RandomAccessFile(mFile, "rw");
				byte[] b = hearMess.getBytes();
				out.write(b);
				System.out.print("我是一名海归，");
				System.out.println("我向文件" + mFile.getName() + "写入如下内容:");
				System.out.println(hearMess);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					//释放系统资源
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("我是海归，这次的信息中没有我需要的信息");
		}
	}

}
