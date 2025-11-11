package com.kodekart.controller;

import java.util.List;
import java.util.Scanner;

import com.kodekart.model.Products;
import com.kodekart.service.AdminProductService;
import com.kodekart.service.impl.AdminProductServiceImpl;

public class AdminDashAddController {
	AdminProductService productService = new AdminProductServiceImpl();
	Scanner scanner = new Scanner(System.in);

// ************** Add Product ***************	
	public void addProduct() {
		System.out.println("\n------Admin Adding Page------");

		System.out.print("Enter Product Name : ");
		String name = scanner.nextLine();

		System.out.print("Enter Product Category : ");
		String category = scanner.nextLine();

		System.out.print("Enter Product Price : ");
		double price = scanner.nextDouble();

		System.out.print("Enter Product Quantity : ");
		int quantity = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter Product Description : ");
		String description = scanner.nextLine();

		Products products = new Products(0, name, category, price, quantity, description);

		boolean addedProduct = productService.addProduct(products);
		if (!addedProduct) {
			System.out.println("Product Added Sucessfully....");
		} else {
			System.out.println("Product Not Added....");
		}
	}
//	************** Update Product ***************

	public void updateProduct() {
		System.out.println("\n------Admin Updating Page------");

		System.out.print("Enter Product Name : ");
		String name = scanner.nextLine();

		int quantity;
		while (true) {
			System.out.print("Enter Product Quantity : ");
			if (scanner.hasNextInt()) {
				quantity = scanner.nextInt();
				scanner.nextLine();
				break;
			} else {
				System.err.println("Please enter only numbers.");
				scanner.next(); // discard
			}
		}

		boolean updated = productService.updateProductQuantity(name, quantity);

		if (updated) {
			System.out.println("Quantity Updated Successfully!");
		} else {
			System.out.println("Update Failed! Product Not Found");
		}
	}

//	 ************** Delete Product ***************
	public void deleteProductByName() {
		
		System.out.println("\n------Admin Updating Page------");
		System.out.print("Enter Product Name : ");
		String name = scanner.nextLine();
		
		boolean deleted = productService.deleteProductByName(name);
		if (deleted) {
			System.out.println("Product Deleted Successfully...!");
		} else {
			System.out.println("Delete Failed! Product Not Found...");
		}
	}
	
//	 ************** Delete Product ***************
	public void getAllProduct() {
		 List<Products> products = productService.getAllProduct();

		    for (Products p : products) {
		        System.out.println("Id : "+p.getId() + "| Name :  " + p.getName() + "| Category : "+p.getCategory()+"| Price : " + p.getPrice()+"| Quantity : "+p.getQuantity()+"| Description : "+p.getDescription());
		    }
	}
}






