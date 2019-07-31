package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class MemberDao {
	private static MemberDao instance;
	private MemberDao() {}
	public static MemberDao getInstance() { return LazyHolder.INSTANCE; }
	private static class LazyHolder { private static final MemberDao INSTANCE = new MemberDao(); }
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int check(String id, String password) throws SQLException{
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPassword = null;
		String sql1 = "select passwd from member2 where id = ?"; 
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbPassword = rs.getString(1);
				if(dbPassword.equals(password)) {
					rs.close(); pstmt.close();
					result = 1;
				}else result = 0;
			}else result = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}
	
	public List<Member> list() throws SQLException {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select * from member2";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setTel(rs.getString("tel"));
				member.setReg_date(rs.getDate("reg_date"));
				list.add(member);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	
	
}
