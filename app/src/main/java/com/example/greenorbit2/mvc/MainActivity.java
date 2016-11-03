package com.example.greenorbit2.mvc;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;


public class MainActivity extends Activity {
    public static final String APP_TAG = "com.mrbool.mvc";

    private ListView lvTask;
    private Button btNewTask;
    private EditText etNewTask;

    private Controller controller;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);

        this.setContentView(R.layout.activity_main);

        this.controller = new Controller(this);

        this.lvTask = (ListView) this.findViewById(R.id.lvTask);
        this.btNewTask = (Button) this.findViewById(R.id.btNewTask);
        this.etNewTask = (EditText) this.findViewById(R.id.etNewTask);

        this.btNewTask.setOnClickListener(this.handleNewTaskEvent);

        this.populateTasks();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void populateTasks() {
        final List<String> tasks = this.controller.getTasks();

        Log.d(MainActivity.APP_TAG, String.format("%d found tasks ",
                tasks.size()));

        this.lvTask.setAdapter(new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        tasks.toArray(new String[]{})));

        this.lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent,
                                    final View view, final int position, final long id)
            {
                Log.d(MainActivity.APP_TAG, String.format("task id: %d and position: %d", id, position));

                final TextView v = (TextView) view;

                MainActivity.this.controller.deleteTask
                        (v.getText().toString());


                MainActivity.this.populateTasks();
            }
        });
    }

    private final View.OnClickListener handleNewTaskEvent =
            new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    Log.d(APP_TAG, "New Task button added");

                    MainActivity.this.controller.addTask(MainActivity.this.etNewTask.getText().toString());

                    MainActivity.this.populateTasks();
                    etNewTask.setText("");
                }
            };


    @Override
    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    private class APP_TAG {
    }

}



