package com.firmas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.firma.Firma;
import com.user.User;

import lombok.Data;

@Data
@Entity
public class Firmas  extends User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FIRMAS_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "FIRMA_ID")
	private Firma firma;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}
}
