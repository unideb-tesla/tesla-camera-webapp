app.service("deviceAPI", ['$http', function ($http) {
    this.prefix = "api/device/";

    this.getAll = function () {
        return $http.get(this.prefix);
    }

    this.getOne = function (id) {
        return $http.get(this.prefix + id);
    }

    this.updateOne = function(device){
        return $http.post(this.prefix, device);
    }
}]);

app.service("imageAPI", ['$http', function ($http) {
    this.prefix = "api/image/";

    this.getListByAddress = function (address) {
        return $http.get(this.prefix + address + "/all");
    }
}]);

app.service("chainAPI", ['$http', function ($http) {
    this.prefix = "api/chain/";

    this.getAll = function () {
        return $http.get(this.prefix);
    }

    this.getOne = function (id) {
        return $http.get(this.prefix + id);
    }

    this.createOne = function (chain) {
        return $http.put(this.prefix, chain);
    }

    this.deleteOne = function (id) {
        return $http.delete(this.prefix + id);
    }

    this.setActive = function (id) {
        return $http.post(this.prefix + id + "/active");
    }

    this.removeActive = function () {
        return $http.delete(this.prefix + "active");
    }

    this.getActive = function () {
        return $http.get(this.prefix + "active");
    }
}]);