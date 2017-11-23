<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;

  use app\models\ProductExtra;

  /**
   *
   */
  class ProductextraController extends ActiveController
  {
    public $modelClass = 'app\models\ProductExtra';

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

    public function actionNo (){
      die('This is No.');
    }
  }

?>
