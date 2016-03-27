     /*
 The class RTPTransmitter demostrates method of transmitting  a RTP stream
 using a RTPManager. To test the RTPTransmitter class  run  JMStudio in another 
 computer with  the open RTPSession  option.  In open RTPSession option use  as 
 session address the IP address of the machine in which RTPTransmitter is running . 
 The  port number of the RTPSession should be the same as  argument remotePort 
 used in the RTPTransmitter. The parameter remoteIPAddress of the RTPTransmitter 
 should be the IP address odf the machine in which the JMStudio is running.
 */
//import JMFUtil.*;

import javax.media.*;
import javax.media.rtp.*;
import javax.media.rtp.event.*;
import java.net.*;
import javax.media.rtp.rtcp.*;
import javax.media.protocol.*;
import java.io.*;
import javax.media.format.*;

public class RTPTransmitterAudio implements Runnable
{

    String remoteIPAddress = "192.168.0.17";
    int remotePort;
    int localPort;
    DataSource source = null;
    InetAddress destaddr = null;
    RTPManager rtpManager;
    Processor processor = null;
    DataSource output = null;
    SendStream stream = null;
    InetAddress localInetaddr;
    SessionAddress remoteAddr;
    Thread t1;

    public RTPTransmitterAudio(String remoteIPAddress, int remotePort, int localPort)
    {
        this.remoteIPAddress = remoteIPAddress;
        this.remotePort = remotePort;
        this.localPort = localPort;

        rtpManager = RTPManager.newInstance();

        try
        {
            localInetaddr = InetAddress.getLocalHost();
        } catch (java.net.UnknownHostException unknown)
        {
            System.out.println(unknown);
        }
        SessionAddress localaddr = new SessionAddress(localInetaddr, localPort);

        try
        {
            rtpManager.initialize(localaddr);
        } catch (InvalidSessionAddressException e)
        {
            System.out.println("Invalid SA" + e);
        } catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception" + ioe);
        }

        rtpManager.addSessionListener(new SessionListenerAdaptor());
        rtpManager.addSendStreamListener(new sendStreamListenerAdaptor());

        try
        {
            destaddr = InetAddress.getByName(remoteIPAddress);
        } catch (java.net.UnknownHostException unknown)
        {
            System.out.println(" Remote IP address error : " + unknown);
            rtpManager.dispose();
            return;
        }

        remoteAddr = new SessionAddress(destaddr, remotePort);
        try
        {
            rtpManager.addTarget(remoteAddr);

        } catch (InvalidSessionAddressException e)
        {
            System.out.println("Invalid SA" + e);
        } catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception" + ioe);
        }
        t1 = new Thread(this);
        t1.start();

    }

    public void run()
    {
	// Choose audio or video for transmission by commenting out the complement
        MediaLocator aLocator = captureDev.getAudioML() ;
//        MediaLocator aLocator = captureDev.getVideoML();
        //MediaLocator aLocator = new MediaLocator("file://c:/race.avi");  
        try
        {
            source = Manager.createDataSource(aLocator);

        } catch (NoDataSourceException nde)
        {
            System.out.println(nde);
        } catch (java.io.IOException ioe)
        {
            System.out.println(ioe);
        }
	// comment out the irrelevent line. Choose the desired encoding format.
        // "GSM" or "G723" or "DVI" for audio. "H263" or "JPEG" for video.
        processor = ProcFactory.createAudioProcessor( source,"GSM");
//        processor = ProcFactory.createAudioProcessor( source,"G723");
//        processor = ProcFactory.createAudioProcessor( source,"DVI");
        
//        processor = ProcFactory.createVideoProcessor(source, "JPEG");
//     processor = ProcFactory.createVideoProcessor(source, "MJPG");

        output = processor.getDataOutput();
        try
        {
            
            System.out.println(rtpManager);
            stream = rtpManager.createSendStream(output, 0);
            System.out.println(stream);
        } 
        catch (UnsupportedFormatException unsupp)
        {
            System.out.println(unsupp);
        } 
        catch (IOException e)
        {
            System.out.println(e);
        }

        try
        {
            stream.start();
        } 
        catch (IOException e)
        {
            System.out.println(e);
        }
        processor.start();

    }

    public void stopconfer()
    {

        try
        {
            if (stream != null)
            {
                stream.stop();
                stream.close();
            }
        } catch (IOException ioe)
        {
            System.out.println(ioe);
        }

        if (processor != null)
        {
            processor.stop();
            processor.close();
        }

        try
        {
            rtpManager.removeTarget(remoteAddr, "Stop requested");
        } catch (InvalidSessionAddressException e)
        {
            System.out.println(e);
        }
        rtpManager.dispose();
    }

    public static void main(String args[])
    {
        RTPTransmitterAudio transmitter = new RTPTransmitterAudio("172.16.7.95", 11000, 12000);
 
    }

}
