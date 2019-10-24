package com.test.test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.test.R;

public class PhotoFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() == null) return;
        Picasso.get().load(getArguments().getString("p")).into((ImageView) view.findViewById(R.id.iv));
    }

    public static PhotoFragment newInstance(String url) {
        PhotoFragment photoFragment = new PhotoFragment();
        Bundle arguments = new Bundle();
        arguments.putString("p", url);
        photoFragment.setArguments(arguments);
        return photoFragment;
    }
}