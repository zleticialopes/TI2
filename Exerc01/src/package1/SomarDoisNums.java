package package1;

import java.util.Scanner;

public class SomarDoisNums {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Declarar variáveis
        int num1, num2, soma;

        // Ler entradas
        System.out.println("Digite um número:");
        num1 = sc.nextInt();

        System.out.println("Digite outro número:");
        num2 = sc.nextInt();

        // Somar
        soma = num1 + num2;

        // Mostrar na tela
        System.out.println("Soma: " + soma);

        // Fechar o Scanner
        sc.close();
    }
}
