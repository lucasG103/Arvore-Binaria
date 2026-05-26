import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArvoreBinaria arvore = new ArvoreBinaria();

        System.out.print("Quantos nos deseja inserir na arvore? (maximo 15): ");
        int quantidade = lerInteiroPositivo();

        if (quantidade > 15) {
            System.out.println("Limite de 15 nos. Serao inseridos apenas 15.");
            quantidade = 15;
        }

        for (int i = 1; i <= quantidade; i++) {
            System.out.print("Digite o valor do no " + i + ": ");
            int valor = lerInteiroPositivo();
            arvore.inserir(valor);
        }

        System.out.println("\nArvore montada com sucesso!");
        arvore.percurso("Em");

        System.out.print("\nDigite o valor do no que deseja remover: ");
        int valorRemover = lerInteiroPositivo();
        arvore.remover(valorRemover);

        System.out.println("\nArvore apos a remocao:");
        arvore.percurso("Em");

        scanner.close();
    }

    private static int lerInteiroPositivo() {
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um numero valido: ");
                scanner.next();
            }
            int numero = scanner.nextInt();
            if (numero > 0) {
                return numero;
            }
            System.out.println("Digite um numero maior que zero: ");
        }
    }
}
