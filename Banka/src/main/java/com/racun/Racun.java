package com.racun;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Racun {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RACUN_ID")
	private Long id;

	// @NotBlank
	@Column
	private String brojRacuna; // 18

	@Column
	private BigDecimal trenutnoStanje;

	@Column
	private BigDecimal rezervisano;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public BigDecimal getTrenutnoStanje() {
		return trenutnoStanje;
	}

	public void setTrenutnoStanje(BigDecimal trenutnoStanje) {
		this.trenutnoStanje = trenutnoStanje;
	}

	public BigDecimal getRezervisano() {
		return rezervisano;
	}

	public void setRezervisano(BigDecimal rezervisano) {
		this.rezervisano = rezervisano;
	}
}
