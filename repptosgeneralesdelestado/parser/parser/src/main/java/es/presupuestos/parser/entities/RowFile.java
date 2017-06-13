package es.presupuestos.parser.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Represent row in BBDD
 *
 */
@JsonPropertyOrder({"padre_ID", "padre_DESC","hijo_ID","hijo_DESC","anno","type","millonesEuros"})
public class RowFile {
	private String padre_ID;
	private String padre_DESC;
	private String hijo_ID;
	private String hijo_DESC;
	private int anno;
	private String millonesEuros;
	private String type;
	
	public RowFile() {
		super();
	}
	
	public RowFile(String padre_ID, String padre_DESC, String hijo_ID,
			String hijo_DESC, int anno,
			String millonesEuros) {
		super();
		this.padre_ID = padre_ID;
		this.padre_DESC = padre_DESC;
		this.hijo_ID = hijo_ID;
		this.hijo_DESC = hijo_DESC;
		this.anno = anno;
		this.millonesEuros = millonesEuros;
	}

	public String getPadre_ID() {
		return padre_ID;
	}
	
	public void setPadre_ID(String padre_ID) {
		this.padre_ID = padre_ID;
	}
	
	public String getPadre_DESC() {
		return padre_DESC;
	}
	
	public void setPadre_DESC(String padre_DESC) {
		this.padre_DESC = padre_DESC;
	}
	
	public String getHijo_ID() {
		return hijo_ID;
	}
	
	public void setHijo_ID(String hijo_ID) {
		this.hijo_ID = hijo_ID;
	}
	
	public String getHijo_DESC() {
		return this.hijo_DESC;
	}
	
	public void setHijo_DESC(String hijo_DESC) {
		this.hijo_DESC = hijo_DESC;
	}
	
	public int getAnno() {
		return anno;
	}
	
	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	public String getMillonesEuros() {
		return millonesEuros;
	}
	
	public void setMillonesEuros(String millonesEuros) {
		this.millonesEuros = millonesEuros;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
