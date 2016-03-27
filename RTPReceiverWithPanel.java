/*
************************************************************************************************
The class RTPReceiver demostrates reception of a RTP stream using a RTPManager.
To run this program you need the class files of the programs ReceiverPlayer.java 
( or Receiver.java ) and SessionListeneraAdaptor.java, ReceiveStreamListenerAdaptor.java
 They can be present in the same directory of this program.

To test this program transmit a media stream from a different machine using JMStudio.  
Use the transmit option of JMStudio with the  session address  of the transmit option 
as the IP address of the machine which runs RTPReceiver. The port number of the 
transmit option should be the local port number you supply for running RTPReceiver. 
The program can be stopped by killing the corresponding process ( ctrl-c in windows 95,  ).
*************************************************************************************************
*/
//import JMFUtil.*;
import java.net.*;
import javax.media.*;
import javax.media.rtp.*;
import javax.media.rtp.event.*;
import javax.media.rtp.rtcp.*;
import javax.swing.JPanel;

public class RTPReceiverWithPanel implements Runnable {

String remoteIPAddress = "192.168.0.11";
int remotePort = 9000;
int localPort  = 9000;
RTPManager rtpManager;
InetAddress destaddr = null;
InetAddress localInetaddr= null;
Thread rc1;
ReceiverPlayerWithPanel  receiver;
JPanel jp;
//Receiver receiver;
//ReceiverStore receiver;
//ReceiverTransmit receiver;
//dataCloning dc;

public RTPReceiverWithPanel(String remoteIPAddress, int remotePort, int localPort,JPanel jp)
{
	this.remoteIPAddress = remoteIPAddress;
	this.remotePort = remotePort;
	this.localPort = localPort;
        this.jp = jp;
        
	rtpManager = RTPManager.newInstance();
       
	
        rc1=new Thread(this);
        rc1.start();
        System.out.print("thread started");
}

public void run()
{


      try
        {
	          localInetaddr = InetAddress.getLocalHost();
                  System.out.println("local inet address = "+localInetaddr);
	}
        catch(java.net.UnknownHostException unknown)
        { 
		System.out.println( unknown ); 
	}
	
        SessionAddress localaddr = new SessionAddress(localInetaddr, localPort);
	//SessionAddress localaddr = new SessionAddress();
	
        try
        {
	   rtpManager.initialize( localaddr);
        }
        catch ( InvalidSessionAddressException e)
        { 
		System.out.println ( "Invalid SA" + e);
	}
        catch(  java.io.IOException ioe ) 
        { 
	   System.out.println ( "IO Exception" + ioe );
         }	

          
        receiver =  new ReceiverPlayerWithPanel(jp);
        //    receiver = new Receiver();
	// receiver =  new ReceiverStore();
	//  receiver = new ReceiverTransmit();
	//dc = new dataCloning();

	 rtpManager.addReceiveStreamListener( receiver );
	// rtpManager.addReceiveStreamListener( dc );
	
        rtpManager.addSessionListener( new SessionListenerAdaptor() );
	
	try
        {
          destaddr = InetAddress.getByName( remoteIPAddress);
        }
        catch(java.net.UnknownHostException unknown ) 
        { 
	  System.out.println( unknown ); 
	}

	SessionAddress remoteAddr = new SessionAddress(destaddr, remotePort);

	try
        {

	rtpManager.addTarget( remoteAddr );
       
     	} 
        catch ( InvalidSessionAddressException e) 
        { 
		System.out.println ( "Invalid SA" + e);
	}
        catch(  java.io.IOException ioe )
        {
	   System.out.println ( "IO Exception" + ioe );
        }	



}

public void stopconfer(){
	
	if ( receiver != null){
		receiver.stop();
		rtpManager.removeReceiveStreamListener( receiver);
		receiver = null;
	}
	
	
	rtpManager.removeTargets( "Stop requested");
	rtpManager.dispose();
	rtpManager = null;
}

public static void main( String args[] ){

//       if ( args.length !=3){
//	System.out.println( "Error, Correct  usage is :  java RTPReceiver remoteIPAddress remotePort localPort"); 
//	System.exit(0);
//     }
    RTPReceiver  recv = new RTPReceiver("172.16.3.24", 10000, 10000 );
//	
//	
//	   try {
//	        Thread.currentThread().sleep(300000); // 5 mins
//                  
//	  } catch (InterruptedException ie) {
//                		System.out.println( ie );
//                		System.exit(0);
//  	} 
//	
//
  }


}


