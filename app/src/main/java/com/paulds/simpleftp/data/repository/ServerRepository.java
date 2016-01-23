package com.paulds.simpleftp.data.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
     * Convert a cursor from server table to a FtpServer object.
     * @param cursor The cursor from server table.
     * @return The converted FtpServer object.
     */
    private FtpServer cursorToServer(Cursor cursor) {
        FtpServer server = new FtpServer();
        server.setId(cursor.getInt(0));
        server.setHost(cursor.getString(1));
        server.setIsAnonymous(cursor.getInt(2) > 0);
        server.setLogin(cursor.getString(3));
        server.setPassword(cursor.getString(4));
        server.setPort(cursor.getInt(5));
        return server;
    }
}
