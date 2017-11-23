<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;

  use app\models\Product;

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

      //disable default list all allproducts
      //unset($actions['index']);

      $actions['index']['prepareDataProvider'] = [$this, 'prepareDataProvider'];

      return $actions;
    }

    public function prepareDataProvider(){
      $rows = (new \yii\db\Query())
      ->select(['p.id as id', 'p.name as name', 'p.price', 'p.description'])
      ->from(['product p'])
      ->distinct()
      ->all();

      return $rows;
    }

    public function actionNo (){
      die('This is No.');
    }
  }

?>
