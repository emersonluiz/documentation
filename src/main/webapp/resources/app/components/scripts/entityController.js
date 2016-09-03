define(['route', 'systemFactory', 'entityFactory', 'modalFactory'], function (app, systemFactory, entityFactory, modalFactory) {

    app.controller('entityController', ['$scope', '$routeParams', 'systemFactory', 'entityFactory', 'modalFactory', function($scope, $routeParams, systemFactory, entityFactory, modalFactory) {

        $scope.entity = {name: "", description: ""};
        $scope.system = {};
        $scope.entities = [];

        $scope.add = function() {
            $scope.createEntity();
            $scope.entity = {name: "", description: ""};
        }

        $scope.remove = function(id) {
            $scope.deleteEntity(id);
        }

        $scope.gotoItems = function(id) {
            location.href = "#system/" + $scope.system.id + "/items/entity/" + id + "/items";
        }

        $scope.getSystem = function () {
        	systemFactory.getSystem($routeParams.systemId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.system = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.listEntity = function () {
            entityFactory.listEntity($routeParams.systemId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.entities = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.createEntity = function () {
            entityFactory.createEntity($scope.system.id, $scope.entity).then(function (d) {
                console.log("SUCCESS", d.data);
                modalFactory.successModal("Successfully created entity!", "sm");
                $scope.listEntity($scope.system.id);
            }).catch(function(d) {
                console.log("ERROR", d);
                $scope.error(d);
            });
        }

        $scope.deleteEntity = function (id) {
            entityFactory.deleteEntity($scope.system.id, id).then(function (d) {
                console.log("SUCCESS", d.data);
                modalFactory.successModal("Successfully deleted entity!", "sm");
                $scope.listEntity();
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
        $scope.listEntity();
    }]);

});