import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateApi api = new ExchangeRateApi();

        while (true) {
            exibirMenu();
            int opcao = lerOpcao(scanner);

            if (opcao == 7) {
                System.out.println("Saindo do programa.");
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
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

            Moedas moedas = api.converterMoeda(moedaBase);
            if (moedas != null) {
                Double taxa = moedas.conversion_rates().get(moedaAlvo);
                if (taxa != null) {
                    System.out.print("Digite o valor que deseja converter de " + moedaBase + " para " + moedaAlvo + ": ");
                    try {
                        double valorOriginal = scanner.nextDouble();
                        scanner.nextLine(); // limpa o buffer

                        double valorConvertido = valorOriginal * taxa;
                        System.out.printf("1 %s = %.2f %s\n", moedaBase, taxa, moedaAlvo);
                        System.out.printf("%.2f %s = %.2f %s\n", valorOriginal, moedaBase, valorConvertido, moedaAlvo);
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido. Por favor, digite um número.");
                        scanner.nextLine(); // limpa entrada inválida
                    }
                } else {
                    System.out.println("Moeda de destino não encontrada.");
                }
            } else {
                System.out.println("Erro ao buscar taxa de câmbio.");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("-------------------------------------------------");
        System.out.println("******** Bem-vindo ao Conversor de Moedas ********");
        System.out.println("-------------------------------------------------");
        System.out.println("============== Menu de Conversão ==============");
        System.out.println("1 - Dólar (USD)  >>  Real (BRL)");
        System.out.println("2 - Real (BRL)  >>  Dólar (USD)");
        System.out.println("3 - Peso argentino (ARS)  >>  Dólar (USD)");
        System.out.println("4 - Dólar (USD)  >>  Peso argentino (ARS)");
        System.out.println("5 - Peso chileno (CLP)  >>  Dólar (USD)");
        System.out.println("6 - Dólar (USD)  >>  Peso chileno (CLP)");
        System.out.println("7 - Sair");
        System.out.println("===============================================");
    }

    private static int lerOpcao(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao >= 1 && opcao <= 7) {
                    return opcao;
                }
                System.out.println("Digite um número entre 1 e 7.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); // limpa entrada inválida
            }
        }
    }
}