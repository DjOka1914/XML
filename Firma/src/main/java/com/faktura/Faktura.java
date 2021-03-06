//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.19 at 01:26:07 PM CEST 
//


package com.faktura;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.stavkaFakture.StavkaFakture;
import com.zaglavljeFakture.ZaglavljeFakture;

import lombok.Data;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "faktura")
@Data
@Entity
public class Faktura {

	@XmlTransient
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FAKTURA_ID")
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "ZAGLAVLJE_FAKTURE_ID")
    @XmlElement(required = true)
    protected ZaglavljeFakture zaglavljeFakture;
    
    
	@OneToMany
	@JoinTable(name = "FAKTURA_STAVKA_FAKTURE", joinColumns = @JoinColumn(name = "FAKTURA_ID"), inverseJoinColumns = @JoinColumn(name = "STAVKA_FAKTURE_ID"))
    @XmlElement(required = true)
    protected List<StavkaFakture> stavkaFakture = new ArrayList<StavkaFakture>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public ZaglavljeFakture getZaglavljeFakture() {
		return zaglavljeFakture;
	}


	public void setZaglavljeFakture(ZaglavljeFakture zaglavljeFakture) {
		this.zaglavljeFakture = zaglavljeFakture;
	}


	public List<StavkaFakture> getStavkaFakture() {
		return stavkaFakture;
	}


	public void setStavkaFakture(List<StavkaFakture> stavkaFakture) {
		this.stavkaFakture = stavkaFakture;
	}

   
   

}
