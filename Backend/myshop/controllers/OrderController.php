<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;

  use app\models\Orders;

  /**
   *
   */
  class OrderController extends ActiveController
  {
    /**
     * ['created_at', 'id_order_products', 'address', 'id_user']
     */
    public $modelClass = 'app\models\Orders';

    public function actionGetfrom($id)
    {
      $orders = new Orders();
      $rows = $orders::find()->where(['id_user'=>$id])->all();
      return $rows;
    }
  }

?>
