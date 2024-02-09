package serviço;

import java.time.LocalDate;

public class ProcessoDeVenda {
	
	Pagamento pagamento;

	public ProcessoDeVenda(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public void processarContrato(ContratoDeVenda contrato, int meses) {
		double valorDividido = contrato.getPrecoTotal() / meses;
		
		for(int i = 1; i <= meses; i++) {
			LocalDate dataVencimento = contrato.getDataAtual().plusMonths(i);
			double juros = pagamento.juros(valorDividido, i);
			double valor = valorDividido + juros;
			contrato.getParcelas().add(new Parcela(dataVencimento, valor));
		}

	}
	
	public double atrasoNaDevolução(ContratoDeVenda contrato, int diasAtrasados) {
		return contrato.getPrecoTotal() * Math.pow(1 + 0.01, diasAtrasados);
	}
	
	

}
