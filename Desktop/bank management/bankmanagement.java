package banking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
 
public class bankmanagement {

	private static final int NULL = 0;
	 
    static Connection con = connection.getConnection();
    static String sql = "";
    public static boolean
    createAccount(String name,String address,String mobile) // create account function
    {
        try {
            // validation
            if (name == "" || address=="" || mobile=="") {
                System.out.println("All Field Required!");
                return false;
            }
            // query
            Statement st = con.createStatement();
            sql = "INSERT INTO customer(cname,balance,address,mobile) values('" + name + "',1000,'" + address + "','" + mobile +"')";
 
            // Execution
            if (st.executeUpdate(sql) == 1) {
                System.out.println(name
                                   + ", Now You Login!");
                return true;
            }
            // return
        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username Not Available!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean
    loginAccount(String name,String mobile) 
    {
        try {
            // validation
            if (name == "" || mobile=="") {
                System.out.println("All Field Required!");
                return false;
            }
            // query
            sql = "select * from customer where cname='"
                  + name + "' and mobile=" + mobile;
            PreparedStatement st
                = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // Execution
            BufferedReader sc = new BufferedReader(
                new InputStreamReader(System.in));
 
            if (rs.next()) {
                // after login menu driven interface method
 
                int ch = 5;
                int amt = 0;
                int Ac = rs.getInt("ac_no");
                
                
                while (true) {
                    try {
                        System.out.println(
                            "Hallo, "
                            + rs.getString("cname"));
                        System.out.println(
                            "1)Deposit Money");
                        System.out.println("2)Withdraw money");
                        System.out.println("3)View Balance");
                        System.out.println("4)LogOut");
 
                        System.out.print("Enter Choice:");
                        ch = Integer.parseInt(
                            sc.readLine());
                        if (ch == 1) {
                            System.out.print(
                                "how much you want to deposit");
                            
                            amt = Integer.parseInt(
                                sc.readLine());
 
                            bankmanagement.deposit(Ac, amt);
                        }
                        else if (ch==2) {
                        	System.out.print(
                                    "how much you want to withdraw");
                        	amt=Integer.parseInt(
                                    sc.readLine());
                        	bankmanagement.withdraw(Ac, amt);
                        }
                        else if (ch == 3) {
 
                            bankmanagement.getBalance(Ac
                                );
                        }
                        else if (ch == 4) {
                            break;
                        }
                        else {
                            System.out.println(
                                "Err : Enter Valid input!\n");
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                return false;
            }
            // return
            return true;
        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username Not Available!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void
    getBalance(int acNo) // fetch balance method
    {
        try {
 
            // query
            sql = "select * from customer where ac_no="
                  + acNo;
            PreparedStatement st
                = con.prepareStatement(sql);
 
            ResultSet rs = st.executeQuery(sql);
            System.out.println(
                "-----------------------------------------------------------");
            System.out.printf("%12s %10s %10s\n",
                              "Account No", "Name",
                              "Balance");
 
            // Execution
 
            while (rs.next()) {
                System.out.printf("%12d %10s %10d.00\n",
                                  rs.getInt("ac_no"),
                                  rs.getString("cname"),
                                  rs.getInt("balance"));
            }
            System.out.println(
                "-----------------------------------------------------------\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean withdraw(int Ac,
                                        int amount)
        throws SQLException 
    {
        // validation
        
        try {
            con.setAutoCommit(false);
            sql = "select * from customer where ac_no="
                  + Ac;
            PreparedStatement ps
                = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            
 
            Statement st = con.createStatement();
 
            // debit
            con.setSavepoint();
 
            sql = "update customer set balance=balance-"
                  + amount + " where ac_no=" + Ac;
            if (st.executeUpdate(sql) == 1) {
                System.out.println("Amount Debited!");
            }
 
            
 
            con.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        }
        // return
        return false;}
    
        public static boolean deposit(int Ac,int amount) throws SQLException {
// validation
        
        try {
            con.setAutoCommit(false);
            sql = "select * from customer where ac_no="
                  + Ac;
            PreparedStatement ps
                = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            
 
            Statement st = con.createStatement();
 
    	// credit
        con.setSavepoint();
        sql = "update customer set balance=balance+"
              + amount + " where ac_no=" + Ac;
        st.executeUpdate(sql);
        System.out.println("deposited "+amount);
        con.commit();
        return true;
    }
    catch (Exception e) {
        e.printStackTrace();
        con.rollback();
    }
    // return
    return false;}

    
    }

