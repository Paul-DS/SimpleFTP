package com.paulds.simpleftp.presentation.model;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.paulds.simpleftp.BR;
import com.paulds.simpleftp.R;
import com.paulds.simpleftp.data.entities.FtpServer;
import com.paulds.simpleftp.presentation.AndroidApplication;
import com.paulds.simpleftp.presentation.activities.EditServerActivity;
import com.paulds.simpleftp.presentation.activities.ListServerActivity;
import com.paulds.simpleftp.presentation.binders.ItemBinder;

import java.util.List;

/**
 * Model for displaying a list of servers.
 *
 * @author Paul-DS
 */
public class ListServerViewModel extends BaseObservable {
    /**
     * The activity context.
     */
    private Activity context;

    /**
     * The list of servers displayed.
     */
    @Bindable
    public ObservableArrayList<FtpServerViewModel> servers;

    /**
     * The selected server.
     */
    public ObservableField<FtpServerViewModel> selectedServer;

    /**
     * Value indicating whether the selected server is visible.
     */
    public ObservableBoolean selectedServerVisible;

    /**
     * Default constructor.
     * @param context The context of the current activity.
     */
    public ListServerViewModel(Activity context) {
        this.context = context;
        this.servers = new ObservableArrayList<FtpServerViewModel>();
        this.selectedServer = new ObservableField<FtpServerViewModel>();
        this.selectedServerVisible = new ObservableBoolean();
    }

    /**
     * Gets the item binder used to display files.
     * @return The item binder used to display files.
     */
    public ItemBinder<FtpServerViewModel> itemViewBinder()
    {
        return new ItemBinder<FtpServerViewModel>(BR.server, R.layout.row_ftp_server);
    }

    /**
     * Refresh the data source of the server list view.
     */
    public void updateList() {
        this.servers.clear();

        List<FtpServer> serverEntities = AndroidApplication.getRepository().getServerRepository().getServers();

        if(servers != null) {
            for (FtpServer server : serverEntities) {
                FtpServerViewModel viewModel = new FtpServerViewModel(this);
                viewModel.setId(server.getId());
                viewModel.setName(server.getName());
                viewModel.setHost(server.getHost());
                viewModel.setPort(server.getPort());
                viewModel.setIsAnonymous(server.isAnonymous());
                viewModel.setLogin(server.getLogin());
                this.servers.add(viewModel);
            }
        }
    }

    /**
     * Called when a server is selected.
     * @param model The view model corresponding to the selected server.
     */
    public void consultServer(FtpServerViewModel model) {
        selectedServer.set(model);
        selectedServerVisible.set(true);
    }

    /**
     * Close the consult window.
     * @param view The current view.
     */
    public void closeServer(View view) {
        selectedServerVisible.set(false);
    }

    /**
     * Delete the selected server.
     * @param view The current view.
     */
    public void deleteServer(View view) {
        if(selectedServer.get() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

            builder.setMessage(R.string.dialog_delete_server_message);

            builder.setPositiveButton(R.string.dialog_delete_server_positive_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AndroidApplication.getRepository().getServerRepository().deleteServer(selectedServer.get().getId());
                    selectedServerVisible.set(false);
                    updateList();
                }
            });

            builder.setNegativeButton(R.string.dialog_delete_server_negative_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    /**
     * Edit a FTP server.
     * @param view The current view.
     */
    public void editServer(View view) {
        if(selectedServer.get() != null) {
            Intent intent = new Intent(context, EditServerActivity.class);

            Bundle bundle = new Bundle();
            bundle.putInt("serverId", selectedServer.get().getId());
            intent.putExtras(bundle);

            context.startActivityForResult(intent, ListServerActivity.KEY_EDIT_SERVER);
        }
    }

    /**
     * Add a new FTP server.
     * @param view The current view.
     */
    public void addServer(View view) {
        Intent intent = new Intent(context, EditServerActivity.class);
        context.startActivity(intent);
    }
}