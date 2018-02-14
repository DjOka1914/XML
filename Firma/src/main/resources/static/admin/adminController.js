var app = angular.module('admin.controllers', []);
var trenutniPresek = 1;

app.controller('adminController', ['$scope','adminService', '$location','$state',
  	function ($scope, adminService, $location,$state) {
		function checkRights() {
			adminService.checkRights().then(
				function (response) {
					if(response.data == null){
						$location.path('login');
					}
					
					
					if(response.data != "") {
						$scope.admin = response.data;
					}
				}, 
				function (response){
				    $location.path('login');
					alert("Greska");
				}
			);
		}
		checkRights();
		
		$scope.findPreseke = function() {
			trenutniPresek = 1;
			zahtev = {};
			zahtev.datum = $("#startDatum").val();
			zahtev.redniBrojPreseka =  trenutniPresek;
			adminService.findPreseke(zahtev).then(
				function (response) {
					if(response.data != null)
					{					
						$scope.nalozi = response.data.stavkaPreseka;
						$scope.ZaglavljePreseka = response.data.zaglavljePreseka;
					}
					else {
						alert("Greska pri validaciji seme!")
					}
					
				}, 
				function (response){
					alert("Greska");
				}
			);
		}
		
		$scope.findPresekeNext = function() {
			trenutniPresek += 1;
			zahtev = {};
			zahtev.datum = $("#startDatum").val();
			zahtev.redniBrojPreseka =  trenutniPresek;
			adminService.findPreseke(zahtev).then(
				function (response) {
					$scope.nalozi = {};
					if(response.data.stavkaPreseka.length == 0){
						trenutniPresek -= 1;
						return;
					}					
					$scope.nalozi = response.data.stavkaPreseka;
					$scope.ZaglavljePreseka = response.data.zaglavljePreseka;
				}, 
				function (response){
					alert("Greska");
				}
			);
		}
		$scope.saveZaglavljeFakture= function () {
			adminService.saveZaglavljeFakture($scope.zaglavljeFakture).then(
				function(response){
					alert("Unos stavki.")
					$state.go("admin.unosStavkeFakture", {});
					$scope.sacuvanoZaglavlje=response.data;
				}, function (response){
					alert("Greska");
				}
			);
		}
		
		$scope.saveStavkaFakture= function () {
			adminService.saveStavkaFakture($scope.stavkaFakture,$scope.sacuvanoZaglavlje.id).then(
				function(response){
					var r = confirm("Dodaj jos jednu stavku?");
					if (r == true) {
						$scope.stavkaFakture=null;
						$state.go("admin.unosStavkeFakture", {});
					} else {
						$scope.sacuvanoZaglavlje=null;
						$scope.stavkaFakture=null;
						$state.go("admin.home", {});
						adminService.sendFaktura(response.data).then(
								function(response){
									alert("Poslata je faktura! ")
								}, function (response){
									alert("Greska");
								}
							);
					}

				}, function (response){
					alert("Greska");
				}
			);
		}
		
		$scope.findAllUlazneFakture = function () {   
			adminService.findAllUlazneFakture().then(
				function(response){
					$scope.allUlazneFakture = response.data;
				}, function (response){
					alert("Greska!");
				}
			);	
		}
		
		$scope.findAllIzlazneFakture = function () {   
			adminService.findAllIzlazneFakture().then(
				function(response){
					$scope.allIzlazneFakture = response.data;
				}, function (response){
					alert("Greska!");
				}
			);	
		}
		
		$scope.findAllNaloge = function () {   
			adminService.findAllNaloge().then(
				function(response){
					$scope.allNalozi = response.data;
				}, function (response){
					alert("Greska!");
				}
			);	
		}
		
		$scope.findAllPoslovniSaradnici = function () {
			adminService.findAllPoslovniSaradnici($scope.admin.firma.id).then(
				function(response){
					$scope.allPoslovniSaradnici = response.data;
				}, function (response){
					alert("Greska!");
				}
			);	
		}
		
		$scope.setSelected = function(code,naziv,adresa,pib) {
	        $scope.selected = code;
	        markRow(code);
	        $scope.zaglavljeFakture.nazivKupca = naziv;
	        $scope.zaglavljeFakture.adresaKupca = adresa;
	        $scope.zaglavljeFakture.pibKupca = pib;


	    };	
	    function markRow(code) {   
	    	 var rows = document.getElementsByTagName('tr');
		        for(var i=0; i<rows.length; i +=1) {
		          rows[i].className = "";
		        }
		     element = document.getElementById(code);
		     element.setAttribute("class", "selectedRow");
		}
	    
	    $scope.createPDF = function (faktura) {
	    	adminService.createPDF(faktura).then(function(response){
				window.location.href = "/faktura/fakturaPDF";
            },
			function(response){
				alert("Greska kod createPDF");
			})
		}	
	    
	    $scope.createHTML = function (faktura) {
	    	adminService.createHTML(faktura).then(function(response){
				alert("Izgenerisan html fakture.")
				window.location.href = "/faktura/fakturaHTML";
            },
			function(response){
				alert("Greska kod createHTML");
			})
		}	
	    $scope.createPDFNalog = function (nalog) {
	    	adminService.createPDFNalog(nalog).then(function(response){
				window.location.href = "/nalog/nalogPDF";
            },
			function(response){
				alert("Greska kod createPDFNalog");
			})
		}	
	    
	    $scope.createHTMLNalog = function (nalog) {
	    	adminService.createHTMLNalog(nalog).then(function(response){
				alert("Izgenerisan html naloga.")
				window.location.href = "/nalog/nalogHTML";
            },
			function(response){
				alert("Greska kod createHTMLNalog");
			})
		}
	    $scope.sendNalog = function (faktura) {
			adminService.sendNalog(faktura).then(function(response){
				alert("Nalog za placanje je poslat banci!");
            },
			function(response){
				alert("Greska kod sendNalog");
			})
		}
	    
	   $scope.findDobavljac = function () {
	    	$scope.zaglavljeFakture.nazivDobavljaca=$scope.admin.firma.naziv;
	    	$scope.zaglavljeFakture.adresaDobavljaca=$scope.admin.firma.adresa;
	    	$scope.zaglavljeFakture.pibDobavljaca=$scope.admin.firma.PIB;
	    	$scope.zaglavljeFakture.uplataNaRacun=$scope.admin.firma.racun.brojRacuna;

		}
	}
	
]);
