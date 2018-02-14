package com.banka;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import com.racun.Racun;

import lombok.Data;

@Entity
@Data
public class Banka {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column(unique = true/*, columnDefinition = "CHAR(3)"*/)
	@NotBlank
	private String kodBanke;

	@Column(unique = true)
	@NotBlank
	private String swiftKod;

	@Column//(length = 120)
	@NotBlank
	private String ime;

	@Column
	private String uri;

	@ManyToOne
	@JoinColumn(name = "OBRACUNSKI_ID")
	private Racun obracunskiRacun;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "BANKA_RACUNI", joinColumns = @JoinColumn(name = "BANKA_ID"), inverseJoinColumns = @JoinColumn(name = "RACUN_ID"))
	private List<Racun> racuni;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKodBanke() {
		return kodBanke;
	}

	public void setKodBanke(String kodBanke) {
		this.kodBanke = kodBanke;
	}

	public String getSwiftKod() {
		return swiftKod;
	}

	public void setSwiftKod(String swiftKod) {
		this.swiftKod = swiftKod;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Racun getObracunskiRacun() {
		return obracunskiRacun;
	}

	public void setObracunskiRacun(Racun obracunskiRacun) {
		this.obracunskiRacun = obracunskiRacun;
	}

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}

}