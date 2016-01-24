package com.paulds.simpleftp.presentation.adapters;

/**
 * Created by Paul on 24/01/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.presentation.model.FileViewModel;
import com.paulds.simpleftp.presentation.model.FtpServerViewModel;
import com.paulds.simpleftp.presentation.viewholders.FileViewHolder;
import com.paulds.simpleftp.presentation.viewholders.FtpServerViewHolder;

import java.util.List;

/**
 * Adapter for displaying a list file view models in a list view.
 *
 * @author Paul-DS
 */
public class FtpServerListAdapter extends ArrayAdapter<FtpServerViewModel> {

    /**
     * Default Constructor.
     * @param context The activity context.
     * @param files A list of FTP server view models to display.
     */
    public FtpServerListAdapter(Context context, List<FtpServerViewModel> files) {
        super(context, 0, files);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_file, parent, false);
        }

        FtpServerViewHolder viewHolder = (FtpServerViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new FtpServerViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.servername);
            convertView.setTag(viewHolder);
        }

        FtpServerViewModel file = getItem(position);
        viewHolder.name.setText(file.getName());

        return convertView;
    }
}
