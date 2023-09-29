package model;

public class Animal {
	private int codigo;
	private String nome;
	private String raca;
	private char sexo;
	private int idade;
	
	public Animal() {
		codigo = -1;
		nome = "";
		raca = "";
		sexo = ' ';
		idade = 0;
	}

	public Animal(int codigo, String nome, String raca, char sexo, int idade) {
		setCodigo(codigo);
		setNome(nome);
		setRaca(raca);
		setSexo(sexo);
		setIdade(idade);
		
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


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Animal " + nome + "   Raca:" + raca + "   Sexo: " + sexo + "   Idade: "
				+ idade;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getCodigo() == ((Animal) obj).getCodigo());
	}	
}