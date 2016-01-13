package com.paulds.simpleftp.presentation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.entities.FtpServer;
import com.paulds.simpleftp.presentation.AndroidApplication;

import java.util.List;

public class ListServer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_server);

        List<FtpServer> servers = AndroidApplication.getRepository().getServerRepository().getServers();

    }
}
