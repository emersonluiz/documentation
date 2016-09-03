define(['route'], function (app) {

    app.factory('jsonPropertyFactory', function($http) {

        var jsonPropertyFactory = {};
        var url = global.documentation;

        jsonPropertyFactory.listJsonProperty = function(jsonId) {
            var promisse = $http.get(url +  "/json/" + jsonId + "/property").success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        jsonPropertyFactory.createJsonProperty = function(jsonId, jsonProperty) {
        	var promisse = $http.post(url + "/json/" + jsonId + "/property", jsonProperty).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        }

        jsonPropertyFactory.deleteJsonProperty = function(jsonId, id) {
            var promisse = $http["delete"](url + "/json/" + jsonId + "/property/" + id).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        return jsonPropertyFactory;

    });

});