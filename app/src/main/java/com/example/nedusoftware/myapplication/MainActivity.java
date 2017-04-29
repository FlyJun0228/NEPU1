package com.example.nedusoftware.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.nedusoftware.myapplication.bean.User;
import com.example.nedusoftware.myapplication.config.Constants;
import com.example.nedusoftware.myapplication.users.Login;
import com.example.nedusoftware.myapplication.users.Users;

import java.io.File;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private LocationFragment mLocationFragment;
    private FindFragment mFindFragment;
    private FavoritesFragment mFavoritesFragment;
    private BookFragment mBookFragment;
    private Boolean isuser;
    private BmobFile bmobFile;
    private Bitmap bitmap;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Bmob.initialize(this, Constants.Bmob_APPID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_location_on_white_24dp, "学校").setActiveColor(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.ic_find_replace_white_24dp, "组织").setActiveColor(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "爱好").setActiveColor(R.color.cadetblue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "生活").setActiveColor(R.color.green_1))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
        View headerView = navigationView.getHeaderView(0);
        imageview = (ImageView) headerView.findViewById(R.id.imageView);
        SharedPreferences pref = getSharedPreferences("userdata", MODE_PRIVATE);
        isuser = pref.getBoolean("isuser", false);
        if (isuser) {
            if (pref.getBoolean("userIcon", false)) {
                downloadBitmap(pref.getString("userId", ""));
            }
        }
        String iconpath = getApplicationContext().getFilesDir() + "/" + pref.getString("userId", "") + ".jpg";
        File icon = new File(iconpath);
        if (isuser && icon.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(iconpath);
            imageview.setImageBitmap(bitmap);
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.it);
            imageview.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences("userdata", MODE_PRIVATE);
        String iconpath = getApplicationContext().getFilesDir() + "/" + pref.getString("userId", "") + ".jpg";
        File icon = new File(iconpath);
        if (isuser && icon.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(iconpath);
            imageview.setImageBitmap(bitmap);
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.it);
            imageview.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            if (isuser) {
                Intent intent = new Intent(MainActivity.this, Users.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * tab方法
     */

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mLocationFragment = LocationFragment.newInstance("位置");
        transaction.replace(R.id.tb, mLocationFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mLocationFragment == null) {
                    mLocationFragment = LocationFragment.newInstance("位置");
                }
                transaction.replace(R.id.tb, mLocationFragment);
                break;
            case 1:
                if (mFindFragment == null) {
                    mFindFragment = FindFragment.newInstance("发现");
                }
                transaction.replace(R.id.tb, mFindFragment);
                break;
            case 2:
                if (mFavoritesFragment == null) {
                    mFavoritesFragment = FavoritesFragment.newInstance("爱好");
                }
                transaction.replace(R.id.tb, mFavoritesFragment);
                break;
            case 3:
                if (mBookFragment == null) {
                    mBookFragment = BookFragment.newInstance("图书");
                }
                transaction.replace(R.id.tb, mBookFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 下载头像Icon by lxy
     */
    public void downloadBitmap(String userId) {
        final String path = getApplicationContext().getFilesDir() + "/" + userId + ".jpg";
        BmobQuery<User> bmobQuery = new BmobQuery<User>();
        bmobQuery.addWhereEqualTo("userId", userId);
        bmobQuery.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null) {
                    Log.e("11111111", "done: " + list.size());
                    for (User user : list) {
                        bmobFile = user.getIcon();
                    }
                    File savePath = new File(path);
                    bmobFile.download(savePath, new DownloadFileListener() {
                        @Override
                        public void done(String s, BmobException e) {
                            bitmap = BitmapFactory.decodeFile(path);
                            Log.e("2222", "done: " + bmobFile + bitmap);

                        }

                        @Override
                        public void onProgress(Integer integer, long l) {

                        }
                    });
                } else {
                }
            }
        });
    }
}

