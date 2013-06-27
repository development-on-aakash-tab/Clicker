/*
 * It is used for Remote Mode.
 * Before conducting any Quiz it copy all responses to Archived Folder.
 */

package com.clicker.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoteXML {
	/**
     * To make Archive of all Quiz Remote Center XML Files
     */
    public void makeClickerRemoteResponseArchive() {
        String user_home = System.getProperty("user.home");
        String ReceivedFolderName = user_home + "/ClickerRemoteFiles/Received";

        File LocalDir = new File(ReceivedFolderName);

        if (LocalDir.exists()) {
            File[] LocalXMLFiles = LocalDir.listFiles();

            String ArchivedFolderName = user_home + "/ClickerRemoteFiles/Archived";

            File LocalDirArchive = new File(ArchivedFolderName);
            if (!LocalDirArchive.exists()) {
                LocalDirArchive.mkdir();
            }

            for (int i = 0; i < LocalXMLFiles.length; i++) {
                try {
                    InputStream in = new FileInputStream(LocalXMLFiles[i]);

                    File LocalXMLFileArchive = new File(LocalDirArchive + "/" + LocalXMLFiles[i].getName());

                    System.out.println("Done");

                    OutputStream out = new FileOutputStream(LocalXMLFileArchive);

                    byte[] buf = new byte[20480];

                    int len;

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();
                    LocalXMLFiles[i].delete();

                } catch (IOException ex) {
                    Logger.getLogger(RemoteXML.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
