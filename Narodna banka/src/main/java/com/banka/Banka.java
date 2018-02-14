package com.banka;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

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
	
	@Column//(length = 18)
	private String obracunskiRacun;

	public String getUri() {
		return uri;
	}

}