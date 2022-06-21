package vttp.workshop6.server;

import java.io.IOException;
import java.net.Socket;

public class CookieClientHandler implements Runnable{
    
    private Socket socket;
    private String cookieFile;
    public CookieClientHandler (Socket s, String file){
        this.socket = s;
        this.cookieFile = file;
    }

    @Override
    public void run(){
        System.out.println("Starting a client thread");
        NetworkIO netIO = null;

        try {
            netIO = new NetworkIO(socket);
            String req = "";
            String randomCookieResp = "";
            while(true){
                req = netIO.read();
                System.out.printf("[client] %s\n", req);
                if(req.trim().equals("exit"))
                    break;
                if (req.trim().equals("get-cookie")) {
                    System.out.printf("file -> %s\n", this.cookieFile);
    
                    randomCookieResp = Cookie.getRandomCookie(this.cookieFile);
                    netIO.write("cookie-text,"+ randomCookieResp);
                    break;
                }else{
                    netIO.write("error,invalid command");
                    break;
                }
            }

            netIO.close();
            socket.close();
            System.out.println("Exiting the thread");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
