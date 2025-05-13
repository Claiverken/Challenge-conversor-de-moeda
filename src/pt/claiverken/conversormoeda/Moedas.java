package pt.claiverken.conversormoeda;

import java.util.Map;

public record Moedas(String result, String base_code, Map<String, Double> conversion_rates) {
}
