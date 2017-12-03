/*
Use localhost to development, because 000webhostapp limit the node.
*/
const localhost = "http://localhost/shop/mycoolshop/Backend/myshop/web/index.php?r=";
const product_url =localhost + "allproducts";
const product_post_url = localhost + "allproducts/create";
const extra_url =localhost + "productextra%2Fgetproductextra";
const category_url = localhost + "category";
const subcategory_url = localhost + "subcategory";

//const product_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=allproducts";
//const product_post_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=allproducts/create";
//const extra_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=productextra";
//const category_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=category";
//const subcategory_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=subcategory";


var app = angular.module('app', ['ngRoute', 'ngStorage']);

app.controller('ApiController', ['$scope', '$http', '$location', '$localStorage', function ($scope, $http, $location, $localStorage) {
    $http.get(product_url)
      .then(function (response) {
          $scope.products = response.data;
      })
      .then (function (res) {
        for (var i = 0; i < $scope.products.length; i++) {
          $scope.products[i].category = [];
          $scope.products[i].subcategory = [];
        }
        getExtraField();
      })
      .catch(function (e) {
          $scope.data = e;
      });

    function getExtraField() {
      $http.get(extra_url)
      .then(function (res) {
        $scope.extra = res.data;
        getCategory();
      })
      .catch(function (e) {
        console.log(e);
      })
    };

    function getCategory() {
      //Get product's category name.
      $http.get(category_url)
        .then(function (res) {
          $scope.categories = res.data;
        })
        .then(function () {
          getSubcategory();
        })

    }

    function getSubcategory() {
      //Get product's category name.
      $http.get(subcategory_url)
        .then(function (res) {
          $scope.subcategories = res.data;
        })
        .then(function () {
          addExtraField();
        })

    }

    function isCategoryAvailable(category, c) {
      for (var i = 0; i < category.length; i++) {
        if (category[i].id == c) {
          return true;
        } else {
          return false;
        }
      }
    }

    function isSubcategoryAvailable(subcategory, c) {
      for (var i = 0; i < subcategory.length; i++) {
        if (subcategory[i].id == c) {
          return true;
        } else {
          return false;
        }
      }
    }

    function addExtraField() {
      for (var i = 0; i < $scope.products.length; i++) {
        for (var j = 0; j < $scope.extra.length; j++) {

          let category_id = $scope.extra[j].category_id;
          let subcategory_id = $scope.extra[j].subcategory_id;
          let c;
          let s;

          if ($scope.products[i].id == $scope.extra[j].product_id) {

            for (var y = 0; y < $scope.categories.length; y++) {
              if (category_id == $scope.categories[y].id) {
                c = {
                  "id": category_id,
                  "name": $scope.categories[y].name
                };
                if (!isCategoryAvailable($scope.products[i].category, category_id)) {
                  $scope.products[i].category.push(c);
                }
              } else {
                c = "Not available";
              }
            };

            for (var y = 0; y < $scope.subcategories.length; y++) {
              if (subcategory_id == $scope.subcategories[y].id) {
                s = {
                  "id": subcategory_id,
                  "name": $scope.subcategories[y].name
                };
                if (!isSubcategoryAvailable($scope.products[i].subcategory, subcategory_id)) {
                  $scope.products[i].subcategory.push(s);
                }
              } else {
                s = "Not available";
              }
            };
          }
        }
      }
    }

  $scope.moreInfo = function (p) {
    $scope.productInfo = p;
    $location.path('product')
  };
  
  function createRandomQuantity() {
    var n = Math.ceil(Math.random() * 30);
    $scope.rq = [];
    for (var i = 1; i < n; i++) {
      var o = {
        'name': i,
        'value': i
      };
      $scope.rq.push(o);
    };
  };
  
  createRandomQuantity();

  $scope.addToCart = function (product, quantity) {
    console.log(quantity);
    console.log(product);

    if (isNaN(parseFloat(quantity)) || product == undefined) {
      return false;
    };

    var cart_item = {
      "id": product.id,
      "name": product.name,
      "price": parseFloat(product.price),
      "quantity": parseFloat(quantity)
    };
    
    var ids = [];
    
    for (var i = 0; i < $localStorage.cart.length; i++) {
      ids.push($localStorage.cart[i].id);
    }
    
    var flag = getItemIndex(cart_item.id, ids);

    if ( flag == -1) {
      $localStorage.cart.push(cart_item);
    } else {
      $localStorage.cart[flag].quantity = $localStorage.cart[flag].quantity + parseFloat(quantity);
    }

    updateCart();

  };

  function getItemIndex(id, ids) {
    this.id = id;
    this.ids = ids;
    return ids.indexOf(id);  
  };
  
  function updateCart() {
    $scope.cart_items = $localStorage.cart;
    $scope.cart_number = 0;
    $scope.cart_money = 0;
    try{
      for (var i = 0; i < $scope.cart_items.length; i++) {
        $scope.cart_money += $scope.cart_items[i].price * $scope.cart_items[i].quantity;
        $scope.cart_number = $scope.cart_number + $scope.cart_items[i].quantity;
        console.log($scope.cart_money);
      };
    }
    catch (err) {
      console.log(err);
      $localStorage.cart = [];
     }
    
  };

  $scope.removeCartItem = function (item) {
    var ids = [];
    
    for (var i = 0; i < $localStorage.cart.length; i++) {
      ids.push($localStorage.cart[i].id);
    };

    var index = getItemIndex(item.id, ids)
    $localStorage.cart.splice(index, 1);
    
    updateCart();
  };

  updateCart();

  /*
  Checkout section
  */
  $scope.checkout = function () {
    $location.path("/checkout");
  }

}]);

app.config(function ($routeProvider) {
  $routeProvider
    .when('/product',{
    templateUrl: 'product_view.html'
  })
  .when('/login',{
    templateUrl: 'login.html'
  })
  .when('/checkout',{
    templateUrl: 'checkout.html'
    })
  .when('/getInfo', {
    templateUrl: 'getUserInfo.html'
  })
  .when('/confirm_order', {
    templateUrl: 'confirm_order.html'
  })
  .otherwise({
    templateUrl: 'home.html'
  });
});
