package com.aro.misaina.smartassurance;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TagFragment extends Fragment {
    Button bTag;

    public TagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bTag = (Button)getView().findViewById(R.id.bTag);
        bTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonTag();
            }
        });
    }
    public void clickButtonTag() {
        Intent intent = new Intent(this.getActivity(), TagActivity.class);
        this.getActivity().startActivity(intent);
    }
}
