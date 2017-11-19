package com.j8.erro.handexception;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示如何捕捉异常处理。
 */
public class CustomerService {
	Map<String, Customer> custStorage = new HashMap<String, Customer>();

	public CustomerService() {
		Customer peter = new Customer("Peter", 20);
		Customer jack = new Customer("Jack", 30);
		custStorage.put("peter", peter);
		custStorage.put("jack", jack);
	}

	public Customer findCustomerById(String name) {
		Customer cust = custStorage.get(name);
		if (null == cust) {
			throw new CustomerNotFoundException("Customer Not Found!");
		}
		return cust;
	}

	public void postCustomer(String name, int age) throws InValidAgeException, InValidNameException {
		// check then throw exception if invalid info
		if (name.isEmpty()) {
			throw new InValidNameException("Please check Name again for valid!");
		}

		if (age > 150 || age < 1) {
			throw new InValidAgeException("Please check Age again for valid!");
		}

		custStorage.put(name, new Customer(name, age));
	}

	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();

		// for Checked Exception
		try {
			customerService.postCustomer("", 10);
		} catch (InValidNameException | InValidAgeException e) { //java7后语法|
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Done!");
		}

		// for Checked Exception
		try {
			customerService.postCustomer("Jack", -1);
		} catch (InValidNameException | InValidAgeException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Done!");
		}

		customerService.findCustomerById("Mary");
	}
}