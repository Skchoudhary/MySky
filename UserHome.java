
import com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class UserHome extends javax.swing.JFrame
{

    ClientGUI gui;
    ArrayList<Record> al1 = new ArrayList<>();
    ArrayList<PendingRecord> al2 = new ArrayList<>();
    ArrayList<OnlineRecord> al3 = new ArrayList<>();
    Account acc1;
    Account1 acc2;
    Account2 acc3;
    int index;

    public UserHome() throws HeadlessException
    {
        initComponents();
    }

    public UserHome(ClientGUI obj)
    {
        gui = obj;
        acc1 = new Account();
        acc2 = new Account1();
        acc3 = new Account2();
        initComponents();
        jtstatus.setModel(acc1);
        jRequest.setModel(acc2);
        jtViewOnline.setModel(acc3);
        setVisible(true);
        setLayout(null);
        int frameWidth = 1160;
        int frameHeight = 567;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int x = screenWidth / 2 - frameWidth / 2;
        int y = screenHeight / 2 - frameHeight / 2;
        setLocation(x, y);
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        tfsearch = new javax.swing.JTextField();
        btsearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtstatus = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jpending = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jRequest = new javax.swing.JTable();
        btReject = new javax.swing.JButton();
        btAccept = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtViewOnline = new javax.swing.JTable();
        btViewOnline = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jbLogOut = new javax.swing.JButton();
        jpvideo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jpaudio = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(0, 60, 22));

        tfsearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btsearch.setBackground(new java.awt.Color(204, 255, 255));
        btsearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btsearch.setText("SEARCH");
        btsearch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btsearchActionPerformed(evt);
            }
        });

        jtstatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(jtstatus);

        jButton2.setBackground(new java.awt.Color(204, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/agent.png"))); // NOI18N
        jButton2.setText("SEND REQUEST");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(tfsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btsearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search Friends", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 55, 32));

        jpending.setBackground(new java.awt.Color(204, 255, 255));
        jpending.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jpending.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/multi-agents.png"))); // NOI18N
        jpending.setText("View Pending");
        jpending.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jpendingActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jRequest);

        btReject.setBackground(new java.awt.Color(204, 255, 255));
        btReject.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btReject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Scissors.png"))); // NOI18N
        btReject.setText("Reject");
        btReject.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btRejectActionPerformed(evt);
            }
        });

        btAccept.setBackground(new java.awt.Color(204, 255, 255));
        btAccept.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btAccept.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/leaves.png"))); // NOI18N
        btAccept.setText("Accept");
        btAccept.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btAcceptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btReject, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpending, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jpending, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btAccept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btReject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pending Requests", jPanel2);

        jPanel4.setBackground(new java.awt.Color(0, 88, 19));

        jtViewOnline.setModel(acc3);
        jScrollPane3.setViewportView(jtViewOnline);

        btViewOnline.setBackground(new java.awt.Color(204, 255, 255));
        btViewOnline.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btViewOnline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/happy-face.png"))); // NOI18N
        btViewOnline.setText("View Online");
        btViewOnline.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btViewOnlineActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/microphone.png"))); // NOI18N
        jButton1.setText("Start Video Chat");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Stop Chat");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btViewOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btViewOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Online Friends", jPanel4);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(30, 60, 540, 430);

        jbLogOut.setBackground(new java.awt.Color(204, 255, 255));
        jbLogOut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbLogOut.setText("Log Out");
        jbLogOut.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbLogOutActionPerformed(evt);
            }
        });
        getContentPane().add(jbLogOut);
        jbLogOut.setBounds(120, 10, 140, 40);

        jpvideo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jpvideoLayout = new javax.swing.GroupLayout(jpvideo);
        jpvideo.setLayout(jpvideoLayout);
        jpvideoLayout.setHorizontalGroup(
            jpvideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        jpvideoLayout.setVerticalGroup(
            jpvideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );

        getContentPane().add(jpvideo);
        jpvideo.setBounds(580, 60, 500, 430);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/2reverse.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1160, 510);

        javax.swing.GroupLayout jpaudioLayout = new javax.swing.GroupLayout(jpaudio);
        jpaudio.setLayout(jpaudioLayout);
        jpaudioLayout.setHorizontalGroup(
            jpaudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jpaudioLayout.setVerticalGroup(
            jpaudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(jpaudio);
        jpaudio.setBounds(10, 20, 50, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btViewOnlineActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btViewOnlineActionPerformed
    {//GEN-HEADEREND:event_btViewOnlineActionPerformed
        try
        {
            gui.cl.dos.writeBytes("send online friends\r\n");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btViewOnlineActionPerformed

    private void btAcceptActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btAcceptActionPerformed
    {//GEN-HEADEREND:event_btAcceptActionPerformed

        index = jRequest.getSelectedRow();
        if (index == -1)
        {
            JOptionPane.showMessageDialog(this,"Select Row First", "Warning Window", JOptionPane.WARNING_MESSAGE);
        } else
        {
            try
            {
                gui.cl.dos.writeBytes("Accept Friend Request\r\n");
                String email = al2.get(index).friendemail;
                gui.cl.dos.writeBytes(email + "\r\n");

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_btAcceptActionPerformed

    private void btRejectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btRejectActionPerformed
    {//GEN-HEADEREND:event_btRejectActionPerformed
        index = jRequest.getSelectedRow();
        if (index == -1)
        {
            JOptionPane.showMessageDialog(this,"Select Row First", "Warning Window", JOptionPane.WARNING_MESSAGE);;
        } else
        {
            try
            {
                gui.cl.dos.writeBytes("Reject Friend Request\r\n");
                String email = al2.get(index).friendemail;
                gui.cl.dos.writeBytes(email + "\r\n");

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_btRejectActionPerformed

    private void jpendingActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jpendingActionPerformed
    {//GEN-HEADEREND:event_jpendingActionPerformed
        try
        {
            al2.clear();
            gui.cl.dos.writeBytes("Pending Request\r\n");
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jpendingActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        index = jtstatus.getSelectedRow();
        if (index == -1)
        {
            JOptionPane.showMessageDialog(this, "Select row first", "Warning Window", JOptionPane.WARNING_MESSAGE);
        } else if (al1.get(index).status.equals("friend request sent"))
        {
            JOptionPane.showMessageDialog(this, "Friend request Already Sent", "Warning Window", JOptionPane.WARNING_MESSAGE);
        } else if (al1.get(index).status.equals("Accept/Reject"))
        {
            JOptionPane.showMessageDialog(this, "Accept/Reject", "Accept/Reject", JOptionPane.INFORMATION_MESSAGE);
        } else if (al1.get(index).status.equals("Friend"))
        {
            JOptionPane.showMessageDialog(this, "Already Friend", "Error Window", JOptionPane.ERROR_MESSAGE);
        } else
        {
            try
            {
                gui.cl.dos.writeBytes("send friend request\r\n");
                String email = al1.get(index).email;
                String name = al1.get(index).name;
                gui.cl.dos.writeBytes(email + "\r\n");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btsearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btsearchActionPerformed
    {//GEN-HEADEREND:event_btsearchActionPerformed

        try
        {
            String search = tfsearch.getText();
            if (search.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please Fill Search Text Field", "Warning Window", JOptionPane.WARNING_MESSAGE);
            } else
            {
                al1.clear();
                gui.cl.dos.writeBytes("Request Search\r\n");
                gui.cl.dos.writeBytes(search + "\r\n");
                gui.cl.dos.flush();
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btsearchActionPerformed

    private void jbLogOutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbLogOutActionPerformed
    {//GEN-HEADEREND:event_jbLogOutActionPerformed

        UserHome uh = new UserHome();
        uh.dispose();
    }//GEN-LAST:event_jbLogOutActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        int index = jtViewOnline.getSelectedRow();
        if(index == -1){
            JOptionPane.showMessageDialog(this, "Select row first");
        }
        else
        {
            try
            {
                gui.cl.dos.writeBytes("startchat#"+al3.get(index).ip+"\r\n");
                gui.cl.dos.flush();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    class Record
    {

        String name;
        String email;
        String status;

        Record(String name, String email, String status)
        {
            this.name = name;
            this.email = email;
            this.status = status;

        }

    }

    class PendingRecord
    {

        String friendemail;
        String friendName;

        PendingRecord(String friendemail, String friendname)
        {
            this.friendName = friendname;
            this.friendemail = friendemail;
        }
    }

    class OnlineRecord
    {

        String name;
        String email;
        String ip;

        public OnlineRecord(String name, String Email, String ip)
        {
            this.name = name;
            this.email = Email;
            this.ip = ip;
        }

    }

    class Account extends AbstractTableModel
    {

        String column[] =
        {
            "NAME", "EMAIL", "STATUS"
        };

        @Override
        public int getRowCount()
        {

            return al1.size();
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
                return al1.get(row).name;
            } else if (col == 1)
            {

                return al1.get(row).email;
            } else
            {
                return al1.get(row).status;
            }

        }

        public String getColumnName(int a)
        {
            return column[a];
        }

    }

    class Account1 extends AbstractTableModel
    {

        String column[] =
        {
            "NAME", "EMAIL"
        };

        @Override
        public int getRowCount()
        {

            return al2.size();
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
                return al2.get(row).friendName;
            } else
            {
                return al2.get(row).friendemail;
            }

        }

        public String getColumnName(int a)
        {
            return column[a];
        }

    }

    class Account2 extends AbstractTableModel
    {

        String column[] =
        {
            "NAME", "EMAIL"
        };

        @Override
        public int getRowCount()
        {

            return al3.size();
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
                return al3.get(row).name;
            } else
            {
                return al3.get(row).email;
            }

        }

        public String getColumnName(int a)
        {
            return column[a];
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAccept;
    private javax.swing.JButton btReject;
    private javax.swing.JButton btViewOnline;
    private javax.swing.JButton btsearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTable jRequest;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbLogOut;
    public javax.swing.JPanel jpaudio;
    private javax.swing.JButton jpending;
    public javax.swing.JPanel jpvideo;
    private javax.swing.JTable jtViewOnline;
    private javax.swing.JTable jtstatus;
    private javax.swing.JTextField tfsearch;
    // End of variables declaration//GEN-END:variables
}
