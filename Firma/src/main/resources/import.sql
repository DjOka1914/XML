INSERT INTO RACUN(BROJ_RACUNA, TRENUTNO_STANJE, REZERVISANO) VALUES ('AAA111111111111199',0,0);
INSERT INTO RACUN(BROJ_RACUNA, TRENUTNO_STANJE, REZERVISANO) VALUES ('BBB222222222222299',0,0);
INSERT INTO RACUN(BROJ_RACUNA, TRENUTNO_STANJE, REZERVISANO) VALUES ('BBB333333333333399',0,0);

INSERT INTO RACUN(BROJ_RACUNA, TRENUTNO_STANJE, REZERVISANO) VALUES ('QQQ777777777777777',0,0);
INSERT INTO RACUN(BROJ_RACUNA, TRENUTNO_STANJE, REZERVISANO) VALUES ('WWW777777777777777',0,0);

INSERT INTO BANKA (KOD_BANKE, SWIFT_KOD,IME,OBRACUNSKI_ID,URI) VALUES('AAA','AAAIOI11','BANKA1',4,'http://localhost:8081');
INSERT INTO BANKA (KOD_BANKE, SWIFT_KOD,IME,OBRACUNSKI_ID,URI) VALUES('BBB','BBBIII22','BANKA2',5,'http://localhost:8082');

INSERT INTO FIRMA (NAZIV, ADRESA,PIB,URI,RACUN_ID) VALUES('Firma001','Dimitrija Tucovica 1','11111111111','http://localhost:5001',1);
INSERT INTO FIRMA (NAZIV, ADRESA,PIB,URI,RACUN_ID) VALUES('Firma002','Toze Veselinovica 14','22222222222','http://localhost:5002',2);
INSERT INTO FIRMA (NAZIV, ADRESA,PIB,URI,RACUN_ID) VALUES('Firma003','Rumenacka 3','33333333333','http://localhost:5003',3);

INSERT INTO FIRMAS (MAIL, PASSWORD,FIRMA_ID) VALUES('firma1',111,1);
INSERT INTO FIRMAS (MAIL, PASSWORD,FIRMA_ID) VALUES('firma2',222,2);
INSERT INTO FIRMAS (MAIL, PASSWORD,FIRMA_ID) VALUES('firma3',333,3);

INSERT INTO POSLOVNI_SARADNICI (FIRMA1_ID,FIRMA2_ID) VALUES (1,2);
INSERT INTO POSLOVNI_SARADNICI (FIRMA1_ID,FIRMA2_ID) VALUES (2,1);
INSERT INTO POSLOVNI_SARADNICI (FIRMA1_ID,FIRMA2_ID) VALUES (3,1);
INSERT INTO POSLOVNI_SARADNICI (FIRMA1_ID,FIRMA2_ID) VALUES (1,3);
INSERT INTO POSLOVNI_SARADNICI (FIRMA1_ID,FIRMA2_ID) VALUES (2,3);
INSERT INTO POSLOVNI_SARADNICI (FIRMA1_ID,FIRMA2_ID) VALUES (3,2);

INSERT INTO BANKA_RACUNI(BANKA_ID,RACUN_ID) VALUES (1,1);
INSERT INTO BANKA_RACUNI(BANKA_ID,RACUN_ID) VALUES (2,2);
INSERT INTO BANKA_RACUNI(BANKA_ID,RACUN_ID) VALUES (2,3);


