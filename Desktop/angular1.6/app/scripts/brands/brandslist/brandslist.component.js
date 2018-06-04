angular.module('brands')
	.component('brandsComponent',{
		template:'brands',
		 bindings: { $router: '<' },
		controller:function() {
			this.$routerOnActivate = function(next) {
		    	console.log('$routerOnActivate', this, arguments);
		    	// Load up the crises for this view
		  	};
		}
	});