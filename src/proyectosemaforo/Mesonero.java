
package proyectosemaforo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mesonero extends Thread{
    private boolean exit;
    private MesonEntrada e;
    private MesonFuerte f;
    private MesonPostre p;
    private int delay;      //Tiempo de sleep
    

    //Constructor
    public Mesonero(MesonEntrada e, MesonFuerte f, MesonPostre p, int hora) {
        exit = false;
        this.e = e;
        this.f = f;
        this.p = p;
        
        delay = (int) (hora * 0.15); //Fraccion de hora de sleep
    }
    
    @Override
    public void run(){
        
        while(exit==false){
            try {
                Thread.sleep(delay);      //0.15h
                e.tomarPlatillo(); //Meson ya lo debe hacer tomar 3
                System.out.println("3 entradas tomadas");
                f.tomarPlatillo(); //Meson ya lo debe hacer tomar 2
                System.out.println("2 fuertes tomados");
                p.tomarPlatillo(); //Meson ya lo debe hacer tomar 1
                System.out.println("1 postre tomado");
                
                Restaurante.servirOrden();
            } catch (InterruptedException ex) {
                Logger.getLogger(Mesonero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
