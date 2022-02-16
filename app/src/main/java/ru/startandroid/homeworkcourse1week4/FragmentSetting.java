package ru.startandroid.homeworkcourse1week4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

public class FragmentSetting extends Fragment {

    RadioGroup rgSetting;
    RadioButton rbGoogle;
    RadioButton rbYandex;
    RadioButton rbBing;
    int KEY_SAVED_RADIO_BUTTON_INDEX;
    int loadSavedCheckInt;

    public SharedPreferences sharedPreferences;

    public static String inSearch;

    final String SAVED_TEXT = "saved_text";
    final int SAVED_INT = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.fragment_setting, null);

        rgSetting = v.findViewById(R.id.rg_setting);
        rbGoogle = v.findViewById(R.id.rb_google);
        rbYandex = v.findViewById(R.id.rb_yandex);
        rbBing = v.findViewById(R.id.rb_bing);

        rgSetting.setOnCheckedChangeListener(rgOnCheckedChangedListener1);
        loadInChecked();
//        if (KEY_SAVED_RADIO_BUTTON_INDEX == 0) {
//            rbGoogle.setChecked(true);
//        } else if (KEY_SAVED_RADIO_BUTTON_INDEX == 1) {
//            rbYandex.setChecked(true);
//        } else if (KEY_SAVED_RADIO_BUTTON_INDEX == 2) {
//            rbBing.setChecked(true);
//        }

        if (loadSavedCheckInt == 0) {
            rbGoogle.setChecked(true);
        } else if (loadSavedCheckInt == 1) {
            rbYandex.setChecked(true);
        } else if (loadSavedCheckInt == 2) {
            rbBing.setChecked(true);
        }

        return v;
    }

    RadioGroup.OnCheckedChangeListener rgOnCheckedChangedListener1 = new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rb_google:
                    inSearch = "google";
                    Log.d("myLogs", inSearch);
                    //KEY_SAVED_RADIO_BUTTON_INDEX = rgSetting.getCheckedRadioButtonId();
                    KEY_SAVED_RADIO_BUTTON_INDEX = 0;
                    break;
                case R.id.rb_yandex:
                    inSearch = "yandex";
                    Log.d("myLogs", inSearch);
//                    KEY_SAVED_RADIO_BUTTON_INDEX = rgSetting.getCheckedRadioButtonId();
                    KEY_SAVED_RADIO_BUTTON_INDEX = 1;
                    break;
                case R.id.rb_bing:
                    inSearch = "bing";
                    Log.d("myLogs", inSearch);
                    KEY_SAVED_RADIO_BUTTON_INDEX = 2;
//                    KEY_SAVED_RADIO_BUTTON_INDEX = rgSetting.getCheckedRadioButtonId();
                    break;
                default:
                    break;
            }
        }
    };

    void setCheckedRadioButton() {

    }

    void saveInSearch() {
        sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_TEXT, inSearch);
        editor.apply();
    }

    void saveChecked() {
        sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //KEY_SAVED_RADIO_BUTTON_INDEX = rgSetting.getCheckedRadioButtonId();
        editor.putInt(String.valueOf(SAVED_INT), KEY_SAVED_RADIO_BUTTON_INDEX);
        editor.apply();
    }

    void loadInChecked() {
//        sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
//        KEY_SAVED_RADIO_BUTTON_INDEX = sharedPreferences.getInt(String.valueOf(SAVED_INT), 0);
//        Toast.makeText(getActivity(), KEY_SAVED_RADIO_BUTTON_INDEX, Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(SAVED_INT), Context.MODE_PRIVATE);
        KEY_SAVED_RADIO_BUTTON_INDEX = sharedPreferences.getInt(String.valueOf(SAVED_INT), loadSavedCheckInt);
        Log.d("myLogs", "Look: " + loadSavedCheckInt);
    }


    @Override
    public void onPause() {
        saveInSearch();
        saveChecked();
        super.onPause();
    }
}
