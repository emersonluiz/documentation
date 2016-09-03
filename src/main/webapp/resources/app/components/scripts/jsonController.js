define(['route', 'systemFactory', 'jsonFactory', 'modalFactory'], function (app, systemFactory, jsonFactory, modalFactory) {

    app.controller('jsonController', ['$scope', '$routeParams', 'systemFactory', 'jsonFactory', 'modalFactory', function($scope, $routeParams, systemFactory, jsonFactory, modalFactory) {

        $scope.json = {name: "", description: ""};

        $scope.system = {};

        $scope.jsons = [];

        $scope.add = function() {
            $scope.createJson();
            $scope.json = {name: "", description: ""};
        }

        $scope.remove = function(id) {
            $scope.deleteJson(id);
        	
        }

        $scope.gotoJSONProperties = function(id) {
            location.href = "#/system/" + $scope.system.id + "/items/json/" + id + "/property";
        }

        $scope.getSystem = function () {
        	systemFactory.getSystem($routeParams.systemId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.system = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.listJson = function () {
            jsonFactory.listJson($routeParams.systemId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.jsons = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.createJson = function () {
            jsonFactory.createJson($scope.system.id, $scope.json).then(function (d) {
                console.log("SUCCESS", d.data);
                modalFactory.successModal("Successfully created json!", "sm");
                $scope.listJson();
            }).catch(function(d) {
                console.log("ERROR", d);
                $scope.error(d);
            });
        }

        $scope.deleteJson = function (id) {
            jsonFactory.deleteJson($scope.system.id, id).then(function (d) {
                console.log("SUCCESS", d.data);
                modalFactory.successModal("Successfully deleted json!", "sm");
                $scope.listJson();
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
        $scope.listJson();
    }]);

});