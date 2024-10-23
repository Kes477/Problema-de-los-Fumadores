package Main;

public class Mesa {
 // Espacios de la mesa
 public volatile int[] recursosEnLaMEsa = new int[2]; // Espacios vacíos en la mesa
 
	/**
	 * @author Kevin Sánchez
	 * @summary Metodo ejecutado por el agente para colocar los elementos en la mesa.
	 * Una vez que la mesa está llena con dos recursos, el estado pasa a true
	 */
 public void colocarRecurso(int numRecurso, int IDrecurso) {
	 this.recursosEnLaMEsa[numRecurso]=IDrecurso;
 }
 
	/**
	 * @author Kevin Sánchez
	 * @summary Metodo ejecutado por un fumador para tomar un elemento de la mesa
	 */
 public void quitarRecurso(int recursoAgarrado) {
			 this.recursosEnLaMEsa[recursoAgarrado]=0;
	 }
 
} // fin clase Mesa