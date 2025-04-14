package models;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteResult;

public class DropboxDeleter {

    public static void deleteFile(String filePath) {
        try {
            DbxClientV2 client = DropBoxService.getClient();
            DeleteResult result = client.files().deleteV2(filePath);

            System.out.println("Fichier supprimé : " + result.getMetadata().getPathDisplay());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
