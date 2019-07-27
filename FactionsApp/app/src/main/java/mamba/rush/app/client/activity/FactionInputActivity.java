package mamba.rush.app.client.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mamba.rush.app.client.R;
import mamba.rush.app.client.api.User;

public class FactionInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faction_input);
    }

    public void returnPage(View v) {
        setContentView(R.layout.activity_main);
        User.closeConnection();
    }

}
