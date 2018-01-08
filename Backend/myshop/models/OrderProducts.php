<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Order_products".
 *
 * @property integer $_id
 * @property string $order_id
 * @property string $product_id
 * @property string $product_name
 * @property string $product_price
 * @property integer $product_quantity
 */
class OrderProducts extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Order_products';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['order_id', 'product_id', 'product_name', 'product_price'], 'required'],
            [['_id', 'product_quantity'], 'integer'],
            [['order_id', 'product_id'], 'string', 'max' => 45],
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
            'order_id' => 'Order ID',
            'product_id' => 'Product ID',
            'product_name' => 'Product Name',
            'product_price' => 'Product Price',
            'product_quantity' => 'Product Quantity',
        ];
    }
}
