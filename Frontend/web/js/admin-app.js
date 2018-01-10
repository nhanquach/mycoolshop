/*
Use localhost to development, because 000webhostapp limit the node.
*/
const and = "%2F";
const host = "http://localhost/shop/mycoolshop/Backend/myshop/web/index.php?r=";

//const host = "http://192.168.11.106/shop/mycoolshop/Backend/myshop/web/index.php?r=";

//Uncomment this to use the 000webhostapp as host. 
//const host = "https://mycoolshop.000webhostapp.com/web/index.php?r=";


//URL for getting data
const product_url = host + "allproducts/getproducts";
const extra_url =host + "productextra%2Fgetproductextra";
const category_url = host + "category";
const subcategory_url = host + "subcategory";
const user_orders_url = host + "order/getfrom&id=";
const order_url = host + "order";
const order_products_url = host + "orderproducts";
const order_product_fromid_url = host + "orderproducts/getproductfrom&id=";


//URL for doing something.
const product_post_url = host + "allproducts/create";
const product_update_url = host + "allproducts/update&id=";
const category_post_url = host + "category/create"
const subcategory_post_url = host + "subcategory/create";
const extra_post_url = host + "productextra/create"
const order_update_url = host + "order/update&id=";

//Login URL
const user_login_url = host + "login" + and + "create";

const order_post_url = host + "order/create"
const orderproducts_post_url = host + "orderproducts/create"

var app = angular.module('admin-app', ['ngRoute', 'ngStorage', 'firebase', 'ui.bootstrap', 'ui.bootstrap.datetimepicker']);

//Service
app.service('sharedData', function () {
  var shared = "";
  this.saveData = function (data) {
    shared = data;
  };
  this.retrieveData = function () {
    return shared;
  }
});

