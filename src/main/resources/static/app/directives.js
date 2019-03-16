app.directive("navHash", function () {
    return {
        restrict: "E",
        scope: {
            active: "=",
            device: "="
        },
        replace: true,
        templateUrl: "webapp/nav.html"
    };
});

app.directive("imageModal", [
    "$rootScope", function ($rootScope) {
        return {
            scope: true,
            link: function ($scope, element, attrs) {
                $rootScope.$on("openImageModal", function (event, args) {
                    $scope.image = args[0];
                    $(element).modal("show");
                });
            },
            replace: true,
            templateUrl: "webapp/image-modal.html"
        };
    }]);
