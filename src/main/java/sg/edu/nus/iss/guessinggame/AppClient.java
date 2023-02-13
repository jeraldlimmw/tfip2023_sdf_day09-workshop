package sg.edu.nus.iss.guessinggame;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class AppClient {
    public static void main (String args[]) throws UnknownHostException, IOException {
        
        String keyInput = "", msgRcv = "";

        Socket sock = new Socket("localhost", 1234);
        System.out.println("Client connected to port 1234");

        InputStream is = sock.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        OutputStream os = sock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        Console cons = System.console();

        while (!keyInput.equalsIgnoreCase("quit")) {
            // get input from user
            keyInput = cons.readLine("Enter 'guess XX' (XX refers to number to guess): ");
            
            // send input to server
            dos.writeUTF(keyInput);
            dos.flush();

            // receive msg from server and print msg
            msgRcv = dis.readUTF();
            System.out.println("From server: " + msgRcv);
            if (msgRcv.contains("right")) { // cause the program to quit when correct
                keyInput = "quit";
            }
        }
        
        dos.close();
        bos.close();
        os.close();
        sock.close();
    }
}
