package com.j8.erro.customizedexception;

import java.util.HashMap;
import java.util.Map;

public class CustomerService {	
	Map<String, Customer> custStorage = new HashMap<String, Customer>();
	
	public CustomerService(){
		Customer peter = new Customer("Peter", 20);
		Customer jack = new Customer("Jack", 30);
		custStorage.put("peter", peter);
		custStorage.put("jack", jack);
	}

	/**
	 * 要捕捉
	 * @param name
	 * @param age
	 * @throws InValidInfoException
	 */
	public void postCustomer(String name, int age) throws InValidInfoException{
		// check then throw exception if invalid info
		if(name.isEmpty() || age > 150 || age < 1){
			throw new InValidInfoException("Please check inputs again: name & age!");
		}
		custStorage.put(name, new Customer(name, age));
	}

	/**
	 * 不需要捕捉，抛出。
	 * @param name
	 * @return
	 */
	public Customer findCustomerById(String name){
		Customer cust = custStorage.get(name);
		if(null == cust){
			throw new CustomerNotFoundException("Customer Not Found!");
		}
		return cust;
	}
	
	
	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		
		// for Checked Exception
		try {
			customerService.postCustomer("Mary", -1);
		} catch (InValidInfoException e) {
			e.printStackTrace();
		}
		
		// for UnChecked Exception
		customerService.findCustomerById("Mary");
		
	}
}