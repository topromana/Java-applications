package Test;
import org.junit.*;

import Controller.Operations;
import Model.Monom;
import Model.Polinom;

public class TestJUnit {

	@Test
	public void testAddition() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		Polinom expected = new Polinom();
		Polinom result = new Polinom();
		Operations add = new Operations();

		add.addTerms(new Monom(1, 3),p1);
		

		add.addTerms(new Monom(1, 2), p2);
	

		result = add.addPolinoms(p1, p2);
	

	    add.addTerms(new Monom(1, 3), expected);
		add.addTerms(new Monom(1, 2), expected);
		
	Assert.assertEquals(expected.terms.get(0).coeficient,result.terms.get(0).coeficient,0.0);
	Assert.assertEquals(expected.terms.get(1).coeficient,result.terms.get(1).coeficient,0.0);
		//Assert.assertNotSame(expected,result);
		//Assert.assertEquals(expected,result);
	}
	@Test
	
	public void testSubtraction() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		Polinom expected = new Polinom();
		Polinom result = new Polinom();
		Operations sub = new Operations();

	    sub.addTerms(new Monom(1, 1), p1);
		

		sub.addTerms(new Monom(2, 3), p2);
		
		result = sub.subtractPolinoms(p1, p2);

		sub.addTerms(new Monom(1, 1), expected);
		sub.addTerms(new Monom(-2, 3), expected);
		//Assert.assertEquals(expected,result);
		Assert.assertEquals(expected.terms.get(0).coeficient,result.terms.get(0).coeficient,0.0);
		Assert.assertEquals(expected.terms.get(1).coeficient,result.terms.get(1).coeficient,0.0);
	}
	
	@Test
	public void testMultiplication() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		Polinom expected = new Polinom();
		Polinom result = new Polinom();
		Operations mul = new Operations();

	    mul.addTerms(new Monom(2, 2), p1);
		

		mul.addTerms(new Monom(2, 3), p2);
		

		result = mul.multiplyPolinoms(p1, p2);

		mul.addTerms(new Monom(4, 5), expected);
		
		//Assert.assertEquals(expected,result);
		Assert.assertEquals(expected.terms.get(0).coeficient,result.terms.get(0).coeficient,0.0);
		Assert.assertEquals(expected.terms.get(1).coeficient,result.terms.get(1).coeficient,0.0);
	}
	
	@Test
	public void testDifferentiation() {
		Polinom p1 = new Polinom();
		//Polinom p2 = new Polinom();
		Polinom expected = new Polinom();
		Polinom result = new Polinom();
		Operations diff = new Operations();

		diff.addTerms(new Monom(1, 2), p1);
		

		result = diff.differentiatePolinom(p1);

		diff.addTerms(new Monom(2, 1), expected);
	
		//Assert.assertEquals(expected,result);
		Assert.assertEquals(expected.terms.get(0).coeficient,result.terms.get(0).coeficient,0.0);
		//Assert.assertEquals(expected.terms.get(1).coeficient,result.terms.get(1).coeficient,0.0);
	}

@Test
	public void testIntegration() {
		Polinom p1 = new Polinom();
		Polinom expected = new Polinom();
		Polinom result = new Polinom();
		Operations integr = new Operations();

		integr.addTerms(new Monom(6, 2), p1);
		

		result = integr.integratePolinom(p1);

		integr.addTerms(new Monom(2,3), expected);
		//integr.addTerms(new Monom(1, 0), expected);
		//Assert.assertEquals(expected,result);
		Assert.assertEquals(expected.terms.get(0).coeficient,result.terms.get(0).coeficient,0.0);
		//Assert.assertEquals(expected.terms.get(1).coeficient,result.terms.get(1).coeficient,0.0);
	}

}
