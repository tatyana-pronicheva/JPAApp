angular.module('market-front').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8881/app/api/v1/';

     $scope.loadProducts = function (){
         $http.get(contextPath + 'cart')
             .then(function (response) {
                 console.log(response);
                 $scope.cart = response.data;
             });
     }

    $scope.deleteProduct = function (id) {
        $http({
            url: contextPath + 'cart/' + id,
            method: 'DELETE'
        }).then(function (response) {
            console.log(response);
            $scope.cart = response.data;
        });
    }

    $scope.loadProducts();
});