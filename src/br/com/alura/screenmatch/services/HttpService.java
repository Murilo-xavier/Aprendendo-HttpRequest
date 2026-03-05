package br.com.alura.screenmatch.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {
    private static final String API_KEY = "4d4d70c9";

    public static HttpResponse<String> buscarDados(String busca) throws IOException, InterruptedException {
        String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=" + API_KEY;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public static String buscarDadosString(String busca) throws IOException, InterruptedException {
        HttpResponse<String> response = buscarDados(busca);
        return response.body();
    }
}
