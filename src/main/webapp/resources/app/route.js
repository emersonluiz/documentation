
define(['angular-amd', 'angular-route', 'angular-resource', 'angular-bootstrap', 'bootstrap-js', 'scripts-config', 'angular-cookies'], function (angularAMD) {

    var app = angular.module('myApp', ['ngRoute', 'ngResource', 'ngCookies', 'ui.bootstrap']);
    
    app.config(function ($routeProvider, $httpProvider) {

        $routeProvider
            .when("/system", angularAMD.route({
                templateUrl   : 'resources/app/components/system.html',
                controller    : 'systemController',
                controllerUrl : 'resources/app/components/scripts/systemController.js'
            }))
            .when("/system/:systemId/items", angularAMD.route({
                templateUrl   : 'resources/app/components/systemItems.html',
                controller    : 'systemItemsController',
                controllerUrl : 'resources/app/components/scripts/systemItemsController.js'
            }))
            .when("/system/:systemId/items/entity", angularAMD.route({
                templateUrl   : 'resources/app/components/entity.html',
                controller    : 'entityController',
                controllerUrl : 'resources/app/components/scripts/entityController.js'
            }))
            .when("/system/:systemId/items/entity/:entityId/items", angularAMD.route({
                templateUrl   : 'resources/app/components/entityItems.html',
                controller    : 'entityItemsController',
                controllerUrl : 'resources/app/components/scripts/entityItemsController.js'
            }))
            .when("/system/:systemId/items/entity/:entityId/items/rest", angularAMD.route({
                templateUrl   : 'resources/app/components/rest.html',
                controller    : 'restController',
                controllerUrl : 'resources/app/components/scripts/restController.js'
            }))
            .when("/system/:systemId/items/entity/:entityId/items/erd", angularAMD.route({
                templateUrl   : 'resources/app/components/erd.html',
                controller    : 'erdController',
                controllerUrl : 'resources/app/components/scripts/erdController.js'
            }))
            .when("/system/:systemId/items/json", angularAMD.route({
                templateUrl   : 'resources/app/components/json.html',
                controller    : 'jsonController',
                controllerUrl : 'resources/app/components/scripts/jsonController.js'
            }))
            .when("/system/:systemId/items/json/:jsonId/property", angularAMD.route({
                templateUrl   : 'resources/app/components/jsonProperty.html',
                controller    : 'jsonPropertyController',
                controllerUrl : 'resources/app/components/scripts/jsonPropertyController.js'
            }))
            .otherwise({
                redirectTo: "/system"
            })
    });

    

    //run
    app.run(function($rootScope, $cookies, $location) {
/*
        $rootScope.$on('$routeChangeStart', function(event, next, current) {
            if($location.url().indexOf("auth") === -1) {
                sessionId = localStorage.getItem('sessionId');
                if (sessionId==='null' || sessionId==='') {
                    $rootScope.authenticate = false;
                    location.href = global.cps;
                } else {
                    if (!$rootScope.authenticate) {
                        $rootScope.authenticate = true;
                    }
                }
                $rootScope.userName = localStorage.getItem('userName');
                var modules = JSON.parse(localStorage.getItem('modules'));
                menuService.setMenu($rootScope, modules);
            }
        });*/

    });

    //start the aplication.
    return angularAMD.bootstrap(app);

});