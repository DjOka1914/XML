package com.zahtevZaDobijanjeNaloga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zahtevZaDobijanjeNaloga", propOrder = {
    "brojRacuna",
})
public class ZahtevZaDobijanjeNaloga {

	 @XmlElement(required = true)
	 protected String brojRacuna;
	 
	 public String getBrojRacuna() {
	        return brojRacuna;
	    }

	    /**
	     * Sets the value of the brojRacuna property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setBrojRacuna(String value) {
	        this.brojRacuna = value;
	    }

}
