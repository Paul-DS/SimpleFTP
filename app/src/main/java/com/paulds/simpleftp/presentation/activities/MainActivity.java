package com.paulds.simpleftp.presentation.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.databinding.ActivityMainBinding;
import com.paulds.simpleftp.presentation.model.MainViewModel;

/**
 * The main activity, which displays 2 explorers
 *
 * @author Paul-DS
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Method called at activity creation.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel viewModel = new MainViewModel(this);
        binding.setModel(viewModel);
    }
}
