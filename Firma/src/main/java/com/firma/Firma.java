package com.firma;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.racun.Racun;

import lombok.Data;

@Data
@Entity
public class Firma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FIRMA_ID")
	private Long id;
	
	@NotBlank
	@Column
	private String naziv;
	
	@NotBlank
	@Column
	private String adresa;
	
	@NotBlank
	@Column
	private String PIB;
	
	@NotBlank
	@Column
	private String uri;
	
	@OneToOne
	@JoinColumn(name = "RACUN_ID")
	private Racun racun;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "POSLOVNI_SARADNICI", joinColumns = @JoinColumn(name = "FIRMA1_ID"), inverseJoinColumns = @JoinColumn(name = "FIRMA2_ID"))
    protected List<Firma> poslovniSaradnici = new ArrayList<Firma>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPIB() {
		return PIB;
	}

	public void setPIB(String pIB) {
		PIB = pIB;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	public List<Firma> getPoslovniSaradnici() {
		return poslovniSaradnici;
	}

	public void setPoslovniSaradnici(List<Firma> poslovniSaradnici) {
		this.poslovniSaradnici = poslovniSaradnici;
	}

}
