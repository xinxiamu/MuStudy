	
	不要在try{}catch(){}里面放循环	
	try {
		for (int i = 0; i < 3; i++) {
			int a = i/0;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	


		