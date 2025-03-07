package models;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;

public class DropboxSharer {
    public static void shareFile(String filePath) {
        try {
            DbxClientV2 client = DropBoxService.getClient();
            SharedLinkMetadata sharedLink = client.sharing().createSharedLinkWithSettings(filePath);

            System.out.println("Lien de partage : " + sharedLink.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
