package com.jsp.jdbc.productdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.protocol.SocksProxySocketFactory;

public class ProductService {
	Scanner sc=new Scanner(System.in);
	
	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb","root","root");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}
	public static void closeCon(Connection con) {
		try {
			con.close();
		}catch (SQLException e) {
			System.out.println("Connection not Established");
		}
	}

	public void addProduct() {
		System.out.println("Enter The product Details");
		System.out.println("ProductId");
		int productId=sc.nextInt();
		System.out.println("ProductName");
		String productName=sc.next();
		System.out.println("ProductBrand");
		String productBrand=sc.next();
		System.out.println("ProductPrice");
		int productPrice=sc.nextInt();
		
		Connection con=getConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("INSERT INTO product VALUES(?,?,?,?)");
			psmt.setInt(1, productId);
			psmt.setString(2, productName);
			psmt.setString(3, productBrand);
			psmt.setInt(4, productPrice);
			int res=psmt.executeUpdate();
			if(res>0) {
				System.out.println("Data Inserted");
				
			}else {
				System.out.println("Not inserted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void findProductById() {
		System.out.println("Enter the productId:");
		int id=sc.nextInt();
		Connection con=getConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("SELECT *FROM product WHERE productId=?");
			psmt.setInt(1, id);
			ResultSet res=psmt.executeQuery();
			while(res.next()) {
				System.out.println("ProductName: "+res.getString("productName")+" productBrand: "+res.getString("productBrand")+" productPrice: "+res.getInt("productPrice"));
			}
			psmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void findProductByName() {
		System.out.println("Enter the productName:");
		String name=sc.next();
		Connection con=getConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("SELECT *FROM product WHERE productName=?");
			psmt.setString(1, name);
			ResultSet res=psmt.executeQuery();
			while(res.next()) {
				System.out.println("ProductId: "+res.getInt("productId")+" productBrand: "+res.getString("productBrand")+" productPrice: "+res.getInt("productPrice"));
			}
			psmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void findProductByBeetweenPrice() {
		System.out.println("Enter the starting price:");
		int start =sc.nextInt();
		System.out.println("Enter the ending price:");
		int end =sc.nextInt();
		Connection con=getConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("SELECT *FROM product WHERE productPrice BETWEEN ? AND ?");
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			ResultSet res=psmt.executeQuery();
			while(res.next()) {
				System.out.println("ProductName: "+res.getString("productName"));
			}
			psmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void findAllProduct() {
		System.out.println("All products");
		Connection con=getConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("SELECT * FROM product");
			ResultSet res=psmt.executeQuery();
			while(res.next()) {
				System.out.println("ProductName: "+res.getString("productName"));
			}
			psmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateProductById() {
		System.out.println("Enter the productId which you want to update:");
		int id=sc.nextInt();
		System.out.println("Enter the productName:");
		String name=sc.next();
		Connection con=getConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("UPDATE product SET productName=? WHERE productId=?");
			psmt.setString(1, name);
			psmt.setInt(2, id);
			int result=psmt.executeUpdate();
			if(result>0) {
				System.out.println("Product updated sussfully!!!");
			}
			else {
				System.out.println("Product is not present!!!");
			}
			psmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteProductById() {
		System.out.println("Enter the productId which you want to delete:");
		int id=sc.nextInt();
		Connection con=getConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("DELETE FROM product WHERE productId=?");
			psmt.setInt(1, id);
			int result=psmt.executeUpdate();
			if(result>0) {
				System.out.println("Product deleted sussfully!!!");
			}
			else {
				System.out.println("Product id is not present!!!");
			}
			
			psmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteProductByName() {
		System.out.println("Enter the productName which you want to delete:");
		String name=sc.next();
		Connection con=getConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("DELETE FROM product WHERE productName=?");
			psmt.setString(1, name);
			int result=psmt.executeUpdate();
			if(result>0) {
				System.out.println("Product deleted sussfully!!!");
			}
			else {
				System.out.println("Product is not present!!!");
			}
			psmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
