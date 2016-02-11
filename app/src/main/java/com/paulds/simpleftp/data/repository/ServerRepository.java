package com.paulds.simpleftp.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.paulds.simpleftp.data.entities.FtpServer;
import com.paulds.simpleftp.data.helpers.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provide an access to servers object.
 *
 * @author Paul-DS
 */
public class ServerRepository {

    /**
     * The database.
     */
    private SQLiteDatabase database;

    /**
     * The database helper used to access database.
     */
    private DatabaseHelper dbHelper;

    /**
     * The columns name.
     */
    private String[] allColumns = {
        DatabaseHelper.COLUMN_ID,
        DatabaseHelper.COLUMN_NAME,
        DatabaseHelper.COLUMN_HOST,
        DatabaseHelper.COLUMN_ANONYMOUS,
        DatabaseHelper.COLUMN_LOGIN,
        DatabaseHelper.COLUMN_PASSWORD,
        DatabaseHelper.COLUMN_PORT
    };

    /**
     * Initialize an instance of ServerRepository
     * @param context The context used for repository initialization.
     */
    public ServerRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * Open the database connection.
     * @throws SQLException
     */
    private void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    private void close() {
        dbHelper.close();
    }

    /**
     * Gets the list of the servers stored in database.
     * @return The list of the servers stored in database.
     */
    public List<FtpServer> getServers() {
        this.open();
        List<FtpServer> results = new ArrayList<FtpServer>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_SERVER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FtpServer server = this.cursorToServer(cursor);
            results.add(server);
            cursor.moveToNext();
        }

        cursor.close();
        this.close();

        return results;
    }

    /**
     * Gets a server stored in database.
     * @param id The identifier of the server to recover.
     * @return The server corresponding to the specified id.
     */
    public FtpServer getServer(int id) {
        this.open();

        FtpServer result = null;

        Cursor cursor = database.query(DatabaseHelper.TABLE_SERVER,
                allColumns, DatabaseHelper.COLUMN_ID + "=" + id, null, null, null, null);

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            result = this.cursorToServer(cursor);
        }

        cursor.close();
        this.close();

        return result;
    }

    /**
     * Add a new FTP server.
     * @param server The server to add.
     */
    public void addServer(FtpServer server) {
        this.open();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_NAME, server.getName());
        values.put(DatabaseHelper.COLUMN_HOST, server.getHost());
        values.put(DatabaseHelper.COLUMN_PORT, server.getPort());
        values.put(DatabaseHelper.COLUMN_ANONYMOUS, server.isAnonymous());
        values.put(DatabaseHelper.COLUMN_LOGIN, server.getLogin());
        values.put(DatabaseHelper.COLUMN_PASSWORD, server.getPassword());

        database.insert(DatabaseHelper.TABLE_SERVER, null, values);
        this.close();
    }

    /**
     * Update a FTP server.
     * @param server The server to update.
     */
    public void updateServer(FtpServer server) {
        this.open();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_NAME, server.getName());
        values.put(DatabaseHelper.COLUMN_HOST, server.getHost());
        values.put(DatabaseHelper.COLUMN_PORT, server.getPort());
        values.put(DatabaseHelper.COLUMN_ANONYMOUS, server.isAnonymous());
        values.put(DatabaseHelper.COLUMN_LOGIN, server.getLogin());
        values.put(DatabaseHelper.COLUMN_PASSWORD, server.getPassword());

        database.update(DatabaseHelper.TABLE_SERVER, values, DatabaseHelper.COLUMN_ID + "=" + server.getId(), null);
        this.close();
    }

    /**
     * Delete a FTP server.
     * @param serverId The identifier of the server to delete.
     */
    public void deleteServer(int serverId) {
        this.open();
        database.delete(DatabaseHelper.TABLE_SERVER, DatabaseHelper.COLUMN_ID + "=" + serverId, null);
        this.close();
    }

    /**
     * Convert a cursor from server table to a FtpServer object.
     * @param cursor The cursor from server table.
     * @return The converted FtpServer object.
     */
    private FtpServer cursorToServer(Cursor cursor) {
        FtpServer server = new FtpServer();
        server.setId(cursor.getInt(0));
        server.setName(cursor.getString(1));
        server.setHost(cursor.getString(2));
        server.setIsAnonymous(cursor.getInt(3) > 0);
        server.setLogin(cursor.getString(4));
        server.setPassword(cursor.getString(5));
        server.setPort(cursor.getInt(6));
        return server;
    }
}
