define(['route', 'modalController'], function (app) { 

    app.factory('modalFactory', function($uibModal) {

        var modalFactory = {};

        // Modal success
        modalFactory.successModal = function (data, size) {
            var modalInstanceSuccess = $uibModal.open({
                animation: true,
                templateUrl: 'resources/app/components/modal/success.html',
                controller: 'modalController',
                size: size,
                backdrop: false,
                resolve: {
                    message: function () {
                        return data;
                    }
                }
            });
            return modalInstanceSuccess;
        }

        // Modal error
        modalFactory.errorModal = function(data, size, status) {
            var modalInstanceError = $uibModal.open({
                animation: true,
                templateUrl: 'resources/app/components/modal/error.html',
                controller: 'modalController',
                size: size,
                backdrop: false,
                resolve: {
                    message: function () {
                        return "Error on server: " + data;
                    }
                }
            });
            return modalInstanceError;
        }

        // Popup modal warning
        modalFactory.warningModal = function(data, size) {
            var modalInstanceWarning = $uibModal.open({
                animation: true,
                templateUrl: 'resources/app/components/modal/warning.html',
                controller: 'modalController',
                size: size,
                backdrop: false,
                resolve: {
                    message: function () {
                        return data;
                    }
                }
            });
            return modalInstanceWarning;
        }

        // Modal confirmation
        modalFactory.confirmationModal = function(data, size) {
            var modalInstanceConfirmation = $uibModal.open({
                animation: true,
                templateUrl: 'resources/app/components/modal/confirmation.html',
                controller: 'modalController',
                size: size,
                backdrop: false,
                resolve: {
                    message: function () {
                        return data;
                    }
                }
            });
            return modalInstanceConfirmation;
        }

        return modalFactory;
    });
})