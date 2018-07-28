package web;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppController {


    private boolean active;
    private AppService appService;


    public void start() {

        final ExecutorService ex = Executors.newFixedThreadPool(5);

        try {
            URI u = new URI("http://localhost:8000/");
            System.out.println(u.getPath());
            HttpServer myserver = HttpServer.create(new InetSocketAddress(8000), 0);
            myserver.createContext("/" , new ReactHandler(appService)); //change for diff front ends

            myserver.setExecutor(ex);

            myserver.start();
        }catch(Exception e){
            System.out.println("failed");
        }
    }
}
