package View;

import java.awt.*;
import javax.swing.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import com.sun.org.apache.xpath.internal.operations.Operation;

import Controller.Controller;
import Controller.Operations;
import Controller.PolinomParser;
import Model.Polinom;

import java.awt.event.*;

public class UI extends JFrame {
	private JPanel panel;
	private JTextField polinomialTextField1 = new JTextField();
	private JTextField polinomialTextField2 = new JTextField();
	private JTextField solutionPolinomialTextFiled = new JTextField();
	private JLabel label1 = new JLabel("polinom 1");
	private JLabel label2 = new JLabel("polinom 2");
	private JLabel label3 = new JLabel("polinom solution");
	private JButton addButton = new JButton("+");
	private JButton subtractButton = new JButton("-");
	private JButton multiplyButton = new JButton("*");
	private JButton integrateButton = new JButton("integrate");
	private JButton differentiateButton = new JButton("'");
	private JLabel label4 = new JLabel("please insert thee polynoms having the following structure: ax^n+bx^n-1+...+zx^0");
	PolinomParser parser = new PolinomParser();

	public UI(String name){
		super("Polynomials");
		panel = new JPanel();
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		polinomialTextField1.setLocation(500, 800);
		polinomialTextField2.setLocation(200, 100);
		polinomialTextField1.setPreferredSize(new Dimension(200, 25));
		polinomialTextField2.setPreferredSize(new Dimension(200, 25));
		solutionPolinomialTextFiled.setPreferredSize(new Dimension(200, 25));


		polinomialTextField1.setVisible(true);
		polinomialTextField2.setVisible(true);
		solutionPolinomialTextFiled.setVisible(true);
		addButton.setPreferredSize(new Dimension(100,25));
		//addButton.setBounds(40, 80, 100, 25);
		addButton.setVisible(true);
		subtractButton.setPreferredSize(new Dimension(100,25));
		subtractButton.setVisible(true);
		multiplyButton.setPreferredSize(new Dimension(100,25));
		multiplyButton.setVisible(true);
		differentiateButton.setPreferredSize(new Dimension(100,25));
		differentiateButton.setVisible(true);
		integrateButton.setPreferredSize(new Dimension(100,25));
		integrateButton.setVisible(true);
		panel.add(label1);
		panel.add(polinomialTextField1);
		panel.add(label2);
		panel.add(polinomialTextField2);
		panel.add(label3);
		panel.add(solutionPolinomialTextFiled );
		panel.add(addButton);
		panel.add(subtractButton);
		panel.add(multiplyButton);
		panel.add(differentiateButton);
		panel.add(integrateButton);
		panel.add(label4);
	
		
		

addButton.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Controller controller = new Controller();
		String result = controller.addition(polinomialTextField1.getText(), polinomialTextField2.getText());
		solutionPolinomialTextFiled.setText("");
		solutionPolinomialTextFiled.setText(result);
	}
});
subtractButton.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Controller controller = new Controller();
		String result = controller.subtraction(polinomialTextField1.getText(), polinomialTextField2.getText());
		solutionPolinomialTextFiled.setText("");
		solutionPolinomialTextFiled.setText(result.toString());
	}
});

multiplyButton.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Controller controller = new Controller();
		String result = controller.multiplication(polinomialTextField1.getText(), polinomialTextField2.getText());
		solutionPolinomialTextFiled.setText("");
		solutionPolinomialTextFiled.setText(result.toString());
	}
});

differentiateButton.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Controller controller = new Controller();
		String result = controller.differentiate(polinomialTextField1.getText());
		solutionPolinomialTextFiled.setText("");
		solutionPolinomialTextFiled.setText(result.toString());
	}
});
integrateButton.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Controller controller = new Controller();
		String result = controller.integration(polinomialTextField1.getText());
		solutionPolinomialTextFiled.setText("");
		solutionPolinomialTextFiled.setText(result.toString());
	}
});
	}
	void reset() {
		polinomialTextField1.setText("");
	}
	public static void main(String args[]) {
		JFrame frame = new UI("Polynomials");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 600);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	
}