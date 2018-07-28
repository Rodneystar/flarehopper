package web;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.http.HttpStatus;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ReactHandler implements HttpHandler {
    private AppService rc;

    public ReactHandler(AppService appService) {
        this.rc = appService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream inS = exchange.getRequestBody();
        BufferedInputStream reader = new BufferedInputStream(inS);
        Headers headers = exchange.getRequestHeaders();
        OutputStream os = exchange.getResponseBody();
        String query = exchange.getRequestURI().getQuery();


        String response = null;
        try {
            response = genResponse();
            exchange.sendResponseHeaders(HttpStatus.SC_OK, response.length());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpStatus.SC_NOT_FOUND, response.length());
        }

        os.write(response.getBytes());
        os.close();

    }

    private String genResponse() throws URISyntaxException, IOException {
        StringBuilder builder = new StringBuilder();
            URI uri = this.getClass().getResource("/index.html").toURI();
            File pageList = new File(uri);
            BufferedReader reader = new BufferedReader(new FileReader(pageList));

            String line = reader.readLine();
            while(line != null){
                builder.append(line + "\n\r");
                line = reader.readLine();
            }

        return builder.toString();
    }
}
