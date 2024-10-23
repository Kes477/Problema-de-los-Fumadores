package ProblemaFumadores;

public class Mesa {
	public static void main(String[] args) {
		Proceso[] procesos=new Proceso[4];
		procesos[0] = new Agente();
		
		for(int i=1;i<procesos.length;i++)
		procesos[i] = new Fumador(i);
		
		System.out.println("La mesa está vacía ");
		
		
	}

}
