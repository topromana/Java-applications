package Controller;

import Model.Polinom;

public interface OperationsInterface {

	public Polinom addPolinoms(Polinom polinom1, Polinom polinom2);
	public Polinom subtractPolinoms(Polinom polinom1, Polinom polinom2);
	public Polinom multiplyPolinoms(Polinom polinom1, Polinom polinom2);
	public Polinom integratePolinom(Polinom polinom1);
	public Polinom differentiatePolinom(Polinom polinom1);
}
