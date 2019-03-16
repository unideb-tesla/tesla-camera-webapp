var app = angular.module(
    "tesla",
    ["ngResource",
        "ngSanitize", "ngRoute", "ngAnimate", "ui.bootstrap", "ng-showdown"]).filter('to_trusted',
    ['$sce', function ($sce, $rootScope) {
        return function (text) {
            return $sce.trustAsHtml(text);
        };

    }]);

app.config(function ($routeProvider) {
    $routeProvider.otherwise({
        templateUrl: "app/devices.html",
        controller: "devicesCtrl"
    }).when("/devices/:id", {
        templateUrl: "app/device.html",
        controller: "deviceCtrl"
    }).when("/chain", {
        templateUrl: "app/chains.html",
        controller: "chainCtrl"
    });
});