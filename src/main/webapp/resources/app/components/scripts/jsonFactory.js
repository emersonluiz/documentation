define(['route'], function (app) {

    app.factory('jsonFactory', function($http) {

        var jsonFactory = {};
        var url = global.documentation;

        jsonFactory.listJson = function(systemId) {
            var promisse = $http.get(url +  "/system/" + systemId + "/json").success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        jsonFactory.getJson = function(systemId, id) {
            var promisse = $http.get(url + "/system/" + systemId + "/json/" + id).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        jsonFactory.createJson = function(systemId, json) {
        	var promisse = $http.post(url + "/system/" + systemId + "/json", json).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        }

        jsonFactory.deleteJson = function(systemId, id) {
            var promisse = $http["delete"](url + "/system/" + systemId + "/json/" + id).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        return jsonFactory;

    });

});