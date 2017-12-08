# Đây là list những API(s) hiện có trên hệ thống Backend <br/>(sẽ được cập nhật).

**url là đường dẫn đi đến Backend.**
Localhost:
```const url = "http://localhost/shop/mycoolshop/Backend/myshop/web/index.php?r=";```
000webhost:
```const url = "https://mycoolshop.000webhostapp.com/web/index.php?r="```

### Tất cả các APIs đều có thể được truy cập thẳng bằng trình duyệt, tôi khuyến khích các bạn hãy thử qua gọi trên trình duyệt để biết APIs trả về những thông tin nào.

##1. <u>Các APIs bình thường:</u>

####Products APIs

- Đường dẫn để lấy các sản phẩm đã được lưu, đường dẫn này nhận phương thức GET:  ```url + "allproducts"```

- Đường dẫn để đăng sản phẩm, đường dẫn này nhận phương thức POST:``url + "allproducts/create"``

  - Yêu cầu phải có các tham số: **'id', 'name', 'description', 'price', 'image'**.
  - Xem thêm file ``Product.php`` trong ``Backend/myshop/models``

  ####Category APIs

- Đường dẫn để lấy các Category đã được lưu, đường dẫn này nhận phương thức GET:  ``url + "category"``

- Đường dẫn để đăng Category, đường dẫn này nhận phương thức POST:  ```url + "category/create"```

  -  Yêu cầu phải có các tham số: **'id', 'name'**
  - Xem thêm file ``Category.php`` trong ``Backend/myshop/models``

  ####Subcategory APIs

- Đường dẫn để lấy các Subcategory đã được lưu, đường dẫn này nhận phương thức GET:  ``url + "subcategory"``

- Đường dẫn để đăng Subcategory, đường dẫn này nhận phương thức POST:  ``url + "subcategory/create"``

  -  Yêu cầu phải có các tham số: **'id', 'name'**
  - Xem thêm file ``Subcategory.php`` trong ``Backend/myshop/models``

  ####Product Extra APIs

- Đường dẫn để lấy các ProductExtra đã được lưu, đường dẫn này nhận phương thức GET:  ``url + "productextra"``

- Đường dẫn để đăng ProductExtra, đường dẫn này nhận phương thức POST: ``url + "productextra/create"``

  - Yêu cầu các tham số: **'product_id', 'category_id', 'subcategory_id'**
  - Xem thêm file ``ProductExtra.php`` trong ``Backend/myshop/models``

  ####User APIs

- Đường dẫn để lấy các User đã được lưu, đường dẫn này nhận phương thức GET:  ``url + "signup"`` ==> trả về JSON các user trong database.

- Đường dẫn để đăng kí User mới, đường dẫn này nhận phương thức POST:  ``url + "signup/create"`` 

  - Yêu cầu các tham số: **'password'**, **'email'**
  - Loại user mặc định sẽ là 'User', không phải 'Admin'
  - Xem thêm file ``Loginuser.php`` trong ``Backend/myshop/models``

##2. <u>Các APIs bất thường</u>

- Để đăng nhập một user, sử dụng đường dẫn ``url + "login/create" + "&email=" + user.email + "&password=" + user.password`` ==> sẽ trả về 1 Object User nếu xác thực thành công.
  *Trong đó user.email và user.password là email và password của người đăng nhập.*

#####Xem qua 2 file ``app.js`` và ``app-admin.js`` để thấy ví dụ cụ thể hơn.**

**Sẽ còn cập nhật thêm**