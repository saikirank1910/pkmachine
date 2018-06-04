'use strict';

/**
 * @ngdoc overview
 * @name myNewProjectApp
 * @description
 * # myNewProjectApp
 *
 * Main module of the application.
 */
angular
  .module('myNewProjectApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngComponentRouter',
    'products',
    'brands',
    'header',
    'footer',
    'login',
    'myApp'
  ])
.config(function($locationProvider) {
   $locationProvider.html5Mode(true);
})

.value('$routerRootComponent', 'appComponent');
