package com.price.util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RefreshLowestPrice {
    private static Connection conn;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jingdong", "root", "root");
            stmt = conn.createStatement();
            //Statement(");
            pstmt = conn.prepareStatement("UPDATE jingdong.product SET lowest_price = ? WHERE id = ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ResultSet rs = stmt.executeQuery("SELECT product_id,min(price) FROM jingdong.product_price GROUP BY product_id");
        List<DTO> list = new ArrayList<>();
        while (rs.next()) {
            long l = rs.getLong(1);
            float price = rs.getFloat(2);
            //System.out.println(l + "|" + price);
            list.add(new DTO(l, price));
        }
        stmt.close();
        for (DTO d : list) {
            pstmt.setFloat(1,d.price);
            pstmt.setLong(2,d.id);
            System.out.println("id:" + d.id + "|" + "price:" + d.price);
            pstmt.executeUpdate();
        }
        pstmt.close();
        conn.close();
    }

    private static class DTO{
        long id;
        float price;
        public DTO(long id, float price) {
            this.id = id;
            this.price = price;
        }
    }
}
