package com.ymu.ftp;

import java.io.IOException;

import sun.net.ftp.FtpClient;

/**
 * demo，测试jdk自带ftp功能。缺点：不能大文件上传下载。
 * 
 * @author mutou
 *
 */
public class FtpByJdk {

	// 本地文件名
	private String localfilename;

	// 远程文件名
	private String remotefilename;

	// FTP客户端
	private FtpClient ftpClient;

	/**
	 * connectServer 连接ftp服务器
	 * 
	 * @throws java.io.IOException
	 * @param path
	 *            文件夹，空代表根目录
	 * @param password
	 *            密码
	 * @param user
	 *            登陆用户
	 * @param server
	 *            服务器地址
	 */
	public void connectServer(String server, String user, String password,
			String path) throws IOException {
//		// server：FTP服务器的IP地址；user:登录FTP服务器的用户名
//		// password：登录FTP服务器的用户名的口令；path：FTP服务器上的路径
//		ftpClient = new FtpClient();
//		ftpClient.openServer(server);
//		ftpClient.login(user, password);
//		// path是ftp服务下主目录的子目录
//		if (path.length() != 0)
//			ftpClient.cd(path);
//		// 用2进制上传、下载
//		ftpClient.binary();
	}

}
