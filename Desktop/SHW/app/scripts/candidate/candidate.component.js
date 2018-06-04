angular.module('candidate')
    .component('candidateComponent', {
        templateUrl: '../views/candidate.html',
        controller: function ($http, appService) {
            var ctrl = this;
            ctrl.name = "";
            ctrl.email = "";
            ctrl.number;
            ctrl.technology = "";
            ctrl.technologies = [];
            appService.make({ endpoint: "superadmin/getTechnnologies", method: "GET" })
                .then(function (response) {
                    ctrl.technologies = response.data;
                }, function (error) {
                    console.log(error);
                });
            let verify = localStorage.getItem('admin');
            console.log(verify + " admin page");
            if (verify == "false") {
                window.location.href = 'http://172.16.201.248:9000';
            }

            ctrl.uploadFile = function (candidateAddForm) {
                candidateAddForm.$setPristine();
                var data = new FormData();
                data.append('name', ctrl.name);
                data.append('email', ctrl.email);
                data.append('number', ctrl.number);
                data.append('technology', ctrl.technology);
                angular.forEach(ctrl.files, function (file, email, name, number) {
                    data.append('file', file);
                });
                $http.post('http://172.16.201.248:1910/SHW-rest/rest/candidate/upload', data, {
                    transformRequest: angular.identity,
                    headers: {
                        'Content-Type': undefined
                    }
                }).then(function (response) {
                    
                    $("#candidateAdded").show();
                    setTimeout(function() { $("#candidateAdded").hide(); }, 2500);
                }, function (error) {
                    console.log(error);
                });
                ctrl.name = "";
                ctrl.email = "";
                ctrl.number = "";
                ctrl.technology = "";
            }
            appService.make({ endpoint: "superadmin/getTechnnologies", method: "GET" })
                .then(function (response) {
                    console.log("inside success", response);
                    ctrl.technologies = response.data;
                  
                    candidateAddForm.$setPristine();
                }, function (error) {
                    console.log(error);
                });
        },

    });