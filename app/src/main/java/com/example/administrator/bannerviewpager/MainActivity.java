package com.example.administrator.bannerviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.bannerviewpager.widget.BannerContainer;
import com.example.administrator.bannerviewpager.widget.BannerViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Integer> idList=new ArrayList<>();
        idList.add(R.mipmap.image1);
        idList.add(R.mipmap.image2);
        idList.add(R.mipmap.image3);
        idList.add(R.mipmap.image4);
//        BannerViewPager bannerViewPager= (BannerViewPager) findViewById(R.id.banner);
//        bannerViewPager.setImageList(this,idList).setDuration(4000);
        BannerContainer banner= (BannerContainer) findViewById(R.id.banner_container);
        banner.setImageList(idList);
    }
}
