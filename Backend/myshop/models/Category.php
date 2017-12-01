<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Category".
 *
 * @property integer $_id
 * @property string $id
 * @property string $name
 * @property string $type
 */
class Category extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Category';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'name'], 'required'],
            [['id', 'type'], 'string', 'max' => 45],
            [['name'], 'string', 'max' => 50],
            [['id'], 'unique'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            '_id' => 'Id',
            'id' => 'ID',
            'name' => 'Name',
            'type' => 'Type',
        ];
    }

    public function getProduct()
    {
      return $this->hasOne(Product::className(), ['id' => 'id'])
      ->viaTable('ProductExtra', ['product_id', 'catergory_id']);
    }
}
