package entidades;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Comparable<Cliente>{
	
	private String nome;
	private Integer idade;
	private Character sexo;
	private String cpf;
	private String celular;
	
	Endereco end = new Endereco();
	List<Filme> list = new ArrayList<>();
	
	public Cliente() {
	}
	
	public Cliente(String nome, Integer idade, Character sexo, String cpf,String celular) {
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.cpf = cpf;
		this.celular = celular;
	}

	public Cliente(String nome, Integer idade, Character sexo, String cpf, String celular, Endereco end) {
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.cpf = cpf;
		this.end = end;
		this.celular = celular;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}


	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public List<Filme> getList() {
		return list;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String toString() {
		String sexoMF = null;
		if(this.sexo == 'M' || this.sexo == 'm') {
			sexoMF = "Masculino";
		}else if(this.sexo == 'F' || this.sexo == 'f') {
			sexoMF = "Feminino";
		}
		return "Nome: " + nome + "\n" 
				+ "Idade: " + idade + " anos \n"
				+ "Sexo: " + sexoMF + "\n"
				+ "CPF: " + cpf + "\n"
				+ "Celular: " + celular;
	}

	@Override
	public int compareTo(Cliente outro) {
		return nome.toUpperCase().compareTo(outro.getNome().toUpperCase());
	}


	
	
	
	


	
	
	
	
	

}
