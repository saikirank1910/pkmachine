angular.module('adminPageOptions').component('adminSelectOptionsComponent',{
 templateUrl:"../views/AdminNavBar.html",
$routeConfig: [
    {path: '/candidate', name: 'Candidate', component: 'candidateComponent',useAsDefault: true},
    {path: '/adminPanelAssigneeForm', name: 'AdminPanelAssigneeForm', component: 'adminPanelAssigneeModuleComponent'}
   // {path: '/panelAssigneeForm', name: 'PanelAssigneeForm', component: 'PanelAssigneeComponent'},
],
controller:function(){
    var self=this;
    self.logout=function(){
        localStorage.setItem('admin',false);
        window.location.href='http://172.16.201.248:9000/#!/';
    }
}
});