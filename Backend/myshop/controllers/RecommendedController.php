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

  class RecommendedController extends ActiveController
  {
    public $modelClass = 'app\models\Product';

    public function actionGetproductbylimit($id, $limit)
    {
     $result = [];

     if(!isset($limit)){
       $limit = 5;
     }

      $query = (new Query())->from('Product')->where(['!= ', 'id', $id])->limit($limit);
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

    public function actionGetproductsbutnot($id){
      $result = [];

      $query = (new Query())->from('product')->where(['!= ', 'id', $id]);
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

      $query = (new Query())->from('Subcategory');
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



    public function actionGetproducts(){
      $result = [];

      $query = (new Query())->from('product');
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

      $query = (new Query())->from('Subcategory');
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
