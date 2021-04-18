package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BemDeConsumo extends Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	private LocalDate memoria;

	public LocalDate getMemoria() {
		return memoria;
	}

	public void setMemoria(LocalDate memoria) {
		// quantidade de memoria ram da GPU
		if (getDataFabricacao().isBefore(memoria.atStartOfDay()))
			this.memoria = memoria;
	}

	public BemDeConsumo() {
		super();
		// prazo para se tornar obsoleta 12 meses.
		memoria = LocalDate.now().plusMonths(12);
	}

	public BemDeConsumo(int id, String descricao, float preco, int quantidade, LocalDateTime fabricacao, LocalDate v) {
		super(id, descricao, preco, quantidade, fabricacao);
		setMemoria(v);
	}

	/**
	 * M�todo sobreposto da classe Object. � executado quando um objeto precisa ser
	 * exibido na forma de String.
	 */
	@Override
	public String toString() {
		return super.toString() + "   Data para a GPU se tornar obsoleta: " + memoria;
	}

	@Override
	public boolean emValidade() {
		return LocalDateTime.now().isBefore(this.getMemoria().atTime(23, 59));
	}

}
