package pt.claiverken.conversormoeda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class GeradorDeFile {

    public void salvaJson(ConversaoFeita conversao) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String dataHoraFormatada = LocalDateTime.now().format(formatter);

        String nomeArquivo = String.format(
                "historico/conversao_%s_%d_%d_%s.json",
                dataHoraFormatada,
                System.currentTimeMillis(),
                System.nanoTime(),
                UUID.randomUUID()
        );

        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(gson.toJson(conversao));
            System.out.println("Conversão salva com sucesso: " + nomeArquivo);
            registrarLog("Conversão salva com sucesso no arquivo: " + nomeArquivo);
        } catch (IOException e) {
            String erro = "Erro ao salvar o arquivo: " + e.getMessage();
            System.out.println(erro);
            registrarLog(erro);
        }
    }

    private void registrarLog(String mensagem) {
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String idUnico = UUID.randomUUID().toString();

        try (FileWriter logWriter = new FileWriter("log.txt", true)) {
            logWriter.write("[" + dataHora + "] [" + idUnico + "] " + mensagem + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no log: " + e.getMessage());
        }
    }
}
