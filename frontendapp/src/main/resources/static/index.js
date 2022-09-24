(function () {
    angular
        .module('market-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                redirectTo: '/store'
            })
            .when('/store', {
                templateUrl: 'products_table/products_table.html',
                controller: 'productsTableController'
            })
            .when('/edit_product/:productId', {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .otherwise({
                redirectTo: '/store'
            });
    }

    function run($rootScope, $http) {}
    })();


angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/backend/api/v1/';

    $scope.isUserLoggedIn = function () {
        if ($localStorage.token) {
            return true;
        } else {
            return false;
        }
    };
 $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/authenticate', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.token = {username: $scope.user.username, token: response.data.token};
                    let payload = JSON.parse(atob(response.data.token.split('.')[1]));
                    console.log(payload);
                    $scope.userId = payload.id;
                    console.log($scope.userId);
                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {});
    };

        if ($localStorage.token) {
            try {
                let jwt = $localStorage.token.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                 $scope.userId = payload.id;
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.token;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }
     }
    if ($localStorage.token) {
             $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token.token;
    }
});