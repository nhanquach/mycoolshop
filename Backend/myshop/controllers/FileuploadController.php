<?php
  namespace app\controllers;

  use yii\rest\ActiveController;
  use yii\filters\auth\HttpBasicAuth;

  use app\models\Product;
  /**
   *
   */
  class FileuploadController extends ActiveController
  {
    public $modelClass = 'app\models\Product';
    
    public function actionIndex()
    {
        $postdata = fopen( $_FILES[ 'data' ][ 'tmp_name' ], "r" );
        /* Get file extension */
        $extension = substr( $_FILES[ 'data' ][ 'name' ], strrpos( $_FILES[ 'data' ][ 'name' ], '.' ) );

        /* Generate unique name */
        $filename = $this->documentPath . uniqid() . $extension;

        /* Open a file for writing */
        $fp = fopen( $filename, "w" );

        /* Read the data 1 KB at a time
          and write to the file */
        while( $data = fread( $postdata, 1024 ) )
            fwrite( $fp, $data );

        /* Close the streams */
        fclose( $fp );
        fclose( $postdata );

		/* the result object that is sent to client*/
        $result = new UploadResult;
        $result->filename = $filename;
        $result->document = $_FILES[ 'data' ][ 'name' ];
        $result->create_time = date( "Y-m-d H:i:s" );
        return $result;
        
    }
  }

?>
