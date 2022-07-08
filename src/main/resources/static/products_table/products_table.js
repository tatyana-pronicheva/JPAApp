angular.module('market-front').controller('productsTableController', function ($scope, $http, $location) {
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

    $scope.navToEditProductPage = function (productId){
        $location.path('/edit_product/'+ productId);
    }

    $scope.addToCart = function (productId){
        $http.post(contextPath + 'cart/' + productId)
            .then(function successCallback(response) {
                    alert("Success");
                }, function failCallback(response) {
                    alert("Error");
                }
            );
    }

    $scope.loadProducts();
});