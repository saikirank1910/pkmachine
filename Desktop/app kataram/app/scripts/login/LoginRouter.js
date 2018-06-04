
angular.module("myApp").config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "../views/login/UserLogin.html"
        })
        .when("/register", {
            templateUrl: "../views/login/NewRegistration.html"
        })
       .when("/viewPost", {
            templateUrl: "../views/dashBoard/viewPosts.html"
        }).when("/postQuery", {
            templateUrl: "../views/dashBoard/postQuery.html"
        }).when("/search", {
            templateUrl: "../views/dashBoard/searchPost.html"
        }).when("/profile", {
            templateUrl: "../views/dashBoard/editProfile.html"
        }).when("/smeBoard", {
            templateUrl: "../views/dashBoard/smeBoard.html"
        }).when("/adminBoard", {
            templateUrl: "../views/dashBoard/adminBoard.html"
        })

});
