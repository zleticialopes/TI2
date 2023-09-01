
package com.ti2cc;

public class Principal {
    
    public static void main(String[] args) {
        
        DAO dao = new DAO();
        
        dao.conectar();

        // Inserir um elemento na tabela
        Animais animal = new Animais(11, "pablo", "salsicha", 'M', 4);
        if (dao.inserirAnimal(animal)) {
            System.out.println("Inserção com sucesso -> " + animal.toString());
        }

        // Mostrar animais do sexo masculino
        System.out.println("==== Mostrar animais do sexo masculino === ");
        Animais[] animaisMasculinos = dao.getAnimaisMasculinos();
        for (int i = 0; i < animaisMasculinos.length; i++) {
            System.out.println(animaisMasculinos[i].toString());
        }

        // Atualizar animal
        animal.setRaca("nova raça");
        dao.atualizarAnimal(animal);

        // Mostrar todos os animais
        System.out.println("==== Mostrar todos os animais === ");
        Animais[] todosOsAnimais = dao.getAnimais();
        for (int i = 0; i < todosOsAnimais.length; i++) {
            System.out.println(todosOsAnimais[i].toString());
        }
        
        // Excluir animal
        dao.excluirAnimal(animal.getCodigo());
        
        // Mostrar animais restantes
        todosOsAnimais = dao.getAnimais();
        System.out.println("==== Mostrar animais restantes === ");
        for (int i = 0; i < todosOsAnimais.length; i++) {
            System.out.println(todosOsAnimais[i].toString());
        }
        
        dao.close();
    }
}