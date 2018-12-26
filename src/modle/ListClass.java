package modle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ListClass {
	
	private static Connection con;
	private static String sql;
	private static PreparedStatement psmt;
	private static ResultSet rs;
	
	public static int checkAdd(String atr) {
		int a=1;
		con=ConnectionManager.getConnection();
		sql="select * from address";
		try {
			boolean flag=false;
			psmt = con.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				if(atr.equals(rs.getString("Add"))) {
					flag=false;
					break;
				}else {
					flag=true;
				}
			}
			if(flag) {
				sql="INSERT INTO address values(?,?)";
				psmt = con.prepareStatement(sql);
				psmt.setString(1, null);
				psmt.setString(2, atr);
				psmt.executeUpdate();
				a=0;
			}else {
				a=1;
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	public static boolean addVotes(String data) {
		JSONArray jsonArr=JSONArray.fromObject(data);
		JSONObject jsonObj=jsonArr.getJSONObject(0);
		int index=jsonObj.getInt("classId");
		//String className=jsonArr.getJSONObject(1).getString("className");
		int votes=jsonArr.getJSONObject(2).getInt("votse")+1;
		con=ConnectionManager.getConnection();
		int row=0;
		sql="update class set votes=? where classId=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, votes);
			psmt.setInt(2, index);
			row = psmt.executeUpdate();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row > 0 ? true : false;
	}
	
	public List<ClassModle> findKey(String key){
		List<ClassModle> list=new ArrayList<ClassModle>();
		con=ConnectionManager.getConnection();
		if(key.isEmpty()) {
			sql="select * from class";
		}else {
			sql="select * from class where className like '%"+key+"%'";
		}
		try {
			psmt = con.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				ClassModle c=new ClassModle();
				c.setClassId(rs.getInt("classId"));
				c.setClassName(rs.getString("className"));
				c.setVotes(rs.getInt("votes"));
				c.setImage(rs.getInt("image"));
				list.add(c);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static JSONArray findList(){
		JSONArray  jsonArr=new JSONArray();
		con=ConnectionManager.getConnection();
		sql="select * from class ";
		try {
			psmt = con.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.accumulate("classId", rs.getInt("classId"));
				obj.accumulate("className", rs.getString("className"));
				obj.accumulate("votse", rs.getInt("votes"));
				obj.accumulate("pic", rs.getInt("image"));
				jsonArr.add(obj);
			}
			rs.close();
			psmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArr;
	}
}
