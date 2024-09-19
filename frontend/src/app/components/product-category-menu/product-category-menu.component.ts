import { Component, OnInit } from '@angular/core';
import { ProductCategory } from 'src/app/common/product-category';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-category-menu',
  templateUrl: './product-category-menu.component.html',
  styleUrls: ['./product-category-menu.component.css'],
})
export class ProductCategoryMenuComponent {
  productCategories: ProductCategory[] = [
    {
      id: 1,
      categoryName: 'Shoes'
    },
    // Add more product categories as needed
  ];
  selectedCategory!: ProductCategory | undefined;

  constructor(private productService: ProductService) {}

  // ngOnInit() {
  //   this.listProductCategories();
  // }

  // listProductCategories() {
  //   this.productService.getProductCategories().subscribe((data) => {
  //     this.productCategories = data;
  //   });
  // }

  // Function to set the selected category
  setSelectedCategory(category: ProductCategory) {
    this.selectedCategory = category;
  }
}
