package data;

import java.math.BigDecimal;

public class tovars {
	private BigDecimal kod_tovara = null; // ��� ������
	private String name = null; // ������������
	private BigDecimal cena = null; // ����
	private String tip_tovara = null; // ��� ������

	public BigDecimal getKod_tovara() {
		return kod_tovara;
	}

	public void setKod_tovara(BigDecimal kod_tovara) {
		this.kod_tovara = kod_tovara;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public String getTip_tovara() {
		return tip_tovara;
	}

	public void setTip_tovara(String tip_tovara) {
		this.tip_tovara = tip_tovara;
	}

	public String toString() {
		return getKod_tovara() + "," + getName() + "," + getCena() + " ���.,"
				+ getTip_tovara();
	}

}
