<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;

  use app\models\Subcategory;

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

    public function actionNo (){
      die('This is No.');
    }
  }

?>
