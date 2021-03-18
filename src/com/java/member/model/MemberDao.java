package com.java.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.database.ConnectionProvider;
import com.java.database.jdbcUtil;

public class MemberDao {
	// Data Access Object
	// Singleton pattern : 단 한개의 객체만을 가지고 구현한다.
	// 어플리케이션에서 어떠한 클래스가 단 한번만 메모리를 할당해 그 메모리 내에서 객체를 만들어 사용하는 방법
	private static MemberDao instance = new MemberDao(); // A a = new A() 상수

	public static MemberDao getInstance() {
		return instance;
	}

	public int insert(MemberDto memberDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int value = 0;

		try {
			String sql = "insert into member values(member_num_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPassword());
			pstmt.setString(3, memberDto.getName());
			pstmt.setString(4, memberDto.getJumin1());
			pstmt.setString(5, memberDto.getJumin2());
			pstmt.setString(6, memberDto.getEmail());
			pstmt.setString(7, memberDto.getZipcode());
			pstmt.setString(8, memberDto.getAddress());
			pstmt.setString(9, memberDto.getJob());
			pstmt.setString(10, memberDto.getMailing());
			pstmt.setString(11, memberDto.getInterest());
			pstmt.setString(12, memberDto.getMemberLevel());

			value = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}

		return value;
	}

	public int idCheck(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int value = 0;

		try {
			String sql = "select id from member where id=?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				value = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}

		return value;
	}

	public String loginCheck(String id, String password) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String value = null;

		try {
			String sql = "select member_level from member where id=? and password=?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				value = rs.getString("member_level");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}

		return value;
	}

	public int delet(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int value = 0;
		try {
			String sql = "delete from member where id=?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				value = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}

		return value;
	}

	public MemberDto update(String id) {
		MemberDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {			
			String sql = "select id, password, name, jumin1, jumin2, email, zipcode, address, job, mailing, interest from member where id=?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, id);			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setJumin1(rs.getString("jumin1"));
				dto.setJumin2(rs.getString("jumin2"));
				dto.setEmail(rs.getString("email"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress(rs.getString("address"));
				dto.setJob(rs.getString("job"));
				dto.setMailing(rs.getString("mailing"));
				dto.setInterest(rs.getString("interest"));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		return dto;
	}

	public int updateOk(String id, MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int value = 0;
		
		try {
			String sql = "update member set password=?,"
					+ "email=?,zipcode=?,address=?,job=?,mailing=?,interest=? where id=?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getZipcode());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getJob());
			pstmt.setString(6, dto.getMailing());
			pstmt.setString(7, dto.getInterest());
			pstmt.setString(8, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				value = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return value;
	}

}