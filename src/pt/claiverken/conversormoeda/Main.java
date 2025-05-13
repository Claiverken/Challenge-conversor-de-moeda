package pt.claiverken.conversormoeda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateApi api = new ExchangeRateApi();

        while (true) {
            exibirMenu();
            int opcao = lerOpcao(scanner);

            if (opcao == 7) {
                System.out.println("*******************************************************");
                System.out.println("*                 Programa Finalizado                 *");
                System.out.println("*******************************************************");
                break;
            }

            String moedaBase = "";
            String moedaAlvo = "";

            switch (opcao) {
                case 1 -> { moedaBase = "USD"; moedaAlvo = "BRL"; }
                case 2 -> { moedaBase = "BRL"; moedaAlvo = "USD"; }
                case 3 -> { moedaBase = "ARS"; moedaAlvo = "USD"; }
                case 4 -> { moedaBase = "USD"; moedaAlvo = "ARS"; }
                case 5 -> { moedaBase = "CLP"; moedaAlvo = "USD"; }
                case 6 -> { moedaBase = "USD"; moedaAlvo = "CLP"; }
            }

            System.out.printf("\n\uD83D\uDD04 Buscando taxas de câmbio para %s...\n", moedaBase);

            Moedas moedas = api.converterMoeda(moedaBase);
            if (moedas != null) {
                Double taxa = moedas.conversion_rates().get(moedaAlvo);
                if (taxa != null) {
                    System.out.print("\uD83D\uDCB5 Digite o valor que deseja converter de " + moedaBase + " para " + moedaAlvo + ": ");
                    try {
                        double valorOriginal = scanner.nextDouble();
                        scanner.nextLine();

                        double valorConvertido = valorOriginal * taxa;
                        System.out.println("============== \uD83D\uDCB1Conversão feita com sucesso\uD83D\uDCB1 ==============");
                        System.out.printf("\uD83D\uDCC8 1 %s = %.2f %s\n", moedaBase, taxa, moedaAlvo);
                        System.out.printf("\uD83D\uDCB0 %.2f %s = %.2f %s\n", valorOriginal, moedaBase, valorConvertido, moedaAlvo);
                        System.out.println("=============================================================");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        String dataHora = java.time.LocalDateTime.now().toString();

                        ConversaoFeita conversao = new ConversaoFeita(
                                moedaBase,
                                moedaAlvo,
                                valorOriginal,
                                taxa,
                                valorConvertido,
                                dataHora
                        );

                        GeradorDeFile gerador = new GeradorDeFile();
                        gerador.salvaJson(conversao);
                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_RED + "Valor inválido. Por favor, digite um número." + ANSI_RESET);
                        scanner.nextLine();
                    }
                } else {
                    System.out.println(ANSI_RED + "Moeda de destino não encontrada." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Erro ao buscar taxa de câmbio." + ANSI_RESET);
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println("********* \uD83D\uDCB1 Bem-vindo ao Conversor de pt.claiverken.conversormoeda.Moedas \uD83D\uDCB1 *********");
        System.out.println("---------------------------------------------------------");
        System.out.println("============== \uD83D\uDCC4 Menu de Conversão \uD83D\uDCC4 ==============");
        System.out.println("1\uFE0F⃣ Dólar (USD)  >>  Real (BRL)");
        System.out.println("2\uFE0F⃣ Real (BRL)  >>  Dólar (USD)");
        System.out.println("3\uFE0F⃣ Peso argentino (ARS)  >>  Dólar (USD)");
        System.out.println("4\uFE0F⃣ Dólar (USD)  >>  Peso argentino (ARS)");
        System.out.println("5\uFE0F⃣ Peso chileno (CLP)  >>  Dólar (USD)");
        System.out.println("6\uFE0F⃣ Dólar (USD)  >>  Peso chileno (CLP)");
        System.out.println("7\uFE0F⃣ Sair");
        System.out.println("====================================================");
    }

    private static int lerOpcao(Scanner scanner) {
        while (true) {
            try {
                System.out.print("➡\uFE0F Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao >= 1 && opcao <= 7) {
                    return opcao;
                }
                System.out.println(ANSI_RED + "❌Opção inválida.❌" + ANSI_RESET);
                System.out.println("\uD83D\uDC49\uD83C\uDFFB Digite um número entre 1 e 7. \uD83D\uDC48\uD83C\uDFFB");
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Entrada inválida. Tente novamente." + ANSI_RESET);
                scanner.nextLine();
            }
        }
    }
}