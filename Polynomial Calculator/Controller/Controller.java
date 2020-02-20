package Controller;

import Model.Polinom;

public class Controller {
	PolinomParser parser = new PolinomParser();

	public String addition(String polinomialA, String polinomialB) {

		Polinom result = new Polinom();
		Operations add = new Operations();
		Polinom polinom1 = new Polinom();
		Polinom polinom2 = new Polinom();
		polinom1 = parser.parse(polinomialA);
		polinom2 = parser.parse(polinomialB);
		result = add.addPolinoms(polinom1, polinom2);
		return result.toString();

	}
	
	public String subtraction(String polinomialA, String polinomialB) {
		Polinom result = new Polinom();
		Operations subtract = new Operations();
		Polinom polinom1 = new Polinom();
		Polinom polinom2 = new Polinom();
        polinom1 = parser.parse(polinomialA);
        polinom2 = parser.parse(polinomialB);
		result = subtract.subtractPolinoms(polinom1,polinom2);
		return result.toString();
		
	}
	
	public String multiplication(String polinomialA, String polinomialB) {
		Polinom result = new Polinom();
		Operations multiply = new Operations();
		Polinom polinom1 = new Polinom();
		Polinom polinom2 = new Polinom();
        polinom1 = parser.parse(polinomialA);
        polinom2 = parser.parse(polinomialB);
		result = multiply.multiplyPolinoms(polinom1,polinom2);
		return result.toString();
		
	}
	
	public String integration(String polinomialA) {
		Polinom result = new Polinom();
		Operations integrate = new Operations();
		Polinom polinom1 = new Polinom();
        polinom1 = parser.parse(polinomialA);
		result = integrate.integratePolinom(polinom1);
		return result.toString();
		
		
	}
	
	public String differentiate(String polinomialA) {
		Polinom result = new Polinom();
		Operations differentiate = new Operations();
		Polinom polinom1 = new Polinom();
        polinom1 = parser.parse(polinomialA);
		result = differentiate.differentiatePolinom(polinom1);
		return result.toString();
	}

}
