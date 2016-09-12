package com.example.administrator.bannerviewpager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.administrator.bannerviewpager.R;
import com.example.administrator.bannerviewpager.widget.Indicator.CirclePagerIndicator;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */

public class BannerContainer extends RelativeLayout {

    private Context mContext;
    private BannerViewPager banner;
    private CirclePagerIndicator indicator;

    public BannerContainer(Context context) {
        this(context,null);
    }

    public BannerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        init();
    }

    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.banner_layout,this,true);
        banner= (BannerViewPager) findViewById(R.id.banner);
        indicator= (CirclePagerIndicator) findViewById(R.id.indicator);
    }

    public void setImageList(List<Integer> idList){
        banner.setImageList(mContext,idList);
        indicator.bindViewPager(banner);
    }


}