--INSERT INTO NALOG(ID_PORUKE, DUZNIK, SVRHA_PLACANJA, PRIMALAC, DATUM_NALOGA,DATUM_VALUTE,RACUN_DUZNIKA, MODEL_ZADUZENJA, POZIV_NA_BROJ_ZADUZENJA, RACUN_PRIMAOCA, MODEL_ODOBRENJA,POZIV_NA_BROJ_ODOBRENJA, IZNOS, OZNAKA_VALUTE,HITNO,OBRADJEN) VALUES ('id111','Firma001','placanje', 'Firma002','2018-01-25','2018-01-25','AAA111111111111199' , 97, '85645678912345678911', 'BBB222222222222299',97,'85622222222222222222',999.99, 'rsd', false,false);
--INSERT INTO NALOG(ID_PORUKE, DUZNIK, SVRHA_PLACANJA, PRIMALAC, DATUM_NALOGA,DATUM_VALUTE,RACUN_DUZNIKA, MODEL_ZADUZENJA, POZIV_NA_BROJ_ZADUZENJA, RACUN_PRIMAOCA, MODEL_ODOBRENJA,POZIV_NA_BROJ_ODOBRENJA, IZNOS, OZNAKA_VALUTE,HITNO,OBRADJEN) VALUES ('id222','Firma001','placanje', 'Firma003','2018-01-25','2018-01-25','AAA111111111111199' , 97, '85645678912345678911', 'BBB333333333333399',97,'88833333333333233333',1000, 'rsd', false,false);
--INSERT INTO NALOG(ID_PORUKE, DUZNIK, SVRHA_PLACANJA, PRIMALAC, DATUM_NALOGA,DATUM_VALUTE,RACUN_DUZNIKA, MODEL_ZADUZENJA, POZIV_NA_BROJ_ZADUZENJA, RACUN_PRIMAOCA, MODEL_ODOBRENJA,POZIV_NA_BROJ_ODOBRENJA, IZNOS, OZNAKA_VALUTE,HITNO,OBRADJEN) VALUES ('id333','Firma001','placanje', 'Firma003','2018-01-25','2018-01-25','AAA111111111111199' , 97, '85645678912345678911', 'BBB333333333333399',97,'88833333333333233333',3000, 'rsd', false,false);
--INSERT INTO NALOG(ID_PORUKE, DUZNIK, SVRHA_PLACANJA, PRIMALAC, DATUM_NALOGA,DATUM_VALUTE,RACUN_DUZNIKA, MODEL_ZADUZENJA, POZIV_NA_BROJ_ZADUZENJA, RACUN_PRIMAOCA, MODEL_ODOBRENJA,POZIV_NA_BROJ_ODOBRENJA, IZNOS, OZNAKA_VALUTE,HITNO,OBRADJEN) VALUES ('id444','Firma001','placanje', 'Firma003','2018-01-25','2018-01-25','AAA111111111111199' , 97, '85645678912345678911', 'BBB333333333333399',97,'88833333333333233333',5000, 'rsd', false,false);
--INSERT INTO NALOG(ID_PORUKE, DUZNIK, SVRHA_PLACANJA, PRIMALAC, DATUM_NALOGA,DATUM_VALUTE,RACUN_DUZNIKA, MODEL_ZADUZENJA, POZIV_NA_BROJ_ZADUZENJA, RACUN_PRIMAOCA, MODEL_ODOBRENJA,POZIV_NA_BROJ_ODOBRENJA, IZNOS, OZNAKA_VALUTE,HITNO,OBRADJEN) VALUES ('id555','Firma001','placanje', 'Firma003','2018-01-25','2018-01-25','AAA111111111111199' , 97, '85645678912345678911', 'BBB333333333333399',97,'88833333333333233333',7000, 'rsd', false,false);

  

    

   
INSERT INTO ZAGLAVLJE_FAKTURE(NAZIV_DOBAVLJACA,ADRESA_DOBAVLJACA,PIB_DOBAVLJACA,NAZIV_KUPCA, ADRESA_KUPCA, PIB_KUPCA,BROJ_RACUNA, DATUM_RACUNA, VREDNOST_ROBE, VREDNOST_USLUGA,UKUPNO_ROBAIUSLUGE,UKUPAN_RABAT, UKUPAN_POREZ, OZNAKA_VALUTE, IZNOS_ZA_UPLATU, UPLATA_NA_RACUN, DATUM_VALUTE) VALUES ('Firma001','Dimitrija Tucovica 1','11111111111','Firma002','Toze Veselinovica 14','22222222222','BBB222222222222299', '2018-02-12',132.25, 78.00, 201.25,20.00,50.00, 'RSD',300.25, 'AAA111111111111199','2018-02-12');
INSERT INTO STAVKA_FAKTURE(REDNI_BROJ, NAZIV_ROBE_ILI_USLUGE, KOLICINA, JEDINICA_MERE, JEDINICNA_CENA, VREDNOST, PROCENAT_RABATA, IZNOS_RABATA,UMANJENO_ZA_RABAT,UKUPAN_POREZ) VALUES (123, 'PSENICA', 75.2, 'kg', 120.00, 7500.00, 8.0, 100.00 , 100.00 , 360.0);
INSERT INTO FAKTURA(ZAGLAVLJE_FAKTURE_ID) VALUES(1);
INSERT INTO FAKTURA_STAVKA_FAKTURE(FAKTURA_ID,STAVKA_FAKTURE_ID) VALUES(1,1);


