define(['route', 'systemFactory', 'modalFactory'], function (app, systemFactory, modalFactory) {

    app.controller('systemController', ['$scope', 'systemFactory', 'modalFactory', function($scope, systemFactory, modalFactory) {

        $scope.system = {name: "", description: ""};

        $scope.systems = [];

        $scope.add = function() {
            $scope.createSystem();
            $scope.system = {name: "", description: ""};
        }

        $scope.remove = function(id) {
            $scope.deleteSystem(id);
        }

        $scope.gotoItems = function(id) {
            location.href = "#system/" + id + "/items";
        }

        $scope.generateDocumentation = function(id) {
        	systemFactory.generateDocumentation(id).then(function (d) {
                console.log("SUCCESS", d);

                var a = document.createElement('a');
                var blob = new Blob([d.data], {'type':"application/octet-stream"});
                a.href = URL.createObjectURL(blob);
                a.download = "filename.zip";
                a.click();
                
            }).catch(function(d) {
                console.log("ERROR", d);
                $scope.error(d);
            });
        }

        $scope.listSystem = function() {
            systemFactory.listSystem().then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.systems = d.data;
            }).catch(function(d) {
                console.log("ERROR", d);
            });
        }

        $scope.createSystem = function() {
            systemFactory.createSystem($scope.system).then(function (d) {
                console.log("SUCCESS", d.data);
                modalFactory.successModal("Successfully created system!", "sm");
                $scope.listSystem();
            }).catch(function(d) {
                console.log("ERROR", d);
                $scope.error(d);
            });
        }

        $scope.deleteSystem = function(id) {
            systemFactory.deleteSystem(id).then(function (d) {
                console.log("SUCCESS", d.data);
                modalFactory.successModal("Successfully deleted system!", "sm");
                $scope.listSystem();
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

        $scope.listSystem();
    }]);

});