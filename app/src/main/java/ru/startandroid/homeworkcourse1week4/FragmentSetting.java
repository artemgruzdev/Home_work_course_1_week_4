package ru.startandroid.homeworkcourse1week4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Objects;

public class FragmentSetting extends Fragment {

    RadioGroup rgSetting;
    RadioButton rbGoogle;
    RadioButton rbYandex;
    RadioButton rbBing;


    public static String inSearch;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.fragment_setting, null);

        rgSetting = v.findViewById(R.id.rg_setting);
        rbGoogle = v.findViewById(R.id.rb_google);
        rbYandex = v.findViewById(R.id.rb_yandex);
        rbBing = v.findViewById(R.id.rb_bing);

        rgSetting.setOnCheckedChangeListener(rgOnCheckedChangedListener);
        loadRadioButton();

        return v;
    }

    RadioGroup.OnCheckedChangeListener rgOnCheckedChangedListener = new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rb_google:
                    inSearch = Settings.GOOGLE;
                    break;
                case R.id.rb_yandex:
                    inSearch = Settings.YANDEX;
                    break;
                case R.id.rb_bing:
                    inSearch = Settings.BING;
                    break;
                default:
                    break;
            }
        }
    };


    void saveInSearch() {
        SharedPreferences sharedPreferences;
        sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Settings.SAVED_TEXT, inSearch);
        editor.apply();
    }


    public void saveRadioButton() {
        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Settings.GOOGLE, rbGoogle.isChecked());
        editor.putBoolean(Settings.YANDEX, rbYandex.isChecked());
        editor.putBoolean(Settings.BING, rbBing.isChecked());
        editor.apply();

    }

    public void loadRadioButton() {
        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        rbGoogle.setChecked(sharedPreferences.getBoolean(Settings.GOOGLE, false));
        rbYandex.setChecked(sharedPreferences.getBoolean(Settings.YANDEX, false));
        rbBing.setChecked(sharedPreferences.getBoolean(Settings.BING, false));
    }


    @Override
    public void onPause() {
        saveInSearch();
        saveRadioButton();
        super.onPause();
    }
}
