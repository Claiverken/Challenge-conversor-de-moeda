public record ConversaoFeita(
        String moedaOrigem,
        String moedaDestino,
        double valorOriginal,
        double taxaDeConversao,
        double valorConvertido,
        String dataHora
) {}
