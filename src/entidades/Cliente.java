package entidades;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Cliente implements Comparable<Cliente> {

	private int id;
	
	private String nome;
	private Integer idade;
	private Character sexo;
	private String CPF;
	private String celular;

	Endereco end = new Endereco();
	List<Filme> listFilme = new ArrayList<>();

	public Cliente() {
	}

	public Cliente(int id, String nome, Integer idade, Character sexo, String cPF, String celular, Endereco end) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		CPF = cPF;
		this.celular = celular;
		this.end = end;
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
		return CPF;
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

	public int getId() {
		return id;
	}

	public void adicionarFilme(Filme filme) {
		this.listFilme.add(filme);
	}

	@Override
	public int compareTo(Cliente outro) {
		return nome.toUpperCase().compareTo(outro.getNome().toUpperCase());
	}

	/*
	 * CPF possui 11 dígitos
	 * 
	 * Multiplica-se os 9 primeiros dígitos pela sequência decrescente de números de
	 * 10 à 2 e soma os resultados obtidos
	 * 
	 * Multiplica-se o resultado acima por 10 e divide por 11, o resto da divisão é
	 * o penúltimo número do CPF (caso o resto seja 10, o resultado se torna 0).
	 * Logo se o número calculado for diferente do penúltimo número, o CPF é
	 * inválido.
	 * 
	 * Caso o primeiro dígito seja igual, multiplica-se os 10 primeiros dígitos pela
	 * sequência decrescente de números de 11
	 * 
	 * Multiplica-se a soma feita acima por 10
	 * 
	 * Verificamos se o resto da divisão da multiplicação acima por 11 é igual ao
	 * segundo dígito, se for, o CPF é válido.
	 * 
	 */

	public static boolean validarCPF(String CPF) {
		// considera-se erro CPF"s formados por uma sequência de números iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o código para eventuais erros de conversao de tipo (int)
		try {
			// Cálculo do 1° Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere "0" no inteiro 0
				// (48 eh a posicao de "0" na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Cálculo do 2° Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public String toString() {
		String sexoMF = null;
		if (this.sexo == 'M' || this.sexo == 'm') {
			sexoMF = "Masculino";
		} else if (this.sexo == 'F' || this.sexo == 'f') {
			sexoMF = "Feminino";
		}
		return "ID: " + id + "\nNome: " + nome + "\n" + "Idade: " + idade + " anos \n" + "Sexo: " + sexoMF + "\n" + "CPF: "
				+ CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11) + "\n" + "Celular: " + celular;
	}
}
