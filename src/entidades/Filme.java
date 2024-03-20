package entidades;

import serviço.ContratoDeVenda;

public class Filme {
	
	private String nome;
	private Integer classificacao;
	
	ContratoDeVenda contratoDeVenda;
	
	public Filme() {
	}
	

	public Filme(String nome) {
		super();
		this.nome = nome;
	}


	public Filme(String nome, Integer classificacao, ContratoDeVenda contratoDeVenda) {
		this.nome = nome;
		this.classificacao = classificacao;
		this.contratoDeVenda = contratoDeVenda;
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
	/* public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ")
          .append("Nome: ").append(nome)
          .append(", Idade: ").append(idade)
          .append(", Sexo: ").append(sexo);
        return sb.toString();
    }*/


	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(nome)
		.append(" -- Classificação indicativa: ")
		.append(classificacao)
		.append(" anos.")
		.append("Preço total: R$")
		.append(String.format("%.2f", contratoDeVenda.getPrecoTotal()))
		.append("\n");
		
		return sb.toString();
	}
	

}
