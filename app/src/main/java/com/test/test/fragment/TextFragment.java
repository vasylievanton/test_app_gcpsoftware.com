package com.test.test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.test.R;

public class TextFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() == null) return;
        ((TextView) view.findViewById(R.id.tv)).setText(getArguments().getString("s"));
    }

    public static TextFragment newInstance(String text) {
        TextFragment textFragment = new TextFragment();
        Bundle arguments = new Bundle();
        arguments.putString("s", text);
        textFragment.setArguments(arguments);
        return textFragment;
    }
}
