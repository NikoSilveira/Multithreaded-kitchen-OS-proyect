package proyectosemaforo;

import java.util.concurrent.*;

public abstract class Meson { //Buffer
    
    private Platillo[] buffer;
    private int i=0, j=0;      //proximo en entrar; proximo en salir
    private int mustTake;
    
    private int servidos;      //contador de servidos por meson
    private int available;     //contador de disponibles por meson
	
    private Semaphore mutex = new Semaphore(1,true); //Solo uno modifica a la vez el meson
    private Semaphore hayPlatillo = new Semaphore(0, true);
    private Semaphore hayEspacio;
	
	//Constructor
	public Meson(int tam, int mustTake){ //Se pasa el tamaño que tendra el buffer
            buffer = new Platillo[tam];
            hayEspacio = new Semaphore(buffer.length, true); //el semaforo dice que hay espacio segun longitud del array
            this.mustTake = mustTake;                        //cantidad de platillos a tomar de la mesa
            
            this.servidos = 0;
            this.available = 0;
	}
	
	public void ponerPlatillo (Platillo platillo) throws InterruptedException{
            hayEspacio.acquire();   //hay espacio para poner el platillo, ve a ponerlo
            mutex.acquire();        //Solo uno a la vez a seccion critica
            
            buffer[i] = platillo;       //Colocar el platillo en el meson
            i = (i+1) % buffer.length;
            available++;
            
            mutex.release();        //liberado para el proximo
            hayPlatillo.release();
	}
	
	public void tomarPlatillo () throws InterruptedException{
            hayPlatillo.acquire(mustTake);  //Espera si no hay aun en la mesa (AQUI COLOCAR NUMERO, 1 por default)
            mutex.acquire();        //Solo uno a la seccion critica
		
            //buffer[j] = null;
            j=(j+1) % buffer.length;
            available = available - mustTake;
            servidos = servidos + mustTake;
            
            mutex.release();
            hayEspacio.release(mustTake);   //Si no hay espacio, pues ahora se libera
            
	}

    //Getters para contadores
    public int getServidos() {
        return servidos;
    }

    public int getAvailable() {
        return available;
    }
    
}