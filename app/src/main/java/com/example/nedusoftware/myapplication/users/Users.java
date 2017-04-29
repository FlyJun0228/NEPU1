package com.example.nedusoftware.myapplication.users;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nedusoftware.myapplication.MainActivity;
import com.example.nedusoftware.myapplication.R;
import com.example.nedusoftware.myapplication.bean.User;
import com.example.nedusoftware.myapplication.config.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by NEDUsoftware on 2017/4/27.
 */

public class Users extends Activity implements View.OnClickListener {
    private RelativeLayout layout_icon;
    private RelativeLayout layout_name;
    private RelativeLayout layout_sex;
    private ProgressBar progressBar;
    private TextView t_name;
    private TextView t_sex;
    private ImageView imageView;
    private String userid;
    private String iconpath;
    private String objecetId;
    private Button button;
    private File icon;
    private File img;
    private BmobFile bmobFile;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        initViews();
        initListeners();
        initData();
        Bmob.initialize(this, Constants.Bmob_APPID);
    }

    private void initViews() {
        layout_icon = (RelativeLayout) findViewById(R.id.layout_icon);
        layout_name = (RelativeLayout) findViewById(R.id.layout_name);
        layout_sex = (RelativeLayout) findViewById(R.id.layout_sex);
        t_name = (TextView) findViewById(R.id.t_name);
        t_sex = (TextView) findViewById(R.id.t_sex);
        imageView = (ImageView) findViewById(R.id.im_icon);
        button = (Button) findViewById(R.id.btn_exituser);
        img = new File(Environment.getExternalStorageDirectory() + "/Img.jpg");
        progressBar = (ProgressBar) findViewById(R.id.progressBar_icon);
    }

    private void initListeners() {
        button.setOnClickListener(this);
        layout_icon.setOnClickListener(this);
    }

    private void initData() {
        SharedPreferences pref = getSharedPreferences("userdata", MODE_PRIVATE);
        userid = pref.getString("userId", "");
        objecetId = pref.getString("objectId", "");
        t_name.setText(pref.getString("userName", ""));
        t_sex.setText(pref.getString("userSex", "男 "));
        iconpath = getApplicationContext().getFilesDir() + "/" + userid + ".jpg";
        icon = new File(iconpath);
        if (icon.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(iconpath);
            imageView.setImageBitmap(bitmap);
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.it);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exituser:
                userexit();
                break;
            case R.id.layout_icon:
                seticon();
                break;
        }
    }

    private void userexit() {
        SharedPreferences.Editor editor = getSharedPreferences("userdata", MODE_PRIVATE).edit();
        editor.putBoolean("isuser", false);
        editor.apply();
        Intent intent = new Intent(Users.this, MainActivity.class);
        startActivity(intent);
        Users.this.finish();
    }

    private void seticon() {
        new AlertDialog.Builder(this)
                .setTitle("选择来源")
                .setItems(new String[]{"拍照", "图库"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent_1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent_1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(img));
                                startActivityForResult(intent_1, 1);
                                break;
                            case 1:
                                Intent intent_2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent_2, 2);
                                break;
                        }
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                startphotoZoom(Uri.fromFile(img));
                break;
            case 2:
                startphotoZoom(data.getData());
                break;
            case 3:
                if (data != null) {
                    save(data);
                }
        }
    }

    public void startphotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    public void save(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            try {
                FileOutputStream fos = new FileOutputStream(iconpath);
                photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                uploadBitmap();
                imageView.setImageBitmap(photo);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadBitmap() {
        final BmobFile bmobFile = new BmobFile(new File(iconpath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void onProgress(Integer integer) {
                progressBar.setProgress(integer);
            }

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    SharedPreferences.Editor editor = getSharedPreferences("userdata", MODE_PRIVATE).edit();
                    editor.putBoolean("userIcon", true);
                    editor.apply();
                    Toast.makeText(Users.this, "上传成功！", Toast.LENGTH_SHORT).show();
                    User user = new User();
                    user.setIcon(bmobFile);
                    user.update(objecetId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                        }
                    });
                } else {
                    Toast.makeText(Users.this, "上传失败！" + e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
