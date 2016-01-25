package com.paulds.simpleftp.presentation.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.data.entities.FtpServer;
import com.paulds.simpleftp.presentation.AndroidApplication;
import com.paulds.simpleftp.presentation.adapters.FileListAdapter;
import com.paulds.simpleftp.presentation.adapters.FtpServerListAdapter;
import com.paulds.simpleftp.presentation.model.FtpServerViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity which displays the list of FTP servers.
 *
 * @author Paul-DS
 */
public class ListServerActivity extends AppCompatActivity {

    /**
     * The list view which contains the servers.
     */
    ListView lvServers;

    /**
     * The adapter used to display the server view models
     */
    FtpServerListAdapter serversAdapter;

    /**
     * The floating button used to add new server.
     */
    FloatingActionButton fabAddServer;

    /**
     * Method called at activity creation.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_server);
        lvServers = (ListView)findViewById(R.id.lvServers);
        fabAddServer = (FloatingActionButton)findViewById(R.id.fabAddServer);

        fabAddServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListServerActivity.this, AddServerActivity.class);
                ListServerActivity.this.startActivity(intent);
            }
        });

        serversAdapter = new FtpServerListAdapter(this, new ArrayList<FtpServerViewModel>());
        lvServers.setAdapter(serversAdapter);
    }

    /**
     * Method called when resuming activity.
     */
    @Override
    protected void onResume() {
        super.onResume();

        this.updateList();
    }

    /**
     * Refresh the data source of the server list view.
     */
    private void updateList() {
        serversAdapter.clear();

        List<FtpServer> servers = AndroidApplication.getRepository().getServerRepository().getServers();
        List<FtpServerViewModel> viewModels = new ArrayList<FtpServerViewModel>();

        if(servers != null) {
            for (FtpServer server : servers) {
                FtpServerViewModel viewModel = new FtpServerViewModel();
                viewModel.setName(server.getName());
                viewModels.add(viewModel);
            }
        }

        serversAdapter.addAll(viewModels);
        serversAdapter.notifyDataSetChanged();
    }
}
