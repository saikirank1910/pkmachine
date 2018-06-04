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
    'ngSanitize',
    'angular-growl',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngComponentRouter',
    'footer',
    'loginModule',
    'candidate',
    'admin',
    'panel',
    'skills',
    'header',
    'self',
    'superAdminSelectOptions',
    'selfAssessment',
    'adminPanelAssigneeModule',
    'panelEvaluation',
    'panelMemberAuthentication',
    'CandidateDetailsInPanel',
    'CandidateSkillScoreInPanel',
    'traits',
    'CandidateAssessmentPageInPanel',
    'adminPageOptions',
    'skillLevel',
    'round1InPanel',
    'error',
    'feedBackSubmitted',
    'ui.bootstrap'
  ])
.config(function($locationProvider,$routeProvider,$httpProvider) {
  // $locationProvider.html5Mode(true);
    $httpProvider.defaults.headers.common = {};
    $httpProvider.defaults.headers.post   = {};
    $httpProvider.defaults.headers.put    = {};
    $httpProvider.defaults.headers.patch  = {};
})

.value('$routerRootComponent', 'appComponent');
