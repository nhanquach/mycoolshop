<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;

  use app\models\OrderProducts;

  /**
   *
   */
  class OrderproductsController extends ActiveController
  {
    /**
     * [['order_id', 'product_id', 'product_name', 'product_price'], 'required'],
     */
    public $modelClass = 'app\models\OrderProducts';

    public function actionGetproductfrom($id)
    {
      $p = new OrderProducts();
      $rows = $p::find()->where(['order_id'=>$id])->all();
      return $rows;
    }
  }

?>
