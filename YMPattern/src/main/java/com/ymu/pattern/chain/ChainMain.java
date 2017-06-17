package com.ymu.pattern.chain;

import java.util.ArrayList;

import com.ymu.pattern.chain.structure.ChainReponseAbstract;
import com.ymu.pattern.chain.structure.IHandler;
import com.ymu.pattern.chain.structure.impl.BeiJing;
import com.ymu.pattern.chain.structure.impl.ShangHai;
import com.ymu.pattern.chain.structure.impl.TianJin;

public class ChainMain extends ChainReponseAbstract {

	public static void main(String[] args) {
		ChainMain applicantion = new ChainMain();
		ArrayList<IHandler> handlers = new ArrayList<IHandler>();
		handlers.add(new BeiJing());
		handlers.add(new ShangHai());
		handlers.add(new TianJin());
		applicantion.reponseClient(handlers, "11131456753246543");
		applicantion.reponseClient(handlers, "22314567532465436");
		applicantion.reponseClient(handlers, "5501456753246543");
	}
}
