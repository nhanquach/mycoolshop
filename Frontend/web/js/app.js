/*
Use localhost to development, because 000webhostapp limit the node.
*/
const host = "http://localhost/shop/mycoolshop/Backend/myshop/web/index.php?r=";

//const host = "http://192.168.11.106/shop/mycoolshop/Backend/myshop/web/index.php?r=";


// //Uncomment this to use the 000webhostapp as host.
// const host = "https://mycoolshop.000webhostapp.com/web/index.php?r=";
 

//const product_url =host + "allproducts";
const product_url =host + "allproducts/getproducts";
const product_post_url = host + "allproducts/create";
const extra_url = host + "productextra%2Fgetproductextra";
const category_url = host + "category";
const subcategory_url = host + "subcategory";
const emails_url = host + "signup";
const signup_url = host + "signup/create"
const signin_url = host + "login/create";
const user_update_url = host + "signup/update";
const user_orders_url = host + "order/getfrom&id=";
const order_post_url = host + "order/create"
const orderproducts_post_url = host + "orderproducts/create"
const order_product_url = host + "orderproducts/getproductfrom&id=";

var app = angular.module('app', ['ngRoute', 'ngStorage']);

app.service('user_service', function () {
  var user;
  this.saveUser = function (data) {
    user = data;
  };
  this.getUser = function () {
    return user;
  }
});

app.controller('SignupController', ['$scope', '$http', '$localStorage', '$timeout', 'user_service', function ($scope, $http, $localStorage, $timeout, user_service) {

  /*
  Notify system
  */
  $scope.showAlert = function (type, message, duration) {
    if (isNaN(duration)) {
      this.duration = 3000;
    } else {
      this.duration = duration;
    }

    $scope.alert = {
      'type': "alert-" + type,
      'message': message,
      'show': true
    };

    $timeout(
      function () {
        $scope.alert = {
          'type': "alert-" + type,
          'message': message,
          'show': false
        };
      }, this.duration);
  };

  /**
   * Get a list of user's email
   */
  $scope.availableE = [];
  $scope.invalid = "";

  function getEmails() {
    $http.get(emails_url)
      .then(function (response) {
        for (var i = 0; i < response.data.length; i++){
          $scope.availableE.push(response.data[i].email);
        }
      })
      .catch(function (e) {
        console.log(e)
      });
  };

  getEmails();

  function isAvailableEmail(email) {
    var flag = false;
    for (var i = 0; i < $scope.availableE.length; i++){
      if (email == $scope.availableE[i]) {
        return flag = true;
      }
    }
    return flag;
  };

  $scope.checkEmail = function (email) {
    var flag = isAvailableEmail(email);
    if (flag) {
      $scope.invalid = "is-invalid";
    } else {
      $scope.invalid = "";
    }
  }

  //Signup new user
  $scope.Signup = function (account) {
    if (isMatchPassword(account.pass1, account.pass2) == false) {
      $scope.showAlert("warning", "Password not match", 3000);
    } else {
      this.account = {
        'email': account.email,
        'password': account.pass1,
        'username': account.username,
      };

      $http.post(signup_url, this.account)
        .then(function () {
          $scope.showAlert("success", "Awesome! Welcome to our shop. Sign in now.", 100000);
        })
        .catch(function (err) {
          $scope.showAlert("warning", "Failed to create new User.", 4000);
      })  ;
    }
  }

  function isMatchPassword(pass1, pass2) {
    return (pass1 === pass2);
  };

}]);

