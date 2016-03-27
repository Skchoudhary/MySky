/*   
 The class ProcFactory is a factory to create audio Processor and video Processor
 for the RTPTransmitter application. The static  method createAudioProcessor returns 
 an audioProcessor with a RTP encoded audio track. It takes the DataSource of an
 audio source and a String representing the audio encoding format as its arguments.
 The static method createVideoProcessor returns a video Processor. It  takes the
 DataSource of a video source and a String representing the video encoding format 
 as its arguments. For audio the String encoding can be "GSM" or "G723" or "DVI".
 For video the String encoding can be "H263" or "JPEG".
 */
//import JMFUtil.*;
import javax.media.*;
import java.io.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.control.*;
import java.awt.Dimension;
import javax.swing.*;
public class ProcFactory
{
    public static Processor createVideoProcessor(DataSource datasource, String encoding)
    {
        Processor processor = null;
        VideoFormat format = new VideoFormat(VideoFormat.H263_RTP);
        if (encoding.equals("H263"))
        {
            format = new VideoFormat(VideoFormat.H263_RTP);
        } else if (encoding.equals("JPEG"))
        {
            
            format = new VideoFormat(VideoFormat.JPEG_RTP);
            System.out.println("JPEG VIDEO FORMAT SET");
            
        } 
        else if (encoding.equals("MPEG"))
        {
            format = new VideoFormat(VideoFormat.MPEG_RTP);
        }
        else
        {
            format = new VideoFormat(VideoFormat.MJPG);
        }
        
       
        try
        {
            processor = Manager.createProcessor(datasource);
        } 
        catch (IOException ioe)
        {
            System.out.println(ioe);
        } catch (NoProcessorException npe)
        {
            System.out.println(npe);
        }
        procUtil util = new procUtil(processor);
        // Wait for it to configure
        boolean result = util.syncConfigure();
        if (result == false)
        {
            return null;
        }
        Format setFormat = util.setVideoFormat(format);
        System.out.println(" Video Format set to : " + setFormat);
        ContentDescriptor cd = new ContentDescriptor(ContentDescriptor.RAW);
        processor.setContentDescriptor(cd);
        result = util.syncRealize();
        if (result == false)
        {
            System.out.println("Couldn't realize processor \n ");
        }
        return processor;
    }
    public static Processor createAudioProcessor(DataSource source, String encoding)
    {
        AudioFormat aFormat = new AudioFormat(AudioFormat.GSM_RTP);
        Processor aProcessor = null;
        if (encoding == "GSM")
        {
            System.out.println("hello GSM");
            aFormat = new AudioFormat(AudioFormat.GSM_RTP, 8000, 8, 1);
        } 
        else if (encoding == "G723")
        {
            aFormat = new AudioFormat(AudioFormat.G723_RTP, 8000, 8, 1);
        } else if (encoding == "DVI")
        {
            aFormat = new AudioFormat(AudioFormat.DVI_RTP, 8000, 4, 1, AudioFormat.BIG_ENDIAN, AudioFormat.SIGNED);
        } else
        {
            aFormat = new AudioFormat(AudioFormat.G723_RTP, 8000, 8, 1);
        }
        try
        {
            aProcessor = Manager.createProcessor(source);
        } catch (NoProcessorException npe)
        {
            System.out.println("Couldn't create processor" + npe);
        } catch (IOException ioe)
        {
            System.out.println("IOException creating processor" + ioe);
        }
        procUtil util = new procUtil(aProcessor);
        // Wait for it to configure
        boolean result = util.syncConfigure();
        if (result == false)
        {
            System.out.println("Couldn't configure audio processor");
            return null;
        }
        Format setFormat = util.setAudioFormat(aFormat);
//        System.out.println(" Audio Format set to : " + setFormat);
        // Set the output content descriptor to RAW
        ContentDescriptor cd = new ContentDescriptor(ContentDescriptor.RAW);
        aProcessor.setContentDescriptor(cd);
        result = util.syncRealize();
        if (result == false)
        {
            System.out.println("Couldn't realize audio processor \n");
        }
        return aProcessor;
    }
} // END ProcFactory
