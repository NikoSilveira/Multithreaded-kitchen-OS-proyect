package proyectosemaforo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Archivo {
	private int hora;
	private int maxMesonE;
	private int maxMesonF;
	private int maxMesonP;
	private int initCocinerosE;
	private int initCocinerosF;
	private int initCocinerosP;
	private int maxCocinerosE;
	private int maxCocinerosP;
	private int maxCocinerosF;
        private int initMesoneros;
	private int maxMesoneros;

	public Archivo(String archivo){
        try{
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            
            String linea;
            
            //Definciion de tiempo
            linea = br.readLine();
            this.hora = Integer.parseInt(linea);
            
            //Maximo de mesones
            linea = br.readLine();
            this.maxMesonE = Integer.parseInt(linea);
            linea = br.readLine();
            this.maxMesonF = Integer.parseInt(linea);
            linea = br.readLine();
            this.maxMesonP = Integer.parseInt(linea);
            
            //Cantidad inicial cocineros
            linea = br.readLine();
            this.initCocinerosE = Integer.parseInt(linea);
            linea = br.readLine();
            this.initCocinerosF = Integer.parseInt(linea);
            linea = br.readLine();
            this.initCocinerosP = Integer.parseInt(linea);
            
            //Cantidad maxima cocineros
            linea = br.readLine();
            this.maxCocinerosE = Integer.parseInt(linea);
            linea = br.readLine();
            this.maxCocinerosF = Integer.parseInt(linea);
            linea = br.readLine();
            this.maxCocinerosP = Integer.parseInt(linea);
            
            //Inicial mesoneros
            linea = br.readLine();
            this.initMesoneros = Integer.parseInt(linea);
            
            //Maximo mesoneros
            linea = br.readLine();
            this.maxMesoneros = Integer.parseInt(linea);
        }
        catch(IOException e){
            System.out.println("ERROR");
        } 
    }

    public int getHora() {
        return hora;
    }

    public int getMaxMesonE() {
        return maxMesonE;
    }

    public int getMaxMesonF() {
        return maxMesonF;
    }

    public int getMaxMesonP() {
        return maxMesonP;
    }

    public int getInitCocinerosE() {
        return initCocinerosE;
    }

    public int getInitCocinerosF() {
        return initCocinerosF;
    }

    public int getInitCocinerosP() {
        return initCocinerosP;
    }

    public int getMaxCocinerosE() {
        return maxCocinerosE;
    }

    public int getMaxCocinerosP() {
        return maxCocinerosP;
    }

    public int getMaxCocinerosF() {
        return maxCocinerosF;
    }

    public int getInitMesoneros() {
        return initMesoneros;
    }

    public int getMaxMesoneros() {
        return maxMesoneros;
    }

}