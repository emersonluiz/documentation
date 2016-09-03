define(['route', 'systemFactory', 'entityFactory'], function (app, systemFactory, entityFactory) {

    app.controller('entityItemsController', ['$scope', '$routeParams', 'systemFactory', 'entityFactory', function($scope, $routeParams, systemFactory, entityFactory) {

        $scope.system = {};
        $scope.entity = {};

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

        $scope.getSystem();
        $scope.getEntity();
    }]);

});