package com.paulds.simpleftp.entities;

/**
 * Represent a FTP server object, stored in database.
 *
 * @author Paul-DS
 */
public class FtpServer extends DatabaseEntity {
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
     * Gets the FTP server host name.
     * @return The FTP server host name.
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets the FTP server host name.
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
}
