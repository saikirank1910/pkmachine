angular.module("myApp").component("modelList", {
    templateUrl: 'models.html',
    controller: modelsController,
    bindings: {
      brands: '<'
    }
  });

  function modelsController(){
    var ctrl=this;
    ctrl.products = {
        "Nokia": [{
          "name": "lumia",
          "cost": 100000
        }, {
          "name": "C8",
          "cost": 123456
        }],
        "Mi": [{
          "name": "mi1",
          "cost": 34622
        }, {
          "name": "note",
          "cost": 234521
        }],
        "OnePlus": [{
          "name": "1+3",
          "cost": 25325
        }, {
          "name": "1+5",
          "cost": 534231
        }],
        "Samsung": [{
          "name": "s8",
          "cost": 52355
        }, {
          "name": "galaxy",
          "cost": 23455
        }]
      };
  
      ctrl.getProducts = function(brands) {
        return ctrl.products[brands];
      }
  }
  
  
  
  
  
  