package com.paulds.simpleftp.presentation.model;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.paulds.simpleftp.BR;
import com.paulds.simpleftp.R;
import com.paulds.simpleftp.data.entities.FileEntity;
import com.paulds.simpleftp.data.entities.FtpServer;
import com.paulds.simpleftp.presentation.AndroidApplication;
import com.paulds.simpleftp.presentation.activities.AddServerActivity;
import com.paulds.simpleftp.presentation.activities.ListServerActivity;
import com.paulds.simpleftp.presentation.binders.ItemBinder;

import java.util.ArrayList;
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
    private Context context;

    /**
     * The list of servers displayed.
     */
    @Bindable
    public ObservableArrayList<FtpServerViewModel> servers;

    /**
     * The selected server.
     */
    @Bindable
    public ObservableField<FtpServerViewModel> selectedServer;

    /**
     * Value indicating whether the selected server is visible.
     */
    public ObservableBoolean selectedServerVisible;

    /**
     * Default constructor.
     * @param context The context of the current activity.
     */
    public ListServerViewModel(Context context) {
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
        selectedServer.set(null);
        selectedServerVisible.set(false);
    }

    /**
     * Add a new FTP server.
     * @param view The current view.
     */
    public void addServer(View view) {
        Intent intent = new Intent(context, AddServerActivity.class);
        context.startActivity(intent);
    }
}