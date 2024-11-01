package hu.szit;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * REST API client.
 * 
 * This class using the Java 11 HttpClient class.
 * 
 * Example:
 * <pre>
 * ResClient client = new ResClient();
 * String result = client.get(url);
 * System.out.println(result);
 * </pre>
 * 
 * @author Sallai András
 * @author szit.hu
 * @version 1.1.2
 * @see     HttpClient
 */
public class ResClient {
    HttpClient client;

    /**
     * Create a instance of the Client class.
     */
    public ResClient() {
        this.client = HttpClient.newHttpClient();        
    }

    /**
     * The method request a resource from the server.
     * The result is a string.
     * 
     * @param     url    The server URL
     * @param     token  Bearer token for authentication. Not necessary.
     * @return           The response as JSON string.
     */
    public String get(String url, String... token) {
        HttpRequest request = genGetRequest(url, token);
        return sendRequest(request);
    }
    private HttpRequest genGetRequest(String url, String... args) {
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        if(args.length > 0) {
            String token = args[0];
            builder.header("Authorization", "Bearer " + token);            
        }
        return builder.build();
    }

    /**
     * The method create a new resource on the server.
     * The result is the created resource as JSON string.
     * 
     * @param       url     The server URL.
     * @param       body    The new resource as JSON string.
     * @param       token   Bearer token for authentication. Not necessary.
     * @return      The created resource as JSON string.
     */    
    public String post(String url, String body, String... token) {
        HttpRequest request = this.genPostRequest(url, body, token);        
        return this.sendRequest(request);
    }
    private HttpRequest genPostRequest(String url, String body, String... args) {
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        builder.header("Content-Type", "application/json");
        if(args.length > 0) {
            String token = args[0];
            builder.header("Authorization", "Bearer " + token);            
        }        
        builder.POST(HttpRequest.BodyPublishers.ofString(body));
        return builder.build();
    }

    /**
     * The method modify an existing resource on the server.
     * The result is the modified resource as JSON string.
     * 
     * @param       url     The url of the server.
     * @param       body    The modified resource as JSON string.
     * @param       token   The Bearer token for authentication. Not necessary.
     * @return      The modified resource as JSON string.
     */      
    public String put(String url, String body, String... token) {
        HttpRequest request = this.genPutRequest(url, body, token);
        return this.sendRequest(request);
    }
    private HttpRequest genPutRequest(String url, String body, String... args) {
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        builder.header("Content-Type", "application/json");
        if(args.length > 0) {
            String token = args[0];
            builder.header("Authorization", "Bearer " + token);            
        }
        builder.PUT(HttpRequest.BodyPublishers.ofString(body));
        return builder.build();
    }

    /**
     * The method delete a resource from the server.
     * The result is {}, if the operation was successful.
     * 
     * @param       url     The url of the server.
     * @param       token   The Bearer token for authentication. Not necessary.
     * @return      The result is {}, if the operation was successful.
     */     
    public String delete(String url, String... token) {
        HttpRequest request = this.genDeleteRequest(url, token);
        return this.sendRequest(request);
    }
    private HttpRequest genDeleteRequest(String url, String... args) {
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        builder.header("Content-Type", "application/json");
        if(args.length > 0) {
            String token = args[0];
            builder.header("Authorization", "Bearer " + token);            
        }
        builder.DELETE();
        return builder.build();
    }

    private String sendRequest(HttpRequest request) {
        String result = "";
        try {
            result = trySendRequest(request);                     
        } catch (IOException e) {
            System.err.println("Hiba! A lekérés sikertelen!");
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Hiba! Megszakadt lekérés!");
            System.err.println(e.getMessage());            
        }
        return result;
    }
    private String trySendRequest(HttpRequest request) 
            throws IOException, InterruptedException {
        HttpResponse<String> response = 
        this.client.send(request, BodyHandlers.ofString());        
        return response.body();
    }
}
