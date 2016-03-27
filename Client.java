import java.net.*;
import java.io.*;

public class Client 
{
    DataInputStream dis;
    DataOutputStream dos;
    Socket sock;
    Client() throws IOException
    {
        sock=new Socket("127.0.0.1",9001);
        dis=new DataInputStream(sock.getInputStream());
        dos=new DataOutputStream(sock.getOutputStream());
        dos.writeBytes("connected \r\n");
        dos.flush();
        while(true)
        {
            
        }
    }
    
    
    public static void main(String[] args) throws IOException 
    {
        Client c=new Client();
        
    }
    
}
