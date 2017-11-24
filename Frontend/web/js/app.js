// const product_url = "http://localhost/shop/myshop/web/index.php?r=allproducts";
// const product_post_url = "http://localhost/shop/myshop/web/index.php?r=allproducts/create";
// const extra_url = "http://localhost/shop/myshop/web/index.php?r=productextra%2Fgetproductextra";
// const category_url = "http://localhost/shop/myshop/web/index.php?r=category";
// const subcategory_url = "http://localhost/shop/myshop/web/index.php?r=subcategory";

const product_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=allproducts";
const product_post_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=allproducts/create";
const extra_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=productextra";
const category_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=category";
const subcategory_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=subcategory";

var app = angular.module('app', []);

app.controller('ApiController', ['$scope', '$http',  function ($scope, $http) {
  // $http.get(p)
  //     .then(function (response) {
  //         console.log(response.data);
  //     })

    //Get all products
    //GET POST DELETE PUT UPDATE
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
  $scope.moreInfo = function (product) {
    $('#moreInfo_modal').modal('show');
    $scope.modalTitle = product.name;
    $scope.modalBody = product.description;
    $scope.modalPrice = product.price;
  }
}]);

app.controller('PController', ['$scope', '$http', function ($scope, $http) {
  $scope.postProduct = function (product) {
    $http.post(product_post_url, product).then(function (res) {
      $('#preview').modal('show');
      $scope.result = "";
    }).catch(function (e) {
      console.log(e);
      $('#preview').modal('show');
      $scope.result = e.statusText;
    });
  };

  $scope.al = function () {
    alert();
  };
}]);
