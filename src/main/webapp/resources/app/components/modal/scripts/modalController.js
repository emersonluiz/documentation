define(['route'], function (app) { 

    app.controller('modalController', function ($scope, $uibModalInstance, message) {
        $scope.message = message;

        $scope.ok = function () {
            $uibModalInstance.dismiss('ok');
            window.scroll(0, 0);
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    });

})