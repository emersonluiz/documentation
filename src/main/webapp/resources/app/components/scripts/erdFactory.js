define(['route'], function (app) {

    app.factory('erdFactory', function($http) {

        var erdFactory = {};
        var vUrl = global.documentation;

        erdFactory.listErd = function(entityId) {
            var promisse = $http.get(vUrl +  "/entity/"+entityId+"/erd").success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        erdFactory.getErd = function(entityId, id) {
            var promisse = $http({
	    			method: 'GET',
	                url: vUrl + '/entity/'+entityId+'/erd/'+id,
	                headers: { 'Content-Type': undefined},
	                responseType:'arraybuffer',
	    		}
            ).success(function (response, data) {
                return response;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        }
  
        return erdFactory;

    });

});