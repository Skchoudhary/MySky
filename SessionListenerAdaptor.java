/* 
************************************************************************************
This class is  a simple ( or skeleton) sessionListener which listenes to all session
events and prints on System.out the details of event. This class can be adapted by
extending and overriding the appropriate event handlers.

**************************************************************************************
*/ 

import javax.media.rtp.event.*;
import javax.media.rtp.*;

public class SessionListenerAdaptor implements SessionListener {

 public void update( SessionEvent event ) {
	
	if( event instanceof LocalCollisionEvent ) {
		LocalCollisionEventHandler(event);
	} else if ( event instanceof NewParticipantEvent) {
		NewParticipantEventHandler(event);
	  }

 }


public void LocalCollisionEventHandler( SessionEvent event) {

	System.out.println( "Session Event: LocalCollisionEvent" + event + "\n" ); 
	}

public void NewParticipantEventHandler( SessionEvent event ) {

	Participant participant = ( ( NewParticipantEvent) event ).getParticipant();
	
        System.out.println( "Session Event: NewParticipantEvent from" + participant.getCNAME() + event + "\n" ); 
	
	} 


}
