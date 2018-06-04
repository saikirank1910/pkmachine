angular
  .module('myNewProjectApp')
  	.component('appComponent',{
			template:'<ng-outlet class="inner-context clearfix"></ng-outlet>',
    	controller:AppController,
    	$routeConfig: [
				{path: '/login', name: 'Login', component: 'loginComponent',useAsDefault: true},
				{path: '/registration', name: 'Registration', component: 'registrationComponent'},
				{path: '/search', name: 'Search', component: 'searchComponent'}			
		  ],
		  
  	});

  	function AppController() {
		  var ctrl  = this;
  	}