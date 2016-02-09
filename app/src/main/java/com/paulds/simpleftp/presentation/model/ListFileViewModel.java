package com.paulds.simpleftp.presentation.model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.paulds.simpleftp.BR;
import com.paulds.simpleftp.R;
import com.paulds.simpleftp.data.entities.FileEntity;
import com.paulds.simpleftp.presentation.AndroidApplication;
import com.paulds.simpleftp.presentation.activities.ListServerActivity;
import com.paulds.simpleftp.presentation.binders.ItemBinder;

import java.util.List;

/**
 * Model for displaying a list of files.
 *
 * @author Paul-DS
 */
public class ListFileViewModel extends BaseObservable {
    /**
     * The activity context.
     */
    private Context context;

    /**
     * The current path displayed.
     */
    private String path;

    /**
     * The list of files displayed.
     */
    @Bindable
    public ObservableArrayList<FileViewModel> files;

    /**
     * Default constructor.
     * @param context The context of the current activity.
     */
    public ListFileViewModel(Context context) {
        this.context = context;
        this.files = new ObservableArrayList<FileViewModel>();
        this.changeDirectory("/");
    }

    /**
     * Gets the item binder used to display files.
     * @return The item binder used to display files.
     */
    public ItemBinder<FileViewModel> itemViewBinder()
    {
        return new ItemBinder<FileViewModel>(BR.file, R.layout.row_file);
    }

    /**
     * Update the current path and refresh the files list.
     * @param path The new path.
     */
    public void changeDirectory(String path) {
        List<FileEntity> newFiles = AndroidApplication.getRepository().getFileRepository().listFiles(path);

        files.clear();

        if (!path.equals("/")) {
            FileViewModel viewModel = new FileViewModel(this);
            viewModel.setName("...");
            viewModel.setFilepath(path.substring(0, path.lastIndexOf("/") + 1));

            this.files.add(viewModel);
        }

        if (newFiles != null) {
            for (FileEntity f : newFiles) {
                FileViewModel viewModel = new FileViewModel(this);
                viewModel.setName(f.getName());
                viewModel.setFilepath(f.getPath());

                if (!f.isDirectory()) {
                    viewModel.setSize(f.getSize());
                }

                this.files.add(viewModel);
            }
        }
    }

    /**
     * Called when a file is selected.
     * @param model The view model corresponding to the selected file.
     */
    public void selectFile(FileViewModel model) {
        this.changeDirectory(model.getPath());
    }

    /**
     * Add a new folder
     * @param view The current view.
     */
    public void addFolder(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder.setTitle(R.string.dialog_create_folder_title);

        final EditText input = new EditText(this.context);
        final String currentPath = this.path;

        builder.setView(input);

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AndroidApplication.getRepository().getFileRepository().createFolder(currentPath, input.getText().toString());
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

    /**
     * Open the server list activity.
     * @param view The current view
     */
    public void openParameters(View view) {
        Intent intent = new Intent(context, ListServerActivity.class);
        context.startActivity(intent);
    }
}
