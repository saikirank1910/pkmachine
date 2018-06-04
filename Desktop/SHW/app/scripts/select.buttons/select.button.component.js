angular.module('selectButton')
.component('selectButton',{
    template:
    '<button type="button" class="btn btn-link" ng-link="[\'Skill-Score\']">Skill-Score</button><br>'+
    '<button type="button" class="btn btn-link" ng-link="[\'Candidate\']">Candidate</button><br>'+
    '<button type="button" class="btn btn-link" ng-link="[\'Self-Assesment\']">Self-Assesment</button>',
   controller:function(){

    }
});
