package com.j8.multiThread.javasynchronized;

public class JavaSynchronized extends Thread{
	Data data;
	public JavaSynchronized(Data data){
		this.data = data; 
	}
	
	public void run() {
		data.count();
    }
	
	public static void main(String[] args) {
		Data data = new Data();
		do{
			data.set(0);
			for(int i=0; i<10000; i++){
				 (new JavaSynchronized(data)).start();
			}
			
			try{
				Thread.sleep(1000);
				System.out.println(data.get());
			}catch(Exception e){
			}	
		}while(data.get() == 10000);
	}
}
