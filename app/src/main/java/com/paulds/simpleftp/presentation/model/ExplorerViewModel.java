package com.paulds.simpleftp.presentation.model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Observable;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.paulds.simpleftp.BR;
import com.paulds.simpleftp.R;
import com.paulds.simpleftp.data.entities.FileEntity;
import com.paulds.simpleftp.data.entities.FtpServer;
import com.paulds.simpleftp.presentation.AndroidApplication;
import com.paulds.simpleftp.presentation.activities.EditServerActivity;
import com.paulds.simpleftp.presentation.activities.ListServerActivity;
import com.paulds.simpleftp.presentation.binders.ItemBinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

/**
 * Model for displaying an explorer.
 *
 * @author Paul-DS
 */
public class ExplorerViewModel extends BaseObservable {
    private final static int KEY_ADD_NEW_FAVORITE = -1;

    /**
     * The activity context.
     */
    private Context context;

    /**
     * The current path displayed.
     */
    private String path;

    /**
     * The current server displayed.
     */
    private FtpServer server;

    /**
     * Indicates whether a loading is ongoing.
     */
    public ObservableBoolean isLoading;

    /**
     * The list of files displayed.
     */
    @Bindable
    public ObservableArrayList<FileViewModel> files;

    /**
     * Indicates whether the view is in selection mode.
     */
    public ObservableBoolean isSelectionMode;

    /**
     * The number of selected items
     */
    public ObservableInt numberSelectedItems;

    /**
     * Default constructor.
     * @param context The context of the current activity.
     */
    public ExplorerViewModel(Context context) {
        this.context = context;
        this.isLoading = new ObservableBoolean(false);
        this.files = new ObservableArrayList<FileViewModel>();
        this.isSelectionMode = new ObservableBoolean(false);
        this.numberSelectedItems = new ObservableInt(0);
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

    @Bindable
    public String getTitle() {
        return server != null ? server.getName() : "Local";
    }

    /**
     * Update the current path and refresh the files list.
     * @param path The new path.
     */
    public void changeDirectory(final String path) {
        files.clear();
        this.path = path;

        isLoading.set(true);

        final Handler handler = new Handler();
        final ExplorerViewModel instance = this;

        Thread loadingThread = new Thread() {
            @Override
            public void run() {
                List<FileEntity> newFiles = null;

                try {
                    newFiles = server != null
                            ? AndroidApplication.getRepository().getFtpRepository().listFiles(server, path)
                            : AndroidApplication.getRepository().getFileRepository().listFiles(path);
                } catch (FTPException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FTPIllegalReplyException e) {
                    e.printStackTrace();
                } catch (FTPAbortedException e) {
                    e.printStackTrace();
                } catch (FTPDataTransferException e) {
                    e.printStackTrace();
                } catch (FTPListParseException e) {
                    e.printStackTrace();
                }

                final List<FileViewModel> newList = new ArrayList<FileViewModel>();

                if (path != null && !path.equals("/")) {
                    FileViewModel viewModel = new FileViewModel(instance);
                    viewModel.setName("..");
                    viewModel.setPath(path.substring(0, path.lastIndexOf("/") + 1));

                    newList.add(viewModel);
                }

                if (newFiles != null) {
                    for (FileEntity f : newFiles) {
                        newList.add(new FileViewModel(instance, f));
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        files.addAll(newList);
                        isLoading.set(false);
                    }
                });
            }
        };

        loadingThread.start();
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

        builder.setMessage(R.string.dialog_create_folder_message);

        final EditText input = new EditText(this.context);

        builder.setView(input);

        builder.setPositiveButton(R.string.dialog_create_folder_positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AndroidApplication.getRepository().getFileRepository().createFolder(path, input.getText().toString());
                changeDirectory(path);
            }
        });

        builder.setNegativeButton(R.string.dialog_create_folder_negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Shows the server list.
     * @param view The current view.
     */
    public void showServers(View view) {
        PopupMenu popupMenu = new PopupMenu(this.context, view) {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                if(item.getItemId() > 0) {
                    server = AndroidApplication.getRepository().getServerRepository().getServer(item.getItemId());
                }
                else if(item.getItemId() == KEY_ADD_NEW_FAVORITE) {
                    Intent intent = new Intent(context, EditServerActivity.class);
                    context.startActivity(intent);
                }
                else {
                    server = null;
                }

                notifyPropertyChanged(BR.title);
                changeDirectory("/");
                return true;
            }
        };

        List<FtpServer> servers = AndroidApplication.getRepository().getServerRepository().getServers();

        popupMenu.getMenu().add(0, 0, 0, "Local");

        for (FtpServer server: servers) {
            popupMenu.getMenu().add(0, server.getId(), 0, server.getName());
        }

        popupMenu.getMenu().add(0, KEY_ADD_NEW_FAVORITE, 0, "Add new favorite...");

        popupMenu.show();
    }

    /**
     * Refresh the number of selected items.
     */
    public void refreshSelectedItems() {
        int total = 0;

        for (FileViewModel file: files) {
            if(file.isSelected.get()) {
                total++;
            }
        }

        this.numberSelectedItems.set(total);
    }

    /**
     * Clear the selection.
     * @param view The current view.
     */
    public void clearSelection(View view) {
        for (FileViewModel file: files) {
            file.isSelected.set(false);
        }

        this.isSelectionMode.set(false);
    }
}
