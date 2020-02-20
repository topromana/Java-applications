package Controller;

import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Monom;
import Model.Polinom;

public class PolinomParser {
	
	public Polinom parse(String input) {
		Polinom poly = new Polinom();
		Operations operation = new Operations();
		double coefficient = 0;
		int degree = 0;
		Pattern p = Pattern.compile("(-?\\b\\d+)[xX]\\^(-?\\d+\\b)");
		Matcher m = p.matcher(input);
		while (m.find()) {
			coefficient = Double.parseDouble(m.group(1));
			degree = Integer.parseInt(m.group(2));
			operation.addTerms(new Monom(coefficient, degree), poly);
		}
		return poly;
	}
}
