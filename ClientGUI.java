
import java.awt.Toolkit;
import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.ChangedCharSetException;

public class ClientGUI extends javax.swing.JFrame
{

    Client cl;
    UserInfo userInfo;
    LoginFrame loginframe;
    ChangePasswordFrame cpframe;
    ForgotPasswordFrame fpframe;
    String secanswer;
    UserHome uhome;
    
    RTPTransmitter transmitter = null;
    RTPReceiverWithPanel receiver = null;
    
   RTPReceiverAudioWithPanel receiver_audio = null;
   RTPTransmitterAudio transmitter_audio = null;

    public ClientGUI()
    {
        initComponents();
        setLayout(null);
        jbForgetPassword.setEnabled(false);
        tfServerIP.setText("127.0.0.1");
        setTitle("Video Chat Software");

        setVisible(true);
        int frameWidth = 440;
        int frameHeight = 250;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int x = screenWidth / 2 - frameWidth / 2;
        int y = screenHeight / 2 - frameHeight / 2;
        setLocation(x, y);
        setSize(frameWidth, frameHeight);

    }

    class Client implements Runnable
    {

        DataInputStream dis;
        DataOutputStream dos;
        String serverIP;
        String serverName;

        Client(String serverIP)
        {
            try
            {
                this.serverIP = serverIP;
                Socket sock = new Socket(serverIP, 9001);
                JOptionPane.showMessageDialog(ClientGUI.this, "Successfully Connected With Server", "Connected", JOptionPane.INFORMATION_MESSAGE);
                btSignup.setEnabled(true);
                jbForgetPassword.setEnabled(true);
                btLogin.setEnabled(true);    
                dos = new DataOutputStream(sock.getOutputStream());
                dis = new DataInputStream(sock.getInputStream());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void run()
        {
            try
            {
                while (true)
                {
                    String msg = dis.readLine();
                    System.out.println(" meassage rec :" + msg);
                    if (msg.equals("record added succesfuly"))
                    {
                        userInfo.dispose();
                        JOptionPane.showMessageDialog(userInfo, msg, "Record Added", JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else if(msg.startsWith("chatting request"))
                    {
                        StringTokenizer st = new StringTokenizer(msg,"#");
                        st.nextToken();
                        String username = st.nextToken();
                        String ip = st.nextToken();
                        String ipnext = st.nextToken();
                        int ans = JOptionPane.showConfirmDialog(uhome,"Video chat request from "+username+"\n Do you want to continue ?","Video chat Confirmation",JOptionPane.YES_NO_OPTION);
                        if(ans == JOptionPane.YES_OPTION){
                            dos.writeBytes("video request accepted#"+ipnext+"#"+ip+"\r\n");
                            dos.flush();
                            
                            transmitter = new RTPTransmitter(ip, 10000, 20000);
                            receiver = new RTPReceiverWithPanel(ip, 20000, 10000, uhome.jpvideo);
                            
                            transmitter_audio = new RTPTransmitterAudio(ip, 11000, 12000);
                            receiver_audio = new RTPReceiverAudioWithPanel(ip, 12000, 11000, uhome.jpaudio);
                            
                        }
                        else
                        {
                            dos.writeBytes("video request rejected#"+ip+"\r\n");
                            dos.flush();
                        }
                    }
                    
                    else if(msg.startsWith("video request accepted"))
                    {
                       StringTokenizer st = new StringTokenizer(msg,"#");
                       st.nextToken();
                       String ip = st.nextToken();
                       String ipnext = st.nextToken();
                       if(transmitter != null && receiver != null){
                           transmitter.stopconfer();
                           receiver.stopconfer();
                           transmitter_audio.stopconfer();
                           receiver_audio.stopconfer();
                           transmitter = null;
                           transmitter_audio = null;
                           receiver = null;
                           receiver_audio= null;
                       }
                       if(transmitter == null && receiver == null){
                           transmitter = new RTPTransmitter(ip, 10000, 2000);
                           receiver = new RTPReceiverWithPanel(ip, 20000, 10000, uhome.jpvideo);
                           transmitter_audio = new RTPTransmitterAudio(ip, 11000, 12000);
                           receiver_audio = new RTPReceiverAudioWithPanel(ip, 12000, 10000, uhome.jpaudio);
                       }
                    }
                    else if(msg.startsWith("video request rejected")){
                        StringTokenizer st = new StringTokenizer(msg,"#");
                        st.nextToken();
                        String ip= st.nextToken();
                        JOptionPane.showMessageDialog(uhome, "Video chat request rejected");
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    else if (msg.equalsIgnoreCase("email already exists"))
                    {
                        JOptionPane.showMessageDialog(userInfo, msg, "Warning window", JOptionPane.WARNING_MESSAGE);
                    } else if (msg.equals("login response"))
                    {
                        System.out.println(msg);
                        msg = dis.readLine();
                        System.out.println(msg);
                        if (msg.equals("Login Successful"))
                        {
                            serverName = dis.readLine().toUpperCase();
                            loginframe.dispose();
                            jbChangePassword.setEnabled(true);
                            
                            JOptionPane.showMessageDialog(ClientGUI.this, msg, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                            uhome = new UserHome(ClientGUI.this);
                            uhome.setTitle(serverName);

                        } else
                        {
                            JOptionPane.showMessageDialog(ClientGUI.this, msg, "Warning window", JOptionPane.WARNING_MESSAGE);
                        }

                    } else if (msg.equals("Password Changed Response "))
                    {
                        msg = dis.readLine();
                        if (msg.equals("Password Changed "))
                        {
                            cpframe.dispose();
                        } else if (msg.equals("Password Changed FP"))
                        {
                            fpframe.dispose();
                            msg = dis.readLine();
                        }
                        JOptionPane.showMessageDialog(ClientGUI.this, msg, "Password Change", JOptionPane.INFORMATION_MESSAGE);
                    } else if (msg.equals("Email Check Response"))
                    {
                        msg = dis.readLine();
                        if (msg.equals("true"))
                        {
                            System.out.println("hoiiii");
                            fpframe.jLabel1.setEnabled(true);
                            fpframe.lEmail.setEnabled(true);
                            fpframe.lEmail2.setEnabled(true);
                            fpframe.lEmail3.setEnabled(true);
                            fpframe.lquestion.setEnabled(true);
                            fpframe.lsecurityuestion.setEnabled(true);
                            fpframe.tfConfirmPassword.setEnabled(true);
                            fpframe.tfEmail3.setEnabled(true);
                            fpframe.tfNewPassword.setEnabled(true);
                            fpframe.tfSecurityQuestion.setEnabled(true);
                            fpframe.btSubmit.setEnabled(true);
                            fpframe.lquestion.setText(dis.readLine());
                            secanswer = dis.readLine().trim();
                        } else
                        {
                            JOptionPane.showMessageDialog(fpframe, "Email Account Doesn't Exist", "Error Window", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (msg.equals("sending results"))
                    {
                        while (true)
                        {
                            String name = dis.readLine();
                            if (name.equals("end"))
                            {
                                break;
                            }
                            String email = dis.readLine();
                            String status = dis.readLine();
                            uhome.al1.add(uhome.new Record(name, email, status));
                            uhome.acc1.fireTableDataChanged();
                        }
                    } else if (msg.equals("No record found"))
                    {
                        uhome.al1.clear();
                        uhome.acc1.fireTableDataChanged();
                        JOptionPane.showMessageDialog(uhome, msg, "Error Window", JOptionPane.ERROR_MESSAGE);
                    } else if (msg.equals("friend request send"))
                    {
                        uhome.al1.clear();
                        JOptionPane.showMessageDialog(uhome,"Friend Request Sent", "Friend Request Sent", JOptionPane.INFORMATION_MESSAGE);
                    } else if (msg.equals("Response of Pending request"))
                    {
                        System.out.println("In Client Response of Pending request");
                        msg = dis.readLine();
                        if (msg.equals("No record Found"))
                        {
                            JOptionPane.showMessageDialog(uhome, msg, "Error Window", JOptionPane.ERROR_MESSAGE);
                            System.out.println("Inside pending no record found");

                        } else if (msg.equals("Sending pending request"))
                        {
                            System.out.println("inside pending request check");
                            uhome.al2.clear();
                            while (true)
                            {
                                String friendemail = dis.readLine();
                                if (friendemail.equals("end"))
                                {
                                    break;
                                }
                                String friendname = dis.readLine();
                                uhome.al2.add(uhome.new PendingRecord(friendemail, friendname));
                                uhome.acc2.fireTableDataChanged();
                            }
                        }
                    } else if (msg.equals("Sending Friend Request Accepted"))
                    {
                        System.out.println("client accept");
                        JOptionPane.showMessageDialog(uhome, "Friend Request Accepted", "Request Accepted", JOptionPane.INFORMATION_MESSAGE);
                        uhome.al2.remove(uhome.index);
                        uhome.acc2.fireTableDataChanged();

                    } else if (msg.equals("Reject Friend Request"))
                    {
                        System.out.println("client reject");
                        JOptionPane.showMessageDialog(uhome, "Friend Request Rejected", "Request Rejected", JOptionPane.INFORMATION_MESSAGE);
                        uhome.al2.remove(uhome.index);
                        uhome.acc2.fireTableDataChanged();

                    } else if (msg.equals("sending online friends"))
                    {
                        uhome.al3.clear();
                        System.out.println("Inside online friend request");
                        while (true)
                        {
                            String name = dis.readLine();
                            if (name.equals("end"))
                            {
                                break;
                            }
                            String email = dis.readLine();
                            String ip = dis.readLine();
                            uhome.al3.add(uhome.new OnlineRecord(name, email, ip));
                            uhome.acc3.fireTableDataChanged();
                        }
                    } else if (msg.equals("No Online Friend Yet"))
                    {
                        JOptionPane.showMessageDialog(uhome, msg, "Online friend", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            } catch (Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(userInfo, "Try Again!!");
                btConnect.setEnabled(true);
            }
        }

    }

    public class UserInfo extends javax.swing.JFrame
    {

        public UserInfo()
        {
            initComponents();
            setTitle("SignUp Form");
            setVisible(true);
            setLayout(null);
            setTitle("SIGN UP");
            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shield.png")));
            int frameWidth = 611;
            int frameHeight = 522;
            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = screenWidth / 2 - frameWidth / 2;
            int y = screenHeight / 2 - frameHeight / 2;
            setLocation(x, y);
            setSize(frameWidth, frameHeight);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
            cb1.setModel(new javax.swing.DefaultComboBoxModel(new String[]
            {
                "Which city you belong?", "Your favourite color?", "Two last digits of your phone no.?", "Your hobby?"
            }));
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
        }

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
        {
            String first_name = tx1.getText();
            String last_name = tx2.getText();
            String email = tx3.getText();
            String password = ps1.getText();
            String confirmpassword = pw2.getText();
            String phone_no = tx4.getText();
            String security_ques = cb1.getSelectedItem().toString();
            String security_ans = tx5.getText();

            if (first_name.equals("") || last_name.equals("") || email.equals("")
                    || password.equals("") || confirmpassword.equals("") || phone_no.equals("")
                    || security_ans.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Fill All Row First", "Warning Window", JOptionPane.WARNING_MESSAGE);
            } else if (!password.equals(confirmpassword))
            {

                JOptionPane.showMessageDialog(this, "Your password and confirm password does not match", "Error Window", JOptionPane.ERROR_MESSAGE);
            } else
            {
                try
                {
                    cl.dos.writeBytes("signup request\r\n");
                    cl.dos.writeBytes(first_name + "\r\n");
                    cl.dos.writeBytes(last_name + "\r\n");
                    cl.dos.writeBytes(email + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                    cl.dos.writeBytes(phone_no + "\r\n");
                    cl.dos.writeBytes(security_ques + "\r\n");
                    cl.dos.writeBytes(security_ans + "\r\n");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }

            }

        }

        // Variables declaration - do not modify                     
        private javax.swing.JComboBox cb1;
        private javax.swing.JButton jButton1;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
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
        // End of variables declaration                   
    }

    public class LoginFrame extends javax.swing.JFrame
    {

        public LoginFrame()
        {
            initComponents();
            tfEmail.setText("sk@gmail.com");
            tfPassword.setText("1234");
            setTitle("LOGIN FRAME");
            btLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shield.png")));
            setVisible(true);
            setLayout(null);
            int frameWidth = 440;
            int frameHeight = 216;
            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = screenWidth / 2 - frameWidth / 2;
            int y = screenHeight / 2 - frameHeight / 2;
            setLocation(x, y);
            setSize(frameWidth, frameHeight);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents()
        {

            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            tfEmail = new javax.swing.JTextField();
            btLogin = new javax.swing.JButton();
            tfPassword = new javax.swing.JPasswordField();
            jLabel4 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Login Form");
            getContentPane().setLayout(null);

            jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setText("Password");
            getContentPane().add(jLabel2);
            jLabel2.setBounds(10, 79, 123, 37);

            jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            jLabel3.setText("Email");
            getContentPane().add(jLabel3);
            jLabel3.setBounds(10, 32, 123, 37);
            getContentPane().add(tfEmail);
            tfEmail.setBounds(163, 31, 228, 38);

            btLogin.setBackground(new java.awt.Color(204, 255, 255));
            btLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            btLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shield.png"))); // NOI18N
            btLogin.setText("Login");
            btLogin.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    btLoginActionPerformed(evt);
                }
            });
            getContentPane().add(btLogin);
            btLogin.setBounds(160, 140, 140, 37);
            getContentPane().add(tfPassword);
            tfPassword.setBounds(163, 81, 228, 36);

            jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/2.jpg"))); // NOI18N
            getContentPane().add(jLabel4);
            jLabel4.setBounds(0, 0, 420, 250);

            pack();
        }

        private void btLoginActionPerformed(java.awt.event.ActionEvent evt)
        {
            String email = tfEmail.getText().trim();
            String password = tfPassword.getText().trim();
            if (email.isEmpty() || password.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Enter both fields properly", "Warning Window", JOptionPane.WARNING_MESSAGE);

            } else
            {
                try
                {
                    cl.dos.writeBytes("login request\r\n");
                    cl.dos.writeBytes(email + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                    cl.dos.flush();
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }

        // Variables declaration - do not modify                     
        private javax.swing.JButton btLogin;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JTextField tfEmail;
        private javax.swing.JPasswordField tfPassword;
        // End of variables declaration                   
    }

    public class ChangePasswordFrame extends javax.swing.JFrame
    {

        ChangePasswordFrame()
        {
            initComponents();
            setVisible(true);
            setTitle("CHANGE PASSWORD ");
            setLayout(null);
            btSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shield.png"))); // NOI18N
            int frameWidth = 535;
            int frameHeight = 325;
            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = screenWidth / 2 - frameWidth / 2;
            int y = screenHeight / 2 - frameHeight / 2;
            setLocation(x, y);
            setSize(frameWidth, frameHeight);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
        }

        private void btSubmitActionPerformed(java.awt.event.ActionEvent evt)
        {
            String Email = tfEmail.getText().trim();
            String CurrentPass = tfCpassword.getText().trim();
            String Newpass = tfNpassword.getText().trim();
            String ConfirmPass = ConfirmPassword.getText().trim();

            if (Email.isEmpty() || CurrentPass.isEmpty() || Newpass.isEmpty() || ConfirmPass.isEmpty())
            {

                JOptionPane.showMessageDialog(this, "Please Fill Fields Properly", "Warning Window", JOptionPane.WARNING_MESSAGE);

            } else if (!Newpass.equals(ConfirmPass))
            {
                JOptionPane.showMessageDialog(this, "Password MisMatch", "Error Window", JOptionPane.ERROR_MESSAGE);
            } else
            {
                try
                {

                    cl.dos.writeBytes("Request For Password Change \r\n");
                    cl.dos.writeBytes(Email + "\r\n");
                    cl.dos.writeBytes(CurrentPass + "\r\n");
                    cl.dos.writeBytes(Newpass + "\r\n");
                    cl.dos.writeBytes(ConfirmPass + "\r\n");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }

        // Variables declaration - do not modify                     
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
        // End of variables declaration                   

        private void elseif(boolean equals)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lbEnterServerIP = new javax.swing.JLabel();
        tfServerIP = new javax.swing.JTextField();
        btConnect = new javax.swing.JButton();
        btLogin = new javax.swing.JButton();
        btSignup = new javax.swing.JButton();
        jbChangePassword = new javax.swing.JButton();
        jbForgetPassword = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lbEnterServerIP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbEnterServerIP.setForeground(new java.awt.Color(255, 255, 255));
        lbEnterServerIP.setText("Enter Server IP Address");
        getContentPane().add(lbEnterServerIP);
        lbEnterServerIP.setBounds(30, 0, 190, 30);
        getContentPane().add(tfServerIP);
        tfServerIP.setBounds(20, 30, 220, 40);

        btConnect.setBackground(new java.awt.Color(204, 255, 255));
        btConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/connected.png"))); // NOI18N
        btConnect.setText("Connect");
        btConnect.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btConnectActionPerformed(evt);
            }
        });
        getContentPane().add(btConnect);
        btConnect.setBounds(250, 30, 160, 40);

        btLogin.setBackground(new java.awt.Color(204, 255, 255));
        btLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        btLogin.setText("Login");
        btLogin.setEnabled(false);
        btLogin.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btLogin);
        btLogin.setBounds(250, 90, 160, 40);

        btSignup.setBackground(new java.awt.Color(204, 255, 255));
        btSignup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ID.png"))); // NOI18N
        btSignup.setText("Signup");
        btSignup.setEnabled(false);
        btSignup.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btSignupActionPerformed(evt);
            }
        });
        getContentPane().add(btSignup);
        btSignup.setBounds(20, 90, 220, 40);

        jbChangePassword.setBackground(new java.awt.Color(204, 255, 255));
        jbChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/configuration.png"))); // NOI18N
        jbChangePassword.setText("Change Password");
        jbChangePassword.setEnabled(false);
        jbChangePassword.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbChangePasswordActionPerformed(evt);
            }
        });
        getContentPane().add(jbChangePassword);
        jbChangePassword.setBounds(20, 150, 220, 40);

        jbForgetPassword.setBackground(new java.awt.Color(204, 255, 255));
        jbForgetPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/disc.png"))); // NOI18N
        jbForgetPassword.setText("Forgot Password");
        jbForgetPassword.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbForgetPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(jbForgetPassword);
        jbForgetPassword.setBounds(250, 150, 160, 41);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/2.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 440, 220);

        pack();
    }// </editor-fold>//GEN-END:initComponents
public class ForgotPasswordFrame extends javax.swing.JFrame
    {

        String email;
        String securityanswer;
        String newpassword;
        String confirmpassword;

        public ForgotPasswordFrame()
        {
            initComponents();
            setVisible(true);
            setTitle("FORGOT PASSWORD");
            setLayout(null);
            btCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow-round.png")));
            btSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shield.png")));
            int frameWidth = 550;
            int frameHeight = 490;
            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = screenWidth / 2 - frameWidth / 2;
            int y = screenHeight / 2 - frameHeight / 2;
            setLocation(x, y);
            setSize(frameWidth, frameHeight);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
    }                      

        private void btCheckActionPerformed(java.awt.event.ActionEvent evt)
        {
            email = tfEmail3.getText().trim();
            if (email.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please Fill Email TextField", "Warning Window", JOptionPane.WARNING_MESSAGE);
            } else
            {
                try
                {

                    cl.dos.writeBytes("Email Check\r\n");
                    cl.dos.writeBytes(email + "\r\n");
                    cl.dos.flush();
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }

            }

        }

        private void btSubmitActionPerformed(java.awt.event.ActionEvent evt)
        {
            securityanswer = tfSecurityQuestion.getText().trim();
            newpassword = tfNewPassword.getText().trim();
            confirmpassword = tfConfirmPassword.getText().trim();
            System.out.println(secanswer);
            if (securityanswer.isEmpty() || newpassword.isEmpty() || confirmpassword.isEmpty())
            {

                JOptionPane.showMessageDialog(this, "Please Fill ALL Fields", "Warning Window", JOptionPane.WARNING_MESSAGE);
            } else if (!newpassword.equals(confirmpassword) && !secanswer.equals(securityanswer))
            {

                JOptionPane.showMessageDialog(this, "Sorry,Wrong Security Answer && Password MisMatch", "Error Window", JOptionPane.ERROR_MESSAGE);
            } else if (!secanswer.equals(securityanswer))
            {

                JOptionPane.showMessageDialog(this, "Sorry,Wrong Security Answer", "Error Window", JOptionPane.ERROR_MESSAGE);
            } else if (!newpassword.equals(confirmpassword))
            {
                JOptionPane.showMessageDialog(this, "Password MisMatch", "Error Window", JOptionPane.ERROR_MESSAGE);
            } else
            {
                try
                {
                    cl.dos.writeBytes("Request For Password Change of Forget Password\r\n");
                    cl.dos.writeBytes(email + "\r\n");
                    cl.dos.writeBytes(newpassword + "\r\n");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }

        }

        // Variables declaration - do not modify                     
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
        // End of variables declaration                   
    }

    private void btSignupActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btSignupActionPerformed
    {//GEN-HEADEREND:event_btSignupActionPerformed
        userInfo = new UserInfo();
    }//GEN-LAST:event_btSignupActionPerformed

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btLoginActionPerformed
    {//GEN-HEADEREND:event_btLoginActionPerformed
        loginframe = new LoginFrame();
    }//GEN-LAST:event_btLoginActionPerformed

    private void btConnectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btConnectActionPerformed
    {//GEN-HEADEREND:event_btConnectActionPerformed
        String ipAddress = tfServerIP.getText().trim();
        if (ipAddress.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Enter server ip first");
        } else
        {
            cl = new Client(ipAddress);
            Thread t = new Thread(cl);
            t.start();
            btConnect.setEnabled(false);
            jbForgetPassword.setEnabled(true);
        }
    }//GEN-LAST:event_btConnectActionPerformed

    private void jbChangePasswordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbChangePasswordActionPerformed
    {//GEN-HEADEREND:event_jbChangePasswordActionPerformed

        cpframe = new ChangePasswordFrame();
    }//GEN-LAST:event_jbChangePasswordActionPerformed

    private void jbForgetPasswordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbForgetPasswordActionPerformed
    {//GEN-HEADEREND:event_jbForgetPasswordActionPerformed

        fpframe = new ForgotPasswordFrame();
    }//GEN-LAST:event_jbForgetPasswordActionPerformed

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
            java.util.logging.Logger.getLogger(ClientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ClientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ClientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ClientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ClientGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConnect;
    private javax.swing.JButton btLogin;
    private javax.swing.JButton btSignup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbChangePassword;
    private javax.swing.JButton jbForgetPassword;
    private javax.swing.JLabel lbEnterServerIP;
    private javax.swing.JTextField tfServerIP;
    // End of variables declaration//GEN-END:variables
}
