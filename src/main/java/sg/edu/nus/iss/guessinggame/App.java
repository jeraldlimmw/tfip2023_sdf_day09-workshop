package sg.edu.nus.iss.guessinggame;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
// import java.util.Scanner;

public final class App {

    public static void main(String[] args) {
        
        // new random class
        Random rand = new Random();
        // get random number between 0 to 99 inclusive
        int randomNumber = rand.nextInt(100);

        int myGuess = -1;
        int guesses = 0;

        // original method to get user input from keyboard:
        // Scanner scanner = new Scanner(System.in);
        
        // convert to expect from InputStream if its a socket app
        try (
            ServerSocket ss = new ServerSocket(1234)) {
            System.out.println("Server now running on port 1234");
            
            // wait to accept client connection
            Socket s = ss.accept();

            // prepare input coming from socket
            InputStream is = s.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            // prepare sending data out to client using socket
            OutputStream os = s.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);
            
            String msgRcv = "";
            
            // allow user to guess as many times until they get random number
            while (!msgRcv.equalsIgnoreCase("quit")) {
                // System.out.println("What is your guess?");
                // myGuess = scanner.nextInt();
                msgRcv = dis.readUTF(); // msg format guess XX
                
                if (msgRcv.contains("guess")) {
                    myGuess = Integer.parseInt(msgRcv.substring(6));
                }

                guesses++;
                if (myGuess < randomNumber) {
                    dos.writeUTF("Your guess is lower");
                } else if (myGuess > randomNumber) {
                    dos.writeUTF("Your guess is higher");
                } else {
                    dos.writeUTF("You have guessed it right in " + guesses + " tries!");
                    dos.flush();
                    break;
                }
                // ensure records are written and sent to the client
                dos.flush();
            }
            dos.close();
            bos.close();
            os.close();

            dis.close();
            bis.close();
            is.close();

            s.close();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        } 
    }
}
