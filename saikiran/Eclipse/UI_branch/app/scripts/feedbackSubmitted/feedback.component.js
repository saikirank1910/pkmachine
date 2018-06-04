angular.module('error')
.component('errorComponent',{
    templateUrl:'../views/Error.html',
    controller:function($window) {
        var self=this;
        self.closeWindow = function(){
             //window.location.href = 'http://172.16.201.248:9000';
            close();
        }
        }
});

angular.module('feedBackSubmitted')
.component('feedBackSubmittedComponent',{
    templateUrl:'../views/feedbackSubmitted.html',
    controller:function($window) {
        var self=this;
        self.closeWindow = function(){
            close();
        }
        }
});