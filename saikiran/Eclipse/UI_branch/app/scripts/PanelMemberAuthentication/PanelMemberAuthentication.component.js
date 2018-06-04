angular.module('panelMemberAuthentication')
    .component('panelMemberAuthenticationComponent', {
        templateUrl: '',
        $routeConfig: [
         {path: '/candidateSkillScoreInPanel', name: 'CandidateSkillScoreInPanel', component: 'candidateSkillScoreInPanelComponent'},
         {path: '/candidateDetailsInPanel', name: 'CandidateDetailsInPanel', component: 'candidateDetailsInPanelComponent'},
         {path: '/candidateAssessmentPageInPanel', name: 'CandidateAssessmentPageInPanel', component: 'candidateAssessmentPageInPanelComponent'},
         {path: '/round1', name:'Round1', component:'round1InPanelComponent'}
        ],
        controller: function (appService, $location) {
            var _self = this;
        
                let tokenData = new Object();
                tokenData.panelEmail =  $location.search().email;
                tokenData.token =  $location.search().token;
              
                appService.make({ endpoint: 'panelAssignee/panellogin', method: "POST", data: tokenData })
                    .then(function success(response) {
                        let cid = response.data.candidateId;
                        let pid = response.data.panelId;
                        let round = response.data.round;
                        let token = response.data.isVisited;
                        sessionStorage.setItem("cid", cid);
                        sessionStorage.setItem("pid", pid);
                        sessionStorage.setItem("round", round)
                        if (round!=0 && token==0) {
                            console.log('hello');
                            $location.path('/panelEvaluation');
                        }
                        else{
                           window.open('http://172.16.201.248:9000/#!/error',"_self");
                        }
                    },
                    function error(errors) {
                      
                    });

        }


    });