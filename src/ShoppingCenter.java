import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingCenter {
    public static int nextId = 0;

    public int id;
    private String name;
    public List<Shop> shops;

    ShoppingCenter(String name) {
        this.id = ShoppingCenter.nextId++;
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

    public List<Product> getAllProducts() {
        List<Product> allProducts = new LinkedList<>();
        for (Shop shop : shops) {
            for (ProductDetails productDetails : shop.products) {
                allProducts.add(productDetails.product);
            }
        }
        return allProducts;
    }

    public List<Service> getAllServices() {
        List<Service> allServices = new LinkedList<>();
        for (Shop shop : shops) {
            allServices.add((Service) shop.services);
        }
        return allServices;
    }

    public List<String> getAllShopTypes() {
        List<String> allShopTypes = new LinkedList<>();
        for (Shop shop : shops) {
            allShopTypes.addAll(shop.types);
        }
        return allShopTypes;
    }

    public Set<String> getAllProductTypes() {
        return this.getAllProducts().stream()
                .map(product -> product.type)
                .collect(Collectors.toSet());

//        Set<String> allProductTypes = new HashSet<>();
//        for (Shop shop : shops) {
//            for (ProductDetails productDetails : shop.products) {
//                allProductTypes.add(productDetails.product.type);
//            }
//        }
//
//        return allProductTypes;
//
//        List<String> result = new LinkedList<>();
//        shops.stream()
//                .distinct(type -> shops.??????????)
//                .collect(Collectors.toList());
//

//        return this.getAllProducts().stream()
//                .map(product -> product.type)
//                .collect(Collectors.toList());
    }

}