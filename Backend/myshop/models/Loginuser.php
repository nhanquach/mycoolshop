<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "User".
 *
 * @property integer $_id
 * @property string $username
 * @property string $password
 * @property string $userType
 */
class Loginuser extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'User';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['password','email'], 'required'],
            [['_id'], 'integer'],
            [['password', 'userType', 'phone', 'address'], 'string'],
            [['username'], 'string', 'max' => 255],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            '_id' => 'Id',
            'username' => 'Username',
            'password' => 'Password',
            'userType' => 'User Type',
            'email'    => 'E-mail'
        ];
    }
}
