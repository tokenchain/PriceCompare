package com.price.util;


import java.sql.*;
import java.util.HashMap;
import java.util.Set;

public class CategoryUtil {
    private static CategoryUtil categoryUtil = new CategoryUtil();
    private HashMap<Integer, Category[]> categoryMap;

    private CategoryUtil() {
        try {
            reloadCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CategoryUtil getInstance() {
        return categoryUtil;
    }

    public static void main(String[] args) throws Exception{
        CategoryUtil categoryUtil = CategoryUtil.getInstance();
        Category[] categories = categoryUtil.getCategoryById(672);
        String s = categories[0].getId() + "|" + categories[0].getName() + "|" + categories[0].getParentId() + "|" +
                categories[1].getId() + "|" + categories[1].getName() + "|" + categories[1].getParentId() + "|" +
                categories[2].getId() + "|" + categories[2].getName() + "|" + categories[2].getParentId();
        System.out.println(s);
    }

    /*
    * 重新加载类别信息
    * */
    public synchronized void reloadCategory() throws SQLException,ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jingdong", "root", "root");
        Statement stmt1 = conn.createStatement();
        ResultSet rs = stmt1.executeQuery("SELECT * FROM jingdong.category_type WHERE category_level = 2;");
        categoryMap = new HashMap<>(1500);
        while (rs.next()) {
            Category[] categories = new Category[3];
            categories[0] = new Category(rs.getInt(1), rs.getString(2), rs.getInt(4));
            categoryMap.put(rs.getInt(1), categories);
        }

        PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM jingdong.category_type WHERE id = ?");
        Set<Integer> key = categoryMap.keySet();
        for(Integer k : key) {
            Category[] categories = categoryMap.get(k);
            int parentId = categoryMap.get(k)[0].getParentId();
            pstmt1.setInt(1,parentId);
            ResultSet rs1 = pstmt1.executeQuery();
            while (rs1.next()) {
                categories[1] = new Category(rs1.getInt(1), rs1.getString(2), rs1.getInt(4));
            }
        }

        for(Integer k : key) {
            Category[] categories = categoryMap.get(k);
            int parentId = categoryMap.get(k)[1].getParentId();
            pstmt1.setInt(1,parentId);
            ResultSet rs2 = pstmt1.executeQuery();
            while (rs2.next()) {
                categories[2] = new Category(rs2.getInt(1), rs2.getString(2), rs2.getInt(4));
            }
        }

        for(Integer k : key) {
            Category[] categories = categoryMap.get(k);
            String s = categories[0].getId() + "|" + categories[0].getName() + "|" + categories[0].getParentId() + "|" +
                    categories[1].getId() + "|" + categories[1].getName() + "|" + categories[1].getParentId() + "|" +
                    categories[2].getId() + "|" + categories[2].getName() + "|" + categories[2].getParentId();
            System.out.println(s);
        }

    }

    /*
    * 通过类别Id获取类别信息
    * Category[0]三级类信息
    * Category[1]二级类信息
    * Category[2]一级类信息
    * */
    public synchronized  Category[] getCategoryById(int id) {
        return categoryMap.get(id);
    }

    public class Category {
        private int id;
        private String name;
        private int parentId;
        public Category() {}
        public Category(int id, String name, int parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }
    }
}
