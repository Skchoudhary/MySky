/* 
Example 7.2 This program can be used to find the audio and video capture devices. 
The static method getAudioML() returns the MediaLocator of an audio capture device
 if one such device is present and registered in JMF registry. There is a similar method
getVideoML() to return the MediaLocator of the video capture device.
*/ 

import javax.media.*;
import javax.media.format.*;
import java.util.*;
import java.awt.*;

public class captureDev {
	
	
     public static MediaLocator getAudioML() {
	AudioFormat aformat = new AudioFormat( AudioFormat.LINEAR,44100,16,1);
	Vector devices = CaptureDeviceManager.getDeviceList( aformat);
	
	CaptureDeviceInfo di = null;
	if( devices.size() >0 ){
		di = ( CaptureDeviceInfo) devices.elementAt(0);
		 return di.getLocator();
		} else {
	 		return null;
	}

   }

private static  Dimension size = new Dimension(640,480);
private static  float framerate = 15.0f;

    public static MediaLocator getVideoML() {
	
	//VideoFormat vformat = new javax.media.format.RGBFormat(); 
                      VideoFormat vformat = new VideoFormat(VideoFormat.MJPG,
				size, 
				Format.NOT_SPECIFIED,
                                              		Format.byteArray,
                                              		framerate);

	Vector devices = CaptureDeviceManager.getDeviceList( vformat);
	CaptureDeviceInfo di = null;
	if( devices.size() >0 ){
		di = ( CaptureDeviceInfo) devices.elementAt(0);
			 return di.getLocator();
		} else {
			return null;
		}

         }

public static void main( String args[] ) {
captureDev cap = new captureDev();
System.out.println( "A audio capture device detected : " + cap.getAudioML()   );
System.out.println(  "A video capture Device detected : " + cap.getVideoML() );
} 

}
