
package proyectosemaforo;

public class CocineroEntrada extends Cocinero{

    MesonEntrada e;
    private int delay;   //Tiempo de sleep
    private boolean exit;

    public CocineroEntrada(int hora, MesonEntrada e) {
        super(hora);
        this.e = e;
        
        delay = (int) (hora * 0.25); //Fraccion de hora de sleep
        exit = false;
    }
    
    @Override
    public void run(){
        while(exit==false){
            try{
                Thread.sleep(delay);   //0.25h
                e.ponerPlatillo(new Platillo("entrada")); //EL platillo a poner en parentesis
                System.out.println("Entrada colocada en su meson");
                
            }catch(InterruptedException ex){
                System.out.println("Runtime exception from thread");
            }
        }
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

}
