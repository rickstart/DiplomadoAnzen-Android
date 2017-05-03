package com.mobintum.socceranzen;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;


public class PlayerFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "PlayerFragment";

    private static final String ARG_PARAM_NAME = "paramName";
    private static final String ARG_PARAM_COLOR = "paramColor";
    private String name;
    private int tshirtColor;

    private TextView txtName;
    private ImageView imgTShirt;
    private ImageView imgBall;
    public boolean haveBall = false;

    private CallbacksFragment callbacks;

    public PlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbacks = (CallbacksFragment) context;
    }

    public static PlayerFragment newInstance(String name, int tshirtColor ) {
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_NAME,name);
        args.putInt(ARG_PARAM_COLOR,tshirtColor);
        PlayerFragment fragment = new PlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if( getArguments()!=null ){
            this.name = getArguments().getString(ARG_PARAM_NAME);
            this.tshirtColor = getArguments().getInt(ARG_PARAM_COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        txtName = (TextView) view.findViewById(R.id.txtName);
        imgTShirt = (ImageView) view.findViewById(R.id.imgTShirt);
        imgBall = (ImageView) view.findViewById(R.id.imgBall);
        imgBall.setOnClickListener(this);
        txtName.setText(name);
        imgTShirt.setColorFilter(getResources().getColor(tshirtColor));
        return view;
    }

    @Override
    public void onClick(View view) {
        showBall(false);
        callbacks.pass(getRandomPosition(0,6));
    }

    public static int getRandomPosition(int a, int b){
        Random r = new Random();
        int result = r.nextInt(b-a) + a;
        return result;
    }

    public void showBall(boolean show){
        if (show) {
            haveBall = true;
            imgBall.setVisibility(View.VISIBLE);
        }else{
            haveBall = false;
            imgBall.setVisibility(View.GONE);
        }

    }

    public interface CallbacksFragment{
        public void pass(int position);
    }
}
