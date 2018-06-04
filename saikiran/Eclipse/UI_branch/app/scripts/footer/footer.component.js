angular.module('footer')
	.component('footerComponent',{
		template:'<div class="well well-sm" style="background-color:#092946;'+
		' position:fixed;left:0;bottom:0;text-align:center;margin:0;width:100%;color:whitesmoke">'+
		'© ProKarma Inc.Privacy policy Sitemap '+
		'<a href="https://www.prokarma.com/">About Us </a><a href="#">Contact Us</a></div>',
		controller:FooterController
	});

	function FooterController(appService) {
		var self = this;

		/*appService.make({endpoint:"assets/products.json",method:"GET"})
			.then(function(response){
				console.log(response);
			},function(error){
				console.log(error);
			});*/

	}

	FooterController.$inject = ['appService'];