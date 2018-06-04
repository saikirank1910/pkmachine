angular
  .module('myNewProjectApp')
  	.component('appComponent',{
			template:'<header-component></header-component>\n'+
    	'<ng-outlet class="inner-context clearfix"></ng-outlet><footer-component></footer-component>\n',
    	controller:AppController,
    	$routeConfig: [
				{path: '/login', name: 'Login', component: 'loginComponent',useAsDefault: true},			
				{path: '/selectOptionsForSuperAdmin/...', name: 'SuperAdminSelectOptionsComponent', component: 'superAdminSelectOptionsComponent'},
				{path: '/selectOptionsForAdmin/...', name: 'AdminSelectOptionsComponent', component: 'adminSelectOptionsComponent'},
				{path: '/self', name: 'Self', component: 'selfComponent'},
				{path: '/selfAssessment', name: 'SelfAssessment', component: 'selfAssessmentComponent'},
				{path: '/panelMemberAuthentication', name: 'PanelMemberAuthentication', component: 'panelMemberAuthenticationComponent'},
				{path: '/panelEvaluation/...', name: 'PanelEvaluation', component: 'panelEvaluationComponent'},
				{path: '/panelEvaluation', name: 'PanelEvaluation', component: 'panelEvaluationComponent'},
				{path: '/selectOptionsForAdmin', name: 'AdminSelectOptionsComponent', component: 'adminSelectOptionsComponent'},
				{path: '/error', name: 'Error', component: 'errorComponent'},
				{path: '/submitted', name: 'Submitted', component: 'feedBackSubmittedComponent'}
		  ],
		  
  	});

  	function AppController() {
		  var self  = this;
  	}