/*
 * Copyright (C) 2024 Sallai András
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hu.szit.resclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

/**
 * REST API client.
 * 
 * This class using the Java 11 HttpClient class.
 * 
 * Example:
 * <pre>
 * ResClientAsync client = new ResClientAsync();
 * String result = client.get(url).join();
 * System.out.println(result);
 * </pre>
 * 
 * @author Sallai András
 * @author szit.hu
 * @version 1.1.2
 * @see     HttpClient
 */
public class ResClientAsync {
    HttpClient client;

    /**
     * Create a instance of the Client class.
     */
    public ResClientAsync() {
        this.client = HttpClient.newHttpClient();        
    }

    /**
     * The method request a resource from the server.
     * The result is a string.
     * 
     * @param     url    The server URL
     * @param     token  Bearer token for authentication. Not necessary.
     * @return    Returns a CompletableFuture containing the answer as String.
     */
    public CompletableFuture<String> get(String url, String... token) {
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
     * @return      Returns a CompletableFuture containing the answer as String.
     */    
    public CompletableFuture<String> post(String url, String body, String... token) {
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
     * @return      Returns a CompletableFuture containing the answer as String.
     */      
    public CompletableFuture<String> put(String url, String body, String... token) {
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
     * @return      Returns a CompletableFuture containing the answer as String.
     */     
    public CompletableFuture<String> delete(String url, String... token) {
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

    private CompletableFuture<String> sendRequest(HttpRequest request) {
        
        try {
            return trySendRequest(request);                     
        } catch (IOException e) {
            System.err.println("Hiba! A lekérés sikertelen!");
            System.err.println(e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.err.println("Hiba! Megszakadt lekérés!");
            System.err.println(e.getMessage());
            return null;
        }        
    }
    private CompletableFuture<String> trySendRequest(HttpRequest request) 
            throws IOException, InterruptedException {
        return this.client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body);
        
    }
}
