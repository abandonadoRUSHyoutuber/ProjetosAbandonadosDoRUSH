package rush.polimorfismo;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		String s;
		
		s = JOptionPane.showInputDialog("Raio: ");
		Figura f1 = new Quadrado(Double.parseDouble(s));

		s = JOptionPane.showInputDialog("Lado: ");
		Figura f2 = new Circulo(Double.parseDouble(s));

		System.out.println(f1.calcularArea());
		System.out.println(f2.calcularArea());
	}

}
