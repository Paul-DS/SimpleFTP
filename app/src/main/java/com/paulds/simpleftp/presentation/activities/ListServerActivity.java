package com.paulds.simpleftp.presentation.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.data.entities.FtpServer;
import com.paulds.simpleftp.presentation.AndroidApplication;
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
     * The layer for hiding main view when opening consult view.
     */
    LinearLayout llOverlay;

    /**
     * The layer which displays consult view.
     */
    LinearLayout llConsult;

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
        llOverlay = (LinearLayout) findViewById(R.id.ListServer_llOverlay);
        llConsult = (LinearLayout) findViewById(R.id.ConsultServer_llMain);

        fabAddServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListServerActivity.this, AddServerActivity.class);
                ListServerActivity.this.startActivity(intent);
            }
        });

        lvServers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            displayConsult((FtpServerViewModel)parent.getItemAtPosition(position));
            }
        });

        llOverlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideConsult();
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
     * Method called when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        if(llConsult.getVisibility() == View.VISIBLE) {
            hideConsult();
        }
        else {
            moveTaskToBack(true);
        }
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
                viewModel.setHost(server.getHost());
                viewModel.setPort(server.getPort());
                viewModel.setIsAnonymous(server.isAnonymous());
                viewModel.setLogin(server.getLogin());
                viewModels.add(viewModel);
            }
        }

        serversAdapter.addAll(viewModels);
        serversAdapter.notifyDataSetChanged();
    }

    /**
     * Display the consult view
     * @param server The server view model to display.
     */
    private void displayConsult(FtpServerViewModel server) {
        ((TextView)findViewById(R.id.ConsultServer_tvName)).setText(server.getName());
        ((TextView)findViewById(R.id.ConsultServer_tvHost)).setText(server.getHost());
        ((TextView)findViewById(R.id.ConsultServer_tvPort)).setText(String.valueOf(server.getPort()));
        findViewById(R.id.ConsultServer_cvLogin).setVisibility(server.isAnonymous() ? View.GONE : View.VISIBLE);
        ((TextView)findViewById(R.id.ConsultServer_tvLogin)).setText(server.getLogin());

        llOverlay.setVisibility(View.VISIBLE);
        llConsult.setVisibility(View.VISIBLE);

        llOverlay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
        llConsult.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
    }

    /**
     * Hides the consult view.
     */
    private void hideConsult() {

        Animation consultAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        consultAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llConsult.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Animation overlayAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        overlayAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llOverlay.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        llConsult.startAnimation(consultAnimation);
        llOverlay.startAnimation(overlayAnimation);
    }
}
