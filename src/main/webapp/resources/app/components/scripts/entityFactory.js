define(['route'], function (app) {

    app.factory('entityFactory', function($http) {

        var entityFactory = {};
        var url = global.documentation;

        entityFactory.listEntity = function(systemId) {
            var promisse = $http.get(url +  "/system/" + systemId + "/entity").success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        entityFactory.getEntity = function(systemId, id) {
            var promisse = $http.get(url + "/system/" + systemId + "/entity/" + id).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        entityFactory.createEntity = function(systemId, entity) {
        	var promisse = $http.post(url + "/system/" + systemId + "/entity", entity).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        }

        entityFactory.deleteEntity = function(systemId, id) {
            var promisse = $http["delete"](url + "/system/" + systemId + "/entity/" + id).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        return entityFactory;

    });

});