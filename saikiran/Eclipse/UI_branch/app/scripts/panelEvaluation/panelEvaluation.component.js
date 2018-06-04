angular.module('panelEvaluation')
.component('panelEvaluationComponent',{
    templateUrl :'../views/panelNavBar.html',
     $routeConfig: [
         {path: '/candidateSkillScoreInPanel', name: 'CandidateSkillScoreInPanel', component: 'candidateSkillScoreInPanelComponent'},
         {path: '/candidateDetailsInPanel', name: 'CandidateDetailsInPanel', component: 'candidateDetailsInPanelComponent'},
         {path: '/candidateAssessmentPageInPanel', name: 'CandidateAssessmentPageInPanel', component: 'candidateAssessmentPageInPanelComponent'},
         {path: '/round1', name:'Round1', component:'round1InPanelComponent'}
        ],
    controller:function () {
        var ctrl = this;
        ctrl.cid = sessionStorage.getItem('cid');
        ctrl.pid = sessionStorage.getItem('pid');
        ctrl.round = sessionStorage.getItem('round');       
    }
});