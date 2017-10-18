package com.example.min.newappstution;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.example.min.newappstution.car.GoShopCarAc;
import com.example.min.newappstution.udesk.UdeskUtile;
import com.example.min.newappstution.udesk.UserInfoMode;
import com.example.min.newappstution.updateui.TextAc;
import com.example.min.newappstution.utile.IntentUtile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.udesk.model.UdeskCommodityItem;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.go_T1)
    TextView go_T1;
    @BindView(R.id.go_ShopCar)
    TextView go_ShopCar;
    @BindView(R.id.go_UpdateAc)
    TextView go_UpdateAc;
    @BindView(R.id.openCustomerService)
    TextView openCustomerService;
    @BindView(R.id.seedShopInfo)
    TextView seedShopInfo;
    @BindView(R.id.seedShopTEXT)
    TextView seedShopTEXT;
    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.go_T1, R.id.go_ShopCar, R.id.go_UpdateAc, R.id.openCustomerService, R.id.seedShopInfo, R.id.seedShopTEXT, R.id.img})
    public void viewOnClick(View view) {
        switch (view.getId()) {
            case R.id.go_T1:
                IntentUtile.goAC(MainActivity.this, TextAC1.class);
                break;
            case R.id.go_ShopCar:
                IntentUtile.goAC(MainActivity.this, GoShopCarAc.class);
                break;
            case R.id.go_UpdateAc:
                IntentUtile.goAC(MainActivity.this, TextAc.class);
                break;
            //客服单聊
            case R.id.openCustomerService:
                UserInfoMode infoMode = new UserInfoMode();
                infoMode.setName("张三丰");
                infoMode.setPhone("13501267714");
                infoMode.setDESCRIPTION("测试数据");
                UdeskUtile.setUserInfo(MainActivity.this, infoMode);
                break;
            //向客服发送商品链接
            case R.id.seedShopInfo:
                UdeskCommodityItem item = new UdeskCommodityItem();
                item.setTitle("木林森男鞋新款2016夏季透气网鞋男士休闲鞋网面韩版懒人蹬潮鞋子");// 商品主标题
                item.setSubTitle("¥ 99.00");//商品副标题
                item.setThumbHttpUrl("https://img.alicdn.com/imgextra/i1/1728293990/TB2ngm0qFXXXXcOXXXXXXXXXXXX_!!1728293990.jpg_430x430q90.jpg");// 左侧图片
                item.setCommodityUrl("https://detail.tmall.com/item.htm?spm=a1z10.3746-b.w4946-14396547293.1.4PUcgZ&id=529634221064&sku_properties=-1:-1");// 商品网络链接
                UdeskUtile.createCommodity(MainActivity.this, item);
                break;
            //向客服发送商品链接
            case R.id.seedShopTEXT:
//                UdeskUtile.createCommodityText(MainActivity.this, "1111111111");
                String PATH = Environment.getExternalStorageDirectory() + "/";
                File hkappDir = new File(PATH + "FSA");
                File file = new File(hkappDir.getAbsolutePath() + "/" + "1111" + ".jpg");
                // 判断文件是否存在
//                System.out.println("\n"+Sanselan.hasImageFileExtension(file));
//                System.out.println("\n"+"=======================================");
//                // 获得图片结构描述
//                try {
//                    System.out.println("\n"+Sanselan.dumpImageFile(file));
//                    System.out.println("\n"+"=======================================");
//                    // 获得图片信息
//                    ImageInfo imageInfo = Sanselan.getImageInfo(file);
//                    System.out.println("\n"+imageInfo.getPhysicalHeightDpi());
//                    System.out.println("\n"+imageInfo.getPhysicalWidthDpi());
//                    System.out.println("\n"+imageInfo.getComments().toString());
//                    System.out.println("\n"+imageInfo.getBitsPerPixel());
//                    System.out.println("\n"+imageInfo.getColorTypeDescription());
//                    System.out.println("\n"+imageInfo.getFormatName());
//                    System.out.println("\n"+imageInfo.getMimeType());
//                    System.out.println("\n"+imageInfo.getHeight());
//                    System.out.println("\n"+imageInfo.getWidth());
//                    System.out.println("\n"+"=======================================");
//                } catch (ImageReadException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                getImagInfo(file);
                break;
            //向客服发送商品链接
            case R.id.img:
                Bitmap image = ((BitmapDrawable) img.getDrawable()).getBitmap();
                save(image);
                break;
        }
    }

    private void save(Bitmap bm) {
        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        String PATH = Environment.getExternalStorageDirectory() + "/";
        File hkappDir = new File(PATH + "FSA");
        if (!hkappDir.exists()) {
            hkappDir.mkdir();
        }
        File file = new File(hkappDir.getAbsolutePath() + "/" + "1111" + ".jpg");
        if (!file.exists()) {
            try {
                file.createNewFile();//此处报错！
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Log.i("sss", "已经保存");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void getImagInfo(File source) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(source);
            for (Directory directory : metadata.getDirectories()) {
                if("ExifSubIFDDirectory".equalsIgnoreCase( directory.getClass().getSimpleName() )){

                    //光圈F值=镜头的焦距/镜头光圈的直径
                    System.out.println("光圈值: f/" + directory.getString(ExifSubIFDDirectory.TAG_FNUMBER) );

                    System.out.println("曝光时间: " + directory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME)+ "秒" );
                    System.out.println("ISO速度: " + directory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT) );
                    System.out.println("焦距: " + directory.getString(ExifSubIFDDirectory.TAG_FOCAL_LENGTH) + "毫米" );

                    System.out.println("拍照时间: " + directory.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL) );
                    System.out.println("宽: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH) );
                    System.out.println("高: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT) );

                }
                if("ExifIFD0Directory".equalsIgnoreCase( directory.getClass().getSimpleName() )){
                    System.out.println("照相机制造商: " + directory.getString(ExifIFD0Directory.TAG_MAKE) );
                    System.out.println("照相机型号: " + directory.getString(ExifIFD0Directory.TAG_MODEL) );
                    System.out.println("水平分辨率: " + directory.getString(ExifIFD0Directory.TAG_X_RESOLUTION) );
                    System.out.println("垂直分辨率: " + directory.getString(ExifIFD0Directory.TAG_Y_RESOLUTION) );
                }
            }
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
