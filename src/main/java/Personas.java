import Conexiones.MySqlConnector; //Importe de las librerias                    
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Personas extends javax.swing.JFrame { //public class Personas

    MySqlConnector conector = new MySqlConnector(); //Creación de la variable conector
    Connection conexion = conector.conectar(); //Creación de la Conexion (Connection)
    
    public Personas() { //JFrame que permitira mostrar los Datos
        initComponents();
        mostrarDatos();
    }
    
    private void Limpiar(){ //Función Limpiar, donde se pondrá texto vacío en cada una de las casillas donde se ingresarán datos
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }
    
    private void mostrarDatos(){ //Función mostrarDatos que permitirá mostrar los datos en la tabla 
        try{
            String[] titulos = {"ID", "Nombre", "Apellido", "Dirección", "Teléfono"}; //Creación de los títulos para la tabla
            String[] registros = new String[5]; //Creación del String registros (tamaño 5)
            DefaultTableModel model = new DefaultTableModel(null, titulos); //Obtención de los datos de "titulos" para colocarlos como titulos de la tabla
            String consulta = "SELECT * FROM `personas`"; 
            Statement st = conexion.createStatement(); //Creación de la conexion 
            
            ResultSet result = st.executeQuery(consulta);
            
            while(result.next()){ //While verificará e ingresará datos cada vez que se guarde un dato en la base de datos
                registros[0] = result.getString("ID");
                registros[1] = result.getString("Nombre");
                registros[2] = result.getString("Apellido");
                registros[3] = result.getString("Dirección");
                registros[4] = result.getString("Teléfono");
                model.addRow(registros);
            }
            
            tblPersona.setModel(model); //tblPersona obtiene la variable model
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al mostrar datos");
        }
    }
    
    private void guardarPersona(){ //Función que permitirá guardar la persona en la base de datos
        try {
            Persona nuevaPersona = new Persona(); //Creación de la variable nuevaPersona
            nuevaPersona.setNombre(txtNombre.getText().toString()); //Obtención de todos los valores de las casillas al momento de hacer clic en el botón "GUARDAR"
            nuevaPersona.setApellido(txtApellido.getText().toString());
            nuevaPersona.setDireccion(txtDireccion.getText().toString());
            nuevaPersona.setTelefono(txtTelefono.getText().toString());
            
            String insert = "INSERT INTO `personas`(`Nombre`, `Apellido`, `Dirección`, `Teléfono`) " //Insert de los datos en las casillas correspondientes a la base de datos
                    + "VALUES (?, ?, ?, ?)";
            
            PreparedStatement pst = conexion.prepareStatement(insert); //Ingreso de cada uno de los datos por medio del PreparedStatement a la base de datos
            pst.setString(1, nuevaPersona.getNombre());
            pst.setString(2, nuevaPersona.getApellido());
            pst.setString(3, nuevaPersona.getDireccion());
            pst.setString(4, nuevaPersona.getTelefono());
            
            pst.execute(); //Ejecución del pst que enviará los datos a la base de datos

            JOptionPane.showMessageDialog(null, "Guardado exitosamente");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al guardar");
        }
        
    }
    
    private void eliminarPersona(){ //Función que nos permitirá eliminar a una persona seleccionandola desde la tabla
        try {
            String delete = "DELETE FROM `personas` WHERE `personas`.`ID` = ?"; //Seleccion que tomará como base el ID de la persona para eliminarlo de la tabla y de la base de datos
            int fila = tblPersona.getSelectedRow(); //Creación de la variable fila y se le agrega el valor de la columna seleccionada en la tabla
            String id = tblPersona.getValueAt(fila, 0).toString(); //Obtención de el id por medio del valor de la fila y columna 0
            PreparedStatement pst = conexion.prepareStatement(delete); //PreparedStatement que permitirá eliminar la posición anteriormente elegida
            pst.setInt(1, Integer.parseInt(id));
            
            pst.execute(); //Ejecución del pst que eliminará los datos a la base de datos
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar");
        }
    }
    
    private void modificarPersona(){ //Función que nos permitirá modificar a una persona dentro de la tabla y base de datos
        try {
            Persona nuevaPersona = new Persona(); //Creación de la variable nuevaPersona
            nuevaPersona.setNombre(txtNombre.getText().toString()); //Obtención de cada uno de los datos de la casilla clicada en la tabla
            nuevaPersona.setApellido(txtApellido.getText().toString());
            nuevaPersona.setDireccion(txtDireccion.getText().toString());
            nuevaPersona.setTelefono(txtTelefono.getText().toString());            
            
            int fila =  tblPersona.getSelectedRow(); //Creación de la variable fila al momento de seleccionar un dato en la tabla
            String id = tblPersona.getValueAt(fila, 0).toString();
            String insert = "UPDATE `personas` SET Nombre = ?, Apellido = ?, Dirección = ?, Teléfono = ? WHERE ID = ?"; //Actualización (UPDATE) de el dato tanto en la base de datos como en la tabla 
              
            
            PreparedStatement pst = conexion.prepareStatement(insert); //Insert que agregará los nuevos datos a cada una de las casillas de la tabla y de la base de datos
            pst.setString(1, nuevaPersona.getNombre());
            pst.setString(2, nuevaPersona.getApellido());
            pst.setString(3, nuevaPersona.getDireccion());
            pst.setString(4, nuevaPersona.getTelefono());
            pst.setString(5, id);
            
            pst.execute(); //Ejecución del pst

            JOptionPane.showMessageDialog(null, "Actualizado exitosamente");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al actualizar");
        }      
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPersona = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel2.setText("Teléfono:");

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel3.setText("Apellido:");

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel4.setText("Dirección:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        tblPersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPersonaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPersona);

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 102, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("LIMPIAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(149, 149, 149))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed

    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed

    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
      
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
      
    }//GEN-LAST:event_txtTelefonoActionPerformed

    //Boton 2 que realizará la función de "modificarPersona y mostrarDatos"
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        modificarPersona();
        mostrarDatos();
    }//GEN-LAST:event_jButton2ActionPerformed

    //Boton 4 que realizará la función de Limpiar nuestras casillas
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Limpiar();
    }//GEN-LAST:event_jButton4ActionPerformed

    //Boton 1 que realizará la función de "guardarPersona y mostrarDatos"
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardarPersona();
        mostrarDatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    //Función que tomará los datos de donde cliceemos en nuestra tabla
    private void tblPersonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPersonaMouseClicked
        int fila = tblPersona.rowAtPoint(evt.getPoint());
        txtNombre.setText(tblPersona.getValueAt(fila, 1).toString());
        txtApellido.setText(tblPersona.getValueAt(fila, 2).toString());
        txtDireccion.setText(tblPersona.getValueAt(fila, 3).toString());
        txtTelefono.setText(tblPersona.getValueAt(fila, 4).toString());  
    }//GEN-LAST:event_tblPersonaMouseClicked
   
    //Boton 3 que realizará la función de "eliminarPersona y mostrarDatos"
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        eliminarPersona();
        mostrarDatos();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Personas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPersona;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}