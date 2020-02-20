package Controller;

import javax.swing.JOptionPane;

import Model.Monom;
import Model.Polinom;

public class Operations implements OperationsInterface {

	public void addTerms(Monom m, Polinom p) {
		boolean exists = false;
		double coeff = 0, coeff2 = 0;
		int degree = 0, degree2 = 0;
		for (Monom i : p.getTerms()) {
			degree = i.getDegree();
			degree2 = m.getDegree();
			if (degree == degree2) {
				coeff = (int) i.getCoeficient();
				coeff2 = (int) m.getCoeficient();
				i.coeficient = coeff + coeff2;
				if (i.coeficient == 0)
					p.terms.remove(i);
				exists = true;
				break;
			}
		}
		if (!exists)
			p.terms.add(m);
	}

	public void subtractTerms(Monom m, Polinom p) {
		boolean exists = false;
		double coeff = 0, coeff2 = 0;
		int degree = 0, degree2 = 0;
		for (Monom i : p.getTerms()) {
			degree = i.getDegree();
			degree2 = m.getDegree();
			if (degree == degree2) {
				coeff = (int) i.getCoeficient();
				coeff2 = (int) m.getCoeficient();
				if (coeff == 0 && degree != 0) {
					JOptionPane.showMessageDialog(null, "Incorrect data format, please reintroduce correctly");
				}
				i.coeficient = coeff - coeff2;
				if (i.coeficient == 0)
					p.terms.remove(i);
				exists = true;
				break;
			}
		}
		if (!exists) {
			m.coeficient -= 2 * m.coeficient;
			p.terms.add(m);
		}
	}

	public Monom multiplyTerms(Monom m1, Monom m2) {
			Monom result = new Monom(0,0);
			result.degree = m1.degree + m2.degree;
			result.coeficient = m1.coeficient * m2.coeficient;
		return result;
	}

	public Polinom addPolinoms(Polinom polinom1, Polinom polinom2) {
		for (Monom monom : polinom2.terms) {
			addTerms(monom, polinom1);
		}
		return polinom1;
	}

	public Polinom subtractPolinoms(Polinom polinom1, Polinom polinom2) {
		Polinom result = new Polinom();
		for (Monom monom : polinom2.terms) {
			subtractTerms(monom, polinom1);
		}
		return polinom1;
	}

	public Polinom multiplyPolinoms(Polinom polinom1, Polinom polinom2) {
		Polinom result = new Polinom();
		Monom resultFinal = new Monom(0,0);
		for (Monom monom1 : polinom2.terms) {
			for(Monom monom2 : polinom1.terms) {
			resultFinal = multiplyTerms(monom1,monom2);
		    this.addTerms(resultFinal, result);
			
			}
		}
		return result;
	}

	public Polinom differentiatePolinom(Polinom polinom1) {
		Polinom result = new Polinom();
		for (Monom monom : polinom1.terms) {
			if (monom.degree >= 1) {
				monom.coeficient *= monom.degree;
				monom.degree--;
				result.terms.add(monom);
			}

		}
		return result;
	}

	public Polinom integratePolinom(Polinom polinom1) {
		Polinom result = new Polinom();
		for (Monom monom : polinom1.terms) {
			monom.degree++;
			monom.coeficient /= monom.degree;
			result.terms.add(monom);

		}
		return result;
	}
}
