package addr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class AddrMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);		
		
		while(true) {
			System.out.print("명령어를 입력해주세요 : ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("add")) {
				System.out.print("이름 : ");
				String name2 = sc.nextLine();
				System.out.print("주소 : ");
				String address = sc.nextLine();
				System.out.print("전화번호 : ");
				String phone = sc.nextLine();
				
				insertAddress(name2, address, phone);
				
			} else if(cmd.equals("list")) {
				ArrayList<Addr> addrList = selectAddresses();
				printAddr(addrList);				
			} else if(cmd.equals("update")) {
				System.out.print("수정할 주소록 번호 : ");
				int idx = Integer.parseInt(sc.nextLine());
				
				System.out.print("이름 : ");
				String name2 = sc.nextLine();
				System.out.print("주소 : ");
				String address = sc.nextLine();
				System.out.print("전화번호 : ");
				String phone = sc.nextLine();
				
				updateAddress(idx, name2, address, phone);
			} else if(cmd.equals("delete")) {
				System.out.print("삭제할 주소록 번호 : ");
				int idx = Integer.parseInt(sc.nextLine());
				
				deleteAddress(idx);
			} else if(cmd.equals("search")) {
				
				System.out.print("검색할 이름을 입력해주세요 : ");
				String searchName = sc.nextLine();
				
				ArrayList<Addr> addrList = selectAddressBySearch(searchName);
				printAddr(addrList);
				
			}
			
		}
		
	}

	public static void printAddr(ArrayList<Addr> addrList) {
		System.out.println("========= 주소록 목록 =========");
		for(int i = 0; i < addrList.size(); i++) {
			Addr addr = addrList.get(i);
			System.out.println("번호 : " + addr.getIdx());
			System.out.println("이름 : "  + addr.getName());
			System.out.println("주소 : "  + addr.getAddress());
			System.out.println("전화번호 : " + addr.getPhone());
			System.out.println("==============================");
		}
	}
	
	public static Connection getConnection() {
		
		String url = "jdbc:mysql://localhost:3306/a1?serverTimezone=UTC";
		String user = "root";
		String pass = "";
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);			
		} catch (Exception e) {
			System.out.println("접속 시도중 문제 발생!!");
		}
		
		return conn;
	}
	public static void deleteAddress(int idx) {

		try {
			
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();

			String sql = "DELETE FROM t_addr WHERE idx = " + idx;
;
			System.out.println("sql : " + sql);
			stmt.executeUpdate(sql);

			if(stmt != null) {
				stmt.close();				
			}
			if(conn != null) {
				conn.close();				
			}
			
		} catch (Exception e) {
			System.out.println("접속 시도중 문제 발생!!");
		}
	}
	
	public static void updateAddress(int idx, String name2, String address, String phone) {

		try {
			
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();

			String sql = "UPDATE t_addr SET name2 = '" + name2 + "', address = '" + address + "', phone = '" + phone + "' WHERE idx = " + idx;
			System.out.println("sql : " + sql);
			stmt.executeUpdate(sql);

			if(stmt != null) {
				stmt.close();				
			}
			if(conn != null) {
				conn.close();				
			}
			
		} catch (Exception e) {
			System.out.println("접속 시도중 문제 발생!!");
		}
	}
	
	public static void insertAddress(String name2, String address, String phone) {

		try {
			
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();

			String sql = "INSERT INTO t_addr SET name2 = '" + name2 + "', address = '" + address + "', phone = '" + phone + "'";
			System.out.println("sql : " + sql);
			stmt.executeUpdate(sql);

			if(stmt != null) {
				stmt.close();				
			}
			if(conn != null) {
				conn.close();				
			}
			
		} catch (Exception e) {
			System.out.println("접속 시도중 문제 발생!!");
		}
	}

	
	public static ArrayList<Addr> selectAddresses() {

		ArrayList<Addr> addrList = new ArrayList<>();
			
		try {

			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM t_addr";
			
			System.out.println("sql : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) { // 다음 로우로 이동 다음이 있으면 true 반환, 없으면 false 반환
				
				int idx = rs.getInt("idx");
				String name2 = rs.getString("name2");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				
				Addr a1 = new Addr(idx, name2, address, phone);				
				addrList.add(a1);
			}
						
			if(rs != null) {
				rs.close();				
			}
			if(stmt != null) {
				stmt.close();				
			}
			if(conn != null) {
				conn.close();				
			}
			
		} catch (Exception e) {
			System.out.println("접속 시도중 문제 발생!!");
		}
		
		return addrList;
	}
	
	public static ArrayList<Addr> selectAddressBySearch(String searchName) {

		ArrayList<Addr> addrList = new ArrayList<>();
			
		try {

			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM t_addr where name like '%" + searchName + "%'";
			
			System.out.println("sql : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) { // 다음 로우로 이동 다음이 있으면 true 반환, 없으면 false 반환
				
				int idx = rs.getInt("idx");
				String name2 = rs.getString("name2");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				
				Addr a1 = new Addr(idx, name2, address, phone);				
				addrList.add(a1);
			}
						
			if(rs != null) {
				rs.close();				
			}
			if(stmt != null) {
				stmt.close();				
			}
			if(conn != null) {
				conn.close();				
			}
			
		} catch (Exception e) {
			System.out.println("접속 시도중 문제 발생!!");
		}
		
		return addrList;
	}
}
