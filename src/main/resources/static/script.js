const currentYear = new Date().getFullYear();
const currentYearElement = document.querySelector(".current-year");
currentYearElement.innerHTML = currentYear;

const HOST = 'http://localhost:8080';

function initShopFilters (shops) {
  const shopsFilterSelect = document.querySelector('.shop-select');
  const blankShopElement = document.createElement('option');
  blankShopElement.setAttribute('class', 'blank');
  blankShopElement.innerHTML = '';
  shopsFilterSelect.appendChild(blankShopElement);

  shops.forEach(shop => {
      const shopElement = document.createElement('option');
      shopElement.setAttribute('class', shop.id);
      shopElement.innerHTML = shop.name;
      shopsFilterSelect.appendChild(shopElement);
  });
}

function initShopTypesFilters (shopTypes) {
  const shopTypesFilterSelect = document.querySelector('.filterShopType');

  shopTypes.forEach(type => {
      const shopTypeDiv = document.createElement('div');
      const shopTypeElementParent = document.createElement('input');
      shopTypeElementParent.setAttribute('type', 'checkbox');
      shopTypeElementParent.setAttribute('class', 'shop-type');
      shopTypeElementParent.setAttribute('value', type);
      shopTypesFilterSelect.appendChild(shopTypeDiv);
      shopTypesFilterSelect.appendChild(shopTypeElementParent);

      const shopTypeName = document.createElement('span');
      shopTypeName.setAttribute('value', type);
      shopTypeName.innerHTML = type;
      shopTypesFilterSelect.appendChild(shopTypeName);
      });
}

function initFilters(filters) {
  const { shops, shopTypes } = filters;
  initShopFilters(shops);
  initShopTypesFilters(shopTypes);
}

const filtersPromise = fetch(HOST + '/filters')
  .then(response => response.json())
  .then(filters => initFilters(filters))
  .catch(error => console.log(error));

function getFilteringParams() {
  const productNamePhrase = document.querySelector("#productFilter").value;
  const shopNamePhrase = document.querySelector(".shop-select").value;
  const shopTypes = Array.from(document.querySelectorAll('.shop-type'))
      .filter(typeElement => typeElement.checked)
      .map(checkElement => checkElement.value);
  const minProductPrice = document.querySelector('.range-min').value;
  const maxProductPrice = document.querySelector('.range-max').value;

  return {
      productNamePhrase,
      shopNamePhrase,
      shopTypes,
      minProductPrice,
      maxProductPrice
  }
}

const createQueryString = object => Object.keys(object)
  .filter(key => object[key] !== undefined)
  .filter(key => String(object[key]) !== '')
  .map(key => `${key}=${object[key]}`)
  .join('&');


function fetchFilteredShops(filteringParams) {
  const fetchShopsUrl = HOST + '/shops' + '?' + createQueryString(filteringParams);

  return fetch(fetchShopsUrl)
    .then(response => response.json())
    .catch(() => console.log(error));
}

const callSearch = document.querySelector('.filter-button').addEventListener('click', onLoadShops);

function clearLoadShops() {
  const divToRemove = document.querySelector('.search-results');

  while (divToRemove.hasChildNodes()) {
    divToRemove.removeChild(divToRemove.childNodes[0]);
  }
}

function onLoadShops() {
  clearLoadShops();

  fetchFilteredShops(getFilteringParams())
    .then(showShops)
}

function createShopElement(shop) {
  const shopNextRowElement = document.createElement('tr');
  const shopElementName = document.createElement('td');
  shopElementName.setAttribute('class', shop.id);
  const shopElementFloor = document.createElement('td');
  const shopElementBox = document.createElement('td');
  shopElementName.innerHTML = shop.name;
  shopElementName.addEventListener('click', onShowShopDetails);
  shopElementFloor.innerHTML = shop.location.floor;
  shopElementBox.innerHTML = shop.location.box;
  shopNextRowElement.appendChild(shopElementName);
  shopNextRowElement.appendChild(shopElementFloor);
  shopNextRowElement.appendChild(shopElementBox);

  return shopNextRowElement;
}

function showShops(shops) {
  const mainContainer = document.querySelector('.search-results');

  const shopContainer = document.createElement('table');
  shopContainer.setAttribute('class', 'shop-container');
  const shopRowElement = document.createElement('tr');
  shopContainer.appendChild(shopRowElement);
  const shop1Header = document.createElement('th');
  const shop2Header = document.createElement('th');
  const shop3Header = document.createElement('th');
  shop1Header.innerHTML = 'Shop Name';
  shop2Header.innerHTML = 'Shop Floor';
  shop3Header.innerHTML = 'Shop Box';
  shopRowElement.appendChild(shop1Header);
  shopRowElement.appendChild(shop2Header);
  shopRowElement.appendChild(shop3Header);

  const shopElements = shops
    .map(createShopElement)
    .forEach(element => shopContainer.appendChild(element));

  mainContainer.appendChild(shopContainer);

  return shopContainer;
}

function clearLoadProducts() {
  const divToRemove = document.querySelector('.product-container');

  while (divToRemove.hasChildNodes()) {
    divToRemove.removeChild(divToRemove.childNodes[0]);
  }
}

function onShowShopDetails() {
  var id = event.target.getAttribute('class');
  clearLoadProducts();

  fetchShopDetails(id)
    .then(showShopDetails)
}

function fetchShopDetails(shopId) {
  return fetch(HOST + '/shops/' + shopId + '/products')
  .then(response => response.json())
  .catch(() => console.log(error));
}

function createShopDetailsElement(product) {
  const productContainer = document.createElement('div');
  productContainer.setAttribute('class', 'element-container');

  const productName = document.createElement('span');
  const productType = document.createElement('span');
  const productQuantity = document.createElement('span');
  const productPriceForCompany = document.createElement('span');
  const productPriceForConsumer = document.createElement('span');

  productName.innerHTML = product.name;
  productType.innerHTML = product.type;
  productQuantity.innerHTML = product.quantity;
  productPriceForCompany.innerHTML = product.priceForCompany;
  productPriceForConsumer.innerHTML = product.priceForConsumer;

  productContainer.appendChild(productName);
  productContainer.appendChild(productType);
  productContainer.appendChild(productQuantity);
  productContainer.appendChild(productPriceForCompany);
  productContainer.appendChild(productPriceForConsumer);

  return productContainer;
}

function showShopDetails(products) {
  const mainContainerProduct = document.querySelector('.product-container');
  const productContainer = document.createElement('div');
  productContainer.setAttribute('class', 'container');

  const productElements = products.map(createShopDetailsElement()).forEach(element => productContainer.appendChild(element));

  mainContainerProduct.appendChild(productContainer);

  return productContainer;
}
