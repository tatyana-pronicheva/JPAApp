angular.module('market-front').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:5555/backend/api/v1/';

     $scope.loadProducts = function (){
         let userId = $scope.userId;
         $http.get(contextPath + 'cart/' + userId)
             .then(function (response) {
                 console.log(response);
                 $scope.cart = response.data.items;
             });
     }

    $scope.deleteProduct = function (productId) {
        let userId = $scope.userId;
        $http({
            url: contextPath + 'cart/removeProduct/' + userId,
            method: 'POST',
             data: { "id" : productId}
        }).then(function (response) {
            console.log(response);
            $scope.cart = response.data.items;
        });
    }

    $scope.loadProducts();
});