package com.paulds.simpleftp.presentation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import com.paulds.simpleftp.R;
import com.paulds.simpleftp.data.entities.FtpServer;
import com.paulds.simpleftp.presentation.AndroidApplication;

/**
 * Activity which allows to add a new FTP server.
 *
 * @author Paul-DS
 */
public class AddServerActivity extends AppCompatActivity {

    /**
     * Method called at activity creation.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_server);

        ImageButton ibCreate = (ImageButton) findViewById(R.id.AddServer_ibCreate);
        final EditText etName = (EditText) findViewById(R.id.AddServer_etName);
        final EditText etHost = (EditText) findViewById(R.id.AddServer_etHost);
        final EditText etPort = (EditText) findViewById(R.id.AddServer_etPort);
        final Switch swAnonymous = (Switch) findViewById(R.id.AddServer_swAnonymous);
        final EditText etLogin = (EditText) findViewById(R.id.AddServer_etLogin);
        final EditText etPassword = (EditText) findViewById(R.id.AddServer_etPassword);

        ibCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;

                if(etName.getText().toString().isEmpty()) {
                    etName.setError("Please fill a name for this server.");
                    error = true;
                }

                if(etHost.getText().toString().isEmpty()) {
                    etHost.setError("Please fill the server host.");
                    error = true;
                }

                if(!swAnonymous.isChecked()) {
                    if(etLogin.getText().toString().isEmpty()) {
                        etLogin.setError("Please fill the login for FTP connection.");
                        error = true;
                    }

                    if(etPassword.getText().toString().isEmpty()) {
                        etPassword.setError("Please fill the password for FTP connection.");
                        error = true;
                    }
                }

                if(error) return;

                FtpServer server = new FtpServer();

                server.setName(etName.getText().toString());
                server.setHost(etHost.getText().toString());
                server.setPort(etPort.getText().toString() != null
                        ? Integer.parseInt(etPort.getText().toString())
                        : 21);
                server.setIsAnonymous(swAnonymous.isChecked());
                server.setLogin(etLogin.getText().toString());
                server.setPassword(etPassword.getText().toString());

                AndroidApplication.getRepository().getServerRepository().addServer(server);

                AddServerActivity.this.finish();
            }
        });
    }
}
