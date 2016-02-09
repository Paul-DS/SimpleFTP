package com.paulds.simpleftp.presentation.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.databinding.ActivityListServerBinding;
import com.paulds.simpleftp.presentation.model.FtpServerViewModel;
import com.paulds.simpleftp.presentation.model.ListServerViewModel;

/**
 * Activity which displays the list of FTP servers.
 *
 * @author Paul-DS
 */
public class ListServerActivity extends AppCompatActivity {

    /**
     * The activity binding.
     */
    ActivityListServerBinding binding;

    /**
     * The current view model.
     */
    ListServerViewModel viewModel;

    /**
     * Method called at activity creation.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_server);
        viewModel = new ListServerViewModel(this);
        binding.setModel(viewModel);
        binding.listFileRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Method called when resuming activity.
     */
    @Override
    protected void onResume() {
        super.onResume();

        this.viewModel.updateList();
    }

    /**
     * Method called when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        if(viewModel.selectedServerVisible.get()) {
            viewModel.closeServer(null);
        }
        else {
            super.onBackPressed();
        }
    }
}
