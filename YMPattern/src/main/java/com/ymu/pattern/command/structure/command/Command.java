package com.ymu.pattern.command.structure.command;

/**
 * 命令接口。
 * 该接口用来封装“请求”的方法。
 * @author Administrator
 *
 */
public interface Command {
	
	/**
	 * 一条命令。
	 */
	public abstract void execute();
}
