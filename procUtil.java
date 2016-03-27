/* procUtil.java is a utility class that can be used while configuring and constructing a
Processor. To use it, instantiate  procUtil.class by passing the Processor to be
configured and constructed as the argument. Use syncConfigure(), syncRealize() 
and syncPrefetch() methods of the procUtil to configure, realize and prefetch the Processor.
The method displayGUI() can be used to display the visual and controlPanel component of the 
Processor. The procUtil allows you to set the format of the audio track  or video track, set a 
codec chain in the audio or video track ,set an audio or video renderer. 
*/

import javax.media.*;
import javax.media.protocol.*;
import java.io.*;
import javax.media.format.*;
import java.util.*;
import javax.media.control.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class procUtil{

final Processor processor;

public procUtil( Processor p){
processor = p;
processor.addControllerListener(new StateListener());

}


/*  ***************************************************************************************************
Implementation of syncConfigure(), syncRealize() and syncPrefetch().
*/
 
private   Integer stateLock = new Integer(0);
private   boolean failed = false;

  Integer getStateLock() {
	return stateLock;
       }
   
  void setFailed() {
	failed = true;
    }

public boolean syncConfigure(){
failed = false;
processor.configure();
return waitForState( Processor.Configured );
}

public boolean syncRealize(){
failed = false;
processor.realize();
return waitForState( Processor.Realized );
}

public boolean syncPrefetch(){
failed = false;
processor.prefetch();
return waitForState(Processor.Prefetched );
}

  public   synchronized boolean waitForState( int state) {
	
		
     while (processor.getState() < state && !failed) {
     synchronized( getStateLock()) 
	         {
		    try {  
		        getStateLock().wait();
		    } catch (InterruptedException ie) {  return false; }
	         }
	} // END while

    if (failed ) {  return false;  } else { return true; }
    
  }  // END waitForState    


 class  StateListener implements ControllerListener {
              
            public void controllerUpdate(ControllerEvent ce) {
     	     
	    if (ce instanceof ControllerClosedEvent)
	     { 	
		setFailed();
		 synchronized (getStateLock()) 
             	     	 {         getStateLock().notifyAll();
             	     	}

	     } else  if (ce instanceof ConfigureCompleteEvent ||
	       	 ce instanceof RealizeCompleteEvent ||
	         	 ce instanceof PrefetchCompleteEvent  )
	                 {
	    
        	   	  synchronized (getStateLock()) 
             	      	 {      getStateLock().notifyAll();
             	     	 }
	      }    
            }  // ControllerUpdate
 }      // StateListener


/* *******************************************************************************************
 Implementation of syncConfigure(), syncRealize() and syncPrefetch() over
*/

public  JFrame displayGUI(){
		 JFrame frame = new JFrame("JMF Processor ");			
		
		Component visual = processor.getVisualComponent();
		if( visual != null) {
			frame.getContentPane().add( visual, BorderLayout.CENTER);
		}
			
		Component  control = processor.getControlPanelComponent();
		if( control != null) {
		frame.getContentPane().add(control, BorderLayout.SOUTH); 
		}
                 
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				processor.stop();
				processor.close();
				System.exit(0);			
	 		}
		} );
		frame.pack();
		return frame;
				
	}

public Format setAudioFormat( AudioFormat opFormat) {

	TrackControl track[] = processor.getTrackControls();
		
	
		for (int i = 0; i < track.length; i++) 
		{	Format format = track[i].getFormat();
				
			if (  track[i] instanceof FormatControl && format instanceof  AudioFormat ) 
			{
				Format setFormat =(	(FormatControl)track[i] ).setFormat( opFormat );			
				return setFormat;
				
			} 
			
                                       }
		return null;
}

public Format setVideoFormat( VideoFormat opFormat) {

	TrackControl track[] = processor.getTrackControls();
		
	
		for (int i = 0; i < track.length; i++) 
		{	Format format = track[i].getFormat();
				
			if (  track[i] instanceof FormatControl && format instanceof  VideoFormat ) 
			{
				Format setFormat =(	(FormatControl)track[i] ).setFormat( opFormat );			
				return setFormat;
				
			} 
			
                                       }
		return null;
}

public  void setAudioRenderer( javax.media.Renderer audioRenderer ){
	TrackControl track[] = processor.getTrackControls();
	
		for (int i = 0; i < track.length; i++) 
		{	Format format = track[i].getFormat();
				
	                         if (  track[i] instanceof FormatControl && format instanceof  AudioFormat ) 
		      {  try{
			   track[i].setRenderer( audioRenderer);
		               }catch(UnsupportedPlugInException unsupp){
			System.out.println("Error in setAudioRenderer ; " + unsupp );
		               }		
				
		       } 
			
                                       }
		
}

public  void setVideoRenderer( javax.media.Renderer videoRenderer ){
	TrackControl track[] = processor.getTrackControls();
	
		for (int i = 0; i < track.length; i++) 
		{	Format format = track[i].getFormat();
				
	                         if (  track[i] instanceof FormatControl && format instanceof  VideoFormat ) 
		      {  try{
			   track[i].setRenderer( videoRenderer);
		               }catch(UnsupportedPlugInException unsupp){
			System.out.println("Error in setVideoRenderer ; " + unsupp );
		               }		
				
		       } 
			
                                       }
		
}

public  void setAudioCodecs( Codec [] codecs ){
	TrackControl track[] = processor.getTrackControls();
	
		for (int i = 0; i < track.length; i++) 
		{	Format format = track[i].getFormat();
				
	                         if (  track[i] instanceof FormatControl && format instanceof  AudioFormat ) 
		      {  try{
			   track[i].setCodecChain( codecs);
		               }catch(UnsupportedPlugInException unsupp){
			System.out.println("Error in setVideoRenderer ; " + unsupp );
		               }		
				
		       } 
			
                                       }
		
}

public  void setVideoCodecs( Codec [] codecs ){
	TrackControl track[] = processor.getTrackControls();
	
		for (int i = 0; i < track.length; i++) 
		{	Format format = track[i].getFormat();
				
	                         if (  track[i] instanceof FormatControl && format instanceof  VideoFormat ) 
		      {  try{
			   track[i].setCodecChain( codecs);
		               }catch(UnsupportedPlugInException unsupp){
			System.out.println("Error in setVideoRenderer ; " + unsupp );
		               }		
				
		       } 
			
                                       }
		
}


}  // end procUtil.java