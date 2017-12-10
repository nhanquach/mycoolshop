<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;

  use app\models\Subcategory;
  use app\models\Product;
  use app\models\ProductExtra;

  /**
   *
   */
  class SubcategoryController extends ActiveController
  {
    public $modelClass = 'app\models\Subcategory';

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

      //disable default list all allproducts
      //unset($actions['index']);

      $actions['view']['getCategoryname'] = [$this, 'getCategoryname'];

      return $actions;
    }


    public function actionGetcategoryname($id)
    {
      $c = new Category();
      $cate = $c::find()->select(['name'])->where(['id'=>$id])->one();

      if (sizeof($cate) != 0) {
        return $cate;
      } else {
        $s = "Unknown";
        return $s;
      }
    }

    /**
     * This function is to get all the Product(s) in the same Subcategory.
     * Requires $id.
     * How to use: http://localhost:8080/index.php?r=subcategory/get&id=$id
     * Return Product object(s) if avaiable.
     */
    public function actionGet($id)
    {
      
      $c = new Subcategory();
      $p = new Product();
      $e = new ProductExtra();
      $o = array();
      
      $subcate_id = $c::find()->where(['id'=>$id])->one();
      $product_extra_ids = $e::find()->select(['product_id'])->where(['subcategory_id'=>$subcate_id])->all();
      for($i=0;$i< sizeof($product_extra_ids);$i++){
        $result = $p::find()->where(['id'=>$product_extra_ids[$i]])->distinct()->one();
        array_push($o, $result);
      };
      return $o;
    }
  }

?>
