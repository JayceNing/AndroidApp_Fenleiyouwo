package com.example.fenleiyouwo.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fenleiyouwo.MainActivity;
import com.example.fenleiyouwo.R;
import com.example.fenleiyouwo.knowLedge;
import com.example.fenleiyouwo.place;
import com.example.fenleiyouwo.recycleview.Fruit;
import com.example.fenleiyouwo.recycleview.FruitAdapter;
import com.example.fenleiyouwo.ui.me.MeFragment;
import com.example.fenleiyouwo.ui.me.MeViewModel;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import site.gemus.openingstartanimation.OpeningStartAnimation;

import static android.app.Activity.RESULT_OK;


//public class HomeFragment extends Fragment{
public class HomeFragment extends Fragment implements OnBannerListener {


    private Banner mBanner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //com.loopj.android.image.SmartImageView siv = (com.loopj.android.image.SmartImageView) root.findViewById(R.id.iv);
        //siv.setImageUrl("http://m.qpic.cn/psc?/V51o6AfO138S3m3lNdc10hOSZE4VGVwk/45NBuzDIW489QBoVep5mcSyvVM1aysZ0hGlFdI9ZYJeB9qLyB12qXagYNK75QNzH6uMHceTQdbiZoDifmZP.gC1sNETJW1LGEoiYSei15Z0!/b&bo=KQLqACkC6gADGTw!&rf=viewer_4&t=5");
        //com.loopj.android.image.SmartImageView gif = (com.loopj.android.image.SmartImageView) root.findViewById(R.id.tree);
        //gif.setImageUrl("https://z3.ax1x.com/2021/06/06/2dE9QH.gif");
        initView(root);
        return root;
    }

    public void initView(View root) {
        mBanner = root.findViewById(R.id.mBanner);
        //图片资源
//        Resources res = getResources();
//        Bitmap bmp1 = BitmapFactory.decodeResource(res, R.drawable.know1);
//        Bitmap bmp2 = BitmapFactory.decodeResource(res, R.drawable.know2);
//        Bitmap bmp3 = BitmapFactory.decodeResource(res, R.drawable.know3);
//        Bitmap bmp4 = BitmapFactory.decodeResource(res, R.drawable.know4);
        int[] imageResourceID = new int[]{R.drawable.know1, R.drawable.know2, R.drawable.know3, R.drawable.know4};
        List<Integer> imgeList = new ArrayList<>();
        //轮播标题
        String[] mtitle = new String[]{"可回收物", "厨余垃圾", "有害垃圾", "其他垃圾"};
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < imageResourceID.length; i++) {
            imgeList.add(imageResourceID[i]);//把图片资源循环放入list里面
            titleList.add(mtitle[i]);//把标题循环设置进列表里面
            //设置图片加载器，通过Glide加载图片
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getActivity()).load(path).into(imageView);
                }
            });
            //设置轮播的动画效果,里面有很多种特效,可以到GitHub上查看文档。
            mBanner.setBannerAnimation(Transformer.Accordion);
            mBanner.setImages(imgeList);//设置图片资源
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置banner显示样式（带标题的样式）
            mBanner.setBannerTitles(titleList); //设置标题集合（当banner样式有显示title时）
            //设置指示器位置（即图片下面的那个小圆点）
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.setDelayTime(3000);//设置轮播时间3秒切换下一图
            mBanner.setOnBannerListener(this);//设置监听
            mBanner.start();//开始进行banner渲染
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        OpeningStartAnimation openingStartAnimation = new OpeningStartAnimation.Builder(getContext())
//                .create();
//        openingStartAnimation.show(getActivity());

        ImageView toKnowledge = (ImageView) getActivity().findViewById(R.id.a3);
        toKnowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String username=user.getUsername().toString();

                Intent intent = new Intent(getActivity(), knowLedge.class);
                startActivity(intent);
            }
        });
        ImageView toPlace = (ImageView) getActivity().findViewById(R.id.a2);
        toPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String username=user.getUsername().toString();

                Intent intent = new Intent(getActivity(), place.class);
                startActivity(intent);
            }
        });

        ImageView scanCode = (ImageView) getActivity().findViewById(R.id.a1);
        scanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), scanCodeActivity.class);
                startActivity(intent);

            }
        });




    }


    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(), "你点击了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }
}
