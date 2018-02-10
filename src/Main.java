import java.util.LinkedList;
import java.util.List;

class Main {

    public static void main(String args[]) {

        ShoppingCenter galeriaRzeszow = new ShoppingCenter("Galeria Rzeszów");

        Shop stokrotka = new Shop("Stokrotka", 1, 102);
        stokrotka.types.add("wielobranżowy");
        stokrotka.types.add("warzywny");
        stokrotka.types.add("spożywczy");
        Product marchewka = new Product(2.50, "warzywo", "marchewka", null, 1, 7);
        Product ogorek = new Product(3.40, "warzywo", "ogórek", null, 1, 7);
        Product kapusta = new Product(1.20, "warzywo", "kapusta", null, 1, 7);
        Product banany = new Product(4.90, "owoc", "banany", null, 1, 7);
        Product mandarynki = new Product(3.20, "owoc", "mandarynki", null, 1, 7);
        Product jablka = new Product(1.90, "owoc", "jabłka", null, 1, 7);
        Product cola = new Product(2.50, "napój", "Coca Cola", null, 1, 23);
        Product woda = new Product(0.90, "napój", "Woda mineralna", null, 0.70, 7);

        Employee kowalski = new Employee("Kowalski Jan", new String[] {"kasa", "wykładanie", "fakturowanie"});
        Employee nowak = new Employee("Nowak Adam", new String[] {"kasa", "sprzątanie"});
        Employee sobieski = new Employee("Sobieski Stefan", new String[] {"fakturowanie", "manager"});

        stokrotka.addProduct(marchewka, 30);
        stokrotka.addProduct(ogorek, 20);
        stokrotka.addProduct(kapusta, 40);
        stokrotka.addProduct(banany, 20);
        stokrotka.addProduct(mandarynki, 10);
        stokrotka.addProduct(jablka, 30);
        stokrotka.addProduct(cola, 15);
        stokrotka.updateProduct();
        stokrotka.hire(kowalski);
        stokrotka.hire(nowak);
        stokrotka.hire(sobieski);


        Shop rossman = new Shop("Rossman", 2, 209);
        rossman.types.add("drogeryjny");

        Shop him = new Shop("H&M", 3, 304);
        him.types.add("odzieżowy");

        Shop orsay = new Shop("Orsay", 1, 105);
        orsay.types.add("odzieżowy");

        Shop apteczka = new Shop("Apteczka", 2, 213);
        apteczka.types.add("leczniczy");
        apteczka.types.add("drogeryjny");

        Shop kiosk = new Shop("Kolporter", 3, 301);
        kiosk.types.add("prasa");
        kiosk.types.add("spożywczy");



        galeriaRzeszow.addShop(stokrotka);
        galeriaRzeszow.getShopById(1);
        System.out.println(galeriaRzeszow.findShopByName("Stokrotka"));
        System.out.println(galeriaRzeszow.getAllProducts());
        System.out.println(galeriaRzeszow.getAllServices());
        System.out.println(galeriaRzeszow.getAllShopTypes());
        System.out.println(galeriaRzeszow.getAllProductTypes());

    }
}