angular.module('round1InPanel')
    .component('round1InPanelComponent', {
        templateUrl: '../views/round1Form.html',

        controller: function (appService) {
            var ctrl = this;
            ctrl.cid = sessionStorage.getItem('cid');
            ctrl.pid = sessionStorage.getItem('pid');
            ctrl.round = sessionStorage.getItem('round');
            ctrl.lists = [];
            ctrl.personalTraits = [];
            ctrl.evaluation = [];
            ctrl.roundOneResult = [];
            
            appService.make({ endpoint: 'panelEvaluation/' + ctrl.cid + '/getSkillsRating', method: "GET" })
                .then(function success(response) {
                    let temp = response.data;
                    //console.log(temp);
                    for (var j = 0; j < temp.length; j++) {
                        ctrl.lists[j] = new Object();
                        ctrl.lists[j].subtechnology = temp[j].technologyName;
                        ctrl.lists[j].rating = temp[j].ratingId;
                        ctrl.lists[j].panelId = temp[j].pid;
                    }
                },

                function error(errors) {

                });
            appService.make({ endpoint: 'panelEvaluation/' + ctrl.cid + '/getTraitsRating', method: "GET" })
                .then(function success(response) {
                    let temp = response.data;
                    //console.log(temp);
                    for (var j = 0; j < temp.length; j++) {
                        ctrl.personalTraits[j] = new Object();
                        ctrl.personalTraits[j].name = temp[j].traitName;
                        ctrl.personalTraits[j].rating = temp[j].ratingId;
                        ctrl.personalTraits[j].panelId = temp[j].pid;
                    }
                },
                function error(errors) {

                });
            setTimeout(function () {
                appService.make({ endpoint: 'panelEvaluation/' + ctrl.cid + '/getEvaluation', method: "GET" })
                    .then(function success(response) {
                        ctrl.evaluation = response.data;
                        var panelId = [];
                        var lists = [];
                        var personalTraits = [];
                        var evaluation = {};
                        for (var j = 0; j < ctrl.evaluation.length; j++) {
                            panelId[j] = ctrl.evaluation[j].panelId;
                        }
                        // console.log(panelId);
                        for (var j = 0; j < ctrl.evaluation.length; j++) {
                            ctrl.roundOneResult[j] = new Object();
                            let count=0;
                            for (var i = 0; i < ctrl.evaluation.length; i++) {
                                if (ctrl.evaluation[i].panelId == panelId[j]) {
                                    evaluation[count] = new Object();
                                    evaluation[count] = Object.assign(ctrl.evaluation[i]);
                                    count++;
                                }
                            }
                            //console.log(evaluation);
                            
                            ctrl.roundOneResult[j].evaluation = evaluation;
                            evaluation = [];
                            count=0;
                            for (var i = 0; i < ctrl.lists.length; i++) {
                                if (ctrl.lists[i].panelId == panelId[j]) {
                                    lists[count] = new Object();
                                    lists[count] = Object.assign(ctrl.lists[i]);
                                    count++;
                                }
                            }
                            //console.log(lists);
                            ctrl.roundOneResult[j].lists = lists;
                            lists = [];
                            count=0;
                            for (var i = 0; i < ctrl.personalTraits.length; i++) {
                                if (ctrl.personalTraits[i].panelId == panelId[j]) {
                                    personalTraits[count] = new Object();
                                    personalTraits[count] = Object.assign(ctrl.personalTraits[i]);
                                    count++;
                                }
                            }
                            count=0;
                            //console.log(personalTraits);
                            ctrl.roundOneResult[j].personalTraits = personalTraits;
                            personalTraits = [];
                        }
                        console.log(ctrl.roundOneResult);
                    },
                    function error(errors) {

                    });
            }, 1000);
        }
    });