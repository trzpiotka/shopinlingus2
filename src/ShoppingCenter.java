import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCenter {

    private String name;
    public List<Shop> shops;

    ShoppingCenter(String name) {
        this.shops = new LinkedList<Shop>();
        this.name = name;
    }

    public void addShop(Shop shop) {
        this.shops.add(shop);
    }

    public Shop getShopById(int shopId) {
//        for (Shop shop : this.shops) {
//            if (shop.id == shopId) {
//                return shop;
//            }
//        }
//        System.out.println("Nie ma sklepu o takim Id");
//        return null;

        return shops.stream()
                .filter(shop -> shop.id == shopId)
                .findFirst()
                .orElse(null);

    }

    public void updateShop(int shopId, Shop modifiedShop) {
        deleteShop(shopId);
        modifiedShop.id = shopId;
        addShop(modifiedShop);
    }

    public void deleteShop(int shopId) {
        for (Shop shop : this.shops) {
            if (shop.id == shopId) {
                shops.remove(shop);
            }
        }
        System.out.println("Nie ma sklepu o takim Id");
    }

    public List<Shop> findShopByName(String name) {
//        List<Shop> listOfFoundShops = new LinkedList<Shop>();
//        for (Shop shop : this.shops) {
//            if (shop.name.equals(name)) {
//                listOfFoundShops.add(shop);
//            }
//        }
//        return listOfFoundShops;

        return shops.stream()
                .filter(shop -> shop.name.equals(name))
                .collect(Collectors.toList());
    }

}
