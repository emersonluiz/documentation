define(['route', 'systemFactory'], function (app, systemFactory) {

    app.controller('systemItemsController', ['$scope', '$routeParams', 'systemFactory', function($scope, $routeParams, systemFactory) {

        $scope.system = {};

        $scope.getSystem = function () {
            systemFactory.getSystem($routeParams.systemId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.system = d.data;
            }).catch(function(d) {
                console.log("ERROR", d)
            });
        }

        $scope.getSystem();
    }]);

});