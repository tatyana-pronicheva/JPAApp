angular.module('market-front').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8881/app/api/v1/';

     $scope.loadProducts = function (){
         $http.get(contextPath + 'cart/12')
             .then(function (response) {
                 console.log(response);
                 $scope.cart = response.data.items;
             });
     }

    $scope.deleteProduct = function (productId) {
        $http({
            url: contextPath + 'cart/removeProduct/12',
            method: 'POST',
             data: { "id" : productId}
        }).then(function (response) {
            console.log(response);
            $scope.cart = response.data.items;
        });
    }

    $scope.loadProducts();
});