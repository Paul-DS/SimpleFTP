package com.paulds.simpleftp.presentation.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.databinding.ActivityListFileBinding;
import com.paulds.simpleftp.presentation.model.ListFileViewModel;

/**
 * Activity which displays a list of files.
 *
 * @author Paul-DS
 */
public class ListFileActivity extends AppCompatActivity {

    /**
     * Method called at activity creation.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityListFileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list_file);
        ListFileViewModel viewModel = new ListFileViewModel(this);
        binding.setModel(viewModel);
        binding.listFileRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
