package com.paulds.simpleftp.presentation.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.databinding.ActivityEditServerBinding;
import com.paulds.simpleftp.presentation.model.EditServerViewModel;

/**
 * Activity which allows to add a new FTP server.
 *
 * @author Paul-DS
 */
public class EditServerActivity extends AppCompatActivity {

    /**
     * Method called at activity creation.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityEditServerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_server);
        EditServerViewModel viewModel = null;

        if(this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("serverId")) {
            viewModel = new EditServerViewModel(this, this.getIntent().getExtras().getInt("serverId"));
        }
        else {
            viewModel = new EditServerViewModel(this);
        }

        binding.setModel(viewModel);
    }
}
