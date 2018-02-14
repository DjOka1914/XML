'use strict';

angular.module('routerApp', ['ui.router', 
	'admin.services','admin.controllers',
	'login.services','login.controllers'
	])

.config(function($stateProvider, $urlRouterProvider) {
        
        $urlRouterProvider.otherwise('/login');
        
        $stateProvider
        
        .state('login', {
        	url : '/login',
          	templateUrl : 'login/login.html',
          	controller : 'loginController'
         })
         
         .state('login.logOut', {
        	url : '/logout',
         	resolve: {
        		promiseObj:  function($http,$location){
        			$location.path('login');
                    return $http.get("/user/logOut");
                 }}
         })
         
         .state('loggedIn', {
        	url : '/loggedIn',
        	templateUrl : 'loggedIn.html'
         })
         
         .state('admin', {
        	url : '/admin',
          	templateUrl : 'admin/adminPartial.html',
            controller : 'adminController'
         })
         
         .state('admin.home', {
        	url : '/home',
          	templateUrl : 'admin/adminHome.html'
        })
        .state('admin.unosFakture', {
        	url : '/unosFakture',
          	templateUrl : 'admin/faktura/unosFakture.html'
        })
        .state('admin.unosStavkeFakture', {
        	url : '/unosStavkeFakture',
          	templateUrl : 'admin/faktura/unosStavkeFakture.html'
        })
        .state('admin.sveUlazneFakture', {
        	url : '/sveUlazneFakture',
          	templateUrl : 'admin/faktura/sveUlazneFakture.html'
        })
        .state('admin.sveIzlazneFakture', {
        	url : '/sveIzlazneFakture',
          	templateUrl : 'admin/faktura/sveIzlazneFakture.html'
        })
        .state('admin.sviNalozi', {
        	url : '/sveNalozi',
          	templateUrl : 'admin/nalog/sviNalozi.html'
        })
        .state('admin.izvodi', {
        	url : '/izvodi',
          	templateUrl : 'admin/nalozi.html'
        })
});