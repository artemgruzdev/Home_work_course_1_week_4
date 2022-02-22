package ru.startandroid.homeworkcourse1week4;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FragmentStart fragmentStart;

    // TEST GIT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentStart = new FragmentStart();

        if (getFragmentManager().findFragmentByTag(FragmentStart.TAG) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_fragment, new FragmentStart(), FragmentStart.TAG).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint({"NonConstantResourceId", "CommitTransaction"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.menu_setting:
                Toast.makeText(this, Settings.SETTING, Toast.LENGTH_LONG).show();
                fragmentTransaction.replace(R.id.frame_layout_fragment, new FragmentSetting());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.menu_search:
                Toast.makeText(this, Settings.SEARCH, Toast.LENGTH_LONG).show();
                fragmentTransaction.replace(R.id.frame_layout_fragment, new FragmentSearch());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.menu_exit:
                Toast.makeText(this, Settings.EXIT, Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}