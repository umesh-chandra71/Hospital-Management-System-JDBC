package com.learnJDBC;
import java.sql.*;
import java.util.Scanner;
public class HospitalManagementSystem {
	private static final String url = "jdbc:mysql://localhost:3306/hospital";
	private static final String username = "root";
	private static final String password = "Yashwanth098";
	
	public static boolean checkDoctorAvailability(int doctorID,String appointmentDate,Connection connection)
	{
		String query = "Select count(*) from appointments where doctor_id = ? and appointment_date = ?"; 
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, doctorID);
			preparedStatement.setString(2, appointmentDate);
			
			ResultSet resultset = preparedStatement.executeQuery();
			
			if(resultset.next())
			{
				int count = resultset.getInt(1);
				
				if(count == 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static void bookAppointment(Patient patient,Doctor doctor, Connection connection, Scanner scanner)
	{
		System.out.println("Please Enter Patient ID : ");
		int patientID = scanner.nextInt();
		System.out.println("Please Enter Doctor ID : ");
		int doctorID = scanner.nextInt();
		System.out.println("Please Enter Appointment Date(YYYY-MM-DD) :"); 
		String appointmentDate = scanner.next();
		
		if(patient.getPatientByID(patientID) && doctor.getDoctorByID(doctorID))
		{
			if(checkDoctorAvailability(doctorID,appointmentDate,connection))
			{
				String appointmentQuery = "Insert into appointments (patient_id,doctor_id,appointment_date) values (?,?,?)";
				try
				{
					PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
					preparedStatement.setInt(1, patientID);
					preparedStatement.setInt(2,doctorID);
					preparedStatement.setString(3, appointmentDate);
				
					int rowsAffected = preparedStatement.executeUpdate();
					if(rowsAffected>0)
					{
						System.out.println("Appointment Booked"); 
						
					}
					else
					{
						System.out.println("Appointment could not be booked");
					}
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					
				}
				
			}
			else
			{
				System.out.println("Doctor not available on this date");
			}
		}
		else
		{
			System.out.println("Either doctor or patient does'nt exist!!");
		}
	}
	public static void main(String[] args) {
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Scanner scanner = new Scanner(System.in);
		
		try
		{
			Connection connection = DriverManager.getConnection(url,username,password);
			Patient patient = new Patient(connection,scanner);
			Doctor doctor = new Doctor(connection,scanner);
			
			while(true)
			{
				System.out.println("Hospital Management System");
				System.out.println("1. Add Patient");
				System.out.println("2. Add Doctor");
				System.out.println("3. View Patient");
				System.out.println("4. View Doctors ");
				System.out.println("5. Book Appointment");
				System.out.println("6. Exit");
				System.out.println("\n Please Enter your choice : ");
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1 : 
					patient.addPatient();
					System.out.println();
					break;
				
				case 2 : 
					doctor.addDoctor();
					System.out.println();
					break;
					
				case 3 : 
					patient.viewPatients();
					System.out.println();
					break;
					
				case 4 : 
					doctor.viewDoctors();
					System.out.println();
					break;
					
				case 5 : 
					bookAppointment(patient,doctor , connection, scanner);
					System.out.println();
					break;
				
				case 6 :
					System.out.println("THANK YOU!! For USING HOSPITAL MANAGEMENT SYSTEM");
					return; 
				default:
					System.out.println("Please enter a valid input");
					break;
				
				}
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		

	}

}
