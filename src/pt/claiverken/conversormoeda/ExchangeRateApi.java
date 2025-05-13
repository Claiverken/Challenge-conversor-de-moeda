package pt.claiverken.conversormoeda;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateApi {

    public Moedas converterMoeda(String moedaBase){
        String endereco = "https://v6.exchangerate-api.com/v6/390c970bb33a6f812b768126/latest/" + moedaBase;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moedas.class);
        } catch (Exception e) {
            System.out.println("Erro ao tentar buscar a cotação da moeda: " + e);
            return null;
        }
    }
}
