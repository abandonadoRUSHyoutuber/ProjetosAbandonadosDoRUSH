package rush.polimorfismo;

public class Circulo extends Figura {

	double raio;
	
	public Circulo(double raio) {
		this.raio = raio;
	}
	
	public double calcularArea() {
		return Math.PI * raio * raio;
	}
}
