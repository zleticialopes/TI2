package com.ti2cc;


public class Animais {
    private int codigo;
    private String nome;
    private String raca; 
    private char sexo;
    private int idade;
    
    public Animais() {
        this.codigo = -1;
        this.nome = "";
        this.raca = "";
        this.sexo = '*';
        this.idade = 0;
    }
    
    public Animais(int codigo, String nome, String raca, char sexo, int idade) {
        this.codigo = codigo;
        this.nome = nome;
        this.raca = raca;
        this.sexo = sexo;
        this.idade = idade;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Animal [codigo=" + codigo + ", nome=" + nome + ", raca=" + raca + ", sexo=" + sexo + ", idade=" + idade + "]";
    }   
    
    
}
