package com.paulds.simpleftp.presentation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.data.entities.FtpServer;
import com.paulds.simpleftp.presentation.AndroidApplication;
import com.paulds.simpleftp.presentation.adapters.FtpServerListAdapter;
import com.paulds.simpleftp.presentation.model.FtpServerViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_server);
        ListView lvServers = (ListView)findViewById(R.id.lvServers);

        List<FtpServer> servers = AndroidApplication.getRepository().getServerRepository().getServers();
        List<FtpServerViewModel> viewModels = new ArrayList<FtpServerViewModel>();

        if(servers != null) {
            for (FtpServer server : servers) {
                FtpServerViewModel viewModel = new FtpServerViewModel();
                viewModel.setName(server.getName());
                viewModels.add(viewModel);
            }
        }

        FtpServerListAdapter adapter = new FtpServerListAdapter(this, viewModels);

        lvServers.setAdapter(adapter);

    }
}
