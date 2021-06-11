package com.example.mytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;

public class NotePadNewActivity extends Activity {

    String tag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        Button button2 =(Button)findViewById(R.id.button2);//
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selImageList = new ArrayList<>();
                initImagePicker();
                insertPhoto();
            }
        });
    }

    /**
     * 初始化图片选择器
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    /**
     * 插入图片，打开图片选择器
     */
    private void insertPhoto() {
        ImagePicker.getInstance().setSelectLimit(maxImgCount);
        Intent intent = new Intent(com.example.mytest.NotePadNewActivity.this, ImageGridActivity.class);
        startActivityForResult(intent, 100);
    }

    private ArrayList<ImageItem> selImageList;//当前选择的所有图片
    private final int maxImgCount = 1;               //允许选择图片最大数

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == 100) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    for (int i = 0; i < selImageList.size(); i++) {
                        System.out.println("图片地址="+ selImageList.get(i).path);
                        new GlideImageLoader().displayImage(this,selImageList.get(i).path,findViewById(R.id.imageView),23,34);
                        Toast.makeText(getApplicationContext(), "图片地址=" + selImageList.get(i).path, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == ImagePicker.REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                }
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                tag=Util.store(selImageList.get(0).path);
            }
        }).start();
    }
}
