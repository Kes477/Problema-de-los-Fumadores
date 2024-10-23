package Main;

import java.util.concurrent.Semaphore;
import java.util.Random;

final class Agente extends Proceso implements Runnable {

	 // De utilidad
	 private Random randomRecursoExcluido = new Random();
	 private Random randomrecurso1 = new Random();
	 private int recursoExcluido;
	 private int recurso1;
	 private int recurso2;
	
	 // Constructor
	public Agente(Util util, Mesa mesa, Semaphore semaforo) {
		super(util, mesa, semaforo);
		System.out.println("Agente creado");
	}
	
	/**
	 * @author Kevin Sánchez
	 * @summary El agente revisa la mesa para ver si está vacía
	 */
	public boolean mesaVacia() {
		if(mesa.recursosEnLaMEsa[0]==0 && mesa.recursosEnLaMEsa[1]==0)
			return true;
		else return false;
	}
		
	/**
	 * @author Kevin Sánchez
	 * @summary Corre el hilo: Genera recursos de forma aleatoria y los coloca en la mesa. 
	 * Los "sleep" simulan la tardanza en la ejecución.
	 */
    public void run() {
		
		/**
		 * if: Si la mesa está vacía y si no se han agotado las interacciones, el agente procede:
		 */
		    // 
        	if(this.mesaVacia() && util.get_contador()!=util.get_numInteracciones()+1) // El número de interacciones comienza en 1
        	{
        try {
            // El agente recibe el semáforo
            semaforo.acquire();	
        		
    		/**
    		 * 1. Genera aleatoriamente el recurso que el agente NO pondrá en la mesa.
    		 */
            this.recursoExcluido = randomRecursoExcluido.nextInt(3)+1; 
            
            /**
    		 * 2. switch: en cada caso, genera de forma aleatoria
    		 * cuál de los otros dos recursos colocará de primero y de último.
    		 */
            switch(this.recursoExcluido) {
            case 1: // Tabaco
            	this.recurso1=randomrecurso1.nextInt(2)+2; // recurso1 = 2 o 3
            	this.recurso2= (this.recurso1==2)? 3 : 2;  // recurso2 será el otro número (igual en los demás casos)
            	break;
            	
            case 2: // Papel
            	this.recurso1=randomrecurso1.nextInt(2)*2+1; // recurso1 = 1 o 3
            	this.recurso2= (this.recurso1==1)? 3 : 1;
            	break;
            	
            case 3: // Fósforo
            	this.recurso1=randomrecurso1.nextInt(2)+1; // recurso1 = 1 o 2
            	this.recurso2= (this.recurso1==1)? 2 : 1;
            	break;
            } 
            
            /**
    		 * 3. El agente coloca los recursos en la mesa.
    		 */
            mesa.colocarRecurso(0, this.recurso1);
            System.out.print(util.get_contador() + ". El agente colocó " + this.recursos[this.recurso1] + ".");
            Thread.sleep(300);
            
            mesa.colocarRecurso(1, this.recurso2);
            System.out.println(" El agente colocó " + this.recursos[this.recurso2] + ".");
            Thread.sleep(300);
            
        } // fin try
          
         catch (InterruptedException e) {
        	 ExceptionMensagge(e);
        } 
        finally { 
            /**
    		 * 4. Aumenta el contador.
    		 */
            util.add_contador();
        	semaforo.release();
            // El agente libera el semáforo
        	
        } // fin finally
    	} // fin if	
        	else 
        		if(util.get_contador()==util.get_numInteracciones()+1) {
        	            	util.set_run(false);
        	                /**
        	        		 * 5. Finalmente: Si se acabaron las interacciones,
        	            	 * vuelve falso el estado de los hilos para que dejen de correr. 
        	        		 */       	            	
        	            } // fin if	
	}  // fin run
}  // fin clase Agente