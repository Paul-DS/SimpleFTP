package com.paulds.simpleftp.data.repository;

import android.os.Environment;

import com.paulds.simpleftp.data.entities.FileEntity;
import com.paulds.simpleftp.data.entities.FtpServer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

/**
 * This class provide an access to remote files.
 *
 * @author Paul-DS
 */
public class FtpRepository {
    /**
     * File containing open FTP connections.
     */
    private Hashtable<Integer, FTPClient> openConnections = new Hashtable<Integer, FTPClient>();

    /**
     * Lists the files present at the specified path.
     * @param path The specified path.
     * @return The list of files.
     */
    public List<FileEntity> listFiles(FtpServer server, String path) throws FTPException, IOException, FTPIllegalReplyException, FTPAbortedException, FTPDataTransferException, FTPListParseException {
        FTPClient client = this.getConnection(server);

        if(path != null && !path.equals("/")) {
            client.changeDirectory(path);
        }

        FTPFile[] list = client.list();

        List<FileEntity> results = new ArrayList<FileEntity>();

        if(list != null) {
            for (FTPFile file : list) {
                results.add(this.ftpFileToEntity(file, path));
            }
        }

        return results;
    }


    /**
     * Create a folder at the specified path.
     * @param path The specified path.
     * @param name The name of the folder to create.
     */
    public void createFolder(String path, String name) {
        String fullPath = Environment.getExternalStorageDirectory().toString() + path + "/" + name;

        File folder = new File(fullPath);

        if(!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Delete a list of files and directories.
     * @param server The FTP server.
     * @param elements The specified list.
     */
    public void deleteFiles(FtpServer server, List<FileEntity> elements) throws FTPException, IOException, FTPIllegalReplyException {
        FTPClient client = this.getConnection(server);

        for (FileEntity element: elements) {
            if(element.isDirectory()) {
                client.deleteDirectory(element.getPath());
            }
            else {
                client.deleteFile(element.getPath());
            }
        }
    }

    /**
     * Gets a FTP connection for a given server.
     * @param server The server to connect.
     * @return The FTP connection.
     */
    private FTPClient getConnection(FtpServer server) throws FTPException, IOException, FTPIllegalReplyException {
        FTPClient client = openConnections.get(server.getId());

        if(client == null)
        {
            client = new FTPClient();
            openConnections.put(server.getId(), client);
        }

        if(!client.isConnected()) {
            client.connect(server.getHost(), server.getPort());
        }

        if(!client.isAuthenticated()) {
            client.login(server.isAnonymous() ? "anonymous" : server.getLogin(), server.getPassword());
        }

        return client;
    }

    /**
     * Convert a FTP file to a file entity.
     * @param file The FTP file to convert.
     * @param currentPath The path where the file has been found.
     * @return The converted file entity.
     */
    private FileEntity ftpFileToEntity(FTPFile file, String currentPath)
    {
        FileEntity entity = new FileEntity();
        entity.setPath(currentPath.endsWith("/")
                ? currentPath + file.getName()
                : currentPath + "/" + file.getName());

        entity.setName(file.getName());
        entity.setSize(file.getSize());
        entity.setIsDirectory(file.getType() == FTPFile.TYPE_DIRECTORY);
        return entity;
    }
}