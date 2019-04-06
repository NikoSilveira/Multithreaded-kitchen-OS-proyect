
package proyectosemaforo;

import java.util.concurrent.Semaphore;

public class JefeMesoneros extends Thread{
    
    private Semaphore raceSemaphore;
    private int delay, hora;
    private static int cronometrador;
    private String estado;

    public JefeMesoneros(Semaphore raceSemaphore, int hora) {
        this.raceSemaphore = raceSemaphore;
        this.hora = hora;
        delay = (int) (hora * 0.05); //Fraccion de hora de sleep
        
        cronometrador = 8;
        estado = "Descansando";
    }


    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(hora);
                raceSemaphore.acquire();
                
                //Escritura
                System.out.println("Escribiendo");
                estado = "Escribiendo";
                Thread.sleep(delay);
                
                if(cronometrador!=0){
                    cronometrador--;    //bajar una hora al cronometrador
                }
                
                raceSemaphore.release();
                
                estado = "Descansando";
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static int getCronometrador() {
        return cronometrador;
    }
    
    public static void resetCronometrador() {
        cronometrador = 8;
    }
    
    public String getEstado() {
        return estado;
    }
    
    
    /*private double contadorTiempoCierre;
    
    //Constructor
    public JefeMesoneros(){
        this.contadorTiempoCierre = 0.15;
    }
    
    //Getters & Setters

    public double getContadorTiempoCierre() {
        return contadorTiempoCierre;
    }

    public void setContadorTiempoCierre(double contadorTiempoCierre) {
        this.contadorTiempoCierre = contadorTiempoCierre;
    }
    
    //Reiniciar el contador de tiempo a 0.15
    private void reiniciarTiempo(){
        if(contadorTiempoCierre == 0){
            contadorTiempoCierre = 0.15;
        }
    }*/

    

    
}
