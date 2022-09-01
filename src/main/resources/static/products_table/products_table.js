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

    $scope.addToCart = function (productId, productTitle, productCount, productPrice){
            $http({
                url: contextPath + 'cart/addProduct/12',
                method: "POST",
                data: { "id" : productId,
                        "title": productTitle,
                        "count": productCount,
                        "price": productPrice}
            }).then(function successCallback(response) {
                    alert("Success");
                }, function failCallback(response) {
                    alert("Error");
                }
            );
    }

    renderItem = function (url) {
     $("#buttonsList").append("<a class='nav-link' href='"+url+"'>Скачать сформированный xls</a>");
     }

    $scope.connect = function() {
                     var socket = new SockJS('/app/socket');
                     stomp = Stomp.over(socket);
                     stomp.connect({}, function (frame) {
                     console.log('Connected: ' + frame);
                     stomp.subscribe('/topic/items', function (item) {
                     renderItem(item.body);
                     });
                     });
                     }

    $scope.requestCsvProducts = function() {
                      stomp.send("/app/createCsvProducts", {}, {});
                     }



    $scope.loadProducts();
    $scope.connect();

});