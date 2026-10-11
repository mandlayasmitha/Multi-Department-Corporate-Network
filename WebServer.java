import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
public class WebServer {
    private HttpServer server;
    private int requestCount = 0;
    public void start() throws IOException {
        server = HttpServer.create(
                new InetSocketAddress(8080), 0);
        server.createContext("/", this::handleRequest);
        server.setExecutor(null);
        server.start();
        System.out.println("Web Server started on port 8080");
    }
    private void handleRequest(HttpExchange exchange)
            throws IOException {
        requestCount++;
        String html = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Corporate Network</title>
                    <style>
                        body {
                            font-family: Arial;
                            text-align: center;
                            margin-top: 80px;
                        }
                        h1 {
                            color: #1f4e79;
                        }
                        .box {
                            width: 400px;
                            margin: auto;
                            padding: 25px;
                            border: 1px solid gray;
                            border-radius: 10px;
                        }
                    </style>
                </head>
                <body>
                <div class="box">
                    <h1>ABC CORPORATION</h1>
                    <h2>Corporate Network</h2>
                    <p>Welcome to the company web server.</p>
                    <p>
                    Services:
                    DHCP | DNS | HTTP
                    </p>
                    <p>Server Status: ONLINE</p>
                </div>
                </body>
                </html>
                """;
        byte[] response =
                html.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders()
                .set("Content-Type", "text/html");
        exchange.sendResponseHeaders(
                200, response.length);
        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }
    public int getRequestCount() {
        return requestCount;
    }
    public boolean isRunning() {
        return server != null;
    }
}