package structure.invoker;

import structure.command.Command;

/**
 * 请求者。
 * @author Administrator
 *
 */
public class ArmySuperior {
	
	Command command;   //命令对象的引用    
	
	public ArmySuperior(Command command) {
		this.command = command;
	}
	
	/**
	 * 请求具体命令告知接收者执行命令。
	 */
	public void startExecuteCommand() {
		command.execute();
	}
}
