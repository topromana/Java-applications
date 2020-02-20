package Model;

public class Monom {
	
	public double coeficient;
	public int degree;
	//public double coefficient;
	
	public Monom(double coeficient, int degree) {
		super();
		this.coeficient = coeficient;
		this.degree = degree;
	}
	public double getCoeficient() {
		return coeficient;
	}
	public void setCoeficient(int coeficient) {
		this.coeficient = coeficient;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public String toString() {
		if (getCoeficient() < 0 && getDegree() != 0) {
			return "-" + getCoeficient() + "x^" + getDegree();
		}

		if (getCoeficient() == 0 && getDegree() != 0) {
			return "x^" + getDegree();
		}

		if (getCoeficient() == 1 && getDegree() != 0) {
			return "+x^" + getDegree();
		}

		if (getCoeficient() == -1 && getDegree() != 0) {
			return "-x^" + getDegree();
		}

		return "+" + getCoeficient() + "x^" + getDegree();

	}
	

}
