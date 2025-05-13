import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

public class ExchangeRateApi {
    String endereco = "https://v6.exchangerate-api.com/v6/390c970bb33a6f812b768126/latest/" + moeda;

    public Moedas converterMoeda(String moeda) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body().toLowerCase(), Moedas.class);
        } catch (Exception e) {
            System.out.println("Erro ao tentar buscar a cotação da moeda: " + e);
        }
    }
}
