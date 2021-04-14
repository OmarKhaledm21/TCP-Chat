import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//this is the client sides that operates on second run where it connect with server side using ip,port no. as needed for connection and then sends the first message.
class Client {
    Client() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println(ip);
            Socket other = new Socket(ip, 22000);
            Scanner scan = new Scanner(System.in);
            DataInputStream otherReadSource = new DataInputStream(other.getInputStream());
            DataOutputStream otherWriteSource = new DataOutputStream(other.getOutputStream());
            String str = "";
            str = otherReadSource.readUTF();
            System.out.println(str);
            //here the client side starts with the first message after connection and then exchanges messages in return of each received message.
            while (true){
                str = otherReadSource.readUTF();
                System.out.println("Server : "+str);
                str = scan.nextLine();
                otherWriteSource.writeUTF(str);
                if (str.equalsIgnoreCase("exit")){
                    break;
                }
            }
            otherReadSource.close();
            otherWriteSource.close();
            other.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
