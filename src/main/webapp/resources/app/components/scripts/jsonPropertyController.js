define(['route', 'systemFactory', 'jsonFactory', 'jsonPropertyFactory', 'modalFactory'], function (app, systemFactory, jsonFactory, jsonPropertyFactory, modalFactory) {

    app.controller('jsonPropertyController', ['$scope', '$routeParams', 'systemFactory', 'jsonFactory', 'jsonPropertyFactory', 'modalFactory', function($scope, $routeParams, systemFactory, jsonFactory, jsonPropertyFactory, modalFactory) {

        $scope.jsonProperty = {property: "", type: {name: "string"}};

        $scope.system = {};
        $scope.json = {};

        $scope.types = [{name: "string"}, {name: "int"}, {name: "date"}, {name: "boolean"}, {name: "object"}];

        $scope.jsonProperties = [];
        $scope.relatedJsons = [];

        $scope.add = function() {
            $scope.createJsonProperty();
            $scope.jsonProperty = {property: "", type: {name: "string"}};
        }

        $scope.remove = function(id) {
            $scope.deleteJsonProperty(id);
        }

        $scope.getSystem = function () {
        	systemFactory.getSystem($routeParams.systemId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.system = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.getJson = function () {
        	jsonFactory.getJson($routeParams.systemId, $routeParams.jsonId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.json = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.listJson = function () {
            jsonFactory.listJson($routeParams.systemId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.relatedJsons = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.listJsonProperty = function () {
            jsonPropertyFactory.listJsonProperty($routeParams.jsonId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.jsonProperties = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.createJsonProperty = function () {
        	$scope.jsonProperty.type = $scope.jsonProperty.type.name
            jsonPropertyFactory.createJsonProperty($scope.json.id, $scope.jsonProperty).then(function (d) {
                console.log("SUCCESS", d.data);
                modalFactory.successModal("Successfully created json property!", "sm");
                $scope.listJsonProperty();
            }).catch(function(d) {
                console.log("ERROR", d);
                $scope.error(d);
            });
        }

        $scope.deleteJsonProperty = function (id) {
            jsonPropertyFactory.deleteJsonProperty($scope.json.id, id).then(function (d) {
                console.log("SUCCESS", d.data);
                modalFactory.successModal("Successfully deleted json property!", "sm");
                $scope.listJsonProperty();
            }).catch(function(d) {
                console.log("ERROR", d);
                $scope.error(d);
            });
        }

        $scope.error = function(resp) {
        	var text = "Error on Server."
            if (resp.data.failureReason != undefined) {
                text = resp.data.failureReason;
            }
            modalFactory.errorModal(text, "sm");
        }

        $scope.getSystem();
        $scope.getJson();
        $scope.listJson();
        $scope.listJsonProperty();
    }]);

});