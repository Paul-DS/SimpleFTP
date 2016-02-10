package com.paulds.simpleftp.presentation.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.data.entities.FtpServer;
import com.paulds.simpleftp.databinding.ActivityAddServerBinding;
import com.paulds.simpleftp.databinding.ActivityListFileBinding;
import com.paulds.simpleftp.presentation.AndroidApplication;
import com.paulds.simpleftp.presentation.model.AddServerViewModel;
import com.paulds.simpleftp.presentation.model.ListFileViewModel;

/**
 * Activity which allows to add a new FTP server.
 *
 * @author Paul-DS
 */
public class AddServerActivity extends AppCompatActivity {

    /**
     * Method called at activity creation.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAddServerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_server);
        AddServerViewModel viewModel = new AddServerViewModel(this);
        binding.setModel(viewModel);
    }
}
