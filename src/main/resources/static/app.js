angular.module('market-front', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8881/app/api/v1/';

     $scope.loadProducts = function (){
         $http.get(contextPath + 'products')
             .then(function (response) {
                 console.log(response);
                 $scope.products = response.data;
             });
     }

    $scope.showInfo = function (product) {
        alert(product.title);
    }

    $scope.deleteProduct = function (id) {
        $http({
            url: contextPath + 'products/' + id,
            method: 'DELETE'
        }).then(function (response) {
            console.log(response);
            $scope.products = response.data;
        });
    }

    $scope.changeProduct = function () {
        $http.put(contextPath + 'products/' + $scope.new_product.id, $scope.new_product)
            .then(function successCallback(response) {
                    $scope.products = response.data;
                    $scope.new_product = null;
                    alert("Success");
                }, function failCallback(response) {
                    alert("Error");
                }
            );
    }

    $scope.loadProducts();
});