import java.util.Scanner;

/**
 *
 * @author Omar Khaled
 */

public class TCP_Chat {
    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        if (scan.next().equals("server")){
            Server s = new Server();
        }
        else{
            Client c = new Client();
        }
    }
}


