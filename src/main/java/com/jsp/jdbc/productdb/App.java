package com.jsp.jdbc.productdb;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("1) Enter 1 to add product: \n2) Enter 2 to find product by id:\n3) Enter 3 to find product by name:"
    			+"\n4) Enter 4 to find the product between the range:\n5) Enter 5 to find all the product details:"
    			+ "\n6) Enter 6 to to update product by id:\n7) Enter 7 to delete product by id:\n8) Enter 8 to delete product by name:");
    	int choice=sc.nextInt();
    	

    	ProductService ps=new ProductService();
    	switch(choice) {
    	case 1:ps.addProduct();
    	       break;
    	case 2:ps.findProductById();
    	       break;
    	case 3:ps.findProductByName();
    	       break;
    	case 4:ps.findProductByBeetweenPrice();
    	       break;
    	case 5:ps.findAllProduct();
    	       break;
    	case 6:ps.updateProductById();
    	       break;
    	case 7:ps.deleteProductById();
    	       break;
    	case 8:ps.deleteProductByName();
    	}

    	
    }
}
