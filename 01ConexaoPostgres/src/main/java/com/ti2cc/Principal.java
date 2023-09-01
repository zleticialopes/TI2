
package com.ti2cc;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        DAO dao = new DAO();

        if (!dao.conectar()) {
            System.err.println("Não foi possível conectar ao banco de dados.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1) Listar");
            System.out.println("2) Inserir");
            System.out.println("3) Excluir");
            System.out.println("4) Atualizar");
            System.out.println("5) Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    // Listar animais
                    listarAnimais(dao);
                    break;
                case 2:
                    // Inserir animal
                    inserirAnimal(dao, scanner);
                    break;
                case 3:
                    // Excluir animal
                    excluirAnimal(dao, scanner);
                    break;
                case 4:
                    // Atualizar animal
                    atualizarAnimal(dao, scanner);
                    break;
                case 5:
                    // Sair
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 5);

        scanner.close();
        dao.close();
    }

    private static void listarAnimais(DAO dao) {
        Animais[] animais = dao.getAnimais();
        if (animais != null && animais.length > 0) {
            System.out.println("==== Lista de Animais ====");
            for (Animais animal : animais) {
                System.out.println(animal.toString());
            }
        } else {
            System.out.println("Nenhum animal encontrado.");
        }
    }

    private static void inserirAnimal(DAO dao, Scanner scanner) {
        System.out.print("Digite o código do animal: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o nome do animal: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a raça do animal: ");
        String raca = scanner.nextLine();

        System.out.print("Digite o sexo do animal (M/F): ");
        char sexo = scanner.next().charAt(0);
        scanner.nextLine(); 

        System.out.print("Digite a idade do animal: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); 

        Animais animal = new Animais(codigo, nome, raca, sexo, idade);
        if (dao.inserirAnimal(animal)) {
            System.out.println("Inserção com sucesso -> " + animal.toString());
        } else {
            System.err.println("Erro ao inserir o animal.");
        }
    }

    private static void excluirAnimal(DAO dao, Scanner scanner) {
        System.out.print("Digite o código do animal a ser excluído: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        if (dao.excluirAnimal(codigo)) {
            System.out.println("Animal com código " + codigo + " foi excluído com sucesso.");
        } else {
            System.err.println("Erro ao excluir o animal.");
        }
    }

    private static void atualizarAnimal(DAO dao, Scanner scanner) {
        System.out.print("Digite o código do animal a ser atualizado: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 

        Animais animalExistente = dao.getAnimalPorCodigo(codigo);

        if (animalExistente == null) {
            System.err.println("Animal com código " + codigo + " não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do animal: ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite a nova raça do animal: ");
        String novaRaca = scanner.nextLine();

        System.out.print("Digite o novo sexo do animal (M/F): ");
        char novoSexo = scanner.next().charAt(0);
        scanner.nextLine(); 

        System.out.print("Digite a nova idade do animal: ");
        int novaIdade = scanner.nextInt();
        scanner.nextLine(); 

        animalExistente.setNome(novoNome);
        animalExistente.setRaca(novaRaca);
        animalExistente.setSexo(novoSexo);
        animalExistente.setIdade(novaIdade);

        if (dao.atualizarAnimal(animalExistente)) {
            System.out.println("Animal atualizado com sucesso -> " + animalExistente.toString());
        } else {
            System.err.println("Erro ao atualizar o animal.");
        }
    }
}
