<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Orders".
 *
 * @property integer $_id
 * @property string $created_at
 * @property string $id_order_products
 * @property string $address
 * @property string $id_user
 * @property string $status
 */
class Orders extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Orders';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_order_products', 'address', 'id_user', 'user_name', 'delivery_note', 'price', 'phone', 'email'], 'required'],
            [['_id'], 'integer'],
            [['created_at', 'status'], 'string', 'max' => 45],
            [['address'], 'string', 'max' => 1000],
            [['_id'], 'unique'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            '_id' => 'Id',
            'created_at' => 'Created At',
            'id_order_products' => 'Id Order Products',
            'address' => 'Address',
            'id_user' => 'Id User',
            'status' => 'Status',
        ];
    }
}
