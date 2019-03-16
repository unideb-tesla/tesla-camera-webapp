app.controller("devicesCtrl", function ($scope, $rootScope, $route, $q,
                                        $location, $routeParams, deviceAPI) {
    $scope.init = function () {
        $scope.loading = true;
        $q.all({
            devices: deviceAPI.getAll()
        }).then(
            function (res) {
                $scope.devices = res.devices.data;
            }
        )
        $scope.loading = false;
    }

    $scope.update = function () {
        $scope.init();
    };
})

app.controller("deviceCtrl", function ($scope, $rootScope, $route, $q,
                                       $location, $routeParams, deviceAPI, imageAPI) {
    $scope.init = function () {
        $scope.loading = true;

        $q.all({
            device: deviceAPI.getOne($routeParams.id),
            images: imageAPI.getListByAddress($routeParams.id)
        }).then(
            function (res) {
                $scope.device = res.device.data;
                $scope.images = res.images.data;
            }
        )
        $scope.loading = false;
    }

    $scope.show = function (image) {
        $rootScope.$emit("openImageModal", [image]);
    }

    $scope.update = function(){
        $scope.loading = true;
        deviceAPI.updateOne($scope.device).then(
            function (value) {
                $scope.loading = false;
            },
            function (reason) {
                $scope.init();
            }
        )
    }
});

app.controller("chainCtrl", function ($scope, $rootScope, $route, $q,
                                      $location, $routeParams, chainAPI) {
    $scope.init = function () {
        $scope.loading = true;
        $q.all({
            chains: chainAPI.getAll(),
            active: chainAPI.getActive()
        }).then(
            function (res) {
                $scope.chains = res.chains.data;
                $scope.activeChain = res.active.data;
            }
        )
        $scope.loading = false;
    }

    $scope.create = function () {
        chainAPI.createOne($scope.newChain).then(
            function (value) {
                $scope.init();
            }
        )
    }

    $scope.delete = function (chain) {
        chainAPI.deleteOne(chain.id).then(
            function (value) {
                $scope.init();
            }
        )
    }

    $scope.select = function (chain) {
        chainAPI.setActive(chain.id).then(
            function (value) {
                $scope.init();
            }
        )
    }

    $scope.deselect = function () {
        chainAPI.removeActive().then(
            function (value) {
                $scope.init();
            }
        )
    }
});