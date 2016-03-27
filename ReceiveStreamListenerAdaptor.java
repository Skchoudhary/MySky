/*
*******************************************************************************
The class ReceiveStreamListenerAdaptor listens to the receiveStreams of a RTP session and 
invokes the appropriate receiveStreamevent handlers. The handlers simply print the
details of the event on their occurance. You have to extend this class and override
appropriate handlers if certain receiveStreamEvents have to be handled specially.
For example the class Receiver overrides and handles newReceiveStream events. 

*******************************************************************************
*/

import javax.media.rtp.*;
import javax.media.rtp.event.*;

public class ReceiveStreamListenerAdaptor implements ReceiveStreamListener {

public void update( ReceiveStreamEvent event ) {

if( event instanceof   ActiveReceiveStreamEvent ) {
	ActiveReceiveStreamEventHandler(event); 
} else if( event instanceof   ApplicationEvent ) {
	ApplicationEventHandler(event);
} else if( event instanceof   InactiveReceiveStreamEvent ) {
	InactiveReceiveStreamEventHandler(event);
} else if( event instanceof   NewReceiveStreamEvent ) {
	NewReceiveStreamHandler(event);
} else if( event instanceof   RemotePayloadChangeEvent ) {
	RemotePayloadChangeEventHandler(event);
} else if( event instanceof   StreamMappedEvent ) {
	StreamMappedEventHandler(event);
} else if( event instanceof   TimeoutEvent ) {
	TimeoutEventHandler(event);
} else {
	System.out.println( "UnKnown ReceiveStreamEvent \n");
}


} //update

public void ActiveReceiveStreamEventHandler(ReceiveStreamEvent event){
 System.out.println( event );
}

public void ApplicationEventHandler(ReceiveStreamEvent event){
 System.out.println( event );
}

public void InactiveReceiveStreamEventHandler(ReceiveStreamEvent event){

}

public void NewReceiveStreamHandler(ReceiveStreamEvent event){
  System.out.println( event );
}

public void RemotePayloadChangeEventHandler(ReceiveStreamEvent event){
  System.out.println( event );
}

public void StreamMappedEventHandler(ReceiveStreamEvent event){
  System.out.println( event );
}

public void TimeoutEventHandler(ReceiveStreamEvent event){
  System.out.println( event );
}



}
