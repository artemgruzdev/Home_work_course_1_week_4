package ru.startandroid.homeworkcourse1week4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;


public class FragmentSearch extends Fragment {

    FragmentSetting fragmentSetting = new FragmentSetting();

    EditText etSearch;
    Button btnSearch;
    String savedInSearch;
    String textSearch;
    Intent intent;


    View.OnClickListener mBtnSearchOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            textSearch = etSearch.getText().toString();
            switch (savedInSearch) {
                case "google":
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + textSearch));
                    startActivity(intent);
                    break;
                case "yandex":
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru/search/?text=" + textSearch));
                    startActivity(intent);
                    break;
                case "bing":
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bing.com/search?q=" + textSearch));
                    startActivity(intent);
                    break;
            }
        }
    };

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
        fragmentSetting.sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        savedInSearch = fragmentSetting.sharedPreferences.getString(fragmentSetting.SAVED_TEXT, "");
        Toast.makeText(getActivity(), savedInSearch, Toast.LENGTH_LONG).show();

    }

}
