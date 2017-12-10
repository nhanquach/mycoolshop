<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;

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
    
  }

?>
