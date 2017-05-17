package com.j8.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class Demo {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
		SimpleBindings simpleBindings = new SimpleBindings();
		simpleBindings.put("reservationRateP", 0.8);

		nashorn.eval(
				"function reservationRate_credit() { /*传过来变量*/ var rp = reservationRateP; var creditNum = 0; return rp; }",
				simpleBindings);
		Double f = (Double) nashorn.eval("reservationRate_credit()", simpleBindings);
		System.out.println("---" + f);

	}

}
