/*
Use localhost to development, because 000webhostapp limit the node.
*/
const and = "%2F";
const localhost = "http://localhost/shop/mycoolshop/Backend/myshop/web/index.php?r=";

//URL for getting data
const product_url = localhost + "allproducts";
const extra_url =localhost + "productextra%2Fgetproductextra";
const category_url = localhost + "category";
const subcategory_url = localhost + "subcategory";

//URL for doing something.
const product_post_url = localhost + "allproducts/create";
const category_post_url = localhost + "category/create"
const subcategory_post_url = localhost + "subcategory/create";
const extra_post_url = localhost + "productextra/create"

//Login URL
const user_login_url = localhost + "login" + and + "create";

//const product_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=allproducts";
//const product_post_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=allproducts/create";
//const extra_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=productextra";
//const category_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=category";
//const subcategory_url = "https://mycoolshop.000webhostapp.com/web/index.php?r=subcategory";


var app = angular.module('admin-app', ['ngRoute', 'ngStorage', 'firebase', 'ui.bootstrap', 'ui.bootstrap.datetimepicker']);

//Service
app.service('sharedData', function () {
  var shared = 'none';
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
    let id = Math.random().toString().slice(2,11);
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
    This is add new Category
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

app.config(function ($routeProvider) {
  $routeProvider
    .when('/',{
    templateUrl: 'login.html'
  })
  .when('/home', {
    templateUrl: 'home.html',
  })
  .when('/add_product', {
    templateUrl: 'action/add_product.html'
  })
  .when('/add_category', {
    templateUrl: 'action/add_category.html'
  })
  .when('/add_subcategory', {
    templateUrl: 'action/add_subcategory.html'
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
