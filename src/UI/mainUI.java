/**
 *  The main UI for RFID access management application.
 */

package UI;

import com.phidgets.RFIDPhidget;
import com.phidgets.PhidgetException;
import java.awt.event.ItemEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import listeners.*;
import sqlite.*;

public class mainUI extends javax.swing.JFrame {

    private static String runArgs[];
    private RFIDPhidget rfid;
    private sqlite sql;
    private RFIDAttachListener attach_listener;
    private RFIDDetachListener detach_listener;
    private RFIDErrorListener error_listener;
    private RFIDTagGainListener tagGain_listener;
    private RFIDTagLossListener tagLoss_listener;
    
    /**
     * Creates new form mainUI
     */
    public mainUI() {
        initComponents();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tagDataPanel = new javax.swing.JPanel();
        tagDataField = new javax.swing.JTextField();
        managementPanel = new javax.swing.JPanel();
        tagAccessCheck = new javax.swing.JCheckBox();
        tagRegButton = new javax.swing.JButton();
        tagRemoveButton = new javax.swing.JButton();
        logPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tagDataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tag ID"));

        javax.swing.GroupLayout tagDataPanelLayout = new javax.swing.GroupLayout(tagDataPanel);
        tagDataPanel.setLayout(tagDataPanelLayout);
        tagDataPanelLayout.setHorizontalGroup(
            tagDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tagDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tagDataField, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );
        tagDataPanelLayout.setVerticalGroup(
            tagDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tagDataPanelLayout.createSequentialGroup()
                .addComponent(tagDataField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        managementPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tag Management"));

        tagAccessCheck.setText("Allow access");
        tagAccessCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mainUI.this.itemStateChanged(evt);
            }
        });

        tagRegButton.setText("Register tag");
        tagRegButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actionHandler(evt);
            }
        });

        tagRemoveButton.setText("Remove tag");
        tagRemoveButton.setMaximumSize(new java.awt.Dimension(101, 30));
        tagRemoveButton.setMinimumSize(new java.awt.Dimension(101, 30));
        tagRemoveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actionHandler(evt);
            }
        });

        javax.swing.GroupLayout managementPanelLayout = new javax.swing.GroupLayout(managementPanel);
        managementPanel.setLayout(managementPanelLayout);
        managementPanelLayout.setHorizontalGroup(
            managementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managementPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(managementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(managementPanelLayout.createSequentialGroup()
                        .addComponent(tagAccessCheck)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(managementPanelLayout.createSequentialGroup()
                        .addComponent(tagRegButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tagRemoveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        managementPanelLayout.setVerticalGroup(
            managementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managementPanelLayout.createSequentialGroup()
                .addGroup(managementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagRegButton)
                    .addComponent(tagRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(tagAccessCheck)
                .addGap(0, 37, Short.MAX_VALUE))
        );

        logPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Log"));

        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        jScrollPane1.setViewportView(logTextArea);

        javax.swing.GroupLayout logPanelLayout = new javax.swing.GroupLayout(logPanel);
        logPanel.setLayout(logPanelLayout);
        logPanelLayout.setHorizontalGroup(
            logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        logPanelLayout.setVerticalGroup(
            logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tagDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(managementPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tagDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(managementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_itemStateChanged
        Object src = evt.getItemSelectable();
        
        if( src == tagAccessCheck )
        {
            if( evt.getStateChange() == ItemEvent.SELECTED )
            {
                System.out.println("acc");
                sql.addTagAccess(tagDataField.getText());
            }
            if( evt.getStateChange() == ItemEvent.DESELECTED )
            {
                System.out.println("no acc");
                sql.removeTagAccess(tagDataField.getText());
            }
        }
    }//GEN-LAST:event_itemStateChanged

    private void actionHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actionHandler
        Object src = evt.getSource();
        if( src == tagRegButton )
        {
            sql.registerTag(tagDataField.getText());
        }
        if( src == tagRemoveButton )
        {
            sql.removeTag(tagDataField.getText());
        }
    }//GEN-LAST:event_actionHandler

    private void formWindowClosed(java.awt.event.WindowEvent evt) {
        try
        {
            rfid.removeTagLossListener(tagLoss_listener);
            rfid.removeTagGainListener(tagGain_listener);
            rfid.removeErrorListener(error_listener);
            rfid.removeDetachListener(detach_listener);
            rfid.removeAttachListener(attach_listener);
           
            rfid.close();
            sql.sqlite_close();
           
            rfid = null;
            
            dispose();
            System.exit(0);
        }
        catch(PhidgetException ex)
        {
            logTextArea.append("Phidget Error " + ex.getErrorNumber()+"\n");        
            dispose();
            System.exit(0);
        }
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        System.out.println("opening");
        
        try {
            sql = new sqlite();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            rfid = new RFIDPhidget();

            attach_listener = new RFIDAttachListener(this, this.logTextArea);
            detach_listener = new RFIDDetachListener(this, this.logTextArea);
            tagGain_listener = new RFIDTagGainListener(this.tagDataField, this.logTextArea, this.tagAccessCheck, this.sql);
            tagLoss_listener = new RFIDTagLossListener(this.tagDataField, this.logTextArea, this.tagAccessCheck);
            error_listener = new RFIDErrorListener(this);
            
            rfid.addAttachListener(attach_listener);
            rfid.addDetachListener(detach_listener);
            rfid.addErrorListener(error_listener);
            rfid.addTagGainListener(tagGain_listener);
            rfid.addTagLossListener(tagLoss_listener);
            
            rfid.openAny();
        }
        catch(PhidgetException ex)
        {
            logTextArea.append("Phidget Error " + ex.getErrorNumber()+"\n");        
        }
        
        logTextArea.setEditable(false);
        tagAccessCheck.setEnabled(false);

        sql.sqlite_open("access.sqlite.db");        
    }
    
    private void activateAntenna(boolean state) {                                           
        try
        {
            rfid.setAntennaOn(state);
        }
        catch (PhidgetException ex)
        {
            logTextArea.append("Phidget Error " + ex.getErrorNumber()+"\n");
        }
    } 
    
   private void activateLed(boolean state) {                                           
        try
        {
            rfid.setLEDOn(state);
        }
        catch (PhidgetException ex)
        {
            logTextArea.append("Phidget Error " + ex.getErrorNumber()+"\n");
        }
    } 
    
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
            java.util.logging.Logger.getLogger(mainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel logPanel;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JPanel managementPanel;
    private javax.swing.JCheckBox tagAccessCheck;
    private javax.swing.JTextField tagDataField;
    private javax.swing.JPanel tagDataPanel;
    private javax.swing.JButton tagRegButton;
    private javax.swing.JButton tagRemoveButton;
    // End of variables declaration//GEN-END:variables
}
