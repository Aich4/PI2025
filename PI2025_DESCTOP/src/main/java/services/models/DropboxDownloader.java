package models;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DropboxDownloader {

    public static void downloadFile(String dropboxPath, String localPath) {
        try {
            // Récupérer le client Dropbox (assurez-vous que DropBoxService.getClient() fonctionne correctement)
            DbxClientV2 client = DropBoxService.getClient();

            // Créer un flux de sortie vers le fichier local
            OutputStream outputStream = new FileOutputStream(localPath);

            // Télécharger le fichier depuis Dropbox
            FileMetadata metadata = client.files().download(dropboxPath).download(outputStream);

            System.out.println("Fichier téléchargé : " + metadata.getName());

            // Fermer le flux de sortie
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

