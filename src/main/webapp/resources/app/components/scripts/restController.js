define(['route', 'systemFactory', 'entityFactory', 'jsonFactory', 'restFactory', 'modalFactory'], function (app, systemFactory, entityFactory, jsonFactory, restFactory, modalFactory) {

    app.controller('restController', ['$scope', '$routeParams', 'systemFactory', 'entityFactory', 'jsonFactory', 'restFactory', 'modalFactory', function($scope, $routeParams, systemFactory, entityFactory, jsonFactory, restFactory, modalFactory) {

        $scope.rest = {resource:"", description: "", action: {name: "CREATE"}, method: {name: "GET"}};

        $scope.system = {};
        $scope.entity = {};

        $scope.actions = [{name: "CREATE"}, {name: "DELETE"}, {name: "UPDATE"}, {name: "READ"}, {name: "LIST"}];
        $scope.methods = [{name: "GET"}, {name: "POST"}, {name: "PUT"}, {name: "DELETE"}, {name: "OPTIONS"}, {name: "HEAD"}];

        $scope.requestJson = [];
        $scope.responseJson = [];

        $scope.rests = [];

        $scope.add = function() {
            $scope.createRest();
        	$scope.rest = {resource:"", description: "", action: {name: "CREATE"}, method: {name: "GET"}};
        }

        $scope.remove = function(id) {
            $scope.deleteRest(id);
        }

        $scope.getSystem = function () {
        	systemFactory.getSystem($routeParams.systemId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.system = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.getEntity = function () {
        	entityFactory.getEntity($routeParams.systemId, $routeParams.entityId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.entity = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.listJson = function () {
            jsonFactory.listJson($routeParams.systemId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.requestJsons = d.data;
                $scope.responseJsons = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.listRest = function () {
            restFactory.listRest($routeParams.entityId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.rests = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.createRest = function () {
            $scope.rest.action = $scope.rest.action.name;
            $scope.rest.method = $scope.rest.method.name;

            restFactory.createRest($scope.entity.id, $scope.rest).then(function (d) {
                console.log("SUCCESS", d.data);
                modalFactory.successModal("Successfully created rest!", "sm");
                $scope.listRest();
            }).catch(function(d) {
                console.log("ERROR", d);
                $scope.error(d);
            });
        }

        $scope.deleteRest = function (id) {
            restFactory.deleteRest($scope.entity.id, id).then(function (d) {
                console.log("SUCCESS", d.data);
                modalFactory.successModal("Successfully deleted rest!", "sm");
                $scope.listRest();
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
        $scope.getEntity();
        $scope.listJson();
        $scope.listRest();
    }]);

});