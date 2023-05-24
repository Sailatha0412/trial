package jdbc.functions;
import java.sql.DriverManager;
import java.util.Scanner;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class SpringJdbcTest {
	static DriverManagerDataSource datasource =new DriverManagerDataSource();
	static JdbcTemplate template = null;
	
	static void establishConnection() {
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/mydatabase");
		datasource.setUsername("root");
		datasource.setPassword("Balu@25011994");
		
	}
	static void insertData(int deptno,String name,String loc) {
		template= new JdbcTemplate(datasource);
		String query="Insert into Departments"
				+ "(deptno,name,location) values"
			    + " (?,?,?)";
		int result=template.update(query,deptno,name,loc);
		if(result>0) {
			System.out.println(result+"row(s) has been inserted successfully");
		}
	}
	static void updateDeptLoc(String newval,String oldval) {
		template= new JdbcTemplate(datasource);
		String query="update Departments set location=? where location=?";
		int result=template.update(query,newval,oldval);
		System.out.println(result);
		if(result>0) {
			System.out.println(result+"row(s) has been updated successfully");
		}	
	}
	static void deleteData(int deptno) {
		template= new JdbcTemplate(datasource);
		String query="delete from Departments  where deptno=?";
		int result=template.update(query,deptno);
		if(result>0) {
			System.out.println(result+"row(s) has been deleted successfully");
		}	
	}
	
	public static void main(String[] args) {
		establishConnection();
		Scanner scan= new Scanner(System.in);
		do {
			System.out.println("Menu");
			System.out.println("1.Inserting Data");
			System.out.println("2.Update Location");
			System.out.println("3.Delete Data");
			System.out.println("4.Exit");
		System.out.println("Enter your choice[1-4]: ");
		int choice=scan.nextInt();
		
		switch(choice) {
		case 1:
			System.out.println();
			System.out.println("Enter the department id: ");
			int deptno=scan.nextInt();
			System.out.println("Enter the department name: ");
			String name=scan.next();
			System.out.println("Enter the department location: ");
			String location=scan.next();
			insertData(deptno,name,location);
		break;
		case 2:
			System.out.println("Enter the department location which you want to update: ");
			String oldval=scan.next();
			System.out.println("Enter the department location that you want to update as: ");
			String newval=scan.next();
			updateDeptLoc(newval,oldval);
		break;
	
		case 3:
			
			System.out.println("Enter the department no. that you want to delete: ");
			int deptnum=scan.nextInt();
			deleteData(deptnum);
		break;
		default:
			System.out.println("Invalid Choice");
		}
		}while(true);
	
	}		
}
