<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version = "2.0" 
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
     <xsl:template match="/">
        <html>
            <head>
                <title>Faktura</title>
                <style type="text/css">
                    table {
                        font-family: serif;
                        border-collapse: collapse;
                        margin: 10px auto 10px auto;
                        width: 90%;
                    }
                    th, td {
                        text-align: left;
                        padding: 5px;
                    }
                    tr:nth-child(even){ background-color: #f2f2f2 }
                    th {
                        background-color: #4caf50;
                        font-family: sans-serif;
                        color: white;
                    }
                    tr { border: 1px solid darkgrey; }
                    tr:hover {
                        font-style: italic;
                        background-color: #cae8cb;
                    }
                    body { font-family: sans-serif; }
                   p { text-indent: 5px; }
                    .sup {
                        vertical-align: super;
                        padding-left: 4px;
                        font-size: small;
                        text-transform: lowercase;
                    }
                    
                </style>
            </head>
            <body>
                <h1>Faktura</h1>
                <h2>Zaglavlje fakture</h2>
                <p>Naziv dobavljaca: <xsl:value-of select="faktura/zaglavljeFakture/nazivDobavljaca"/></p>
                <p>Adresa dobavljaca: <xsl:value-of select="faktura/zaglavljeFakture/adresaDobavljaca"/></p>
                <p>PIB dobavljaca: <xsl:value-of select="faktura/zaglavljeFakture/PIBDobavljaca"/></p>
                <p>Naziv kupca: <xsl:value-of select="faktura/zaglavljeFakture/nazivKupca"/></p>
                <p>Adresa kupca: <xsl:value-of select="faktura/zaglavljeFakture/adresaKupca"/></p>
                <p>PIB kupca: <xsl:value-of select="faktura/zaglavljeFakture/PIBKupca"/></p>
                <p>Broj racuna: <xsl:value-of select="faktura/zaglavljeFakture/brojRacuna"/></p>
                <p>Vrednost robe: <xsl:value-of select="faktura/zaglavljeFakture/vrednostRobe"/></p>
				<p>Vrednost usluga: <xsl:value-of select="faktura/zaglavljeFakture/vrednostUsluga"/></p>
				<p>Ukupno roba i usluge: <xsl:value-of select="faktura/zaglavljeFakture/ukupnoRobaIUsluge"/></p>
                <p>Ukupan rabat: <xsl:value-of select="faktura/zaglavljeFakture/ukupanRabat"/></p>
                <p>Ukupan porez: <xsl:value-of select="faktura/zaglavljeFakture/ukupanPorez"/></p>
                <p>Oznaka valute: <xsl:value-of select="faktura/zaglavljeFakture/oznakaValute"/></p>
                <p>Iznos za uplatu: <xsl:value-of select="faktura/zaglavljeFakture/iznosZaUplatu"/></p>
                <p>Uplata na racun: <xsl:value-of select="faktura/zaglavljeFakture/uplataNaRacun"/></p>
                <p>Datum valute: <xsl:value-of select="faktura/zaglavljeFakture/datumValute"/></p>
				
				<table style="border: 1px">
				<caption>Stavka fakture</caption>
                    <tr bgcolor="#9acd32">
                        <th>Redni broj</th>
                        <th>Naziv robe ili usluge</th>
                        <th>Kolicina</th>
                        <th>Jedinica mere</th>
                        <th>Jedinicna cena</th>
                        <th>Vrednost</th>
                        <th>Procenat rabata</th>
                        <th>Iznos rabata</th>
                        <th>Umanjeno za rabat</th>
                        <th>Ukupan porez</th>

                    </tr>
                    
                    <xsl:for-each select="faktura/stavkaFakture">
	                    <tr>
	                    	<td>
                                <xsl:value-of select="redniBroj"/>
                            </td>
                            <td>
                                <xsl:value-of select="nazivRobeIliUsluge"/>
                            </td>
                            <td>
                                <xsl:value-of select="kolicina"/>
                            </td>
                            <td>
                                <xsl:value-of select="jedinicaMere"/>
                            </td>
                            <td>
                                <xsl:value-of select="jedinicnaCena"/>
                            </td>
                            <td>
                                <xsl:value-of select="vrednost"/>
                            </td>
                            <td>
                                <xsl:value-of select="iznosRabata"/>
                            </td>
                            <td>
                                <xsl:value-of select="procenatRabata"/>
                            </td>
                            <td>
                                <xsl:value-of select="umanjenoZaRabat"/>
                            </td>
                            <td>
                                <xsl:value-of select="ukupanPorez"/>
                            </td>
	                    </tr>
                    </xsl:for-each>
                    
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
     
