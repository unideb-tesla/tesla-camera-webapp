var competitor;
var app = angular.module(
    "ProgContR",
    ["ngResource",
        "ngSanitize", "ngRoute", "ngAnimate", "ui.bootstrap", "ng-showdown"]).filter('to_trusted',
    ['$sce', function ($sce, $rootScope) {
        return function (text) {
            return $sce.trustAsHtml(text);
        };

    }]);

app.config(function ($routeProvider) {
    $routeProvider.otherwise({
        templateUrl: "webapp/devices.html",
        controller: "devicesCtrl"
    }).when("/devices/:id", {
        templateUrl: "webapp/device.html",
        controller: "deviceCtrl"
    }).when("/chain", {
        templateUrl: "webapp/chains.html",
        controller: "chainCtrl"
    });
});