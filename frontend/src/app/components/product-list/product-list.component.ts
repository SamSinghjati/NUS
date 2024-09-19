import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { Product } from 'src/app/common/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  products: Product[] = [{
    id: '1',
    name: 'White sneakers',
    imageUrl: 'https://cdn.pixabay.com/photo/2014/09/03/20/15/shoes-434918_1280.jpg',
    unitPrice: 10.99,
    sku: 'P1',
    unitsInStock: 100,
    description: 'A pair of White sneakers shoes',
    active: true,
    dateCreated: new Date(),
    lastUpdated: new Date()
  },
  {
    id: '2',
    name: 'Red high heels',
    imageUrl: 'https://cdn.pixabay.com/photo/2016/03/27/22/16/fashion-1284496_1280.jpg',
    unitPrice: 60.99,
    sku: 'P1',
    unitsInStock: 10,
    description: 'Fashionable red high heels for the ladies',
    active: true,
    dateCreated: new Date(),
    lastUpdated: new Date()
  },
  {
    id: '3',
    name: 'Black high heels',
    imageUrl: 'https://cdn.pixabay.com/photo/2017/07/25/14/50/shoes-2538424_1280.jpg',
    unitPrice: 50.99,
    sku: 'P1',
    unitsInStock: 10,
    description: 'Fashionable black high heels for the ladies',
    active: true,
    dateCreated: new Date(),
    lastUpdated: new Date()
  },
  {
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
  },
  {
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
  },
  {
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
  },
  {
    id: '7',
    name: 'White Nike shoes',
    imageUrl: 'https://cdn.pixabay.com/photo/2016/11/19/18/06/feet-1840619_1280.jpg',
    unitPrice: 79.99,
    sku: 'P1',
    unitsInStock: 22,
    description: 'Classic white Nike shoes',
    active: true,
    dateCreated: new Date(),
    lastUpdated: new Date()
  }
];
  skeletonProducts: [] // Create skeleton cards
  currentCategoryId: number = 1;
  previousCategoryId: number = 1;
  currentCategoryName: string = '';
  searchMode: boolean = false;

  // fields for pagination
  pageNumber: number = 1;
  pageSize: number = 10;
  totalElements: number = 0;
  previousSearchInput: string = '';

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }

  listProducts() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if (this.searchMode) {
      this.handleSearchProducts();
    } else {
      this.handleListProducts();
    }
  }

  handleSearchProducts() {
    const searchInput: string = this.route.snapshot.paramMap.get('keyword')!;

    // if we have a different searchInput than previous
    // then set pageNumber to 1
    if (this.previousSearchInput !== searchInput) {
      this.pageNumber = 1;
    }

    this.previousSearchInput = searchInput;

    // now search for the products using searchInput
    this.productService
      .searchProductsPaginate(this.pageNumber - 1, this.pageSize, searchInput)
      .subscribe(this.processResult());
  }

  handleListProducts() {
    // // check if "id" param is available
    // const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    // if (hasCategoryId) {
    //   // get the "id" param string. convert string to a number using the "+" symbol
    //   this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;

    //   // get the "name" param string
    //   this.currentCategoryName = this.route.snapshot.paramMap.get('name')!;
    // } else {
      // no category id is available .. . default to category id 1
      this.currentCategoryId = 1;
      this.currentCategoryName = 'Books';
    // }

    // check if we have different category than previous
    // NB: Angular will reuse a component if it is currently being viewed

    // if we have a different category id than previous then set pageNumber back to 1
    if (this.previousCategoryId != this.currentCategoryId) {
      this.pageNumber = 1;
    }

    this.previousCategoryId = this.currentCategoryId;

    // now get the products for the given category id
    // this.productService
    //   .getProductListPaginate(
    //     this.pageNumber - 1,
    //     this.pageSize,
    //     this.currentCategoryId
    //   )
    //   .subscribe(this.processResult());
  }

  processResult() {
    return (data: any) => {
      // Reset the skeleton loader by setting skeletonProducts to an empty array
      this.skeletonProducts = [];

      this.products = data._embedded.products;
      // + 1 because pagination component:pages are 1 based however in spring data REST: pages are 0 based
      this.pageNumber = data.page.number + 1;
      this.pageSize = data.page.size;
      this.totalElements = data.page.totalElements;
    };
  }

  updatePageSize(event: Event) {
    // @ts-ignore
    this.pageSize = event.target.value;
    this.pageNumber = 1;
    this.listProducts();
  }

  addToCart(product: Product) {
    const cartItem = new CartItem(product);

    this.cartService.addToCart(cartItem);
  }
}
