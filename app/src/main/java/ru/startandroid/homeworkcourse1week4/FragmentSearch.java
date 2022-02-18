package ru.startandroid.homeworkcourse1week4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;


public class FragmentSearch extends Fragment {

    EditText etSearch;
    Button btnSearch;
    String savedInSearch;


    View.OnClickListener mBtnSearchOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (savedInSearch) {
                case Settings.GOOGLE:
                    openActivity(Settings.LINK_GOOGLE);
                    break;
                case Settings.YANDEX:
                    openActivity(Settings.LINK_YANDEX);
                    break;
                case Settings.BING:
                    openActivity(Settings.LINK_BING);
                    break;
            }
        }
    };

    private void openActivity(String string_link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(string_link + etSearch.getText().toString()));
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.fragment_search, null);

        etSearch = v.findViewById(R.id.et_search);
        btnSearch = v.findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(mBtnSearchOnClickListener);

        loadInSearch();

        return v;
    }

    void loadInSearch() {
        SharedPreferences sharedPreferences;
        sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        savedInSearch = sharedPreferences.getString(Settings.SAVED_TEXT, "");
    }

}
