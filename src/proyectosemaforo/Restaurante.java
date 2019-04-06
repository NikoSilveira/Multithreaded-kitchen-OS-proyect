
package proyectosemaforo;

import java.util.concurrent.Semaphore;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Restaurante extends javax.swing.JFrame{
    
    private static int ordenesServidas;
    
    //R/W
    Semaphore raceSemaphore;
    JefeMesoneros jefe;
    Gerente gerente;

    //Cantidades de cada empleado
    private int cantMesoneros, cantCocinerosE, cantCocinerosF, cantCocinerosP;
    
    //Archivo con datos iniciales
    private Archivo archivo;
    
    //Buffers
    private MesonEntrada bufferE;  //Meson entrada - 20 puestos, mesonero toma 3 platillos
    private MesonFuerte bufferF;   //Meson fuertes - 30 puestos, mesonero toma 2 platillos
    private MesonPostre bufferP;   //Meson postres - 10 puestos, mesonero toma 1 platillo
    
    //Productores
    private CocineroEntrada[] cocinerosEntrada;
    private CocineroFuerte[] cocinerosFuerte;
    private CocineroPostre[] cocinerosPostre;
    
    //Consumidores
    private Mesonero[] mesoneros;

    //Constructor
    public Restaurante() {
        initComponents();
        jButton8.setEnabled(false);
        
        ordenesServidas = 0;
        
        archivo = new Archivo("parametros.txt");
        
        //Lectura escritura
        raceSemaphore = new Semaphore(1);
        jefe = new JefeMesoneros(raceSemaphore, archivo.getHora());
        try {
            gerente = new Gerente(raceSemaphore, archivo.getHora());
            gerente.start();
            
            Thread.sleep(200);
            jefe.start();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        
        //Inicializar auxiliares
        cantMesoneros = archivo.getInitMesoneros();
        cantCocinerosE = archivo.getInitCocinerosE();
        cantCocinerosF = archivo.getInitCocinerosF();
        cantCocinerosP = archivo.getInitCocinerosP();
                
        //Inicializar buffers
        bufferE = new MesonEntrada(archivo.getMaxMesonE(), 3);
        bufferF = new MesonFuerte(archivo.getMaxMesonF(), 2);
        bufferP = new MesonPostre(archivo.getMaxMesonP(), 1);
        
        //Inicializar array productores
        cocinerosEntrada = new CocineroEntrada[archivo.getMaxCocinerosE()];
        cocinerosFuerte = new CocineroFuerte[archivo.getMaxCocinerosF()];
        cocinerosPostre = new CocineroPostre[archivo.getMaxCocinerosP()];
        
        //Inicilizar array consumidores
        mesoneros = new Mesonero[archivo.getMaxMesoneros()];
        
        //Crear consumidores iniciales
        for(int i=0; i<archivo.getInitMesoneros(); i++){
            mesoneros[i] = new Mesonero(bufferE, bufferF, bufferP, archivo.getHora());
        }
        
        //Crear productores iniciales
        for(int i=0; i<archivo.getInitCocinerosE(); i++){ //Entrada
            cocinerosEntrada[i] = new CocineroEntrada(archivo.getHora(),bufferE);
        }
        for(int i=0; i<archivo.getInitCocinerosF(); i++){ //Fuerte
            cocinerosFuerte[i] = new CocineroFuerte(archivo.getHora(),bufferF);
        }
        for(int i=0; i<archivo.getInitCocinerosP(); i++){ //Postre
            cocinerosPostre[i] = new CocineroPostre(archivo.getHora(),bufferP);
        }
        
        //GUI display config
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);
        jTextField6.setEditable(false);
        jTextField7.setEditable(false);
        jTextField8.setEditable(false);
        jTextField9.setEditable(false);
        jTextField10.setEditable(false);
        jTextField11.setEditable(false);
        

        
        
        //INICIO DE LOS THREADS
        
        //Iniciar corrida de los productores iniciales
        for(int i=0; i<archivo.getInitCocinerosE(); i++){
            cocinerosEntrada[i].start();
        }
        for(int i=0; i<archivo.getInitCocinerosF(); i++){
            cocinerosFuerte[i].start();
        }
        for(int i=0; i<archivo.getInitCocinerosP(); i++){
            cocinerosPostre[i].start();
        }
        
        //Iniciar corrida de los consumidores iniciales
        for(int i=0; i<archivo.getInitMesoneros(); i++){
            mesoneros[i].start();
        }
        
        //Actualizar interfaz gráfica
        Timer timer = new Timer(1, (ActionEvent e) -> { 
             jTextField1.setText(Integer.toString(cantCocinerosE));
             jTextField2.setText(Integer.toString(cantCocinerosF));
             jTextField3.setText(Integer.toString(cantCocinerosP));
             jTextField7.setText(Integer.toString(cantMesoneros));
             jTextField8.setText(Integer.toString(ordenesServidas));
             jTextField4.setText(Integer.toString(bufferE.getAvailable()));
             jTextField5.setText(Integer.toString(bufferF.getAvailable()));
             jTextField6.setText(Integer.toString(bufferP.getAvailable()));
             jTextField9.setText(jefe.getEstado());
             jTextField11.setText(Integer.toString(jefe.getCronometrador())+" hora(s)");
             jTextField10.setText(gerente.getEstado());
        });
        timer.start();
        
    }
    
    public static void servirOrden(){   //set de platillos servidos por mesonero
        ordenesServidas++;
    }
    
    public static void resetOrden(){    //Se acabo el dia, platillos servidos a 0
        ordenesServidas = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cocineros de entradas:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 190, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cocineros de platos fuertes:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cocineros de postres:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Entradas:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Platos fuertes:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 176, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Postres:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mesoneros:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Órdenes atendidas:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 282, -1, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Horas que faltan para terminar:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("El jefe de mesoneros está:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 318, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("El gerente está:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, 30));

        jButton1.setText("Contratar mesonero");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 230, -1));

        jButton2.setText("Despedir mesonero");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 400, 230, -1));

        jButton3.setText("Contratar cocinero de entradas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 230, -1));

        jButton4.setText("Contratar cocinero de platos fuertes");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 230, -1));

        jButton5.setText("Contratar cocinero de postres");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 230, -1));

        jButton6.setText("Despedir cocinero de entradas");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 230, -1));

        jButton7.setText("Despedir cocinero de platos fuertes");
        jButton7.setToolTipText("");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 230, -1));

        jButton8.setText("Despedir cocinero de postres");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 230, -1));

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 35, 30));

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("jTextField2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 35, 30));

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("jTextField3");
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 35, 30));

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("jTextField4");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 35, 30));

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("jTextField5");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 35, 30));

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("jTextField6");
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 35, 30));

        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText("jTextField7");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 35, 30));

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("jTextField8");
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 35, 30));

        jTextField9.setText("jTextField9");
        getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 90, 30));

        jTextField10.setText("jTextField10");
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 349, -1, 30));

        jTextField11.setFont(new java.awt.Font("Dialog", 0, 40)); // NOI18N
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setText("jTextField11");
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 180, 110));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectosemaforo/restaurant-bg.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1450, -460, 2440, 1170));

        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Contratar mesonero
        if(cantMesoneros<archivo.getMaxMesoneros()){
            mesoneros[cantMesoneros] = new Mesonero(bufferE, bufferF, bufferP, archivo.getHora());
            mesoneros[cantMesoneros].start();
            cantMesoneros++;
            if(cantMesoneros == archivo.getMaxMesoneros()){
                jButton1.setEnabled(false);
            }
        }
        jButton2.setEnabled(true);
        jTextField7.setText(Integer.toString(cantMesoneros));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //Despedir cocinero de entradas
        if(cantCocinerosE>0){
            cocinerosEntrada[cantCocinerosE-1].setExit(true);
            cocinerosEntrada[cantCocinerosE-1] = null;
            cantCocinerosE--;
            if(cantCocinerosE == 0){
                jButton6.setEnabled(false);
            }
        }
        jButton3.setEnabled(true);
        jTextField1.setText(Integer.toString(cantCocinerosE));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        //Cocineros de entradas
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Contratar cocinero entrada
        if(cantCocinerosE<archivo.getMaxCocinerosE()){
            cocinerosEntrada[cantCocinerosE] = new CocineroEntrada(archivo.getHora(),bufferE);
            cocinerosEntrada[cantCocinerosE].start();
            cantCocinerosE++;
            if(cantCocinerosE == archivo.getMaxCocinerosE()){
                jButton3.setEnabled(false);
            }
        }
        jButton6.setEnabled(true);
        jTextField1.setText(Integer.toString(cantCocinerosE));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //Contratar cocineros fuerte
        if(cantCocinerosF<archivo.getMaxCocinerosF()){
            cocinerosFuerte[cantCocinerosF] = new CocineroFuerte(archivo.getHora(),bufferF);
            cocinerosFuerte[cantCocinerosF].start();
            cantCocinerosF++;
            if(cantCocinerosF == archivo.getMaxCocinerosF()){
                jButton4.setEnabled(false);
            }
        }
        jButton7.setEnabled(true);
        jTextField2.setText(Integer.toString(cantCocinerosF));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Contratar cocinero postres
        if(cantCocinerosP<archivo.getMaxCocinerosP()){
            cocinerosPostre[cantCocinerosP] = new CocineroPostre(archivo.getHora(),bufferP);
            cocinerosPostre[cantCocinerosP].start();
            cantCocinerosP++;
            if(cantCocinerosP == archivo.getMaxCocinerosP()){
                jButton5.setEnabled(false);
            }
        }
        jButton8.setEnabled(true);
        jTextField3.setText(Integer.toString(cantCocinerosP));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //Despedir cocinero platos fuertes
        if(cantCocinerosF>0){
            cocinerosFuerte[cantCocinerosF-1].setExit(true);
            cocinerosFuerte[cantCocinerosF-1] = null;
            cantCocinerosF--;
            if(cantCocinerosF == 0){
                jButton7.setEnabled(false);
            }
        }
        jButton4.setEnabled(true);
        jTextField2.setText(Integer.toString(cantCocinerosF));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //Despedir cocinero postres
        if(cantCocinerosP>0){
            cocinerosPostre[cantCocinerosP-1].setExit(true);
            cocinerosPostre[cantCocinerosP-1] = null;
            cantCocinerosP--;
            if(cantCocinerosP == 0){
                jButton8.setEnabled(false);
            }
        }
        jButton5.setEnabled(true);
        jTextField3.setText(Integer.toString(cantCocinerosP));
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Despedir mesonero
        if(cantMesoneros>0){
            mesoneros[cantMesoneros-1].setExit(true); //Parar thread
            mesoneros[cantMesoneros-1] = null;
            cantMesoneros--;
            if(cantMesoneros == 0){
                jButton2.setEnabled(false);
            }
        }
        jButton1.setEnabled(true);
        jTextField7.setText(Integer.toString(cantMesoneros));
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Restaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Restaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Restaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Restaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form /
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Restaurante().setVisible(true);
            }
        });*/
        
        Restaurante rest = new Restaurante();
        rest.setVisible(true);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
