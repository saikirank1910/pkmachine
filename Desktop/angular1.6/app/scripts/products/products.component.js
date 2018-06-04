angular.module('products')
	.component('productsComponent',{
		template:'products',
		controller:ProductsController
	});

	function ProductsController() {
		var self = this;
	}