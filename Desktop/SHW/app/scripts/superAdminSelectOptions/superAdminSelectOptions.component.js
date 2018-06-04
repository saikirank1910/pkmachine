angular.module('superAdminSelectOptions').component('superAdminSelectOptionsComponent',{
    
templateUrl: '../views/SuperAdminNavBar.html',
   $routeConfig: [
        {path: '/admin', name: 'Admin', component: 'adminComponent',useAsDefault: true },
        {path: '/panel', name: 'Panel', component: 'panelComponent'},
        {path: '/skills', name: 'Skill', component: 'mySkills'},
        {path: '/personalTraits', name: 'Trait', component: 'traitsComponent'},
        {path: '/SkillLevel', name: 'SkillLevel', component: 'skillLevelComponent'}			
    ],
	controller:function() {
        var self=this;
        self.logout=function(){
            localStorage.setItem('superAdmin',false);
            window.location.href='http://172.16.201.248:9000/#!/';
        }
       
    },



});