angular.module('skill')
	.component('skillComponent',{
		templateUrl:'../views/SkillScore.html',
		controller:function() {
		var self=this;
			this.subTechnologies=["HTML","Angular","CSS"];
			this.personalTraits=["Communication Skills","Confidence","Attitude"," Openness to suggessions"];
            
	
			this.addTechnology=function(test){
				self.subTechnologies.push(test);
			}
		}
	});