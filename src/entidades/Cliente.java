package entidades;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Comparable<Cliente> {

	private String nome;
	private Integer idade;
	private Character sexo;
	private Character[] cpf = new Character[15];
	private String celular;

	Endereco end = new Endereco();
	List<Filme> listFilme = new ArrayList<>();

	public Cliente() {
	}

	public Cliente(String nome, Integer idade, Character sexo, Character[] cpf, String celular) {
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.cpf = cpf;
		this.celular = celular;
	}

	public Cliente(String nome, Integer idade, Character sexo, Character[] cpf, String celular, Endereco end) {
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

	public Character[] getCpf() {
		return cpf;
	}

	public Character[] setCpf(Character[] cpf) {
		return this.cpf = cpf;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public List<Filme> getListFilme() {
		return listFilme;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void adicionarFilme(Filme filme) {
		this.listFilme.add(filme);
	}

	public String toString() {
		String sexoMF = null;
		if (this.sexo == 'M' || this.sexo == 'm') {
			sexoMF = "Masculino";
		} else if (this.sexo == 'F' || this.sexo == 'f') {
			sexoMF = "Feminino";
		}
		return "Nome: " + nome + "\n" + "Idade: " + idade + " anos \n" + "Sexo: " + sexoMF + "\n" + "CPF: " + cpf + "\n"
				+ "Celular: " + celular;
	}

	@Override
	public int compareTo(Cliente outro) {
		return nome.toUpperCase().compareTo(outro.getNome().toUpperCase());
	}

	public boolean validarCPF(Character[] cpf) {
		int cpf_novo[] = new int[12];
		int x = 0, y = 0, TotalDigito1 = 0, TotalDigito2 = 0, Digito_Calculado, tamanho, Digito_usuario;
		
		System.out.print("Digite seu CPF: ");
		setCpf(cpf);
		tamanho = cpf.length;

		while (x < tamanho) {
			if (cpf[x] != '.' && cpf[x] != '-') {
				cpf_novo[y] = Integer.parseInt(Character.toString(cpf[x]));
				y = y + 1;
			}
			x++;
		}
		x = 0;

		while (x < 9) {
			TotalDigito1 = TotalDigito1 + ((cpf_novo[x]) * (10 - x));
			TotalDigito2 = TotalDigito2 + ((cpf_novo[x]) * (11 - x));
			x++;
		}

		TotalDigito1 = (TotalDigito1 * 10) % 11;
		if (TotalDigito1 > 9) {
			TotalDigito1 = 0;
		}

		TotalDigito2 = (TotalDigito2 + (TotalDigito1 * 2)) * 10 % 11;
		if (TotalDigito2 > 9) {
			TotalDigito2 = 0;
		}

		Digito_Calculado = (TotalDigito1 * 10) + TotalDigito2;
		x = cpf_novo[9];
		y = cpf_novo[10];

		Digito_usuario = ((x) * 10) + (y);

		if (tamanho == 11 || tamanho == 14) {
			if (Digito_Calculado == Digito_usuario) {
				System.out.println("CPF correto!");
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
