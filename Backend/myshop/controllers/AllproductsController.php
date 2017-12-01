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
      ->select(['p.id as id', 'p.name as name', 'p.price', 'p.description', 'p.created_at as created_at', 'p.seller as seller', 'p.available_start as start_time', 'p.available_end as end_time', 'p.image as image'])
      ->from(['Product p'])
      ->orderby(['created_at' => SORT_DESC])
      ->all();

      return $rows;
    }
  }

?>
