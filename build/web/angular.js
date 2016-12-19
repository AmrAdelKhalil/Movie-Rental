var app=angular.module('myApp',[]);

app.controller('controller',function($scope){
	
	$scope.Number=0;
	$scope.currNumber=2;
	$scope.total=function(Number){

		return new Array(Number);
	}
});