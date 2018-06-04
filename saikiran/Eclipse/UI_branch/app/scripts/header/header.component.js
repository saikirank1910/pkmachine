angular.module('header')
	.component('headerComponent',{
		template:'<div class="well well-sm top-bar _fixed _scrolling" style="background-color:#092946;rigth:0;top0;'+
		'margin:0;width:100%;color:whitesmoke;font-family: -webkit-pictograph;height: 82px;">'+
		'<img class="img-rounded" src="../../images/logo2.png" alt="Prokarma">'+
		'<h1 style="text-align:left;font-size: 19px;margin: 2px;">Sourcerer-The Hiring Wizard</h1></div>',
		controller:HeaderController
	});

	function HeaderController(appService) {
		var self = this;

		/*appService.make({endpoint:"assets/products.json",method:"GET"})
			.then(function(response){
				console.log(response);
			},function(error){
				console.log(error);
			});*/

	}

	HeaderController.$inject = ['appService'];