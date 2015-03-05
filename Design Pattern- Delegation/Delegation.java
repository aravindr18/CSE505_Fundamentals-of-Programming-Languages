/*
 * Problem statement:
 * Extend Delegation technique so that the inheritance of attributes and methods, and also method over-riding are handled. Apply the resulting technique 
 * to the program in Delegation.java which defines four classes, C1, C11, C111, and C112, where the classes C111 and C112 
 * extend C11 which in turn extends C1.The result of your transformation should be four classes called D1, D11, D111, and D112 
 * which correspond to C1, C11, C111, and C112 respectively, but do not make use of class inheritance. 
 * Make the classes D1, D11, D111, and D112 implement interfaces I1, I11, I111 and I112 respectively, 
 * where the interfaces I111 and I112 should inherit from I11 which in turn should inherit from I1. Define these interfaces suitably.
 * 
 * @author: mr.prakashnatarajan@gmail.com
 */
public class Delegation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		C111 c111 = new C111();
		System.out.println(c111.m111());

		C112 c112 = new C112();
		System.out.println(c112.m112());
		
		D111 d111 = new D111();
		System.out.println(d111.m111());

		D112 d112 = new D112();
		System.out.println(d112.m112());

	}
}
class C1 {
	int a1 = 1;

	public int m1() {
		return a1 + p1(100) + q1(100);
	}
	public int p1(int m) {
		return m;
	}
	public int q1(int m) {
		return m;
	}
}

 class C11 extends C1 {
	int a11 = 11;

	public int m11() {
		return m1() + q1(200);
	}
	public int p1(int m) {
		return m * a1;
	}
	public int q1(int m) {
		return m + a11;
	}
}

class C111 extends C11 {
	int a111 = 111;

	public int m111() {
		return m1() + m11() + a111;
	}
	public int p1(int m) {
		return m * a1 * a11;
	}
}

class C112 extends C11 {
	int a112 = 112;

	public int m112() {
		return m1() + m11() + a112;
	}
	public int p1(int m) {
		return m * a1 * a11 * a112;	
	}
}


// -------SIMULATING CLASS INHERITANCE BY DELEGATION ---------

interface I1 {
	int m1();
	int geta1();
	int p1(int m);
	int q1(int m);
	 
}

interface I11 extends I1 {
	int m11();
	int geta11();
}

interface I111 extends I11 {
	int m111();
}

interface I112 extends I11 {
	int m112();
}

class D1 implements I1 {
	int a1 = 1;
	I1 this2 = null;
	public D1() {
		this2 = this;
	}
	public D1(I11 objI11) {
		this2 = objI11;
	}
//	public D1(I111 objI111) {
//		this2 = objI111;
//	}
	@Override
	public int m1() {
		// return a1 + p1(100) + q1(100);
		return a1 + this2.p1(100) + this2.q1(100);
	}

	@Override
	public int geta1() {
		return a1;
	}

	@Override
	public int p1(int m) {
		return m;
	}

	@Override
	public int q1(int m) {
		return m;
	}
}

class D11 implements I11 {
	int a11 = 11;
	I1 objD1 = null;
	public D11() {
		objD1 = new D1(this);
	}
			
//	I1 objD1 = new D1();
	I1 this2 = null;
	public D11(I111 objD111) {
		objD1 = new D1(this);
		this2 = objD111;
	}
	public D11(I112 objD112) {
		objD1 = new D1(this);
		this2 = objD112;
	}
	@Override
	public int m1() {
		return objD1.m1();
	}

	@Override
	public int geta1() {
		return objD1.geta1();
	}

	@Override
	public int p1(int m) {
		// return m * a1;
		return this2.p1(m);
	}

	@Override
	public int q1(int m) {
		// return m + a11;
		return m + geta11();
	}

	@Override
	public int m11() {
		// return m1() + q1(200);
		return m1() + this2.q1(200);
	}

	@Override
	public int geta11() {
		return a11;
	}
}
class D111 implements I111 {
	int a111 = 111;
	I11 objD11 = null;
	
	public D111() {
		objD11 = new D11(this);
	}	
	
	@Override
	public int m11() {
		// TODO Auto-generated method stub
		return objD11.m11();
	}

	@Override
	public int geta11() {
		// TODO Auto-generated method stub
		return objD11.geta11();
	}

	@Override
	public int m1() {
		return objD11.m1();
	}

	@Override
	public int geta1() {
		// TODO Auto-generated method stub
		return objD11.geta1();
	}

	@Override
	public int p1(int m) {
		// return m * a1 * a11;
		return m * geta1() * geta11();
	}

	@Override
	public int q1(int m) {
		// TODO Auto-generated method stub
		return objD11.q1(m);
	}

	@Override
	public int m111() {
		// return m1() + m11() + a111;
		return m1() + m11() + a111;
	}	
}
class D112 implements I112 {
	int a112 = 112;
	I11 objD11 = null;
	
	public D112() {
		objD11 = new D11(this);
	}
	@Override
	public int m11() {
		return objD11.m11();
	}

	@Override
	public int geta11() {
		return objD11.geta11();
	}

	@Override
	public int m1() {
		return objD11.m1();
	}

	@Override
	public int geta1() {
		return objD11.geta1();
	}

	@Override
	public int p1(int m) {
		return m * geta1() * geta11() * a112;
	}

	@Override
	public int q1(int m) {
		return objD11.q1(m);
	}

	@Override
	public int m112() {
		// return m1() + m11() + a112;
		return m1() + m11() + a112;
	}
	
}