import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Server {
    Server() {
        boolean cont=true;
        try {
            Scanner scan=new Scanner(System.in);
            while(true){
                ServerSocket server = new ServerSocket(22000);
                Socket client = server.accept();
                DataInputStream clientReadSource = new DataInputStream(client.getInputStream());
                DataOutputStream clientWriteSource = new DataOutputStream(client.getOutputStream());
                clientWriteSource.writeUTF("Server : You are connected");
                clientWriteSource.writeUTF("We can have a nice chat!");
                //in the following loop server side exchanges messages with client side endlessly until one of two sides types 'exit'.
                while (true){
                    String str = clientReadSource.readUTF();
                    System.out.println("Client : "+str);
                    str = scan.nextLine();
                    clientWriteSource.writeUTF(str);
                    if (str.equalsIgnoreCase("exit")){
                        cont=false;
                        break;
                    }
                }
                clientWriteSource.close();
                clientReadSource.close();
                client.close();
                if(cont==false){
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}