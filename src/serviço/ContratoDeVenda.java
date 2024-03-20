package serviço;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContratoDeVenda {
	
	//horário atual da máquina
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());
	//dtf.format(dataAtual);
	
	private Double precoTotal;
	private LocalDate dataAtual;
	
	List<Parcela> parcelas = new ArrayList<>();
	List<Aluguel> aluguel = new ArrayList<>();
	
	public ContratoDeVenda() {
	}
	
	public ContratoDeVenda(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public ContratoDeVenda(Double precoTotal, LocalDate dataAtual) {
		this.precoTotal = precoTotal;
		this.dataAtual = dataAtual;
	}
	

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public LocalDate getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(LocalDate dataAtual) {
		this.dataAtual = dataAtual;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public List<Aluguel> getAluguel() {
		return aluguel;
	}


}
