package vttp.workshop6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Server App Started" );

        int port = Integer.parseInt(args[0]);
        String cookieFile = args[1];

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        ServerSocket server = new ServerSocket(port);

        while(true){
            System.out.println("Waiting for client connection");
            Socket socket = server.accept();
            System.out.println("Connected");
            CookieClientHandler thr = new CookieClientHandler(socket, cookieFile);
        }

    }
}
