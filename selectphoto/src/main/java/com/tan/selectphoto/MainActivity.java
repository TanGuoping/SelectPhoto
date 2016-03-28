package com.tan.selectphoto;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int TAKE_PHOTO = 0;
    private static final int CROP_PHOTO = 1;
    private static final int SELECT_PHOTO = 2;
    private static final int CROP_PICTURE = 3;
    private ImageView imageView;

    private Uri imageUri;
    private Uri pictureUri;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        initImage();


        imageView = (ImageView) findViewById(R.id.imageViewId);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(this);
        displayDefaultIcon();

    }

    private void displayDefaultIcon(){

        File file = new File(Environment.getExternalStorageDirectory(),"icon.png");
        if(file.exists()){

                if(file.exists()){
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }

                }

            }



    }

    private void initImage(){
        File file = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");

        try {
            if(file.exists()){
                file.delete();
            }

            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageUri = Uri.fromFile(file);

    }


    @Override
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String[] list = new String[]{"拍照","选择照片"};

        builder.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                        intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent1, TAKE_PHOTO);

                        break;
                    case 1:
                        //Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
                       intent2.setType("image/*");
                        startActivityForResult(intent2,SELECT_PHOTO);
                        break;


                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case TAKE_PHOTO:

                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("outputX",200);
                    intent.putExtra("outputY",200);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent,CROP_PHOTO);
                }

                break;

            case CROP_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));

                        imageView.setImageBitmap(bitmap);

                        saveIcon(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                break;

            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri uri = null;
                    if(Build.VERSION.SDK_INT >= 19){
                        uri = Uri.fromFile(new File(getImagePathOnKitKat(data)));
                    }
                    else{
                        uri = Uri.fromFile(new File(getImagePathBeforeKitKat(data)));
                    }

                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(uri, "image/*");
                    intent.putExtra("outputX", 200);
                    intent.putExtra("outputY",200);
                    pictureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"xy.png"));
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                    startActivityForResult(intent, CROP_PICTURE);




                }

            case CROP_PICTURE:
                if(resultCode == RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(pictureUri));

                        imageView.setImageBitmap(bitmap);

                        saveIcon(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    @TargetApi(19)
    public void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();

        Log.d("myUri",uri.toString());
        Log.d("yourUri", MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

        if(DocumentsContract.isDocumentUri(this, uri)){
            String docId = DocumentsContract.getDocumentId(uri);


            /*
            String[] split = docId.split(":");
            Log.d("split",split[0]);
            Log.d("split",split[1]);
            */


            if("com.android.providers.media.documents".equals(uri.getAuthority())){

                String id = docId.split(":")[1];
                String selection ="_id=?";
                String[] selectionArgs = new String[]{id};

                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection,selectionArgs);
                Log.d("imagePath",imagePath);

            }


            else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                Log.d("myDownload",contentUri.toString());
                imagePath = getImagePath(contentUri,null,null);
            }

        }

        else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null,null);
        }
        displayImage(imagePath);



    }

    @TargetApi(19)
    public String getImagePathOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();

        Log.d("myUri",uri.toString());
        Log.d("yourUri", MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

        if(DocumentsContract.isDocumentUri(this, uri)){
            String docId = DocumentsContract.getDocumentId(uri);


            /*
            String[] split = docId.split(":");
            Log.d("split",split[0]);
            Log.d("split",split[1]);
            */


            if("com.android.providers.media.documents".equals(uri.getAuthority())){

                String id = docId.split(":")[1];
                String selection ="_id=?";
                String[] selectionArgs = new String[]{id};

                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection,selectionArgs);
                Log.d("imagePath",imagePath);
                return  imagePath;

            }


            else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                Log.d("myDownload",contentUri.toString());
                imagePath = getImagePath(contentUri,null,null);
                return  imagePath;
            }

        }

        else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null,null);
            return imagePath;
        }

        return "";




    }

    private String getImagePathBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null, null);
        return imagePath;
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null, null);
        displayImage(imagePath);
    }

    private void displayImages(Uri uri){
        Bitmap bitmap = null;
        try {
             bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(imageView != null){
            imageView.setImageBitmap(bitmap);
        }

        else{
            Toast.makeText(this, "选取图片失败", Toast.LENGTH_SHORT).show();
        }

    }

    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
            saveIcon(bitmap);
        }
        else{
            Toast.makeText(this, "选取图片失败", Toast.LENGTH_SHORT).show();
        }
    }
    private String getImagePath(Uri uri, String selection, String[] selectionArgs){
        String path = null;
        Cursor cursor = null;
        cursor = getContentResolver().query(uri,null,selection,selectionArgs,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex("_data"));
            }
            cursor.close();
        }
        Log.d("imagePath",path);
        return  path;
    }


    private void saveIcon(Bitmap bitmap){

        try {
            File file = new File(Environment.getExternalStorageDirectory(),"icon.png");
            if(file.exists()){
                file.delete();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}


