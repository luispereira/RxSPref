package com.lib.rxspref;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SampleApplication.getInstance().getPref().write("valueFloat", 2).subscribe();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                SampleApplication.getInstance().getPref().write("value", "This is a value")
                        .doOnNext(aBoolean -> {
                            if (aBoolean) {
                                SampleApplication.getInstance().getPref().retrieve("value")
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(value -> Snackbar.make(view, "The saved value is [" + value + "]", Snackbar.LENGTH_LONG).setAction("Action", null).show());
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(Schedulers.io())
                        .subscribe());

        findViewById(R.id.buttonFloat).setOnClickListener(v -> {
            SampleApplication.getInstance().getPref().retrieveAsInt("valueFloat").doOnNext(fValue -> Toast.makeText(this, "Value is [" + fValue  + "]", Toast.LENGTH_SHORT).show())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.io())
                    .subscribe();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
