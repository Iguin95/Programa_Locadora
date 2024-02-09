package serviço;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parcela {

	DateTimeFormatter dmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate dataVencimento;
	private double quantia;

	public Parcela() {
	}

	public Parcela(LocalDate dataVencimento, double quantia) {
		this.dataVencimento = dataVencimento;
		this.quantia = quantia;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public double getQuantia() {
		return quantia;
	}

	public void setQuantia(double quantia) {
		this.quantia = quantia;
	}
	
	@Override 
	public String toString() {
		return dataVencimento.format(dmt) + " - " + String.format("%.2f", quantia);
	}

}
