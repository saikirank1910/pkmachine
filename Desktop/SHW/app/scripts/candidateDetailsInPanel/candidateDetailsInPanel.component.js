angular.module('CandidateDetailsInPanel').component('candidateDetailsInPanelComponent', {
    templateUrl:"../../views/candidateprofile.html" ,
    controller: function (appService, $http) {
        var ctrl = this;
        ctrl.cid = sessionStorage.getItem('cid');
        ctrl.path = 'candidate/getCandidateDetails/' + ctrl.cid;
        ctrl.name="";
        ctrl.email="";
        ctrl.phoneNumber="";
        appService.make({ endpoint: ctrl.path, method: "GET" })
            .then(function success(response) {
                let data = response.data;
              //  console.log(data.cname+" "+data.email+" "+data.phoneNumber);
                ctrl.name=data.cname;
                ctrl.email=data.email;
                ctrl.phoneNumber=data.phoneNumber;
            },
            function error(errors) {
               

            });
           
        ctrl.getResume = function () {
            $http.get('http://172.16.201.248:1910/SHW-rest/rest/candidate/getCandidateProfile/' + ctrl.cid, { responseType: 'arraybuffer' })
                .then(function (response) {
                    var file = new Blob([response.data], { type: 'application/pdf' });
                    var fileURL = URL.createObjectURL(file);
                    window.open(fileURL);
                });
        }
    }

});
