package cn.com.hiserivice.hiservice.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.com.hiserivice.hiservice.vo.BannerList;

/**
 * Created by gaojicai1 on 2017/1/20.
 */

public class BrannerAdapter extends PagerAdapter {

    private Context mContext;
    private List<BannerList> mBannerLists;

    public BrannerAdapter(Context context, List<BannerList> bannerLists) {
        mContext = context;
        mBannerLists = bannerLists;
    }

    @Override
    public int getCount() {
        return mBannerLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.branner, null);
        SimpleDraweeView imageView = new SimpleDraweeView(mContext);
        if (position == 0) {
            imageView.setImageURI(mBannerLists.get(0).getImg());
        } else if (position == 1) {
            imageView.setImageURI(mBannerLists.get(1).getImg());
        } else if (position == 2) {
            imageView.setImageURI(mBannerLists.get(2).getImg());
        } else if (position == 3) {
            imageView.setImageURI(mBannerLists.get(3).getImg());
        }

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
