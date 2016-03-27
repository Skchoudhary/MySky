
public class ForgotPasswordFrame extends javax.swing.JFrame
{

    /**
     * Creates new form ForgotPasswordFrame
     */
    public ForgotPasswordFrame()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lEmail = new javax.swing.JLabel();
        btCheck = new javax.swing.JButton();
        lsecurityuestion = new javax.swing.JLabel();
        lEmail2 = new javax.swing.JLabel();
        lEmail3 = new javax.swing.JLabel();
        tfSecurityQuestion = new javax.swing.JTextField();
        tfEmail3 = new javax.swing.JTextField();
        lquestion = new javax.swing.JLabel();
        btSubmit = new javax.swing.JButton();
        tfNewPassword = new javax.swing.JPasswordField();
        tfConfirmPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lEmail.setForeground(new java.awt.Color(255, 255, 255));
        lEmail.setText("EMAIL");
        getContentPane().add(lEmail);
        lEmail.setBounds(40, 40, 177, 33);

        btCheck.setBackground(new java.awt.Color(204, 255, 255));
        btCheck.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow-round.png"))); // NOI18N
        btCheck.setText("CHECK");
        btCheck.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btCheckActionPerformed(evt);
            }
        });
        getContentPane().add(btCheck);
        btCheck.setBounds(260, 110, 140, 41);

        lsecurityuestion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lsecurityuestion.setForeground(new java.awt.Color(255, 255, 255));
        lsecurityuestion.setText("SECURITY QUESTION");
        lsecurityuestion.setEnabled(false);
        getContentPane().add(lsecurityuestion);
        lsecurityuestion.setBounds(30, 190, 180, 33);

        lEmail2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lEmail2.setForeground(new java.awt.Color(255, 255, 255));
        lEmail2.setText("NEW PASSWORD");
        lEmail2.setEnabled(false);
        getContentPane().add(lEmail2);
        lEmail2.setBounds(30, 300, 180, 33);

        lEmail3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lEmail3.setForeground(new java.awt.Color(255, 255, 255));
        lEmail3.setText("CONFIRM PASSWORD");
        lEmail3.setEnabled(false);
        getContentPane().add(lEmail3);
        lEmail3.setBounds(30, 350, 180, 33);

        tfSecurityQuestion.setEnabled(false);
        tfSecurityQuestion.setOpaque(false);
        getContentPane().add(tfSecurityQuestion);
        tfSecurityQuestion.setBounds(263, 223, 251, 34);
        getContentPane().add(tfEmail3);
        tfEmail3.setBounds(263, 42, 251, 34);

        lquestion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lquestion.setForeground(new java.awt.Color(255, 255, 255));
        lquestion.setEnabled(false);
        getContentPane().add(lquestion);
        lquestion.setBounds(30, 230, 180, 34);

        btSubmit.setBackground(new java.awt.Color(204, 255, 255));
        btSubmit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shield.png"))); // NOI18N
        btSubmit.setText("SUBMIT");
        btSubmit.setEnabled(false);
        btSubmit.setOpaque(false);
        btSubmit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(btSubmit);
        btSubmit.setBounds(260, 420, 140, 41);

        tfNewPassword.setEnabled(false);
        tfNewPassword.setOpaque(false);
        getContentPane().add(tfNewPassword);
        tfNewPassword.setBounds(263, 297, 251, 33);

        tfConfirmPassword.setEnabled(false);
        tfConfirmPassword.setOpaque(false);
        getContentPane().add(tfConfirmPassword);
        tfConfirmPassword.setBounds(263, 357, 251, 33);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/2.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 550, 490);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCheckActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btCheckActionPerformed
    {//GEN-HEADEREND:event_btCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCheckActionPerformed

    private void btSubmitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btSubmitActionPerformed
    {//GEN-HEADEREND:event_btSubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSubmitActionPerformed

    public static void main(String args[])
    {
 
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ForgotPasswordFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCheck;
    private javax.swing.JButton btSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lEmail;
    private javax.swing.JLabel lEmail2;
    private javax.swing.JLabel lEmail3;
    private javax.swing.JLabel lquestion;
    private javax.swing.JLabel lsecurityuestion;
    private javax.swing.JPasswordField tfConfirmPassword;
    private javax.swing.JTextField tfEmail3;
    private javax.swing.JPasswordField tfNewPassword;
    private javax.swing.JTextField tfSecurityQuestion;
    // End of variables declaration//GEN-END:variables
}
