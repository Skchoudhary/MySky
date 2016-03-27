/* Example 14.1: ReceiverPlayer.java is a ReceiveStreamListener that looks for 
 NewReceiveStreams events. It extracts the media stream from the posted event, 
 constructs a Player for the stream and presents the media to the user. This program 
 requires the class ReceiveStreamListenerAdaptor.
 */
//import JMFUtil.*;

import javax.media.*;
import javax.media.rtp.event.*;
import javax.media.rtp.*;
import javax.media.protocol.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import javax.media.format.*;
import javax.media.control.*;
import java.util.Vector;
import java.awt.event.*;

public class ReceiverPlayerWithPanel extends ReceiveStreamListenerAdaptor implements ControllerListener {

//    JFrame frame = null;
    String cname = null;
    Component comp = null;
    ReceiveStream stream = null;
    DataSource source = null;
    Player player;
    JPanel jp;

    public ReceiverPlayerWithPanel(JPanel jp) {
        System.out.println("in receiver player constructor ------  ");
        this.jp = jp;
    }

    public void NewReceiveStreamHandler(ReceiveStreamEvent event) {
        System.out.println("Receiver Player gets the event \n" + event);
        stream = ((NewReceiveStreamEvent) event).getReceiveStream();
        source = stream.getDataSource();

        try {
            player = Manager.createPlayer(source);

        } catch (NoPlayerException npe) {
            System.out.println("Receiver : " + npe);
        } catch (IOException ioe) {
            System.out.println("Receiver : " + ioe);
        }
        player.addControllerListener(this);
        player.realize();
        player.prefetch();
        player.start();
    }

    public synchronized void controllerUpdate(ControllerEvent event) {

        if (event instanceof RealizeCompleteEvent) {

            Component visual = player.getVisualComponent();
            if (visual != null) {
                visual.setBounds(0, 0, 200, 100);
                jp.setLayout(new BorderLayout());
                jp.add(visual,BorderLayout.CENTER);
                jp.repaint();
            }
        }
    } // controllerUpdate

    public void stop() {
        if (player != null) {
            player.stop();
            player.close();
        }
      
    }

}