app.controller('ApiController', ['$scope', '$http', '$location', '$localStorage', '$timeout', 'user_service', '$routeParams', function ($scope, $http, $location, $localStorage, $timeout, user_service, $routeParams) {

  $scope.user_status;

  /*
  * Login user and save session.
  */
  $scope.login = function (user) {
    $http.post(signin_url + "&email=" + user.email + "&password=" + user.password)
      .then(function (data) {
        user_service.saveUser(data.data);
        $scope.user_status = user_service.getUser().username;
        $localStorage.signinUser = user_service.getUser();
        $('#modal_signin').modal('hide');
      })
      .catch(function (err) {
        console.log(err);
      })
  };

  function checkCurrentUser() {
    var now_user = $localStorage.signinUser;
    if (now_user != undefined) {
      $scope.user_status = now_user.username;
    }
  };

  checkCurrentUser();

  $scope.logout = function () {
    $localStorage.signinUser = "";
    $scope.user_status = "";
    $location.path("/");
  };

  $http.get(product_url)
      .then(function (response) {
          $scope.products = response.data;
      })
      .catch(function (e) {
          $scope.data = e;
      });

  /*
  * Notify system
  */
  $scope.showAlert = function (type, message, duration) {
    if (isNaN(duration)) {
      this.duration = 3000;
    } else {
      this.duration = duration;
    }

    $scope.alert = {
      'type': "alert-" + type,
      'message': message,
      'show': true
    };

    $timeout(
      function () {
        $scope.alert = {
          'type': "alert-" + type,
          'message': message,
          'show': false
        };
      }, this.duration);
  };

  /**
   * Click a product to go to MoreInfo page.
   * @param {product} p
   */
  $scope.moreInfo = function (p) {
    $location.path('product/' + p.id);
  };

  $scope.getProductById = function () {
    let id = $routeParams.ID;
    let url = host + "allproducts/getproductby&id=";
    console.log(url + id);
    $http.get(url + id)
      .then((value) => {
        $scope.productInfo = value.data[0];
        $scope.getRecommendedItems(5);
      })
      .catch((err) => {
        console.log(err)
      });
  }

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

  //Get Recommended Items
  $scope.getRecommendedItems = function (limit) {
    let url = host + "recommended/getproductbylimit&id=" + $scope.productInfo.id + "&limit=" + limit;
    console.log(url);
    
    $http.get(url)
      .then((value) => {
        $scope.rec_items = value.data;
      })
      .catch((err) => console.log(err));
  };

  /**
   * Cart section
   */
  $scope.addToCart = function (product, quantity) {

    if (isNaN(parseFloat(quantity)) || product == undefined || quantity == undefined) {
      $scope.showAlert("warning", "Oops, some thing went wrong.", 3000);
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
    $scope.showAlert("success", "Product added to Cart", 2000);
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
  * Checkout section
  */
  $scope.checkout = function () {
    $location.path("/checkout");
  }

  $scope.status = "Have an account? Sign in to auto-fill.";

  if ($scope.user_status != undefined) {
    $scope.status = "Auto fill";
  }

  $scope.order = {};

  $scope.auto_fill = function () {
    if ($scope.user_status == undefined) {
      $('#modal_signin').modal('show');
    } else {
      let user = $localStorage.signinUser;
      $scope.order = user;
      $scope.order.user_name = user.username;
      $scope.order.email = user.email;
      $scope.order.id_user = user._id;
    }
  }

  $scope.confirmOrder = function (order, items) {
    var order_id = Math.random().toString().slice(2, 11);
    if ($scope.order.id_user == "" || $scope.order.id_user == undefined){
      var id_user = Math.random().toString().slice(2, 11);
      $scope.order.id_user = id_user;
    }

    if (order.delivery_note == undefined) {
      order.delivery_note = "none";
    };

    $scope.cart_items = items;

    $scope.order.id_order_products = order_id;

    $location.path("/confirm_order");
  }

  $scope.placeOrder = function (order, items) {
    order.price = $scope.cart_money;
    $scope.order._id = null;
    
    $http.post(order_post_url, $scope.order)
      .then(function (data) {
        console.log(data.data);
        for (var i = 0; i < items.length; i++) {
          let order_products = {
            'order_id': order.id_order_products,
            'product_id': items[i].id,
            'product_name': items[i].name,
            'product_price': items[i].price,
            'product_quantity': items[i].quantity
          };
          $http.post(orderproducts_post_url, order_products)
            .then(function (data) {
              console.log(data.data);
              $('#modal_thankyou').modal('show');
            })
            .catch(function (err) {
              console.log(err);
            });
        };
      })
      .catch(function (err) {
        console.log("ERR----")
        console.log(err);
      });
  };

  $scope.backtoShop = function () {
    $('#modal_thankyou').modal('hide');
    $localStorage.cart = [];
    $location.path("/");
    $scope.cart_items = "";
    $scope.cart_money = 0;
    $scope.cart_number = 0;
  };

  /**
   * User management Section
   */

  $scope.gotoUserHome = function () {
    $location.path("/user_home");
  };

  $scope.getUserInfo = function () {
    $localStorage.signinUser.user_name = $localStorage.signinUser.username;
    $scope.order = $localStorage.signinUser;
  };

  $scope.updateInfo = function () {
    let user = $scope.order;

    if (user.phone[0] != 0) {
      user.phone = "0" + user.phone.toString();
    } else {
      user.phone = user.phone.toString();
    }
    user.username = $scope.order.user_name;

    $http.put(user_update_url + "&id=" + user._id + "/update", user)
      .then(function (data) {
        $scope.showAlert("success", "Profile update successfully.", 2000);
        $localStorage.signinUser = data.data;
      })
      .catch(function (err) {
        $scope.showAlert("success", err, 2000);
      });
  };

  /**
   * Get user's Orders
   */
  $scope.getUserOrders = function () {
    let user = $localStorage.signinUser;

    if (user._id != null) {
      user.id_user = user._id;
    };
    console.log(user_orders_url + user.id_user)
    $http.post(user_orders_url + user.id_user)
      .then(function (data) {
        console.log(data.data);
        classifyOrders(data.data);
      })
      .catch(function (err) {
        console.log(err);
    });
  };

  function classifyOrders(orders) {
    $scope.upcomming_orders = [];
    $scope.done_orders = [];
    $scope.cancelled_orders = [];

    for (var i = 0; i < orders.length; i++) {
      if (orders[i].status == "PROGRESSING") {
        orders[i].created_at = moment(orders[i].created_at);
        let temp_eta = moment(orders[i].created_at).add(5, "days");
        orders[i].ETA = temp_eta;
        $scope.upcomming_orders.push(orders[i]);
        console.log($scope.upcomming_orders);
      } else
      if (orders[i].status == "DONE") {
        $scope.done_orders.push(orders[i]);
      } else
      if (orders[i].status == "CANCELLED") {
        $scope.cancelled_orders.push(orders[i]);
      };
    };
  }

  $scope.getOrderProducts = function (order) {
    $http.get(order_product_url + order.id_order_products)
      .then(function (data) {
        $location.path("/order_detail");
        $scope.detail = data.data;
        $scope.o = order;
      })
      .catch(function (err) {
        console.log(err);
      });

  }


}]);

app.config(function ($routeProvider) {
  $routeProvider
    .when('/product/:ID',{
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
  .when('/user_home', {
    templateUrl: 'userpage/home.html'
  })
  .when('/order_detail', {
    templateUrl: 'userpage/order_detail.html'
  })
  .when('/user_order', {
    templateUrl: 'confirm_order.html'
  })
  .when('/user_address', {
    templateUrl: 'confirm_order.html'
    })
  .when('/edit_account', {
    templateUrl: 'userpage/edit_account.html'
  })
  .otherwise({
    templateUrl: 'home.html'
  });
});
