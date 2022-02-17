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

    public SharedPreferences sharedPreferences;

    public static String inSearch;

    final String SAVED_TEXT = "saved_text";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.fragment_setting, null);

        rgSetting = v.findViewById(R.id.rg_setting);
        rbGoogle = v.findViewById(R.id.rb_google);
        rbYandex = v.findViewById(R.id.rb_yandex);
        rbBing = v.findViewById(R.id.rb_bing);

        rgSetting.setOnCheckedChangeListener(rgOnCheckedChangedListener1);
        loadRadioButton();

        return v;
    }

    RadioGroup.OnCheckedChangeListener rgOnCheckedChangedListener1 = new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rb_google:
                    inSearch = "google";
                    break;
                case R.id.rb_yandex:
                    inSearch = "yandex";
                    break;
                case R.id.rb_bing:
                    inSearch = "bing";
                    break;
                default:
                    break;
            }
        }
    };


    void saveInSearch() {
        sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_TEXT, inSearch);
        editor.apply();
    }


    public void saveRadioButton() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("google", rbGoogle.isChecked());
        editor.putBoolean("yandex", rbYandex.isChecked());
        editor.putBoolean("bing", rbBing.isChecked());
        editor.apply();

    }

    public void loadRadioButton() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        rbGoogle.setChecked(sharedPreferences.getBoolean("google", false));
        rbYandex.setChecked(sharedPreferences.getBoolean("yandex", false));
        rbBing.setChecked(sharedPreferences.getBoolean("bing", false));
    }


    @Override
    public void onPause() {
        saveInSearch();
        saveRadioButton();
        super.onPause();
    }
}
