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

public class ReceiverPlayer extends  ReceiveStreamListenerAdaptor implements ControllerListener {

JFrame frame = null;
String cname = null;
Component comp = null;
ReceiveStream stream = null;
DataSource source = null;
Player player;

public void NewReceiveStreamHandler( ReceiveStreamEvent event ) {
   	System.out.println("Receiver Player gets the event \n" + event );
	stream = ( ( NewReceiveStreamEvent)event ).getReceiveStream();
	source = stream.getDataSource();	

	try{
       		 player = Manager.createPlayer(source);
	 
    	 } catch (NoPlayerException npe  ) {
		 System.out.println("Receiver : " + npe);
     	} catch (IOException ioe) {
		 System.out.println("Receiver : " + ioe);
   	} 
    	player.addControllerListener( this  ); 
	player.realize();
	player.prefetch();
	player.start();	
}


public synchronized void controllerUpdate( ControllerEvent event ) {

	 if( event instanceof RealizeCompleteEvent) {
		   
		      frame = new JFrame();			
	 	      Component visual = player.getVisualComponent();
		      if( visual != null) {
			frame.getContentPane().add( visual, BorderLayout.CENTER);
		       }
			
		     Component  control = player.getControlPanelComponent();
		     if( control != null) {
			frame.getContentPane().add(control, BorderLayout.SOUTH); 
		     }
                 
	                       frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				player.stop();
				player.close();
				//System.exit(0);			
	
			}
					
		});			
			
		frame.pack();
		frame.setVisible(true);
	} 
        } // controllerUpdate

public void stop(){
	if (player != null){
	player.stop();
	player.close();
	}
	if( frame != null) {frame.dispose();}
}



}
