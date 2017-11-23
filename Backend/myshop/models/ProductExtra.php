<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Product_Extra".
 *
 * @property integer $_id
 * @property integer $product_id
 * @property string $category_id
 * @property string $subcategory_id
 */
class ProductExtra extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Product_Extra';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['product_id', 'category_id', 'subcategory_id'], 'required'],
            [['product_id'], 'integer'],
            [['category_id', 'subcategory_id'], 'string', 'max' => 45],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            '_id' => 'Id',
            'product_id' => 'Product ID',
            'category_id' => 'Category ID',
            'subcategory_id' => 'Subcategory ID',
        ];
    }
}
