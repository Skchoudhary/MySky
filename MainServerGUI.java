
import java.awt.Color;
import java.awt.Toolkit;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.table.*;

public class MainServerGUI extends javax.swing.JFrame
{

    Account acc;
    boolean check1 = false;
    ArrayList<Server.ClientHandler> al = new ArrayList<>();

    public MainServerGUI()
    {
        initComponents();
        setLayout(null);
        setVisible(true);
        setTitle("Main Server");
        int frameWidth = 440;
        int frameHeight = 480;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int x = screenWidth / 2 - frameWidth / 2;
        int y = screenHeight / 2 - frameHeight / 2;
        setLocation(x, y);
        setSize(frameWidth, frameHeight);
        acc = new Account();
        tb1.setModel(acc);
        getContentPane().setBackground(Color.gray);
    }

    class Server implements Runnable
    {

        public void run()
        {

            try
            {
                ServerSocket sersok = new ServerSocket(9001);
                System.out.println("SERVER STARTED");
                while (check1 == true)
                {
                    Socket sock = sersok.accept();
                    Thread t1 = new Thread(new ClientHandler(sock));
                    t1.start();

                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }

        }

        class ClientHandler implements Runnable
        {

            DataInputStream dis;
            DataOutputStream dos;
            String ip;
            String loginEmail;
            String search;
            String username = "Not Define";

            ClientHandler(Socket sock)
            {
                try
                {
                    dis = new DataInputStream(sock.getInputStream());
                    dos = new DataOutputStream(sock.getOutputStream());
                    ip = sock.getInetAddress().getHostAddress();
                    al.add(this);
                    acc.fireTableDataChanged();

                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }

            public void run()
            {
                try
                {
                    while (true)
                    {

                        String check = dis.readLine();
                        System.out.println("check = " + check);
                        if (check == null)
                        {
                            al.remove(this);
                            acc.fireTableDataChanged();

                        } else if (check.startsWith("startchat"))
                        {
                            StringTokenizer st = new StringTokenizer(check, "#");
                            st.nextToken();
                            String ip = st.nextToken();
                            for (int i = 0; i < al.size(); i++)
                            {
                                if (al.get(i).ip.equals(ip))
                                {
                                    al.get(i).dos.writeBytes("chatting request#" + username + "#" + this.ip + "#" + ip + "\r\n");
                                    al.get(i).dos.flush();
                                    break;
                                }
                            }
                        } else if (check.startsWith("video request accepted"))
                        {
                            StringTokenizer st = new StringTokenizer(check, "#");
                            st.nextToken();
                            String ip = st.nextToken();
                            String ipnext = st.nextToken();
                            for (int i = 0; i < al.size(); i++)
                            {
                                if (al.get(i).ip.equals(ipnext))
                                {
                                    al.get(i).dos.writeBytes(check + "\r\n");
                                    al.get(i).dos.flush();
                                    break;
                                }
                            }
                        } else if (check.startsWith("video request rejected"))
                        {
                            StringTokenizer st = new StringTokenizer(check, "#");
                            st.nextToken();
                            String ip = st.nextToken();
                            for (int i = 0; i < al.size(); i++)
                            {
                                if (al.get(i).ip.equals(ip))
                                {
                                    al.get(i).dos.writeBytes(check + "\r\n");
                                    al.get(i).dos.flush();
                                    break;
                                }
                            }
                        } else if (check.equals("signup request"))
                        {
                            String first_name = dis.readLine();
                            String last_name = dis.readLine();
                            String email = dis.readLine();
                            String password = dis.readLine();
                            String phone_no = dis.readLine();
                            String security_ques = dis.readLine();
                            String security_ans = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from details1 where email_id='" + email + "'");
                            if (rs.next())
                            {
                                dos.writeBytes("email already exists");
                            } else
                            {
                                rs.moveToInsertRow();
                                rs.updateString("name", first_name);
                                rs.updateString("last_name", last_name);
                                rs.updateString("email_id", email);
                                rs.updateString("password", password);
                                rs.updateString("phone_number", phone_no);
                                rs.updateString("security_question", security_ques);
                                rs.updateString("security_answer", security_ans);
                                rs.insertRow();
                                System.out.println("row inserted");
                                dos.writeBytes("record added succesfuly\r\n");
                            }
                        } else if (check.equals("login request"))
                        {
                            String email = dis.readLine();
                            String password = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from details1 where email_id='" + email + "' and password='" + password + "'");
                            if (rs.next())
                            {
                                dos.writeBytes("login response\r\n");
                                dos.writeBytes("Login Successful\r\n");
                                dos.flush();
                                loginEmail = email;
                                username = rs.getString("name") + " " + rs.getString("last_name");
                                dos.writeBytes(username + "\r\n");
                                acc.fireTableDataChanged();

                            } else
                            {
                                dos.writeBytes("login response\r\n");
                                dos.writeBytes("Invalid Username or Password\r\n");
                                dos.flush();
                            }
                        } else if (check.equals("Request For Password Change "))
                        {
                            String email = dis.readLine();
                            String password = dis.readLine();
                            String newPassword = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from details1 where email_id='" + email + "' and password='" + password + "'");
                            if (rs.next() && !newPassword.equals(""))
                            {
                                rs.updateString("password", newPassword);
                                rs.updateRow();
                                dos.writeBytes("Password Changed Response \r\n");
                                dos.writeBytes("Password Changed \r\n");
                                dos.flush();

                            } else
                            {
                                dos.writeBytes("Password Changed Response \r\n");
                                dos.writeBytes("Invalid Username or Password\r\n");
                                dos.flush();
                            }
                        } else if (check.equals("Email Check"))
                        {
                            String email = dis.readLine();
                            String security;
                            String securityanswer;
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from details1 where email_id='" + email + "'");
                            dos.writeBytes("Email Check Response\r\n");
                            dos.flush();
                            if (rs.next())
                            {
                                security = rs.getString("security_question");
                                securityanswer = rs.getString("security_answer");
                                dos.writeBytes("true\r\n");
                                dos.writeBytes(security + "\r\n");
                                dos.writeBytes(securityanswer + "\r\n");
                                dos.flush();

                            } else
                            {
                                dos.writeBytes("false\r\n");
                                dos.flush();
                            }
                        } else if (check.equals("Request For Password Change of Forget Password"))
                        {
                            String email = dis.readLine();
                            String newPassword = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from details1 where email_id='" + email + "'");
                            if (rs.next())
                            {
                                rs.updateString("password", newPassword);
                                rs.updateRow();
                                dos.writeBytes("Password Changed Response \r\n");
                                dos.writeBytes("Password Changed FP\r\n");
                                dos.writeBytes("Password Changed\r\n");
                                dos.flush();
                            }
                        } else if (check.equals("Request Search"))
                        {
                            search = dis.readLine();
                            search_results(search);
                            System.out.println("LoginNAme" + loginEmail);
                        } else if (check.equals("send friend request"))
                        {
                            String friendemail = dis.readLine();
                            //String name = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from friends");
                            // System.out.println("LoginNAme" + loginEmail);
                            rs.moveToInsertRow();
                            rs.updateString("useremail", loginEmail);
                            rs.updateString("friendemail", friendemail);
                            //rs.updateString("status", "pending");
                            rs.insertRow();
                            dos.writeBytes("friend request send\r\n");
                            dos.flush();
                            //System.out.println("row inserted");
                            search_results(search);
                        } else if (check.equals("Pending Request"))
                        {
                            pending_request();
                        } else if (check.equals("Accept Friend Request"))
                        {
                            String email = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from friends where (friendemail ='" + loginEmail + "' and useremail='" + email + "')");
                            if (rs.next())
                            {

                                System.out.println("inside accept");
                                rs.updateString("status", "Accept");
                                rs.updateRow();
                                System.out.println("staus updated");
                                rs.moveToInsertRow();
                                rs.updateString("useremail", loginEmail);
                                rs.updateString("friendemail", email);
                                rs.updateString("status", "Accept");
                                rs.insertRow();
                                System.out.println("row inserted");
                                dos.writeBytes("Sending Friend Request Accepted\r\n");
                                dos.flush();
                                // pending_request();
                                System.out.println("Meassage sent");
                            }
                        } else if (check.equals("Reject Friend Request"))
                        {
                            String email = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from friends where (friendemail ='" + loginEmail + "' and useremail='" + email + "')");
                            if (rs.next())
                            {
                                dos.writeBytes("Reject Friend Request\r\n");
                                System.out.println("inside reject");
                                dos.flush();
                                rs.deleteRow();
                                System.out.println("row deleted");
//                                pending_request();
                            }
                        } else if (check.equals("send online friends"))
                        {
                            int flag = 0;
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from friends where useremail='" + loginEmail + "' and status='Accept'");

                            if (rs.next())
                            {

                                System.out.println("Inside mainserver online friend");
                                dos.writeBytes("sending online friends\r\n");
                                rs.beforeFirst();
                                while (rs.next())
                                {
                                    String friendemail = rs.getString("friendemail");
                                    System.out.println("online friend email = " + friendemail);
                                    for (int i = 0; i < al.size(); i++)
                                    {
                                        if (al.get(i).loginEmail.equals(friendemail))
                                        {
                                            flag = 1;
                                            dos.writeBytes(al.get(i).username + "\r\n");
                                            dos.writeBytes(friendemail + "\r\n");
                                            dos.writeBytes(al.get(i).ip + "\r\n");
                                            System.out.println("ROW SENT");
                                        }
                                    }
                                }
                                dos.writeBytes("end\r\n");
                            }
                            if (flag == 0)
                            {
                                System.out.println("Inside Mian Serever no online friend");
                                dos.writeBytes("No Online Friend Yet\r\n");
                                dos.flush();
                            }

                        }

                    }
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                    al.remove(this);
                    acc.fireTableDataChanged();
                }
            }

            void search_results(String search)
            {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery("select * from details1 where name like '" + search + "%'");
                    Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    if (rs.next())
                    {
                        dos.writeBytes("sending results\r\n");
                        //System.out.println("inside if");
                        rs.beforeFirst();
                        while (rs.next())
                        {
                            String uname = rs.getString("name");
                            String ulname = rs.getString("last_name");
                            String uemail = rs.getString("email_id");
                            String fullname = uname + " " + ulname;
//                            System.out.println("full name = " + fullname);
//                            System.out.println("friendemail = " + uemail);
//                            System.out.println("logemail " + loginEmail);
                            if (!(uemail.equals(loginEmail)))
                            {

                                ResultSet rs1 = stmt1.executeQuery("select * from friends where (useremail ='" + loginEmail + "' and friendemail='" + uemail + "') or(useremail ='" + uemail + "' and friendemail='" + loginEmail + "')");
                                if (rs1.next())
                                {
//                                    System.out.println("in status code\r\n");
                                    //rs1.beforeFirst();
                                    //  while (rs1.next())
                                    {
                                        String status = rs1.getString("status");
                                        String useremail = rs1.getString("useremail");
                                        if (loginEmail.equals(useremail) && status.equals("pending"))
                                        {
                                            dos.writeBytes(fullname + "\r\n");
                                            dos.writeBytes(uemail + "\r\n");
                                            dos.writeBytes("friend request sent\r\n");
                                        } else if (status.equals("pending"))
                                        {
                                            dos.writeBytes(fullname + "\r\n");
                                            dos.writeBytes(uemail + "\r\n");
                                            dos.writeBytes("Accept/Reject\r\n");
                                        } else
                                        {
                                            dos.writeBytes(fullname + "\r\n");
                                            dos.writeBytes(uemail + "\r\n");
                                            dos.writeBytes("Friend\r\n");
                                        }

                                    }
                                } else
                                {
                                    dos.writeBytes(fullname + "\r\n");
                                    dos.writeBytes(uemail + "\r\n");
                                    dos.writeBytes("Add Friend\r\n");
                                }
                            }

                        }

                        dos.writeBytes("end\r\n");
                    } else
                    {
                        dos.writeBytes("No record found\r\n");
                    }
                } catch (Exception e)
                {

                }
            }

            void pending_request()
            {
                try
                {
                    dos.writeBytes("Response of Pending request\r\n");
                    dos.flush();
                    System.out.println("inside pending request");
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("login email = " + loginEmail);
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "system");
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery("select * from friends where (friendemail ='" + loginEmail + "' and status='pending')");
                    Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    if (rs.next())
                    {
                        dos.writeBytes("Sending pending request\r\n");
                        rs.beforeFirst();
                        while (rs.next())
                        {
                            System.out.println("inside record");
                            String friendemail = rs.getString("useremail");
                            ResultSet rs1 = stmt1.executeQuery("select * from details1 where email_id='" + friendemail + "'");
                            rs1.next();
                            String friendname = rs1.getString("name") + " " + rs1.getString("last_name");
                            dos.writeBytes(friendemail + "\r\n");
                            dos.writeBytes(friendname + "\r\n");
                            dos.flush();
                            System.out.println("Writing from pending request");
                        }
                        dos.writeBytes("end\r\n");
                        dos.flush();
                    } else
                    {
                        dos.writeBytes("No record Found\r\n");
                        dos.flush();
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    class Account extends AbstractTableModel
    {

        String column[] =
        {
            "USER NAME", "IP ADDRESS"
        };

        @Override
        public int getRowCount()
        {

            return al.size();
        }

        @Override
        public int getColumnCount()
        {
            return column.length;

        }

        @Override
        public Object getValueAt(int row, int col)
        {

            if (col == 0)
            {
                return al.get(row).username;
            } else
            {
                return al.get(row).ip;
            }

        }

        public String getColumnName(int a)
        {
            return column[a];
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        StopButton = new javax.swing.JButton();
        StartButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        lb2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Server");
        setBackground(new java.awt.Color(0, 153, 153));
        getContentPane().setLayout(null);

        StopButton.setBackground(new java.awt.Color(204, 255, 255));
        StopButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        StopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shut-down.png"))); // NOI18N
        StopButton.setText("STOP");
        StopButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                StopButtonActionPerformed(evt);
            }
        });
        getContentPane().add(StopButton);
        StopButton.setBounds(240, 20, 150, 40);

        StartButton.setBackground(new java.awt.Color(204, 255, 255));
        StartButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        StartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/satellite.png"))); // NOI18N
        StartButton.setText("START");
        StartButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                StartButtonActionPerformed(evt);
            }
        });
        getContentPane().add(StartButton);
        StartButton.setBounds(30, 20, 130, 40);

        tb1.setBackground(new java.awt.Color(204, 255, 255));
        jScrollPane1.setViewportView(tb1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 110, 360, 320);
        getContentPane().add(lb2);
        lb2.setBounds(220, 80, 90, 0);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/2.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 420, 460);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed

        {
            check1 = true;
            Server serv = new Server();
            Thread t2 = new Thread(serv);
            t2.start();
            JOptionPane.showMessageDialog(MainServerGUI.this, "Server Started");
            StartButton.setEnabled(false);
        }
    }//GEN-LAST:event_StartButtonActionPerformed

    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopButtonActionPerformed
        // TODO add your handling code here:
        check1 = false;
        lb2.setText("SERVER DISCONNECTED");

    }//GEN-LAST:event_StopButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MainServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainServerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton StartButton;
    private javax.swing.JButton StopButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb2;
    private javax.swing.JTable tb1;
    // End of variables declaration//GEN-END:variables
}
