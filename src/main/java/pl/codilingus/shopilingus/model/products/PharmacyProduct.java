package pl.codilingus.shopilingus.model.products;

import pl.codilingus.shopilingus.model.types.ProductType;

public class PharmacyProduct extends Product {

  private static final double PHARMACY_TAX = 0.23;
  private static final double DEFAULT_NFZ_REFUND = 0.7;

  private final String latinName;
  private final double nfzRefund;

  public PharmacyProduct(String name, String latinName, double price, double nfzRefund) {
    super(name, price, ProductType.DRUG, PHARMACY_TAX);
    this.latinName = latinName;
    this.nfzRefund = nfzRefund;
  }

  public PharmacyProduct(String name, String latinName, double price) {
    this(name, latinName, price, DEFAULT_NFZ_REFUND);
  }

  @Override
  public String getFullName() {
    return super.getName() + "(" + this.latinName + ")";
  }

  @Override
  public double getPriceForConsumer() {
    return (1.0 - this.nfzRefund) * super.getPriceForConsumer();
  }

}
