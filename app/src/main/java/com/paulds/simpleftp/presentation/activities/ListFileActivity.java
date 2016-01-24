package com.paulds.simpleftp.presentation.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.presentation.AndroidApplication;
import com.paulds.simpleftp.presentation.adapters.FileListAdapter;
import com.paulds.simpleftp.presentation.model.FileViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity which displays a list of files.
 *
 * @author Paul-DS
 */
public class ListFileActivity extends AppCompatActivity {

    /**
     * The path of the current folder displayed.
     */
    String currentPath;

    /**
     * The button to access the server list.
     */
    ImageButton ibServers;

    /**
     * The list view which contains the files.
     */
    ListView lvFiles;

    /**
     * The adapter used to display the file view models
     */
    FileListAdapter filesAdapter;

    /**
     * The floating button used to add folder.
     */
    FloatingActionButton fabPlus;

    /**
     * Method called at activity creation.
     * @param bundle The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_file);
        ibServers = (ImageButton) findViewById(R.id.ibServers);
        lvFiles = (ListView) findViewById(R.id.lvFiles);
        fabPlus = (FloatingActionButton) findViewById(R.id.fabPlus);

        filesAdapter = new FileListAdapter(ListFileActivity.this, new ArrayList<FileViewModel>());
        this.updateList("/");
        lvFiles.setAdapter(filesAdapter);

        ibServers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListFileActivity.this, ListServerActivity.class);
                ListFileActivity.this.startActivity(intent);
            }
        });

        lvFiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListFileActivity.this, "Click", Toast.LENGTH_LONG).show();
                ListFileActivity.this.updateList(((FileViewModel) parent.getItemAtPosition(position)).getFilepath());
            }
        });

        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListFileActivity.this);

                builder.setTitle(R.string.dialog_create_folder_title);

                final EditText input = new EditText(ListFileActivity.this);

                builder.setView(input);

                builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AndroidApplication.getRepository().getFileRepository().createFolder(ListFileActivity.this.currentPath, input.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    /**
     * Update the list view with a new path.
     * @param path The new path.
     */
    private void updateList(String path)
    {
        this.currentPath = path;
        filesAdapter.clear();

        File[] files = AndroidApplication.getRepository().getFileRepository().listFiles(path);

        List<FileViewModel> viewModels = new ArrayList<FileViewModel>();

        if(path != "/") {
            FileViewModel viewModel = new FileViewModel();
            viewModel.setFilename("...");
            viewModel.setFilepath(path.substring(0, path.lastIndexOf("/") + 1));

            viewModels.add(viewModel);
        }

        if(files != null) {
            for (File f : files) {
                FileViewModel viewModel = new FileViewModel();
                viewModel.setFilename(f.getName());
                viewModel.setFilepath(f.getPath());

                if(!f.isDirectory()) {
                    viewModel.setSize(f.length());
                }

                viewModels.add(viewModel);
            }
        }

        filesAdapter.addAll(viewModels);
        filesAdapter.notifyDataSetChanged();
    }
}
