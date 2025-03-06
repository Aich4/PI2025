package services;
import models.Destination;
import java.util.ArrayList;
import java.util.List;

public class WishlistService {
    private static WishlistService instance;
    private List<Destination> wishlist = new ArrayList<>();

    private WishlistService() {}

    public static WishlistService getInstance() {
        if (instance == null) {
            instance = new WishlistService();
        }
        return instance;
    }

    public void add(Destination destination) {
        if (!wishlist.contains(destination)) {
            wishlist.add(destination);
        }
    }

    public void remove(Destination destination) {
        wishlist.remove(destination);
    }

    public List<Destination> getWishlist() {
        return wishlist;
    }
}
