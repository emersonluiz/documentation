define(['route'], function (app) {

    app.factory('restFactory', function($http) {

        var restFactory = {};
        var url = global.documentation;

        restFactory.listRest = function(entityId) {
            var promisse = $http.get(url +  "/entity/" + entityId + "/rest").success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        restFactory.getRest = function(entityId, id) {
            var promisse = $http.get(url + "/entity/" + entityId + "/rest/" + id).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        restFactory.createRest = function(entityId, rest) {
        	var promisse = $http.post(url + "/entity/" + entityId + "/rest", rest).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        }

        restFactory.deleteRest = function(entityId, id) {
            var promisse = $http["delete"](url + "/entity/" + entityId + "/rest/" + id).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        return restFactory;

    });

});