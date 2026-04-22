package com.learnJDBC;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	private Connection connection;
	private Scanner scanner;
	public Doctor(Connection connection,Scanner scanner)
	{
		this.connection = connection;
		this.scanner = scanner;
	} 
	public void addDoctor()
	{
		System.out.println("Enter Doctors's Name : ");
		String name = scanner.next();
		System.out.println("Enter Doctors's Specialization : ");
		String specialization = scanner.next();
		
		try
		{
			String Query = "Insert into doctors (name,specialization) values (?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(Query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, specialization);
			
		
		
			int affectedRows = preparedStatement.executeUpdate(); 
			
			if(affectedRows>0)
			{
				System.out.println("Doctor Added Successfully ");
				
			}
			else
			{
				System.out.println("Failed to add Doctor !");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	public void viewDoctors()
	{
		String query = "select * from doctors";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultset = preparedStatement.executeQuery();
			
			System.out.println("Doctors: ");
			System.out.println("+-------------+----------------------+----------------------+");
			System.out.println("| Doctor ID   | Name                 | Specialization       |");
			System.out.println("+-------------+----------------------+----------------------+"); 
			
			while(resultset.next())
			{
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				//int age = resultset.getInt("age");
				String specialization = resultset.getString("specialization");
				
				System.out.printf("|%-13s|%-22s|%-22s|\n",id,name,specialization);
				System.out.println("+-------------+----------------------+----------------------+");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public boolean getDoctorByID(int id)
	{
		String query = "select * from doctors where id = ?";
		
		try
		{
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1,id);
			ResultSet resultset = preparedstatement.executeQuery();
			if(resultset.next())
			{
				return true;
			}
			else
			{
				return false;
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
		}
		return false;
	}
	
}
