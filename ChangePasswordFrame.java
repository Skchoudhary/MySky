

public class ChangePasswordFrame extends javax.swing.JFrame
{
 ChangePasswordFrame()
    {
        initComponents();
        setVisible(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        tfCpassword = new javax.swing.JPasswordField();
        ConfirmPassword = new javax.swing.JPasswordField();
        tfNpassword = new javax.swing.JPasswordField();
        btSubmit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Current Password");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 80, 186, 29);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Email");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(21, 23, 186, 29);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Confirm Password");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(21, 194, 186, 29);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("New Password");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(21, 133, 186, 29);
        getContentPane().add(tfEmail);
        tfEmail.setBounds(250, 20, 263, 33);
        getContentPane().add(tfCpassword);
        tfCpassword.setBounds(250, 80, 263, 33);
        getContentPane().add(ConfirmPassword);
        ConfirmPassword.setBounds(248, 195, 263, 33);
        getContentPane().add(tfNpassword);
        tfNpassword.setBounds(248, 134, 263, 33);

        btSubmit.setBackground(new java.awt.Color(204, 255, 255));
        btSubmit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shield.png"))); // NOI18N
        btSubmit.setText("Submit");
        btSubmit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(btSubmit);
        btSubmit.setBounds(250, 260, 137, 30);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/2.jpg"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 530, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSubmitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btSubmitActionPerformed
    {//GEN-HEADEREND:event_btSubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSubmitActionPerformed


    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ChangePasswordFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ChangePasswordFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ChangePasswordFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ChangePasswordFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ChangePasswordFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ConfirmPassword;
    private javax.swing.JButton btSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField tfCpassword;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JPasswordField tfNpassword;
    // End of variables declaration//GEN-END:variables
}
