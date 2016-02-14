package com.paulds.simpleftp.presentation.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.databinding.FragmentExplorerBinding;
import com.paulds.simpleftp.presentation.model.ExplorerViewModel;

/**
 * The fragment which displays an explorer for local or remote files.
 *
 * @author Paul-DS
 */
public class ExplorerFragment extends Fragment {
    /**
     * Method called at view creation.
     * @param inflater The LayoutInflater used to inflate any views in the fragment.
     * @param container The parent view.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentExplorerBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explorer, container, false);
        ExplorerViewModel viewModel = new ExplorerViewModel(this.getContext());
        binding.setModel(viewModel);
        binding.listFileRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return binding.getRoot();
    }
}
