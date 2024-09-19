import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { Product } from 'src/app/common/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
})
export class ProductDetailsComponent implements OnInit {
  product: Product = new Product();

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private cartService: CartService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleProductDetails();
    });
  }

  handleProductDetails() {
    // get the "id" param string. convert it to a number using the "+" symbol
    const productId: number = +this.route.snapshot.paramMap.get('id')!;
    //console log the productId
    console.log(productId);
    // this.productService.getProduct(productId).subscribe(
    //   (data) => {
    //     this.product = data;
    //   },
    //   (error) => {
    //     this.router.navigateByUrl('/products');
    //   }
    // );
    
    //write if productid = 1, this.product = {} and so on till id = 4
    if (productId == 1) {
      this.product = {
        id: '1',
        name: 'White sneakers',
        imageUrl: 'https://cdn.pixabay.com/photo/2014/09/03/20/15/shoes-434918_1280.jpg',
        unitPrice: 10.99,
        sku: 'P1',
        unitsInStock: 100,
        description: 'A pair of white sneakers',
        active: true,
        dateCreated: new Date(),
        lastUpdated: new Date()
      };
    } else if (productId == 2) {
      this.product = {
        id: '2',
        name: 'Red high heels',
        imageUrl: 'https://cdn.pixabay.com/photo/2016/03/27/22/16/fashion-1284496_1280.jpg',
        unitPrice: 60.99,
        sku: 'P1',
        unitsInStock: 10,
        description: 'Trending red high heels for the ladies. Made for royalty like you!',
        active: true,
        dateCreated: new Date(),
        lastUpdated: new Date()
      };
    } else if (productId == 3) {
      this.product = {
        id: '3',
        name: 'Black high heels',
        imageUrl: 'https://cdn.pixabay.com/photo/2017/07/25/14/50/shoes-2538424_1280.jpg',
        unitPrice: 50.99,
        sku: 'P1',
        unitsInStock: 10,
        description: 'Fashionable black high heels for the ladies. Classic choice for classy look!',
        active: true,
        dateCreated: new Date(),
        lastUpdated: new Date()
      };
    } else if (productId == 4) {
      this.product = {
        id: '4',
        name: 'Black sneakers',
        imageUrl: 'https://cdn.pixabay.com/photo/2016/12/10/16/57/shoes-1897708_1280.jpg',
        unitPrice: 20.99,
        sku: 'P1',
        unitsInStock: 55,
        description: 'Rugged shoes for the men',
        active: true,
        dateCreated: new Date(),
        lastUpdated: new Date()
      };
    } else if (productId == 5) {
        this.product = {
          id: '5',
          name: 'Brown formal shoes',
          imageUrl: 'https://cdn.pixabay.com/photo/2015/01/16/15/01/fashion-601561_1280.jpg',
          unitPrice: 50.99,
          sku: 'P1',
          unitsInStock: 22,
          description: 'A pair of brown dress shoes, perfect for business meetings',
          active: true,
          dateCreated: new Date(),
          lastUpdated: new Date()
        }
    } else if (productId == 6) {
        this.product = {
          id: '6',
          name: 'DC comic sneakers',
          imageUrl: 'https://cdn.pixabay.com/photo/2016/06/03/17/35/shoes-1433925_1280.jpg',
          unitPrice: 200.99,
          sku: 'P1',
          unitsInStock: 22,
          description: 'Sneakers with DC comic characters art. Nerd out in style!',
          active: true,
          dateCreated: new Date(),
          lastUpdated: new Date()
        }
    } else if (productId == 7) {
        this.product = {
          id: '7',
          name: 'White Nike shoes',
          imageUrl: 'https://cdn.pixabay.com/photo/2016/11/19/18/06/feet-1840619_1280.jpg',
          unitPrice: 79.99,
          sku: 'P1',
          unitsInStock: 22,
          description: 'Classic white Nike shoes. Must have for every sneakerhead!',
          active: true,
          dateCreated: new Date(),
          lastUpdated: new Date()
        }
    }
    else {
      this.router.navigateByUrl('/products');
    }
  }

  addToCart() {
    const cartItem = new CartItem(this.product);
    this.cartService.addToCart(cartItem);
  }
}
