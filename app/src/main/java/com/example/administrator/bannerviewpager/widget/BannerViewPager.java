package com.example.administrator.bannerviewpager.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/10.
 */

public class BannerViewPager extends ViewPager {

    private Context mContext;
    private int duration;
    private int currentPosition;
    private List<ImageView> imageList;

    private Thread moveThread=new Thread(){
        @Override
        public void run() {
            while (true){
                try {
                    sleep(duration);
                    handler.sendEmptyMessage(0x11);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    private  Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x11:
                    int position=BannerViewPager.this.getCurrentItem();
                    BannerViewPager.this.setCurrentItem(position+1,true);
                    break;
            }
        }
    };

    public BannerViewPager(Context context) {
        this(context,null);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        this.setOffscreenPageLimit(3);
    }

    private void initView(){
//        imageList=new ArrayList<>();
        duration=2000;
        this.setAdapter(new BannerPagerAdapter());
        this.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state==ViewPager.SCROLL_STATE_IDLE){
                    int length=imageList.size();
                    int position=BannerViewPager.this.getCurrentItem();
                    if (position==0){
                        BannerViewPager.this.setCurrentItem(length-2,false);
                    }else if (position==length-1){
                        BannerViewPager.this.setCurrentItem(1,false);
                    }
                }
            }
        });
        this.setCurrentItem(1);
//        try {
//            Field field=ViewPager.class.getDeclaredField("mScroller");
//            field.setAccessible(true);
//            Field field1=ViewPager.class.getDeclaredField("sInterpolator");
//            Interpolator interpolator= (Interpolator) field1.get(this);
//            MyScroller scroller=new MyScroller(mContext,interpolator);
////            scroller.setmDuration(2000);
//            field.set(this,scroller);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        moveThread.start();
    }

    public BannerViewPager setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public BannerViewPager setImageList(Context context,List<Integer> idList){
        imageList=new ArrayList<>();
        for (int id:idList){
            ImageView imageView=new ImageView(mContext);
            ViewGroup.LayoutParams params=getLayoutParams();
            params.width= ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
            imageView.setImageResource(id);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageList.add(imageView);
        }
        int length=idList.size();
        ImageView first=new ImageView(mContext);
        ViewGroup.LayoutParams params=getLayoutParams();
        params.width= ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
        first.setImageResource(idList.get(length-1));
        first.setLayoutParams(params);
        first.setScaleType(ImageView.ScaleType.FIT_XY);
        imageList.add(0,first);
        ImageView last=new ImageView(mContext);
        last.setImageResource(idList.get(0));
        last.setLayoutParams(params);
        last.setScaleType(ImageView.ScaleType.FIT_XY);
        imageList.add(last);
        initView();
        return this;
    }

    public BannerViewPager setImageList(List<ImageView> imageList) {
//        this.imageList = imageList;
//        this.imageList.add(0,imageList.get(imageList.size()-1));
//        this.imageList.add(this.imageList.size()-1,imageList.get(0));
//        for (ImageView imageView:this.imageList){
//            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            imageView.setLayoutParams(params);
//        }
        this.imageList=new ArrayList<>();

        this.setAdapter(new BannerPagerAdapter());
        return this;
    }

    private class MyScroller extends Scroller{

        private int mDuration=800;
        public MyScroller(Context context) {
            super(context);
        }

        public MyScroller(Context context,Interpolator interpolator){
            super(context,interpolator);
        }

        public void setmDuration(int mDuration) {
            this.mDuration = mDuration;
        }

        public int getmDuration() {
            return mDuration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy,mDuration);
        }
    }

    private class BannerPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view ==object ;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=imageList.get(position);
            if (imageView.getParent()==null){
                container.addView(imageList.get(position));
            }
            return imageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(imageList.get(position));
        }
    }



}
