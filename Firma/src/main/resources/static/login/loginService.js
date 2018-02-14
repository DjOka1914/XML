var services = angular.module('login.services', ['ngResource']);

services.service('loginService', ['$http', function($http){
	
	this.logIn = function(user){
		return $http.post("/user/logIn",user);
	}
	
	this.logOut = function(){
		return $http.get("/user/logOut");
	}
}]);