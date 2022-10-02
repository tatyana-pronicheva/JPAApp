angular.module('market-front').controller('editProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:5555/backend/api/v1/';

    $scope.prepareProductForUpdate = function () {
        $http.get(contextPath + 'products/' + $routeParams.productId)
            .then(function successCallback(response) {
                    $scope.updated_product = response.data;
                }, function failCallback(response) {
                    alert("Error");
                    $location.path('/store');
                }
            );
    }

    $scope.changeProduct = function () {
        $http.put(contextPath + 'products/' + $routeParams.productId, $scope.updated_product)
            .then(function successCallback(response) {
                    $scope.products = response.data;
                    $scope.updated_product = null;
                    alert("Success");
                    $location.path('/store');
                }, function failCallback(response) {
                    alert("Error");
                }
            );
    }


    $scope.prepareProductForUpdate();


});