package bankabc;

import java.util.Scanner;
import java.sql.*;  

public class Main {
	static Connection con;

	public static void main(String[] args) {
		
		boolean quit = false;
		
		System.out.println("Welcome to  Bank");
		
		Scanner s = new Scanner(System.in); //For taking input from user
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/karthik","root","");//Connecting to the database.	    
			}
		catch(Exception e){ System.out.println(e);}  
			
			
		do
		{
			System.out.println("C - To create the new Account");
			System.out.println("R - To fetch the existing single Account");
			System.out.println("Rs - To fetch all Accounts");
			System.out.println("U - Update a single Account");
			System.out.println("D - Delete an existing Account");
			System.out.println("Q - Quit");
			
			String usr_choice = s.next();//Taking user input.
			switch(usr_choice)
			{
			
			case "C":                             //-> Creates new account 
				System.out.println("New Acoount");
				
				System.out.println("Enter Your Name: ");
				String usrname = s.next();
				
				System.out.println("Account Type: ");
				String acc_type = s.next();
				
				long acc_no = (long)((Math.random() * 900000000)+600000000); //Generating a random account number which acts as primary key for user
				
				System.out.println("Amount to Deposit: ");
				double amount = s.nextDouble();
				
				if(amount == 0)//Checking  new amount is zero or not
				{
					System.out.println("Please depoist min 10 Rs");
				}
				if(amount == Double.NaN)// Checking whether enter number r not
				{ 
					System.out.println("Enter numbers only");
					
				}
				 
				try {
				String sql = "insert into bankacc values ('"+usrname+"','"+acc_type+"','"+acc_no+"','"+amount+"')";//Inserting new records into table
				Statement stmt=con.createStatement();
				stmt.executeUpdate(sql);
				System.out.println("Account successfully created ");
				System.out.println("Please note down account number for future transaction: " + acc_no);
				System.out.println("**********************************************");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				break;
				
			case "R":									//-> Retrieve single user details
				
				System.out.println("Enter Account No to retrieve details: ");
				long acc_number = s.nextLong();
					
				if(acc_number == 0 || acc_number == Double.NaN || acc_number != 10) //Checking user enter a valid account number or not
				{
					System.out.println("Invalid Account Number");
				}
				
				try {
					String sql = "select *from bankacc where accNo = '"+acc_number+"' ";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next())
					{
						String name =rs.getString(1);
						String accType =rs.getString(2);
						int  accNo = rs.getInt(3);
						double money = rs.getDouble(4);
						
						System.out.println("Name: " +name +"\n"+ "Acoount Type: " +accType );
						System.out.println("Account No: " +accNo  +"\n"+ "Deposited Money : " +money );
						System.out.println("**********************************************");
						
					}
					
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					break;
				
			case "D":
					System.out.println("Enter Account No to retrieve details: ");
					long acc_numb = s.nextLong();
					
					if(acc_numb == 0 || acc_numb == Double.NaN || acc_numb != 10) //Checking user enter a valid account number or not
					{
						System.out.println("Invalid Account Number");
					}
					
				
				try {
					String sql = "delete from bankacc where accNo = '"+acc_numb+"' ";
					Statement stmt=con.createStatement();
					stmt.executeUpdate(sql);
					
					System.out.println("Account successfully deleted");
					System.out.println("**********************************************");
					
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					break;
					
					
			case "U":
				
					System.out.println("Enter your account no: ");
					long acc_num = s.nextLong();
					
					if(acc_num == 0 || acc_num == Double.NaN || acc_num != 10) //Checking user enter a valid account number or not
					{
						System.out.println("Invalid Account Number");
					}
					
					System.out.println("N -> Name");
					System.out.println("S -> Acccount Type");
					System.out.println("D -> Add Money");
					System.out.println("E -> Exit");
					
					System.out.println("Want you want to be updated ");
					char in = s.next().charAt(0); //Taking input from user
					
										
					switch(in)
					{
					case 'N':								// Changes the user name which user want
					
						
						System.out.println("New name you want change: ");
						String newName = s.next();
						
						try {
							String sql = "update bankacc set usrname = '"+newName+"'  where accNo = '"+acc_num+"' ";
							Statement stmt=con.createStatement();
							stmt.executeUpdate(sql);
							
							System.out.println("Account successfully Updated");
							System.out.println("**********************************************");
							
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						break;
						
					case 'S':									//Change the account type
						
						System.out.println("Type you want change Saving/Current/Fixed Deposit: ");
						String Acc = s.next();
						
						try {
							String sql = "update bankacc set accType = '"+Acc+"'  where accNo = '"+acc_num+"' ";
							Statement stmt=con.createStatement();
							stmt.executeUpdate(sql);
							
							System.out.println("Account successfully Updated");
							System.out.println("**********************************************");
							
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						break;
					     
					case 'D':										//Deposits the money into user Account using user number
						
						System.out.println("Amount you want to add: ");
						double cash = s.nextDouble();
					
						
						try {
							String sql = "select cash from bankacc where accNo = '"+acc_num+"' ";
							Statement stmt=con.createStatement();
							ResultSet rs = stmt.executeQuery(sql);
							double  dcash = 0;
							while(rs.next())
								{ 
								dcash = rs.getDouble(1);
								}
							double check = dcash + cash;
							
							String sql2 = "update bankacc set cash = '"+check+"'  where accNo  = '"+acc_num+"' ";
							stmt.executeUpdate(sql2);
						
							
							System.out.println(dcash+ " successfully added to your account");
							System.out.println("**********************************************");
							
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						break;
						
					case 'E':
						break;
						
							
						default:
							System.out.println("Invalid Choice");
					
					}
				
					break;
			
					
			case "Rs":							//-> To retrieve all details from database.
				try {
					String sql = "select *from bankacc";
					Statement stmt=con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next())
					{
						String name =rs.getString(1);
						String accType =rs.getString(2);
						int  accNo = rs.getInt(3);
						double money = rs.getDouble(4);
						
						System.out.println("Name: " +name +"\n"+ "Acoount Type: " +accType );
						System.out.println("Account No: " +accNo  +"\n"+ "Deposited Money : " +money );
						System.out.println("**********************************************");
						
					}
					
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				
				
			case "Q":
				quit = true;
				break;
			
			
			default:
				System.out.println("Valid Choice");
			}		
			
		}
		while(!quit); //Quits form while loops user enter 'Q'
		

		s.close();
	}

}
