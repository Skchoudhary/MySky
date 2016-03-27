import javax.swing.*;
public class ForgotPassword extends javax.swing.JFrame
{
    String email;

    public ForgotPassword()
    {
        initComponents();
        setSize(600,700);
        setVisible(true);
        lbSecurityQuestion.enable(false);
        lbNewPassword.enable(false);
        lbConfirmPassword.enable(false);
        tfSecurityQuestion.enable(false);
        tfNewPassword.enable(false);
        tfConfirmPassword.enable(false);
                
        
                
    }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lbSecurityQuestion = new javax.swing.JLabel();
        lbQuestion = new javax.swing.JLabel();
        btCheck = new javax.swing.JButton();
        tfSecurityQuestion = new javax.swing.JTextField();
        lbNewPassword = new javax.swing.JLabel();
        tfNewPassword = new javax.swing.JPasswordField();
        lbConfirmPassword = new javax.swing.JLabel();
        tfConfirmPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("FORGOT PASSWORD!");

        lbEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbEmail.setText("E_MAIL");

        tfEmail.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tfEmailActionPerformed(evt);
            }
        });

        lbSecurityQuestion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbSecurityQuestion.setText("SECURITY_QUESTION:");

        btCheck.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCheck.setText("CHECK");
        btCheck.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btCheckActionPerformed(evt);
            }
        });

        tfSecurityQuestion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tfSecurityQuestionActionPerformed(evt);
            }
        });

        lbNewPassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbNewPassword.setText("NEW_PASSWORD");

        lbConfirmPassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbConfirmPassword.setText("CONFIRM PASSWORD");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbSecurityQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSecurityQuestion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNewPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfConfirmPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(btCheck)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addComponent(btCheck)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbSecurityQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfSecurityQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfSecurityQuestionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_tfSecurityQuestionActionPerformed
    {//GEN-HEADEREND:event_tfSecurityQuestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSecurityQuestionActionPerformed

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_tfEmailActionPerformed
    {//GEN-HEADEREND:event_tfEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailActionPerformed

    private void btCheckActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btCheckActionPerformed
    {//GEN-HEADEREND:event_btCheckActionPerformed
        email = tfEmail.getText().trim();
            if (email.isEmpty()==true)
            {
                JOptionPane.showMessageDialog(this, "PLEASE FILL THE E_MAIL FIELD");

            }
            
           else 
                
            {
              /*lbSecurityQuestion.setEnabled(true);
                lbNewPassword.setEnabled(true);
                lbConfirmPassword.setEnabled(true);
                tfSecurityQuestion.setEnabled(true);
                tfNewPassword.setEnabled(true);
                tfConfirmPassword.setEnabled(true);*/
            }
    }//GEN-LAST:event_btCheckActionPerformed

    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ForgotPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbConfirmPassword;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbNewPassword;
    private javax.swing.JLabel lbQuestion;
    private javax.swing.JLabel lbSecurityQuestion;
    private javax.swing.JPasswordField tfConfirmPassword;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JPasswordField tfNewPassword;
    private javax.swing.JTextField tfSecurityQuestion;
    // End of variables declaration//GEN-END:variables
}
