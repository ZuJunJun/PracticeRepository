package com.example.jay.packageandroidproject.third;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.util.UICommonUtil;
import com.example.jay.packageandroidproject.view.XCircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment implements View.OnClickListener {

    private XCircleImageView mView;

    public static ThirdFragment newInstance() {
        ThirdFragment messageFragment = new ThirdFragment();
        return messageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view.findViewById(R.id.circle_view);
        view.findViewById(R.id.tv1).setOnClickListener(this);
        view.findViewById(R.id.tv2).setOnClickListener(this);
        view.findViewById(R.id.tv3).setOnClickListener(this);
        view.findViewById(R.id.tv4).setOnClickListener(this);
        view.findViewById(R.id.tv5).setOnClickListener(this);
        view.findViewById(R.id.tv6).setOnClickListener(this);
        view.findViewById(R.id.tv7).setOnClickListener(this);
        view.findViewById(R.id.tv8).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                mView.setType(XCircleImageView.TYPE.CIRCLE);
                break;
            case R.id.tv2:
                mView.setType(XCircleImageView.TYPE.ROUND);
                mView.setRoundRadius(UICommonUtil.dp2px(getContext(),5));
                break;
            case R.id.tv3:
                mView.setType(XCircleImageView.TYPE.BORDER_CIRCLE);
                break;
            case R.id.tv4:
                mView.setType(XCircleImageView.TYPE.BORDER_ROUND);
                mView.setRoundRadius(UICommonUtil.dp2px(getContext(),5));
                break;
            case R.id.tv5:
                if (mView.getType() == XCircleImageView.TYPE.BORDER_CIRCLE
                        || mView.getType() == XCircleImageView.TYPE.BORDER_ROUND) {
                    mView.setmBorderColor(Color.RED);
                }
                break;
            case R.id.tv6:
                if (mView.getType() == XCircleImageView.TYPE.BORDER_CIRCLE
                        || mView.getType() == XCircleImageView.TYPE.BORDER_ROUND) {
                    mView.setmBorderColor(Color.BLUE);
                }
                break;
            case R.id.tv7:
                if (mView.getType() == XCircleImageView.TYPE.BORDER_CIRCLE
                        || mView.getType() == XCircleImageView.TYPE.BORDER_ROUND) {
                    mView.setmBorderColor(Color.YELLOW);
                }
                break;
            case R.id.tv8:
                if (mView.getType() == XCircleImageView.TYPE.BORDER_CIRCLE
                        || mView.getType() == XCircleImageView.TYPE.BORDER_ROUND) {
                    mView.setmBorderColor(Color.GREEN);
                }
                break;
        }
    }
}
