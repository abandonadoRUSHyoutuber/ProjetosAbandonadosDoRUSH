package rush.polimorfismo;

public class Quadrado extends Figura {

	double lado;
	
	public Quadrado(double lado) {
		this.lado = lado;
	}
	
	public double calcularArea() {
		return lado*lado;
	}
}
