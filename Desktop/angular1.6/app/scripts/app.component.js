angular
  .module('myNewProjectApp')
  	.component('appComponent',{
  		template:'<div>MyApp</div><nav>\n' +
    	'<a ng-link="[\'Brands\']">Brands</a>\n' +
    	'<a ng-link="[\'Products\']">Products</a>\n' +
    	'</nav>\n' +
    	'<ng-outlet></ng-outlet>\n',
    	controller:AppController,
    	$routeConfig: [
    		{path: '/brands', name: 'Brands', component: 'brandsComponent', useAsDefault: true},
    		{path: '/products', name: 'Products', component: 'productsComponent' }
  		]
  	});

  	function AppController() {
  		var self  = this;


  	}