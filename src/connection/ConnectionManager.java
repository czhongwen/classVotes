package connection;

import java.sql.*;

/** @pdOid 21ac2134-f474-4a66-ae78-d916d5727769 */
public class ConnectionManager {
   /** @pdOid 922852cc-30c6-4bd8-b4dd-7d17fd998145 */
   private static String driver="com.mysql.jdbc.Driver";
   /** @pdOid 657a7a08-0fa6-41eb-831a-f0cd9d7c1503 */
   private static String url="jdbc:mysql://localhost:3306/classemb?useUnicode=true&amp;characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull&amp;transformedBitIsBoolean=true";
   /** @pdOid 9daa5231-89a9-4647-a74b-61ca96dfae6f */
   private static String userName="root";
   /** @pdOid 86b572b9-b6a3-4554-b593-d03d4a39854e */
   private static String pwd="root";
   /** @pdOid b6f7db99-7fa2-4f5f-bba0-b436eaaeb6d9 */
   private static Connection con;
   
   /** 初始化私有属性，加载驱动
    * 
    * 
    * @pdOid 92b585ed-6e46-4c62-ac83-708443179f1e */
   public ConnectionManager() {}
   
   /** @pdOid d9c8bfa2-78e8-49c1-b55b-97c400a9d522 */
   public static Connection getConnection() {
	   try {
  			Class.forName(driver);
  		} catch (ClassNotFoundException e) {
  			e.printStackTrace();
  		}
   	try {
   		con = DriverManager.getConnection(url,userName,pwd);
   	} catch (SQLException e) {
   		e.printStackTrace();
   	}
   	return con;		
   }
   
   /** @pdOid ca48995d-adc6-4e7a-acb8-40b1722777d0 */
   public static void close() {
   	try {
   		con.close();
   	} catch (SQLException e) {
   		e.printStackTrace();
   	}
   }

}