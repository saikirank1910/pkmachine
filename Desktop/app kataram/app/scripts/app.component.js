angular
  .module('myApp')
  	.component('appComponent',{
  		template:'<div ng-view></div><nav>\n',
    	controller:AppController,
    	$routeConfig: [
    	
  		]
  	});

  	function AppController() {
  		var self  = this;
  	}