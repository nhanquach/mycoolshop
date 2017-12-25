# Đây là list những API(s) hiện có trên hệ thống Backend <br/>(sẽ được cập nhật).

**url là đường dẫn đi đến Backend.**
Localhost:
```const url = "http://localhost/shop/mycoolshop/Backend/myshop/web/index.php?r=";```
000webhost:
```const url = "https://mycoolshop.000webhostapp.com/web/index.php?r="```

### Tất cả các APIs đều có thể được truy cập thẳng bằng trình duyệt, tôi khuyến khích các bạn hãy thử qua gọi trên trình duyệt để biết APIs trả về những thông tin nào.

## 1. <u>Các APIs bình thường:</u>

#### Products APIs

- Đường dẫn để lấy các sản phẩm đã được lưu, đường dẫn này nhận phương thức GET:  ```url + "allproducts"```

- Đường dẫn để đăng sản phẩm, đường dẫn này nhận phương thức POST:``url + "allproducts/create"``

  - Yêu cầu phải có các tham số: **'id', 'name', 'description', 'price', 'image'**.
  - Xem thêm file ``Product.php`` trong ``Backend/myshop/models``

  #### Category APIs

- Đường dẫn để lấy các Category đã được lưu, đường dẫn này nhận phương thức GET:  ``url + "category"``

- Đường dẫn để đăng Category, đường dẫn này nhận phương thức POST:  ```url + "category/create"```

  -  Yêu cầu phải có các tham số: **'id', 'name'**
  -  Xem thêm file ``Category.php`` trong ``Backend/myshop/models``

  #### Subcategory APIs

- Đường dẫn để lấy các Subcategory đã được lưu, đường dẫn này nhận phương thức GET:  ``url + "subcategory"``

- Đường dẫn để đăng Subcategory, đường dẫn này nhận phương thức POST:  ``url + "subcategory/create"``

  -  Yêu cầu phải có các tham số: **'id', 'name'**
  -  Xem thêm file ``Subcategory.php`` trong ``Backend/myshop/models``

  #### Product Extra APIs

- Đường dẫn để lấy các ProductExtra đã được lưu, đường dẫn này nhận phương thức GET:  ``url + "productextra"``

- Đường dẫn để đăng ProductExtra, đường dẫn này nhận phương thức POST: ``url + "productextra/create"``

  - Yêu cầu các tham số: **'product_id', 'category_id', 'subcategory_id'**
  - Xem thêm file ``ProductExtra.php`` trong ``Backend/myshop/models``

  #### User APIs

- Đường dẫn để lấy các User đã được lưu, đường dẫn này nhận phương thức GET:  ``url + "signup"`` ==> trả về JSON các user trong database.

- Đường dẫn để đăng kí User mới, đường dẫn này nhận phương thức POST:  ``url + "signup/create"`` 

  - Yêu cầu các tham số: **'password'**, **'email'**
  - Loại user mặc định sẽ là 'User', không phải 'Admin'
  - Xem thêm file ``Loginuser.php`` trong ``Backend/myshop/models``

***Cập nhật cho User APIs**
- Thêm các trường dữ liệu 'username', 'address', 'phone' để tiện cho việc auto-fill khi người dùng đã đăng nhập. Các trường dữ liệu này không bắt buộc khi tạo user account mới mà có thể bổ sung sau.

- 'username' là tên của người dùng được sử dụng để đặt hàng.

- API cập nhật thông tin user:
  - Đường dẫn nhận giao thức PUT: ``user_update_url = url + "signup/update" + "&id=" + user_id + "/update"`` và truyền dữ liệu sẽ được ghi đè.
  - Ví dụ: ``$http.put(user_update_url + "&id=" + user._id + "/update", user)`` với ``user`` là biến tương ứng với thông tin user đã được thêm (address, phone,...).

  #### Order APIs

- Đường dẫn để lấy toàn bộ các Order, giao thức GET: ``url + "order"``

- Đường dẫn để lấy các Order của **user_id**, giao thức GET: ``url + "order/getfrom&id=" + user_id``

- Đường dẫn để thêm Order, giao thức POST: ``url + "order/create"``
  - Tham số yêu cầu: **'id_order_products'**, **'address'**, **'id_user'**, **'delivery_note'**, **'phone'**, **'email'**, **'price'**, **'user_name'**
    - Trong đó: **'id_order_products'** là một dãy số ngẫu nhiên, đây sẽ là ``id`` của order đó và được dùng để truy xuất đến các products thuộc ``id`` này trong bảng ``Order_products`` (xem db để hiểu thêm).
    - **'id_user'** là id của user, nếu người dùng không đăng nhập, có thể sử dụng một chuỗi số ngẫu nhiên để thay thế.
    - **'delivery_note'** nếu không có thì có thể để là "none".
    - **'user_name'** tên của người đặt hàng.
  - Sẽ trả về một đối tượng Order nếu thành công, **status** của order mới mặc định là *"PROGRESSING"*; **created_at** mặc định là lúc yêu cầu được gửi tính theo đồng hồ của Backend.

  #### Order Products APIs
  Đây là các APIs sẽ dùng chung với Order APIs nhằm lưu lại các chi tiết của đơn hàng.
  - Đường dẫn GET toàn bộ Order Products: ``url + "orderproducts"``
  - Đường dẫn GET các record của **order_id**: ``url + "orderproducts/getproductfrom&id=" + order_id``
  - Đường dẫn POST các sản phẩm của **order_id** vào **order_products**: ``url + "orderproducts/create"``
    - Yêu cầu bắt buộc phải có: **'order_id'**, **'product_id'**, **'product_name'**, **'product_price'**, **'product_quantity'**
      - Trong đó: **'order_id'** là **id** của **order**, một đơn hàng sẽ có một order_id vì vậy khi lưu order, chúng ta sẽ dùng order_id của order đó để lưu trong bảng Order_Product luôn.

  #### Recommended APIs
  Đây là những APIs sẽ trả về những items được khuyến nghị với 2 APIs chính:
  a. ```url + recommended/getproducts```, API này sẽ trả về toàn bộ products được khuyến nghị.

  b. ```url + recommended/getproductsbutnot + &id=<product_id>``` sẽ trả về những products trong khuyến nghị trừ product có ```id = <product_id>```

## 2. <u>Các APIs bất thường</u>

- Để đăng nhập một user, sử dụng đường dẫn ``url + "login/create" + "&email=" + user.email + "&password=" + user.password`` ==> sẽ trả về 1 Object User nếu xác thực thành công.
  *Trong đó user.email và user.password là email và password của người đăng nhập.*

##### Xem qua 2 file ``app.js`` và ``app-admin.js`` để thấy ví dụ cụ thể hơn.**

##3. <u>Lưu ý nhỏ</u>
Để lấy được *Order* và những thông tin các sản phẩm trong Order của một User thì chúng ta có thể làm như sau:
Lấy **user_id** của user -->dùng **user_id** để lấy được **order_id** --> dùng **order_id** để lấy các **products** trong Order Products APIs





---

**Sẽ còn cập nhật thêm**