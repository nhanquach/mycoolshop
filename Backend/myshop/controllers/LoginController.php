<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;

  use app\models\Loginuser;

  /**
   *
   */
  class LoginController extends ActiveController
  {
    public $modelClass = 'app\models\Loginuser';

    protected function verbs()
    {
        return [
            'create' => ['POST'],
        ];
    }

    public function actions()
    {
      $actions = parent::actions();
      unset($actions['create']);
      return $actions;
    }

    public function actionCreate($email, $password)
    {
        $u = new Loginuser();
        $u = $u::find()->where(['email'=>$email, 'password'=>$password])->one();
        if (sizeof($u) != 0) {
            return $u;
        } else {
            $s = "None";
            return $s;
        }
    }
  }

?>
