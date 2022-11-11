package banking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class bank {

	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(
	            new InputStreamReader(System.in));
	        String name = "";
	        String address="";
	        String mobile="";
	        
	        int ch;
	 
	        while (true) {
	            System.out.println(
	                "\n ->||    Welcome to InBank    ||<- \n");
	            System.out.println("1)Create Account");
	            System.out.println("2)Login Account");
	 
	            try {
	                System.out.print("\n    Enter Input:"); //user input
	                ch = Integer.parseInt(sc.readLine());
	 
	                switch (ch) {
	                case 1:
	                    try {
	                        System.out.print(
	                            "Enter Unique UserName:");
	                        name = sc.readLine();
	                        System.out.print(
	                            "Enter address:");
	                        address = sc.readLine();
	                        System.out.print(
		                            "Enter contact number:");
		                     mobile =sc.readLine();
	 
	                        if (bankmanagement.createAccount(
	                                name,address,mobile)) {
	                            System.out.println(
	                                "MSG : Account Created Successfully!\n");
	                        }
	                        else {
	                            System.out.println(
	                                "ERR : Account Creation Failed!\n");
	                        }
	                    }
	                    catch (Exception e) {
	                        System.out.println(
	                            " ERR : Enter Valid Data::Insertion Failed!\n");
	                    }
	                    break;
	 
	                case 2:
	                    try {
	                        System.out.print(
	                            "Enter  UserName:");
	                        name = sc.readLine();
	                        System.out.print(
	                            "Enter  contact:");
   	                        mobile =sc.readLine();
	 
	                        if (bankmanagement.loginAccount(
	                                name,mobile)) {
	                            System.out.println(
	                                "MSG : Logout Successfully!\n");
	                        }
	                        else {
	                            System.out.println(
	                                "ERR : login Failed!\n");
	                        }
	                    }
	                    catch (Exception e) {
	                        System.out.println(
	                            " ERR : Enter Valid Data::Login Failed!\n");
	                    }
	 
	                    break;
	 
	                default:
	                    System.out.println("Invalid Entry!\n");
	                }
	 
	                if (ch == 4) {
	                    System.out.println(
	                        "Exited Successfully!\n\n Thank You :)");
	                    break;
	                }
	            }
	            catch (Exception e) {
	                System.out.println("Enter Valid Entry!");
	            }
	        }
	        sc.close();
	    }
	}

	
