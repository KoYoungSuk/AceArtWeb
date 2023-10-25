package com.wp.auntweb;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ChildClass c = new ChildClass();
        c.child_method4(); 
	}

}

class ParentClass{
	public void parent_method() {
		System.out.println("parent_method");
	}
	
	public void parent_method2() {
		System.out.println("parent_method2"); 
	}
	
	public void parent_method3() {
		System.out.println("parent_method3"); 
	}
	public void parent_method4() {
		//child_method2(); 
	}
}

class ChildClass extends ParentClass {
	public void child_method() {
		System.out.println("child_method"); 
	}
	public void child_method2() {
		System.out.println("child_method2"); 
	}
	public void child_method3() {
		System.out.println("child_method3"); 
	}
	public void child_method4() {
		parent_method2(); 
	}
}