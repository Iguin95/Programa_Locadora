package entidades;


public class Filme {
	
	private String nome;
	private Integer classificacao;
	
	public Filme() {
	}

	public Filme(String nome, Integer classificacao) {
		this.nome = nome;
		this.classificacao = classificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}

	
	public String toString() {
		return nome + " -- Classificação indicativa: " + classificacao + " anos.";
	}

}
