
package proyectosemaforo;

public class CocineroPostre extends Cocinero{

    MesonPostre p;
    private int delay;   //Tiempo de sleep
    private boolean exit;
    
    public CocineroPostre(int hora, MesonPostre p) {
        super(hora);
        this.p = p;
        
        delay = (int) (hora * 0.30); //Fraccion de hora de sleep
        exit = false;
    }
   
    @Override
    public void run(){
        while(exit==false){
            try{
                Thread.sleep(delay);   //0.30h
                p.ponerPlatillo(new Platillo("Postre")); //EL platillo a poner en parentesis
                System.out.println("Postre colocado en su meson");
                
            }catch(InterruptedException ex){
               System.out.println("Runtime exception from thread");
            }
        }
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
