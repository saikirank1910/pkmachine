angular
  .module('myNewProjectApp')
  	.service('appService',['$http',function($http){
		    this.apiBase = 'http://172.16.101.149:8080/Event-rest/';
		 
		    this.make = function(options) {
		       var url = this.apiBase;
		 
		      // resolve URL
		      if(options.endpoint) {
		        url += options.endpoint;
		      }
		 
		      // return a new request object
		      return new HTTP(url, options);
		    }
		 
		  // Our XHR object. This one gets a new instance with every request.
		  var HTTP = function(url, opts) {
				return $http({method: opts.method, url: url, data: opts.data,
					 headers: {
					'Content-Type': 'application/json'
				  }});
		  };
  	}]);