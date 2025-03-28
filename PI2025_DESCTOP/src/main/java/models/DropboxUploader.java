package models;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import java.io.FileInputStream;
import java.io.InputStream;

public class DropboxUploader {

    public static void uploadFile(String filePath, String s) {
        try {
            DbxClientV2 client = DropBoxService.getClient();
            InputStream in = new FileInputStream(filePath);
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);

            // Convert Windows backslashes to forward slashes (if necessary)
            fileName = fileName.replace("\\", "/");

            FileMetadata metadata = client.files().uploadBuilder("/" + fileName)
                    .uploadAndFinish(in);

            System.out.println("Fichier ajouté : " + metadata.getPathDisplay());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
