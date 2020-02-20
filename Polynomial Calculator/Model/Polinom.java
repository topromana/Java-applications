package Model;

import java.util.ArrayList;

public class Polinom {
	public ArrayList<Monom> terms = new ArrayList<Monom>();
	
	public ArrayList<Monom> getTerms(){
		return terms;
	}
	public void setTerms(ArrayList<Monom> terms) {
		this.terms =terms;
	}
	
	@Override
	public String toString() {
		String s = " ";
		for (Monom m : terms) {
			s += " " + m.toString();
		}
		return s;
	}

}
