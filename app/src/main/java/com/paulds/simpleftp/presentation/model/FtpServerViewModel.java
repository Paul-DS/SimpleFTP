package com.paulds.simpleftp.presentation.model;

import android.view.View;

/**
 * Model for displaying a FTP server in a view.
 *
 * @author Paul-DS
 */
public class FtpServerViewModel {
    /**
     * The main view model
     */
    private ListServerViewModel mainViewModel;

    /**
     * The identifier of the FTP server.
     */
    private int id;

    /**
     * The named used to display the FTP server.
     */
    private String name;

    /**
     * The FTP server host name.
     */
    private String host;

    /**
     * A value indicating whether a login/password must be used for FTP connection.
     */
    private boolean isAnonymous;

    /**
     * The login used for FTP connection.
     */
    private String login;

    /**
     * The password used for FTP connection.
     */
    private String password;

    /**
     * The port used for FTP connection.
     */
    private int port;

    /**
     * Default constructor.
     * @param mainViewModel The parent view model.
     */
    public FtpServerViewModel(ListServerViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    /**
     * Gets the identifier of the FTP server.
     * @return The server identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the identifier of the FTP server.
     * @param id The server identifier.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name used to display the FTP server.
     * @return The server name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name used to display the FTP server.
     * @param name The server name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the FTP server host name.
     * @return The FTP server host name.
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the FTP server host name.
     * @param host The FTP server host name.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Gets a value indicating whether a login/password must be used for FTP connection.
     * @return A value indicating whether a login/password must be used for FTP connection.
     */
    public boolean isAnonymous() {
        return isAnonymous;
    }

    /**
     * Sets a value indicating whether a login/password must be used for FTP connection.
     * param isAnonymous A value indicating whether a login/password must be used for FTP connection.
     */
    public void setIsAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    /**
     * Gets the login used for FTP connection.
     * @return The login used for FTP connection.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login used for FTP connection.
     * @param login The login used for FTP connection.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the login to displayed.
     * @return The login to displayed.
     */
    public String getDisplayLogin() {
        return this.isAnonymous ? "Anonymous" : this.login;
    }

    /**
     * Gets the password used for FTP connection.
     * @return The password used for FTP connection.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password used for FTP connection.
     * @param password The password used for FTP connection.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the port used for FTP connection.
     * @return The port used for FTP connection.
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the port used for FTP connection.
     * @param port The port used for FTP connection.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Called when the file item is clicked.
     * @param view The current view.
     */
    public void onItemClick(View view) {
        this.mainViewModel.consultServer(this);
    }
}
