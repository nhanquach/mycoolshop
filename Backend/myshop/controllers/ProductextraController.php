<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;
  use yii\data\ActiveDataProvider;
  use yii\db\Query;

  use app\models\ProductExtra;

  /**
   *
   */
  class ProductextraController extends ActiveController
  {
    public $modelClass = 'app\models\ProductExtra';

    

    public function actions()
    {
      $actions = parent::actions();

      //disable default list all allproducts
      //unset($actions['index']);
      $actions['view']['getCategoryname'] = [$this, 'getCategoryname'];

      return $actions;
    }

    public function actionGetproductextra()
    {
      $c = new ProductExtra();
      $cate = $c::find()->all();

      if (sizeof($cate) != 0) {
        return $cate;
      } else {
        $s = "Unknown";
        return $s;
      }
    }

    public function actionGetextraid($pid)
    {
      $query = (new Query())->from('Product_Extra')->where(['product_id'=>$pid]);
      $provider = new ActiveDataProvider([
        'query' => $query,
      ]);
      //Products Array
      $extra_arr = $provider->getModels();

      //Make $products_arr an Array of Objects
      for ($i=0; $i < count($extra_arr); $i++) {
        $extra_arr[$i] = (Object) $extra_arr[$i];
      }
      return $extra_arr;
    }

  };

?>
