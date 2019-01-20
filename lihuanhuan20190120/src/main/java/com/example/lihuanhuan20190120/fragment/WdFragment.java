package com.example.lihuanhuan20190120.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lihuanhuan20190120.R;

import butterknife.ButterKnife;

public class WdFragment extends Fragment {
    private Button bt;
    private ImageView iv_img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wdfragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        iv_img= view.findViewById(R.id.iv_img);

        bt = view.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //缩放
                ObjectAnimator valueAnimator = (ObjectAnimator) ObjectAnimator.ofFloat(iv_img,"scaleX",1,2);
                ObjectAnimator valueAnimator2 = (ObjectAnimator) ObjectAnimator.ofFloat(iv_img,"scaleY",1,2);
                //旋转
                ObjectAnimator valueAnimator1 = (ObjectAnimator) ObjectAnimator.ofFloat(iv_img,"rotationY",0,180);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(valueAnimator).with(valueAnimator1).with(valueAnimator2);
                animatorSet.setDuration(2000);
                animatorSet.start();

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
