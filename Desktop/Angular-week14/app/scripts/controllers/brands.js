angular.module("myApp").component("brandList", {
    templateUrl: 'brands.html',
    controller: brandsController
  });
  
function brandsController() {
    var ctrl = this;
    ctrl.list = ["Nokia", "Mi", "OnePlus", "Samsung"];
    ctrl.models =null;
  }