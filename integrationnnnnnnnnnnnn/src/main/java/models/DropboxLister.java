package models;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;


public class DropboxLister {

    public static void listFiles() {
        try {
            DbxClientV2 client = DropBoxService.getClient();
            ListFolderResult result = client.files().listFolder("");

            for (Metadata metadata : result.getEntries()) {
                System.out.println("Fichier : " + metadata.getPathLower());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
