package structure.command.impl;

import structure.command.Command;
import structure.receiver.CompanyArmy;

/**
 * 具体命令。
 * @author Administrator
 *
 */
public class ConcreteCommand implements Command {
	
	CompanyArmy army; //接收者的引用
	
	public ConcreteCommand(CompanyArmy army) {
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
