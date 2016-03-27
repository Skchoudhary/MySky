
/*
*******************************************************************************
The class sendStreamListenerAdaptor listens to the sendStream of a session and 
invokes the appropriate sendStreamevent handlers. The handlers simply print the
details of the event on their occurance. You have to extend this class and override
appropriate handlers if certain sendStreamEvents have to be handled specifically.

*******************************************************************************
*/


import javax.media.rtp.*;
import javax.media.rtp.event.*;


public class sendStreamListenerAdaptor implements SendStreamListener {


public void update( SendStreamEvent event ) {
 

	if (event  instanceof ActiveSendStreamEvent){
		ActiveSendStreamEventHandler(event);		
	  }
	else if( event  instanceof InactiveSendStreamEvent) {
		InactiveSendStreamEventHandler(event);
	  }
	 else if (event  instanceof LocalPayloadChangeEvent ) {
		LoaclPayloadChangeEventHandler(event);	
     	  }
	 else if (event  instanceof NewSendStreamEvent ) {
		NewSendStreamHandler(event);
	  }
	 else if (event  instanceof StreamClosedEvent ){
		StreamClosedEventHandler(event);
	 }
	 else {
	     System.out.println( " Unknown Send stream Event detected \n ");

  	 } 



  } // update


public void ActiveSendStreamEventHandler(SendStreamEvent event){
	System.out.println( "Active Send Stream detected \n " + event); 
	
	}

public void InactiveSendStreamEventHandler(SendStreamEvent event) {
	System.out.println( "InActive Send Stream detected \n  "+ event ); 
	}	
public void LoaclPayloadChangeEventHandler(SendStreamEvent event) {
	System.out.println( "Local Payload change detected \n " + event); 
	}
public void NewSendStreamHandler(SendStreamEvent event){
	System.out.println( "New Send Stream Event detected \n " + event); 
	}
public void StreamClosedEventHandler(SendStreamEvent event) {
	System.out.println( "Stream closed Event \n " + event); 
	}

} // class sendStream
