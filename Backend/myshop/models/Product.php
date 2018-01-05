<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Product".
 *
 * @property integer $id
 * @property string $name
 * @property string $description
 * @property integer $price
 * @property string $image
 * @property string $available_start
 * @property string $available_end
 */
class Product extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Product';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'name', 'description', 'price', 'image'], 'required'],
            [['id'], 'integer'],
            [['name'], 'string', 'max' => 255],
            [['description'], 'string', 'max' => 2555],
            [['image'], 'string', 'max' => 400],
            [['available_start'], 'string', 'max' => 25],
            [['available_end'], 'string', 'max' => 45],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'name' => 'Name',
            'description' => 'Description',
            'price' => 'Price',
            'image' => 'Image',
            'available_start' => 'Available Start',
            'available_end' => 'Available End',
        ];
    }

    // public function getExtraproducts()
    // {
    //   return $this->hasMany(Extraproduct::className(), ['product_id'=>'id']);
    // }
    //
    // public function getCategories()
    // {
    //   return $this->hasMany(Category::className(), ['id' => 'id'])
    //   ->via('Extraproducts');
    // }

}
