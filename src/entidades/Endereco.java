package entidades;

public class Endereco {

	private String ruaAvenida;
	private String cidade;
	private String uf;
	private Integer numeroCasa;
	
	public Endereco() {
	}

	public Endereco(String ruaAvenida, String cidade, String uf, Integer numeroCasa) {
		this.ruaAvenida = ruaAvenida;
		this.cidade = cidade;
		this.uf = uf;
		this.numeroCasa = numeroCasa;
	}

	public String getRuaAvenida() {
		return ruaAvenida;
	}

	public void setRuaAvenida(String ruaAvenida) {
		this.ruaAvenida = ruaAvenida;
	}


	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(Integer numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	

	
}
