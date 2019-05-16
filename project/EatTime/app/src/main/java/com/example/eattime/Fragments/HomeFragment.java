package com.example.eattime.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eattime.Common.Common;
import com.example.eattime.Model.User;
import com.example.eattime.R;
import com.example.eattime.Service.PicassoImageLoadingService;
import com.facebook.accountkit.AccountKit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ss.com.bannerslider.Slider;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.layout_user_info)
    LinearLayout layout_user_info;
    @BindView(R.id.txt_user_name)
    TextView txt_user_name;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this,view);

        // Khoi tao
        Slider.init(new PicassoImageLoadingService());
        // check login chua?
        if (AccountKit.getCurrentAccessToken() != null)
        {
            setUserInfomation();
        }

        return view;

    }

    private void setUserInfomation() {
        layout_user_info.setVisibility(View.VISIBLE);
        txt_user_name.setText(Common.currentUser.getName());
    }

}
