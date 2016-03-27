
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UserInfo extends JFrame
{

    int length;

    public UserInfo()
    {
        initComponents();
        setSize(600, 550);
        setVisible(true);
        setLayout(null);
      
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lb3 = new javax.swing.JLabel();
        lb1 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        lb5 = new javax.swing.JLabel();
        lb6 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tx2 = new javax.swing.JTextField();
        tx3 = new javax.swing.JTextField();
        tx4 = new javax.swing.JTextField();
        tx1 = new javax.swing.JTextField();
        ps1 = new javax.swing.JPasswordField();
        tx5 = new javax.swing.JTextField();
        cb1 = new javax.swing.JComboBox();
        pw2 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lb3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb3.setForeground(new java.awt.Color(255, 255, 255));
        lb3.setText("E-MAIL");
        getContentPane().add(lb3);
        lb3.setBounds(20, 125, 200, 30);

        lb1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb1.setForeground(new java.awt.Color(255, 255, 255));
        lb1.setText("NAME");
        getContentPane().add(lb1);
        lb1.setBounds(20, 25, 200, 30);

        lb4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb4.setForeground(new java.awt.Color(255, 255, 255));
        lb4.setText("PASSWORD");
        getContentPane().add(lb4);
        lb4.setBounds(20, 175, 200, 30);

        lb5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb5.setForeground(new java.awt.Color(255, 255, 255));
        lb5.setText("PHONE-NUMBER");
        getContentPane().add(lb5);
        lb5.setBounds(20, 275, 200, 30);

        lb6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb6.setForeground(new java.awt.Color(255, 255, 255));
        lb6.setText("SECURITY QUESTION");
        getContentPane().add(lb6);
        lb6.setBounds(20, 325, 200, 30);

        lb2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb2.setForeground(new java.awt.Color(255, 255, 255));
        lb2.setText("LAST NAME");
        getContentPane().add(lb2);
        lb2.setBounds(20, 75, 200, 30);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(521, 356, 0, 0);
        getContentPane().add(tx2);
        tx2.setBounds(315, 77, 250, 30);
        getContentPane().add(tx3);
        tx3.setBounds(315, 127, 250, 30);
        getContentPane().add(tx4);
        tx4.setBounds(315, 277, 250, 30);
        getContentPane().add(tx1);
        tx1.setBounds(315, 27, 250, 30);
        getContentPane().add(ps1);
        ps1.setBounds(315, 177, 250, 30);
        getContentPane().add(tx5);
        tx5.setBounds(315, 362, 250, 30);

        cb1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Which city you belong?", "Your favourite color?", "Two last digits of your phone no.?", "Your hobby?" }));
        getContentPane().add(cb1);
        cb1.setBounds(20, 362, 245, 30);
        getContentPane().add(pw2);
        pw2.setBounds(315, 227, 250, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CONFIRM PASSWORD");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 225, 200, 30);

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shield.png"))); // NOI18N
        jButton1.setText("SUBMIT");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(320, 440, 200, 40);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/2.jpg"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 610, 520);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[])
    {

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new UserInfo().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cb1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb6;
    private javax.swing.JPasswordField ps1;
    private javax.swing.JPasswordField pw2;
    private javax.swing.JTextField tx1;
    private javax.swing.JTextField tx2;
    private javax.swing.JTextField tx3;
    private javax.swing.JTextField tx4;
    private javax.swing.JTextField tx5;
    // End of variables declaration//GEN-END:variables
}
