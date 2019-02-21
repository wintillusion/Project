package db;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	
	public static Connection GetConnection() {
		Connection con = null;
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/dogtest");
			con = ds.getConnection();
			con.setAutoCommit(false);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static Connection getConnection() {		//컨넥션 리턴해줌. 그러면서 컨넥션 객체가 생성.
		Connection con=null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/MySQLDB");
			con = ds.getConnection();
			con.setAutoCommit(false);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		try {
			con.commit();
			System.out.println("commit success");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("rollback success");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
