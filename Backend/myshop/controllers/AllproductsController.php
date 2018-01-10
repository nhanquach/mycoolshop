<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;
  use yii\data\ActiveDataProvider;
  use yii\db\Query;

  use app\models\Product;
  use app\models\Category;
  use app\models\Subcategory;
  use app\models\ProductExtra;

  /**
   *
   */
  class AllproductsController extends ActiveController
  {
    public $modelClass = 'app\models\Product';

    protected function verbs()
    {
        return [
            'index' => ['GET', 'HEAD'],
            'view' => ['GET', 'HEAD'],
            'create' => ['POST'],
            'update' => ['PUT', 'PATCH'],
            'delete' => ['DELETE'],
        ];
    }

    public function actions()
    {
      $actions = parent::actions();

      $actions['index']['prepareDataProvider'] = [$this, 'prepareDataProvider'];

      return $actions;
    }

    public function prepareDataProvider(){
      $rows = (new \yii\db\Query())
      ->select(['p.id as id', 'p.name as name', 'p.price', 'p.description', 'p.created_at as created_at', 'p.seller as seller', 'p.available_start as start_time', 'p.available_end as end_time', 'p.image as image'])
      ->from(['Product p'])
      ->orderby(['created_at' => SORT_DESC])
      ->all();

      return $rows;
    }

    /**
     * Use this function to request Category of a product using product's id.
     * This function requires $id variable
     * how to use: http://localhost:8080/index.php?r=allproducts/getcategory&id=$id
     * $id is product's id
     * return Category Object(s) of the product id.
     */
    public function actionGetcategory($id)
    {
      //http://localhost:8080/index.php?r=allproducts/getcategory&id=
      $c = new Category();
      $p = new Product();
      $e = new ProductExtra();
      $o = array();

      $product_id = $p::find()->where(['id'=>$id])->one();
      $category_ids = $e::find()->select(['category_id'])->where(['product_id'=>$product_id])->all();
      for($i=0;$i< sizeof($category_ids);$i++){
        $result = $c::find()->where(['id'=>$category_ids[$i]])->distinct()->one();
        array_push($o, $result);
      };
      return $o;
    }

    /**
     * Use this function to get product's subcategory.
     * This function requires $id variable
     * how to use: http://localhost:8080/index.php?r=allproducts/getsubcategory&id=$id
     * $id is product's id
     * return Subcategory Object(s) of the product id.
     */
    public function actionGetsubcategory($id)
    {
      $c = new Subcategory();
      $p = new Product();
      $e = new ProductExtra();
      $o = array();

      $product_id = $p::find()->where(['id'=>$id])->one();
      $subcategory_ids = $e::find()->select(['subcategory_id'])->where(['product_id'=>$product_id])->all();
      for($i=0;$i< sizeof($subcategory_ids);$i++){
        $result = $c::find()->where(['id'=>$subcategory_ids[$i]])->distinct()->one();
        array_push($o, $result);
      };
      return $o;
    }

    public function actionGetproducts(){
      $result = [];

      $query = (new Query())->from('Product')
      ->orderBy(['created_at' => SORT_DESC]);
      $provider = new ActiveDataProvider([
        'query' => $query,
      ]);
      //Products Array
      $products_arr = $provider->getModels();

      //Make $products_arr an Array of Objects
      for ($i=0; $i < count($products_arr); $i++) {
        $products_arr[$i] = (Object) $products_arr[$i];
      }

      $query = (new Query())->from('Product_Extra');
      $provider = new ActiveDataProvider([
        'query' => $query,
      ]);
      //ProductsExtra Array
      $extras_arr = $provider->getModels();
      //Make $extras_arr an Array of Objects
      for ($i=0; $i < count($extras_arr); $i++) {
        $extras_arr[$i] = (Object) $extras_arr[$i];
      }

      $query = (new Query())->from('Category');
      
      $provider = new ActiveDataProvider([
        'query' => $query,
      ]);
      //Categories Array
      $categories_arr = $provider->getModels();
      //Make $categories_arr an Array of Objects
      for ($i=0; $i < count($categories_arr); $i++) {
        $categories_arr[$i] = (Object) $categories_arr[$i];
      }

      $query = (new Query())->from('SubCategory');
      $provider = new ActiveDataProvider([
        'query' => $query,
      ]);
      //Subcategories Array
      $subcategories_arr = $provider->getModels();
      //Make $subcategories_arr an Array of Objects
      for ($i=0; $i < count($subcategories_arr); $i++) {
        $subcategories_arr[$i] = (Object) $subcategories_arr[$i];
      }

      for ($i=0; $i < count($products_arr); $i++) {
        $result[$i] = (Object) $products_arr[$i];
      }

      for ($i=0; $i < count($products_arr); $i++) {
        for ($j=0; $j < count($extras_arr); $j++) {
          if ( $products_arr[$i]->id == $extras_arr[$j]->product_id) {
            //$result[$i] =  $products_arr[$i]->id;
            for ($l=0; $l < count($categories_arr); $l++) {
              if ($extras_arr[$j]->category_id == $categories_arr[$l]->id) {
                 $result[$i]->category[$l] = $categories_arr[$l];
              }
            }

            for ($l=0; $l < count($subcategories_arr); $l++) {
              if ($extras_arr[$j]->subcategory_id == $subcategories_arr[$l]->id) {
                 $result[$i]->subcategory[$l] = $subcategories_arr[$l];
              }
            }
          }
        }
      }
      return $result;
    }

    public function actionGetproductby($id)
    {
      $result = [];

      $query = (new Query())->from('Product')->where(['id'=>$id])
      ->orderBy(['created_at' => SORT_DESC]);
      $provider = new ActiveDataProvider([
        'query' => $query,
      ]);
      //Products Array
      $products_arr = $provider->getModels();

      //Make $products_arr an Array of Objects
      for ($i=0; $i < count($products_arr); $i++) {
        $products_arr[$i] = (Object) $products_arr[$i];
      }

      $query = (new Query())->from('Product_Extra');
      $provider = new ActiveDataProvider([
        'query' => $query,
      ]);
      //ProductsExtra Array
      $extras_arr = $provider->getModels();
      //Make $extras_arr an Array of Objects
      for ($i=0; $i < count($extras_arr); $i++) {
        $extras_arr[$i] = (Object) $extras_arr[$i];
      }

      $query = (new Query())->from('Category');
      
      $provider = new ActiveDataProvider([
        'query' => $query,
      ]);
      //Categories Array
      $categories_arr = $provider->getModels();
      //Make $categories_arr an Array of Objects
      for ($i=0; $i < count($categories_arr); $i++) {
        $categories_arr[$i] = (Object) $categories_arr[$i];
      }

      $query = (new Query())->from('SubCategory');
      $provider = new ActiveDataProvider([
        'query' => $query,
      ]);
      //Subcategories Array
      $subcategories_arr = $provider->getModels();
      //Make $subcategories_arr an Array of Objects
      for ($i=0; $i < count($subcategories_arr); $i++) {
        $subcategories_arr[$i] = (Object) $subcategories_arr[$i];
      }

      for ($i=0; $i < count($products_arr); $i++) {
        $result[$i] = (Object) $products_arr[$i];
      }

      for ($i=0; $i < count($products_arr); $i++) {
        for ($j=0; $j < count($extras_arr); $j++) {
          if ( $products_arr[$i]->id == $extras_arr[$j]->product_id) {
            //$result[$i] =  $products_arr[$i]->id;
            for ($l=0; $l < count($categories_arr); $l++) {
              if ($extras_arr[$j]->category_id == $categories_arr[$l]->id) {
                 $result[$i]->category[$l] = $categories_arr[$l];
              }
            }

            for ($l=0; $l < count($subcategories_arr); $l++) {
              if ($extras_arr[$j]->subcategory_id == $subcategories_arr[$l]->id) {
                 $result[$i]->subcategory[$l] = $subcategories_arr[$l];
              }
            }
          }
        }
      }
      return $result;
    }

  }

?>
