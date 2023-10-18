package ttp.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MySQLDemo {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/employees?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useServerPrepStmts=true";


    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        Map<String, Integer> typeInfo = new HashMap<>();
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            DatabaseMetaData metaData = conn.getMetaData();
            try (ResultSet resultSet = metaData.getTypeInfo()) {
                while (resultSet.next()) {
                    typeInfo.put(resultSet.getString("TYPE_NAME"), resultSet.getInt("DATA_TYPE"));
                }
            }
//            ResultSet rs = metaData.getTables(null, null, "employees", null);

            String sql = "SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE, COLUMN_KEY, EXTRA, COLLATION_NAME, ORDINAL_POSITION FROM " +
                    "information_schema.columns WHERE TABLE_NAME IN ('employees')";
            stmt = conn.createStatement();
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//                preparedStatement.setString(1, conn.getCatalog());
//            String sql;
//            sql = "SHOW FULL columns from employees";
            ResultSet rs = stmt.executeQuery(sql);
//                ResultSet rs = preparedStatement.executeQuery();
                // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                String field = rs.getString("COLUMN_NAME");
                String type = rs.getString("DATA_TYPE");
                System.out.print("COLUMN_NAME: " + field);
                System.out.print(", DATA_TYPE: " + type);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
//            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
