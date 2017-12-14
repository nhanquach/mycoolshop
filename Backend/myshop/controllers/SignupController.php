<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;

  use app\models\Loginuser;

  /**
   *
   */
  class SignupController extends ActiveController
  {
    public $modelClass = 'app\models\Loginuser';
  }

?>
