
package proyectosemaforo;

public class CocineroFuerte extends Cocinero{

    MesonFuerte f;
    private int delay;   //Tiempo de sleep
    private boolean exit;
    
    public CocineroFuerte(int hora, MesonFuerte f) {
        super(hora);
        this.f = f;
        
        delay = (int) (hora * 0.33); //Fraccion de hora de sleep
        exit = false;
    }

    @Override
    public void run(){
        while(exit==false){
            try{
                Thread.sleep(delay);   //0.33h
                f.ponerPlatillo(new Platillo("Fuerte")); //El platillo a poner en parentesis
                System.out.println("Fuerte colocado en su meson");
                
            }catch(InterruptedException ex){
               System.out.println("Runtime exception from thread");
            }
        }
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