app.controller('AdminController', ['$scope', '$http', '$location', '$localStorage', 'sharedData', '$firebaseStorage', function ($scope, $http, $location, $localStorage, sharedData, $firebaseStorage) {

  $scope.alert = {
    'show': false,
    'message': "Email or Password is incorrected."
  }

  // var lookUpDate = ["Sunday", "Monday", "Tuesday", "Webnesday", "Thursday", "Friday", "Saturday"];

  // $scope.getDate = function (d) {
  //   console.log(d);
  //   let from = moment(d.startDate);
  //   let end = moment(d.endDate);
  //   let days = end.diff(from, 'days');
  //   $scope.t = [];
  //   for (var i = 0; i < days; i++){
  //     let today = moment(from).add(i, 'days').day();
  //     let day = lookUpDate[today];
  //     if ($scope.t.indexOf(day) == -1)
  //     {
  //       $scope.t.push(lookUpDate[today]);
  //     }
  //   }
  // }

  $scope.logout = function () {
    $scope.admin = "";
    $localStorage.currentUser = "none";
  }

  var currentUser = "empty";

  $scope.checkCurrentUser = function () {
    currentUser = $localStorage.currentUser;
    //console.log($localStorage.currentUser == undefined);
    if (currentUser == undefined) {
      $location.path('login');
      return false;
    };
    if (currentUser.username == "admin") {
      $location.path('home');
      $scope.admin = currentUser;
      return true;
    } else {
      $location.path('login');
      return false; 
    }
  }

  $scope.checkPermission = function () {
    // if (currentUser.username != "admin") {
    //   $location.path('login');
    // };
  }

  //$scope.checkCurrentUser();
  
  $scope.login = function (user) {
    let email = user.email;
    let password = user.password;

    $http.post(user_login_url+"&email="+email+"&"+"password="+password).then(function (res) {
      $scope.alert.show = false;
      let r = res.data;

      if (r == "None") {
        $scope.alert.show = true;
      } ;

      if (r.userType == "Admin") {
        $localStorage.currentUser = r;
        sharedData.saveData(r);
        $scope.admin = $localStorage.currentUser;
        $location.path('home');
      } else {
        $location.path('/');
      };

    })
    .catch(function (e) {
      console.log(e);
    });
  }

  //I don't know about this.
  $scope.addproductview = function () {
    $location.path('add_product');
  };
  
  $scope.status = "Waiting for input";
  $scope.post_product = function (p) {
    let url = sharedData.retrieveData();
    p.image = url;
    var id = Math.random().toString().slice(2,11);
    let product = {
      "id": id,
      "name": p.name,
      "description": p.description,
      "price": p.price,
      "image": p.image
    }

    new_product(product)

    for (var i = 0; i < p.subcategory.length; i++){
      for (var j = 0; j < p.category.length; j++){
        var extra = {
          'product_id': id,
          'category_id': p.category[j],
          'subcategory_id': p.subcategory[i]
        };
        new_extra(extra);
      }
    }
  }

  var new_extra = function (extra) {
    console.log(extra_post_url);
    //[['product_id', 'category_id', 'subcategory_id'], 'required']
    $http.post(extra_post_url, extra)
      .then(function () {
        $scope.status = "Got it!";
      })
      .catch(function (err) {
        console.log(err);
      })
  };

  var new_product = function (product) {
    $http.post(product_post_url, product)
      .then(function (response) {
        $scope.status = "New product added.";
      })
      .catch(function (e) {
        console.log(e)
        $scope.status = e.statusText;
      });
  }

  /*
    This is add new Subcategory
  */
  /* Post new record to the Subcategory table */
  $scope.post_subcategory = function (subcategory) {
    $scope.message = "Waiting for input...";
    $scope.type = "alert-light";

    $http.post(subcategory_post_url, subcategory)
      .then(function (response) {
        let message = response;
        if (message.statusText == "Created") {
          $scope.message = "new subcategory added successfully.";
          $scope.type = "alert-success";
          $scope.getAvailableSubcategory()
        }
      }).catch(function (e) {
        $scope.message = "Data validation failed."
        $scope.type = "alert-warning";
      }
    );
  }
  /* Get the available Subcategories to display in a table */
  $scope.getAvailableSubcategory = function () {
    $http.get(subcategory_url).then(function (res) {
      $scope.a_subcategory = res.data;
    });
  };

  /* Global variable for display data */
  $scope.a_subcategory = $scope.getAvailableSubcategory();
  
  /*
    This is add new Category & manage Category
  */
  /* Post new record to the Category table */
  $scope.post_category = function (category) {

    $scope.message = "Waiting for input...";
    $scope.type = "alert-light";

    $http.post(category_post_url, category)
      .then(function (response) {
        let message = response;
        if (message.statusText == "Created") {
          $scope.message = "new category added successfully.";
          $scope.type = "alert-success";
          $scope.getAvailableCategory()
        }
      }
      ).catch(function (e) {
        $scope.message = "Data validation failed."
        $scope.type = "alert-warning";
        console.log(e);
      });
  }
  
  /* Get all records in Category table */
  $scope.getAvailableCategory = function () {
    $http.get(category_url).then(function (res) {
      $scope.a_category = res.data;
    });
  };
  
  /* Scope variable for display data to table */
  $scope.a_category = $scope.getAvailableCategory();


  /* Confirm user wants to delete category */
  $scope.confirm_delete_category = function (c) {
    $scope.a = c;
  }

  /* Function to delete a category */
  $scope.delete_category = function (c) {
    let delete_url = category_url + "/delete&id=" + c._id;
    $http.delete(delete_url, c).then((value) => {
      $scope.a_category = $scope.getAvailableCategory();
    })
      .catch((err) => {
        $scope.a_category = $scope.getAvailableCategory();
    });
  };

  /**
   * Order management section
   */

  $scope.getOrders = function () {
    $http.get(order_url)
      .then(function (data) {
        resolveData(data.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  var resolveData = function (data) {
    for (var i = 0; i < data.length; i++) {
      data[i].created_at = moment(data[i].created_at);
      let m = moment(data[i].created_at);
      data[i].eta = m.add(5, "days");


      let order_status1 = "DONE";
      let order_status2 = "CANCELLED";
      let order_status3 = "PROGRESSING";
      if (data[i].status == order_status3) {
        data[i].state = "";
        data[i].option1 = order_status1;
        data[i].option2 = order_status2;
      } else {
        if (data[i].status == order_status2) {
          data[i].state = "danger";
          data[i].option1 = order_status1;
          data[i].option2 = order_status3;
        } else {
          if (data[i].status == order_status1) {
            data[i].state = "success";
            data[i].option1 = order_status2;
            data[i].option2 = order_status3;
          };
        };
      };
    }
    $scope.orders = data;
  };

  $scope.restore = function () {
    $scope.getOrders();
  };

  $scope.changeStatus = function (order) {
    console.log(order);
    $scope.txtColor = 'primary';
    if (order.status == "PROGRESSING") {
      $scope.txtColor = 'primary';
    } else {
      if (order.status == "DONE") {
        $scope.txtColor = 'success';
      } else {
        $scope.txtColor = 'danger';
      }
    }
    $('#confirm_modal').modal('show');
    $scope.confirm_order = order;
  };

  $scope.updateOrder = function (order) {
    let stt = {
      'delivery_note': order.delivery_note,
      'phone': order.phone,
      'email': order.email,
      'status': order.status
    };
    console.log(stt);
    $http.put(order_update_url + order._id, stt)
      .then(function (data) {
        console.log(data.data);
      })
      .then(function () {
        $scope.getOrders();
      })
      .then(function () {
        $('#confirm_modal').modal('hide');
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  $scope.showDetail = function (order) {
    $scope.o = order;
    $http.get(order_product_fromid_url + order.id_order_products)
      .then(function (data) {
        $scope.detail = data.data;
      })
      .catch(function (err) {
        console.log(err);
    })
    $location.path("/order_detail");
  };
  
}]);

app.controller('UploadCtrl', ['$firebaseStorage', '$scope', '$timeout', 'sharedData', function ($firebaseStorage, $scope, $timeout, sharedData) {
  $scope.bar = false;
  $scope.thumbnail = false;
  var ctrl = this;
  ctrl.fileToUpload = null;
  ctrl.onChange = function onChange(fileList) {
    ctrl.fileToUpload = fileList[0];
    var r = Math.random().toString(36).substr(2, 15);
    var storageRef = firebase.storage().ref("product_images/"+r);
    var storage = $firebaseStorage(storageRef);
    var uploadTask = storage.$put(ctrl.fileToUpload);
    uploadTask.$progress(function (snapshot) {
      $scope.bar = true;
      $scope.percentUploaded = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
      console.log($scope.percentUploaded);
    });
    uploadTask.$complete(function (snapshot) {
      $scope.downloadURL = snapshot.downloadURL;
      sharedData.saveData($scope.downloadURL);
      $timeout(function () {
        $scope.bar = false;  
      }, 1000);
      $scope.thumbnail = true;
    });
    uploadTask.$error(function (error) {
      console.error(error);
      $scope.bar = false;
    });
  };
}]);

app.controller('manage_productController', function ($scope, $http, $location, $routeParams, sharedData) {

  $scope.e_product;
  $scope.products;
  const host = "http://localhost/shop/mycoolshop/Backend/myshop/web/index.php?r=";
  const product_url = host + "allproducts/getproducts";
  const getProductById_url = host + "allproducts/getproductby&id=";

  $http.get(product_url)
    .then(function (response) {
      $scope.products = response.data;
    })
    .catch(function (e) {
      $scope.data = e;
    });   

  var getAllProducts = function () {
    $http.get(product_url)
      .then(function (response) {
        $scope.products = response.data;
      })
      .catch(function (e) {
        $scope.data = e;
      });
  };

  $scope.edit_product = function (product) {
    $location.path('/edit_product/'+product.id);
  };

  $scope.getProductById = function () {
    let ID = $routeParams.ID;
    const host = "http://localhost/shop/mycoolshop/Backend/myshop/web/index.php?r=";
    const getProductById_url = host + "allproducts/getproductby&id=";
    
    $http.get(getProductById_url + ID)
      .then((value) => {
        $scope.product = value.data[0];
        $scope.product.price = parseInt($scope.product.price);
        classifyCategory();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  classifyCategory = function () {
    let c = [];
    let flag = false;
    console.log($scope.product);
    for (let i = 0; i < $scope.a_category.length; i++){
      for (let j = 0; j < $scope.product.category.length; j++){
        c[i] = $scope.a_category[i];
        console.log($scope.a_category[i].id == $scope.product.category[j].id);
        if ($scope.a_category[i].id == $scope.product.category[j].id) {
          let selected = {
            '_id': parseInt($scope.product.category[j]._id),
            'id': $scope.product.category[j].id,
            'name': $scope.product.category[j].name,
            'selected': 'selected'
          };
          c[i] = selected;
          console.log(c);
          break;
        }
      }
    }
    $scope.product.category = c;
    console.log($scope.product.category);
  };

  $scope.updateProduct = function (product) {
    console.log(product);
    let new_product = {
      'id': product.id,
      'name': product.name,
      'price': product.price,
      'description': product.description,
      'image': product.image
    };

    var extra = {};

    for (var i = 0; i < product.subcategory.length; i++) {
      for (var j = 0; j < product.category.length; j++) {
        extra = {
          'product_id': product.id,
          'category_id': product.category[j],
          'subcategory_id': product.subcategory[i]
        };
      }
    }

    let new_image_url = sharedData.retrieveData();
    if (new_image_url != "" && new_image_url != null) {
      new_product.image = new_image_url;
    };
    console.log(product_update_url);
    $http.put(product_update_url + new_product.id, new_product)
      .then((value) => {
        console.log(value.data);
        $('#successModal').modal('show');
      })
      .catch((err) => {
        console.log(err);
      });
    
    let extra_getId_url = host + "productextra/getextraid&pid=" + extra.product_id;
    let extra_id;
    
    console.log(extra_getId_url);

    $http.get(extra_getId_url)
      .then((value) => {
        extra_id = value.data[0]._id;
      })
      .then(function () {
        let extra_update_url = host + "productextra/update&id=" + extra_id;
        console.log(extra_update_url);
        $http.put(extra_update_url, extra)
          .then((value) => {
            console.log(value.data);
          }).catch((err) => {
            console.log(err.data);
          });
      });
    
  };
  
  $scope.goBack = function () {
    $location.path('/manage_product');
  };

  $scope.confirm_delete_product = function (p) {
    $scope.delete_product = p;
    $('#confirmDeleteModal').modal('show');
  };

  $scope.delete_aproduct = function () {
    $('#confirmDeleteModal').modal('hide');
    
    let delete_url = host + "allproducts/delete&id=" + $scope.delete_product.id;
    
    $http.delete(delete_url).then((value) => {
      console.log(value.data);
      getAllProducts();
    }).catch((err) => console.log(err));
  };

});


app.config(function ($routeProvider) {
  $routeProvider
    .when('/', {
      templateUrl: 'login.html'
    })
    .when('/home', {
      templateUrl: 'home.html',
    })
    .when('/add_product', {
      templateUrl: 'action/add_product.html'
    })
    .when('/manage_product', {
      templateUrl: 'action/manage_product.html'
    })  
    .when('/edit_product/:ID', {
      templateUrl: 'action/edit_product.html',
      controller: 'manage_productController'
    })
  .when('/add_category', {
    templateUrl: 'action/add_category.html'
  })
  .when('/add_subcategory', {
    templateUrl: 'action/add_subcategory.html'
  })
  .when('/manage_order', {
  templateUrl: 'action/manage_order.html'
  })
  .when('/order_detail', {
    templateUrl: 'action/order_detail.html'
    })
    .when('/manage_category', {
      templateUrl: 'action/manage_category.html'
    })  
  .otherwise({
    templateUrl: 'login.html'
  });
});

app.directive("fileUpload", function () {
  return {
    restrict: "E",
    transclude: true,
    scope: {
      onChange: "="
    },
    template: '<input class="btn" type="file" name="file" accept="image/*" /><label><ng-transclude></ng-transclude></label>',
    link: function (scope, element, attrs) {
      element.bind("change", function () {
        scope.onChange(element.children()[0].files);
      });
    }
  }
});
