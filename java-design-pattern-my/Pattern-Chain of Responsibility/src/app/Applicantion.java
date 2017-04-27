package app;

import java.util.ArrayList;

import structure.ChainReponseAbstract;
import structure.IHandler;
import structure.impl.BeiJing;
import structure.impl.ShangHai;
import structure.impl.TianJin;

public class Applicantion extends ChainReponseAbstract {

	public static void main(String[] args) {
		Applicantion applicantion = new Applicantion();
		ArrayList<IHandler> handlers = new ArrayList<IHandler>();
		handlers.add(new BeiJing());
		handlers.add(new ShangHai());
		handlers.add(new TianJin());
		applicantion.reponseClient(handlers, "11131456753246543");
		applicantion.reponseClient(handlers, "22314567532465436");
		applicantion.reponseClient(handlers, "5501456753246543");
	}
}
