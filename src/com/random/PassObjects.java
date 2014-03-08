package com.random;
public class PassObjects {

	public static void main(String[] args) {
		Employee obj1 = new Employee(10);
		Employee obj2 = new Employee(20);
		Check obj = new Check();
		
		System.out.println(obj1 + "\t" + obj2);
		System.out.println(obj1.id + "\t" + obj2.id);
		
		obj.swap(obj1, obj2);
		
		System.out.println(obj1 + "\t" + obj2);
		System.out.println(obj1.id + "\t" + obj2.id);
	}

}

class Employee {
	int id;

	Employee(int id) {
		this.id = id;
	}
}

class Check {
	void swap(Employee obj1, Employee obj2) {
//		Employee temp;
//		temp = obj1;
//		obj1 = obj2;
//		obj2 = temp;
		int temp;
		temp = obj1.id;
		obj1.id = obj2.id;
		obj2.id = temp;
		System.out.println("\n" + obj1 + "\t" + obj2 + "\n");
	}
}