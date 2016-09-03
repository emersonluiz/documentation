define(['route'], function (app) {

    app.factory('systemFactory', function($http) {

        var systemFactory = {};
        var vUrl = global.documentation + "/system";

        systemFactory.listSystem = function() {
            var promisse = $http.get(vUrl).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        systemFactory.getSystem = function(id) {
            var promisse = $http.get(vUrl + "/" + id).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        systemFactory.createSystem = function(system) {
        	var promisse = $http.post(vUrl, system).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        }

        systemFactory.deleteSystem = function(id) {
            var promisse = $http["delete"](vUrl + "/" + id).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        systemFactory.generateDocumentation = function(id) {
        	console.log("AQUI ENTROU...")
            var promisse = $http({
	    			method: 'GET',
	                url: vUrl + '/' + id + '/generate_documentation',
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

        return systemFactory;

    });

});