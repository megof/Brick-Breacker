/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final;

import brickBreaker.Ventana;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Megof
 */
public class menu extends javax.swing.JFrame {

    String Jugador;
    /**
     * Creates new form menu
     */
    public menu() {
        Jugador = Jugadores.Jugador;
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnCrear = new javax.swing.JButton();
        BtnPuntuaciones = new javax.swing.JButton();
        BtnJugadores = new javax.swing.JButton();
        BtnJugar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnCrear.setText("crear");
        BtnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCrearActionPerformed(evt);
            }
        });

        BtnPuntuaciones.setText("puntuaciones");
        BtnPuntuaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPuntuacionesActionPerformed(evt);
            }
        });

        BtnJugadores.setText("jugadores");
        BtnJugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJugadoresActionPerformed(evt);
            }
        });

        BtnJugar.setText("Jugar");
        BtnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJugarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnCrear)
                    .addComponent(BtnJugadores)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnPuntuaciones)
                        .addGap(42, 42, 42)
                        .addComponent(BtnJugar)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(BtnCrear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnPuntuaciones)
                    .addComponent(BtnJugar))
                .addGap(18, 18, 18)
                .addComponent(BtnJugadores)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCrearActionPerformed
        this.dispose();
        Crud m = new Crud();
// TODO add your handling code here:
    }//GEN-LAST:event_BtnCrearActionPerformed

    private void BtnPuntuacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPuntuacionesActionPerformed
        // TODO add your handling code here:
        Puntuaciones m = new Puntuaciones();
        this.dispose();
    }//GEN-LAST:event_BtnPuntuacionesActionPerformed

    private void BtnJugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJugadoresActionPerformed
        Jugadores m = new Jugadores();
        this.dispose();
    }//GEN-LAST:event_BtnJugadoresActionPerformed

    private void BtnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJugarActionPerformed
        if(Jugador==""){
            JOptionPane.showMessageDialog(null, "Por favor seleccione un jugador");
        }else{
            this.dispose();
            Ventana v = new Ventana();
            v.main(null);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnJugarActionPerformed

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
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCrear;
    private javax.swing.JButton BtnJugadores;
    private javax.swing.JButton BtnJugar;
    private javax.swing.JButton BtnPuntuaciones;
    // End of variables declaration//GEN-END:variables
}
