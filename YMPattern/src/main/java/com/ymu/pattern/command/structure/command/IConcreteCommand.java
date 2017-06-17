package com.ymu.pattern.command.structure.command;

import com.ymu.pattern.command.structure.receiver.CompanyArmy;

/**
 * 具体命令。
 * @author Administrator
 *
 */
public class IConcreteCommand implements Command {
	
	CompanyArmy army; //接收者的引用
	
	public IConcreteCommand(CompanyArmy army) {
		this.army = army;
	}
	
	/**
	 * 封装着指挥官的请求。
	 */
	@Override
	public void execute() {
		//请求接收者偷袭敌人 
		army.sneakAttack();
	}
}
