angular.module('market-front', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8881/app/';

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
            url: contextPath + 'products/delete/' + id,
            method: 'GET'
        }).then(function (response) {
            console.log(response);
            $scope.products = response.data;
        });
    }


    $scope.loadProducts();
});