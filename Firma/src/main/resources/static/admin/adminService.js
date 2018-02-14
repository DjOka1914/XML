var services = angular.module('admin.services', ['ngResource']);

services.service('adminService', ['$http', function($http){
	this.checkRights = function(){
		return $http.get("/firmas/checkRights");
	}	
	
	this.saveZaglavljeFakture = function(zaglavljeFakture){
		return $http.post("/zaglavljeFakture",zaglavljeFakture);
	}
	
	this.saveStavkaFakture = function(stavkaFakture,idZaglavlja){
		return $http.post("/faktura/"+idZaglavlja,stavkaFakture);
	}
	
	this.findAllUlazneFakture = function(){
		return $http.get("/faktura/findAllUlazneFakture");
	}
	this.findAllIzlazneFakture = function(){
		return $http.get("/faktura/findAllIzlazneFakture");
	}
	this.findAllNaloge = function(){
		return $http.get("/nalog/findAllNaloge");
	}
	
	this.findAllPoslovniSaradnici = function(idFirme){
		return $http.get("/firma/findAllPoslovniSaradnici/"+idFirme);
	}
	
	this.createHTML = function(faktura){
		return $http.post("/faktura/createHTML",faktura);
	}
	
	this.createPDF = function(faktura){
		return $http.post("/faktura/createPDF",faktura);
	}
	this.createHTMLNalog = function(nalog){
		return $http.post("/nalog/createHTML",nalog);
	}
	
	this.createPDFNalog = function(nalog){
		return $http.post("/nalog/createPDF",nalog);
	}
	
	this.sendNalog = function(faktura){
		return $http.post("/nalog/sendNalog",faktura);
	}
	
	this.sendFaktura = function(faktura){
		return $http.post("/faktura/sendFaktura",faktura);
	}
	
	
	this.findPreseke = function(presek){
		return $http.post("/firma/findPresek",zahtev);
	}
}]);