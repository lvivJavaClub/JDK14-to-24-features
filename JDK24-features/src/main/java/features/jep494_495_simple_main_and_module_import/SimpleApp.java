package features.jep494_495_simple_main_and_module_import;

import module jdk.httpserver;
import module java.base;

import java.sql.Date;
import java.io.IOException;
import java.net.InetSocketAddress;

// java --enable-preview src/main/java/features/jep494_495_simple_main_and_module_import/SimpleApp.java

public class SimpleApp {

  public static void main(String[] args) throws IOException {

    var httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
    httpServer.createContext("/", exchange -> {
      String response = "Hello, Java!";
      exchange.sendResponseHeaders(200, response.length());
      try (var outputStream = exchange.getResponseBody()) {
        outputStream.write(response.getBytes());
      }
    });
    httpServer.start();
    System.out.println("Server started at http://localhost:8080");
  }
}
