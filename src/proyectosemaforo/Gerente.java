
package proyectosemaforo;

import java.util.concurrent.Semaphore;

public class Gerente extends Thread{
    private Semaphore raceSemaphore;
    private int delay, delay2, hora;
    double random;
    private String estado;
    
    public Gerente(Semaphore raceSemaphore, int hora) {
        this.raceSemaphore = raceSemaphore;      
        this.hora = hora;
        
        estado = "Descansando";
        delay2 = (int) (hora * 0.10); //Fraccion de hora de sleep
    }

    @Override
    public void run() {
        while (true) {
            try {
                random = (Math.random()*156)+45;
                random = random / 100;
                delay = (int) (hora * random);
                Thread.sleep(delay);
                raceSemaphore.acquire();
                
                //Lectura
                System.out.println("Leyendo");
                estado = "Leyendo";
                System.out.println("TEST "+estado);
                if(JefeMesoneros.getCronometrador()==0){
                    Thread.sleep(delay2);
                    JefeMesoneros.resetCronometrador(); //reset timer
                    Restaurante.resetOrden();           //reset ordenes servidas
                }
                
                raceSemaphore.release();
                estado = "Descansando";
                
            } catch (InterruptedException ex ) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String getEstado() {
        return estado;
    }
}
