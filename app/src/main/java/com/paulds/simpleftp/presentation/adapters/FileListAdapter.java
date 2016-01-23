package com.paulds.simpleftp.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.presentation.model.FileViewModel;
import com.paulds.simpleftp.presentation.viewholders.FileViewHolder;

import java.io.File;
import java.util.List;

/**
 * Adapter for displaying a list file view models in a list view.
 *
 * @author Paul-DS
 */
public class FileListAdapter extends ArrayAdapter<FileViewModel> {

    /**
     * Default Constructor.
     * @param context The activity context.
     * @param files A list of file view models to display.
     */
    public FileListAdapter(Context context, List<FileViewModel> files) {
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

        FileViewHolder viewHolder = (FileViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new FileViewHolder();
            viewHolder.filename = (TextView) convertView.findViewById(R.id.filename);
            viewHolder.filesize = (TextView) convertView.findViewById(R.id.filesize);
            convertView.setTag(viewHolder);
        }

        FileViewModel file = getItem(position);
        viewHolder.filename.setText(file.getFilename());
        viewHolder.filesize.setText(file.getSize() != null ? file.getSize() / 1024 + "ko" : "");

        return convertView;
    }
}
