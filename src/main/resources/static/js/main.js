const getProducts = () => {
    return fetch('/api/products')
        .then(response => response.json())
        .catch(err => console.log(err));
}

const handlerAddToCart = (productId) => {

}

//description: "Very nice"
//imageUrl: "https://picsum.photos/id/103/200/300"
//name: "My Nice Picture"
//price: 10.1
//productId: "product-1"
//published: true

const createProductHtmlElement = (productData) => {
    const template = `
        <li class="product">
            <div class="card" style="width: 18rem;">
              <img src="${productData.imageUrl}" class="card-img-top product__image" alt="${productData.name} photo">
              <div class="card-body">
                <h5 class="card-title">${productData.name}</h5>
                <p class="card-text">${productData.description}</p>
                <button
                    class="product__add-to-cart btn btn-primary"
                    data-product-id="${productData.productId}"
                >
                    Add to cart
                      <span class="product__price position-relative top-10 start-50 translate-middle badge rounded-pill bg-danger">
                        ${productData.price} PLN
                        <span class="visually-hidden">price</span>
                      </span>
                </button>
              </div>
            </div>
        </li>
    `;

    return createHtmlElementFromString(template);
}

const createHtmlElementFromString = (htmlAsString) => {
    let myElement = document.createElement('div');
    myElement.innerHTML = htmlAsString.trim();

    return myElement.firstChild;
}

const appendToProductsList = (productsList, productsElements) => {
    productsElements.forEach(productElement => productsList.appendChild(productElement));
}

const initializeCartHandler = (productElement) => {
    const addToCartBtn = productElement.querySelector('.product__add-to-cart');
    addToCartBtn.addEventListener('click', (event) => {
        const productId = event.target.getAttribute('data-product-id');
        const addProductUrl = `/api/add-product/${productId}`;
        console.log('i am going to add to cart following product: ' + productId);

        fetch(addProductUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        })
            .then(() => refreshCurrentOffer())
            .catch(err => console.log('something is not YES :>'));
    })
}
const refreshHtmlOffer = (offer) => {
    const offerEl = document.querySelector('.offer');
    offerEl.querySelector('.offer__total').innerText = `${offer.total} PLN`;
    offerEl.querySelector('.offer__items-count').innerText = `${offer.itemsCount}`;
}

const refreshCurrentOffer = () => {
    fetch('/api/current-offer')
        .then(r => r.json())
        .then(refreshHtmlOffer);
}

const initializeCartHandlerForElements = (productsElements) => {
    productsElements.forEach(el => initializeCartHandler(el));

    return productsElements;
}

const handleCheckout = () => {
    console.log('checkout');
}

(() => {
    console.log("Hello Wprld");
    const productList = document.querySelector('.products__list');

    getProducts()
        .then(productsAsJsonObjects => productsAsJsonObjects.map(createProductHtmlElement))
        .then(productsAsHtmlElements => initializeCartHandlerForElements(productsAsHtmlElements))
        .then(productsAsHtmlElements => appendToProductsList(productList, productsAsHtmlElements));

    refreshCurrentOffer();

    const checkoutBtn = document.querySelector('#checkout');
    checkoutBtn.addEventListener('click', () => {
        handleCheckout();
    });
})();
