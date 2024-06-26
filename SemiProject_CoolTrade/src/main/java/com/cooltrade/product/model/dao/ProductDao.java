package com.cooltrade.product.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.cooltrade.common.JDBCTemplate.*;

import com.cooltrade.common.PageInfo;
import com.cooltrade.member.model.vo.BankAccount;
import com.cooltrade.member.model.vo.DeliveryAddress;
import com.cooltrade.product.model.dao.ProductDao;
import com.cooltrade.product.model.vo.Category;
import com.cooltrade.product.model.vo.Images;
//import com.cooltrade.product.model.vo.LikeProduct;
import com.cooltrade.product.model.vo.Product;
import com.cooltrade.product.model.vo.Search;

public class ProductDao {
   Properties prop = new Properties();

   public ProductDao() {
      String filePath = ProductDao.class.getResource("/db/sql/product-mapper.xml").getPath();
      try {
         prop.loadFromXML(new FileInputStream(filePath));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public Product countSalesRate(Connection conn) {
      Product p = new Product();
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String sql = prop.getProperty("countSalesRate");

      try {
         pstmt = conn.prepareStatement(sql);

         rset = pstmt.executeQuery();

         if (rset.next()) {
            p.setSalesRate(rset.getInt("salesRate"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return p;
   }

   public Product todayStockGoods(Connection conn) {
      Product p = new Product();
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String sql = prop.getProperty("todayStockGoods");

      try {
         pstmt = conn.prepareStatement(sql);

         rset = pstmt.executeQuery();

         if (rset.next()) {
            p.setTstockgoods(rset.getInt("tstockgoods"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return p;
   }

   public Product countReportedProduct(Connection conn) {
      Product p = new Product();
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String sql = prop.getProperty("countReportedProduct");

      try {
         pstmt = conn.prepareStatement(sql);

         rset = pstmt.executeQuery();

         if (rset.next()) {
            p.setReportedProduct(rset.getInt("reportedproduct"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return p;
   }

   public int selectProductCount(Connection conn) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      int productCount = 0;
      String sql = prop.getProperty("selectProductCount");

      try {
         pstmt = conn.prepareStatement(sql);

         rset = pstmt.executeQuery();

         if (rset.next()) {
            productCount = rset.getInt("count");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return productCount;
   }

   public ArrayList<Product> selectRandomProduct(Connection conn) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<Product> list = new ArrayList<Product>();

      String sql = prop.getProperty("selectRandomProduct");

      try {
         pstmt = conn.prepareStatement(sql);

         rset = pstmt.executeQuery();

         while (rset.next()) {
            Product p = new Product();
            p.setProductNo(rset.getInt("product_no"));
            p.setSellerNo(rset.getString("seller_no"));
            p.setProductName(rset.getString("product_name"));
            p.setZone(rset.getString("zone"));
            p.setPrice(rset.getInt("price"));
            p.setUploadDate(rset.getString("upload_date"));
            p.setStrPrice(rset.getString("str_price"));
            p.setTimeDiff(rset.getString("time_diff"));

            list.add(p);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return list;
   }

   public ArrayList<Category> selectCategoryList(Connection conn) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<Category> list = new ArrayList<Category>();

      String sql = prop.getProperty("selectCategoryList");

      try {
         pstmt = conn.prepareStatement(sql);

         rset = pstmt.executeQuery();

         while (rset.next()) {
            list.add(new Category(rset.getString("category_no"), rset.getString("category_name"),
                  rset.getInt("count")));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return list;
   }

   public ArrayList<Product> searchProductList(Connection conn, String search) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<Product> plist = new ArrayList<Product>();
      String sql = prop.getProperty("searchProductList");

      try {
         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, search);
         pstmt.setString(2, search);

         rset = pstmt.executeQuery();

         while (rset.next()) {
            Product p = new Product();
            p.setProductNo(rset.getInt("product_no"));
            p.setSellerNo(rset.getString("seller_no"));
            p.setProductName(rset.getString("product_name"));
			p.setStrPrice(rset.getString("str_price"));
            p.setZone(rset.getString("zone"));
            p.setUploadDate(rset.getString("upload_date"));
            p.setTimeDiff(rset.getString("time_diff"));
            
            plist.add(p);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return plist;
   }

   public ArrayList<Product> searchProductListWithCno(Connection conn, String search, String cno) {
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   ArrayList<Product> plist = new ArrayList<Product>();
	   String sql = prop.getProperty("searchProductListWithCno");
	   
	   try {
		   pstmt = conn.prepareStatement(sql);
		   
		   pstmt.setString(1, search);
		   pstmt.setString(2, search);
		   pstmt.setString(3, cno);
		   
		   rset = pstmt.executeQuery();
		   
		   while (rset.next()) {
			   Product p = new Product();
			   p.setProductNo(rset.getInt("product_no"));
			   p.setSellerNo(rset.getString("seller_no"));
			   p.setProductName(rset.getString("product_name"));
			   p.setStrPrice(rset.getString("str_price"));
			   p.setZone(rset.getString("zone"));
			   p.setUploadDate(rset.getString("upload_date"));
			   p.setTimeDiff(rset.getString("time_diff"));
			   
			   plist.add(p);
		   }
	   } catch (SQLException e) {
		   e.printStackTrace();
	   } finally {
		   close(rset);
		   close(pstmt);
	   }
	   return plist;
   }

   public ArrayList<Category> searchCatList(Connection conn, String search) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<Category> catList = new ArrayList<Category>();
      String sql = prop.getProperty("searchCatList");

      try {
         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, search);
         pstmt.setString(2, search);

         rset = pstmt.executeQuery();

         while (rset.next()) {
            Category c = new Category();
            c.setCategoryName(rset.getString("category_name"));
            c.setCategoryNo(rset.getString("category_no"));
            c.setCategoryCount(rset.getInt("count"));

            catList.add(c);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return catList;
   }

   public ArrayList<Category> searchCatListWithCno(Connection conn, String search, String cno) {
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   ArrayList<Category> catList = new ArrayList<Category>();
	   String sql = prop.getProperty("searchCatListWithCno");
	   
	   try {
		   pstmt = conn.prepareStatement(sql);
		   
		   pstmt.setString(1, search);
		   pstmt.setString(2, search);
		   pstmt.setString(3, cno);
		   
		   rset = pstmt.executeQuery();
		   
		   while (rset.next()) {
			   Category c = new Category();
			   c.setCategoryName(rset.getString("category_name"));
			   c.setCategoryNo(rset.getString("category_no"));
			   c.setCategoryCount(rset.getInt("count"));
			   
			   catList.add(c);
		   }
	   } catch (SQLException e) {
		   e.printStackTrace();
	   } finally {
		   close(rset);
		   close(pstmt);
	   }
	   return catList;
   }

   public int countProduct(Connection conn, String search) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      int pCount = 0;
      String sql = prop.getProperty("countProduct");

      try {
         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, search);
         pstmt.setString(2, search);

         rset = pstmt.executeQuery();

         if (rset.next()) {
            pCount = rset.getInt("count");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return pCount;
   }

   public int countProductWithCno(Connection conn, String search, String cno) {
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   int pCount = 0;
	   String sql = prop.getProperty("countProductWithCno");
	   
	   try {
		   pstmt = conn.prepareStatement(sql);
		   
		   pstmt.setString(1, search);
		   pstmt.setString(2, search);
		   pstmt.setString(3, cno);
		   
		   rset = pstmt.executeQuery();
		   
		   if (rset.next()) {
			   pCount = rset.getInt("count");
		   }
	   } catch (SQLException e) {
		   e.printStackTrace();
	   } finally {
		   close(rset);
		   close(pstmt);
	   }
	   return pCount;
   }
   
   public ArrayList<Product> selectRecentList(Connection conn){
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<Product> recentList = new ArrayList<Product>();
      String sql = prop.getProperty("selectRecentList");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            Product p = new Product();
            p.setProductNo(rset.getInt("product_no"));
            p.setSellerNo(rset.getString("seller_no"));
            p.setProductName(rset.getString("product_name"));
            p.setPrice(rset.getInt("price"));
            p.setZone(rset.getString("zone"));
            p.setUploadDate(rset.getString("upload_date"));
            p.setTimeDiff(rset.getString("time_diff"));
            p.setStrPrice(rset.getString("str_price"));
            
            recentList.add(p);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      return recentList;
   }
   
   public Product selectProductDetail(Connection conn, int pno) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = prop.getProperty("selectProductDetail");
      Product p = new Product();
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, pno);
         
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            p.setProductNo(rset.getInt("product_no"));
            p.setSellerNum(rset.getInt("seller_no"));
            p.setSellerNo(rset.getString("user_id"));
            p.setNickName(rset.getString("nickname"));
            p.setCategoryNo(rset.getString("category_name"));
            p.setProductName(rset.getString("product_name"));
            p.setPrice(rset.getInt("price"));
            p.setProductDesc(rset.getString("product_desc"));
            p.setZone(rset.getString("zone"));
            p.setOndo(rset.getDouble("ondo"));
            p.setProductStatus(rset.getString("pstatus"));
            p.setTradeType(rset.getInt("trade_type"));
            p.setUploadDate(rset.getString("upload_date"));
            p.setUploadType(rset.getString("upload_type"));
            p.setCount(rset.getInt("count"));
            p.setTimeDiff(rset.getString("time_diff"));
            p.setStrPrice(rset.getString("str_price"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      return p;
   }
   
   public ArrayList<Product> selectMonthSales(Connection conn){
      ArrayList<Product> list = new ArrayList<Product>();
      Product p = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectMonthSales");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            list.add(new Product(rset.getInt("price"),
                            rset.getString("trade_date")
                            ));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      return list;
   }
   
   public ArrayList<Product> selectCategorySales(Connection conn){
      ArrayList<Product> list = new ArrayList<Product>();
      Product p = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectCategorySales");
      
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            list.add(new Product(rset.getString("category_name"),
                            rset.getInt("count")
                            ));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      return list;
   }
   
   public int increaseCount(Connection conn, int pno) {
      PreparedStatement pstmt = null; 
      int result = 0;
      String sql = prop.getProperty("increaseCount");
      
      try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, pno);
            
            result = pstmt.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
         }finally {
            close(pstmt);
         }
         return result;
      
   }   

   public int insertPopularWord(Connection conn, String search) {
      int result = 0;
      
      PreparedStatement pstmt = null;
      
      String sql = prop.getProperty("insertPopularWord");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, search);
         
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      return result;
   }
   
   public ArrayList<Images> selectImages(Connection conn, int pno) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<Images> imglist = new ArrayList<Images>();
      String sql = prop.getProperty("selectImages");
      
      try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, pno);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
               Images img = new Images();
               img.setRefPno(rset.getInt("ref_pno"));
               img.setImgNo(rset.getInt("img_no"));
               img.setOriginName(rset.getString("origin_name"));
               img.setChangeName(rset.getString("change_name"));
               img.setImgPath(rset.getString("img_path"));
               
               imglist.add(img);
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }finally {
            close(rset);
            close(pstmt);
         }
         return imglist;
   }
   
   public ArrayList<Search> selectPopularWord(Connection conn) {
      ArrayList<Search> list = new ArrayList<Search>();
      
      PreparedStatement pstmt = null;
      
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectPopularWord");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            
            list.add(new Search(rset.getString("popw_word"),
                           		rset.getInt("wordcnt")));            
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      return list;
   }
   
   public ArrayList<Product> searchKeywords(Connection conn, ArrayList<String> extractedKeywords, String cpCategory, int pno){
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<Product> plist = new ArrayList<Product>();
      
      StringBuilder sql = new StringBuilder("SELECT * FROM (");
      sql.append("SELECT PRODUCT_NO, PRODUCT_NAME, ROWNUM RNUM FROM PRODUCT JOIN CATEGORY USING (CATEGORY_NO) WHERE (");
      for(int i = 0; i < extractedKeywords.size(); i++) {
          sql.append("PRODUCT_NAME LIKE '%'|| ? || '%' OR PRODUCT_DESC LIKE '%'|| ? || '%'");
          if(i < extractedKeywords.size() - 1) {
              sql.append(" OR ");
          }
      }
      sql.append(") AND CATEGORY_NAME = '").append(cpCategory).append("' ");
      sql.append("AND PRODUCT_NO != ? ");
      sql.append("ORDER BY DBMS_RANDOM.VALUE");
      sql.append(") WHERE RNUM <= 20");
      
      try {
         pstmt = conn.prepareStatement(sql.toString());
         
         int last = extractedKeywords.size() * 2 + 1;
         for(int i = 0, j = 1; i < extractedKeywords.size(); i++) {
            pstmt.setString(j++, extractedKeywords.get(i));
                pstmt.setString(j++, extractedKeywords.get(i));
         }
         pstmt.setInt(last, pno);

         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            Product p = new Product();
            p.setProductNo(rset.getInt("product_no"));
            p.setProductName(rset.getString("product_name"));
            plist.add(p);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }   
      return plist;
   }
   
   public int countPopularWord(Connection conn) {
      int endValue = 0;
      
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("countPopularWord");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            endValue = rset.getInt("count");
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      return endValue;
   }
   
   public ArrayList<Images> getTitleImg(Connection conn, ArrayList<Product> plist){
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = prop.getProperty("getTitleImg");
      ArrayList<Images> imglist = new ArrayList<Images>();
      try {
         for(Product p : plist) {
            pstmt = conn.prepareStatement(sql);
         
            pstmt.setInt(1, p.getProductNo());
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
               Images img = new Images();
               img.setImgNo(rset.getInt("img_no"));
               img.setRefPno(rset.getInt("ref_pno"));
               img.setOriginName(rset.getString("origin_name"));
               img.setChangeName(rset.getString("change_name"));
               img.setImgPath(rset.getString("img_path"));
               
               imglist.add(img);
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      return imglist;
   }
   
   public int insertProductSell(Connection conn, Product p) {
      int result = 0;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("insertProductSell");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, Integer.parseInt(p.getSellerNo()));
         pstmt.setString(2, p.getCategoryNo());
         pstmt.setString(3, p.getProductName());
         pstmt.setInt(4, p.getPrice());
         pstmt.setString(5, p.getProductDesc());
         pstmt.setInt(6, p.getPieces());
         pstmt.setString(7, p.getZone());
         pstmt.setString(8, p.getProductStatus());
         pstmt.setInt(9, p.getDeliveryCharge());
         pstmt.setInt(10, p.getTradeType());
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;

   }
   
   public int insertImagesList(Connection conn, ArrayList<Images> list) {
      int result = 0;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("insertImagesList");
      
      try {
         
         for(Images img : list) {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, img.getImgLevel());
            pstmt.setString(2, img.getOriginName());
            pstmt.setString(3, img.getChangeName());
            pstmt.setString(4, img.getImgPath());
            
            result = pstmt.executeUpdate();
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
      
   }
   
   public int deletePopularSearch(Connection conn) {
      int result = 0;
      
      PreparedStatement pstmt = null;
      
      String sql = prop.getProperty("deletePopularSearch");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      return result;
      
   }
   
   public int insertPopularSearch(Connection conn, String[] list) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String sql = prop.getProperty("insertPopularSearch");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         for(String s : list) {
            pstmt.setString(1, s );
            result = pstmt.executeUpdate();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      return result;
   }
   
   public ArrayList<Product> selectRecommendProduct(Connection conn, int pno){
      
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<Product> plist = new ArrayList<Product>();
      String sql = prop.getProperty("selectRecommendProduct");
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, pno);
         rset = pstmt.executeQuery();
         while(rset.next()) {
            Product p = new Product();
             p.setProductNo(rset.getInt("product_no"));
             p.setProductName(rset.getString("product_name"));
           
             plist.add(p);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
         // TODO Auto-generated catch block
      }
      return plist;
   }

   
   public int countUserPopwList(Connection conn) {
      int result = 0;
      
      PreparedStatement pstmt = null;
      
      ResultSet rset = null;
      
      String sql = prop.getProperty("countUserPopwList");
      try {
         pstmt = conn.prepareStatement(sql);
         
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            result = rset.getInt("count");
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt); 
      }
      return result;
      
   }
   
   public ArrayList<Search> selectUserPopwList(Connection conn, PageInfo pi){
      ArrayList<Search> list = new ArrayList<Search>();
      Search s = null;
      PreparedStatement pstmt = null;
      
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectUserPopwList");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         
         int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
         int endRow = startRow + pi.getBoardLimit() - 1;
         
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            
            list.add(new Search(rset.getString("user_popw_word"),
                           rset.getInt("user_popw_order")
                           ));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      
      return list;
      
   }
   
   public ArrayList<Product> selectDaySales(Connection conn){
      ArrayList<Product> list = new ArrayList<Product>();
      Product p = null;
      PreparedStatement pstmt = null;
      
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectDaySales");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            list.add(new Product(rset.getInt("price"),
                            rset.getString("trade_date")
                            ));
         }
         
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(pstmt);
      }
      
      return list;
   }
   
    public ArrayList<Product> updateSellForm(Connection conn, int pno) {
      ArrayList<Product> pList = new ArrayList<Product>();
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = prop.getProperty("updateSellForm");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, pno);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            Product p = new Product();
            p.setProductNo(rset.getInt("product_no"));
            p.setCategoryNo(rset.getString("category_no"));
            p.setCategoryName(rset.getString("category_name"));
            p.setProductName(rset.getString("product_name"));
            p.setPrice(rset.getInt("price"));
            p.setProductDesc(rset.getString("product_desc"));
            p.setPieces(rset.getInt("pieces"));
            p.setZone(rset.getString("zone"));
            p.setProductStatus(rset.getString("product_status"));
            p.setDeliveryCharge(rset.getInt("delivery_charge"));
            p.setTradeType(rset.getInt("trade_type"));
            p.setTitleImg(rset.getString("titleimg"));
            
            pList.add(p);
            
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      //System.out.println(pList);
      return pList;
   }
   
   public int updateProductSell(Connection conn, Product p, int pno) {
      int result = 0;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("updateProductSell");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, p.getCategoryNo());
         pstmt.setString(2, p.getProductName());
         pstmt.setInt(3, p.getPrice());
         pstmt.setString(4, p.getProductDesc());
         pstmt.setInt(5, p.getPieces());
         pstmt.setString(6, p.getZone());
         pstmt.setString(7, p.getProductStatus());
         pstmt.setInt(8, p.getDeliveryCharge());
         pstmt.setInt(9, p.getTradeType());
         pstmt.setInt(10, pno);
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      System.out.println("상품수정 다오" + result);
      return result;
   }
   
   public int updateImagesList(Connection conn, ArrayList<Images> list) {
      int result = 0;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("updateImagesList");
      
      try {
         
         for(Images img : list) {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, img.getImgLevel());
            pstmt.setString(2, img.getOriginName());
            pstmt.setString(3, img.getChangeName());
            pstmt.setString(4, img.getImgPath());
            
            result = pstmt.executeUpdate();
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }
   
   public int insertNewImagesList(Connection conn, ArrayList<Images> list, int pno, int userNo) {
      int result = 0;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("insertNewImagesList");
      
      try {
         
         for(Images img : list) {
        	 System.out.println(img);
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, pno);
            pstmt.setInt(2, img.getImgLevel());
            pstmt.setString(3, img.getOriginName());
            pstmt.setString(4, img.getChangeName());
            pstmt.setString(5, img.getImgPath());
            
            result = pstmt.executeUpdate();
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      System.out.println("이미지수정다오:" + result);
      return result;
   }
   
   public int deleteProductSellImage(Connection conn, int pno) {
      int result = 0;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("deleteProductSellImage");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, pno);
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      System.out.println("이미지삭제다오 : " + result);
      return result;
   }
   
   public int likeInsert(Connection conn, int uno, int pno) {
      int result = 0;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("likeInsert");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, uno);
         pstmt.setInt(2, pno);
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }
   
   public int selectLikeCount(Connection conn, int pno) {
      int likeCount = 0;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = prop.getProperty("selectLikeCount");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, pno);
         
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            likeCount = rset.getInt("count");
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return likeCount;
   }

   public ArrayList<Product> searchByCategory(Connection conn, String cno){
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchByCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setStrPrice(rset.getString("str_price"));
				p.setZone(rset.getString("zone"));
				p.setTimeDiff(rset.getString("time_diff"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;

   }
   public int deleteBoard(Connection conn ,int pno) {
	      int result = 0;
	      PreparedStatement pstmt = null;
	      String sql = prop.getProperty("deleteBoard");
	      try{
	      pstmt= conn.prepareStatement(sql);
	         pstmt.setInt(1, pno);
	         System.out.println(pno);
	         result = pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close(pstmt);
	      }
	      return result;
   }
	
	public String getCategoryName(Connection conn, String cno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getCategoryName");
		String CategoryName = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cno);
	
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				CategoryName = rset.getString("category_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return CategoryName;
	}
	
	public int countProductByCat(Connection conn, String cno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("countProductByCat");
		int pCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pCount = rset.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return pCount;
	}
	
	public ArrayList<Product> arrangeByDateC(Connection conn, String cno){
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("arrangeByDateC");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setStrPrice(rset.getString("str_price"));
				p.setZone(rset.getString("zone"));
				p.setTimeDiff(rset.getString("time_diff"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Product> arrangePriceHighC(Connection conn, String cno){
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("arrangePriceHighC");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setStrPrice(rset.getString("str_price"));
				p.setZone(rset.getString("zone"));
				p.setTimeDiff(rset.getString("time_diff"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Product> arrangePriceLowC(Connection conn, String cno){
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("arrangePriceLowC");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setStrPrice(rset.getString("str_price"));
				p.setZone(rset.getString("zone"));
				p.setTimeDiff(rset.getString("time_diff"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Product> arrangeByDateS(Connection conn, String search, String cno){
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("arrangeByDateS");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, cno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setStrPrice(rset.getString("str_price"));
				p.setZone(rset.getString("zone"));
				p.setTimeDiff(rset.getString("time_diff"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Product> arrangePriceHighS(Connection conn, String search, String cno){
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("arrangePriceHighS");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, cno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setStrPrice(rset.getString("str_price"));
				p.setZone(rset.getString("zone"));
				p.setTimeDiff(rset.getString("time_diff"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Product> arrangePriceLowS(Connection conn, String search, String cno){
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("arrangePriceLowS");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, cno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setStrPrice(rset.getString("str_price"));
				p.setZone(rset.getString("zone"));
				p.setTimeDiff(rset.getString("time_diff"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Product> arrangeByDateS2(Connection conn, String search){
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("arrangeByDateS2");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setStrPrice(rset.getString("str_price"));
				p.setZone(rset.getString("zone"));
				p.setTimeDiff(rset.getString("time_diff"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Product> arrangePriceHighS2(Connection conn, String search){
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("arrangePriceHighS2");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setStrPrice(rset.getString("str_price"));
				p.setZone(rset.getString("zone"));
				p.setTimeDiff(rset.getString("time_diff"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Product> arrangePriceLowS2(Connection conn, String search){
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("arrangePriceLowS2");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setStrPrice(rset.getString("str_price"));
				p.setZone(rset.getString("zone"));
				p.setTimeDiff(rset.getString("time_diff"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public boolean checkLike(Connection conn, int uno, int pno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean checkFlag = false;
		
		String sql = prop.getProperty("checkLike");
      try {
			pstmt = conn.prepareStatement(sql);
         	pstmt.setInt(1, uno);
			pstmt.setInt(2, pno);
         rset = pstmt.executeQuery();
			
			if(rset.next()) {
            checkFlag = true;
         	}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
      return checkFlag;
		
	}
	public int getReportedUserNo(Connection conn, int pno) {
		System.out.println(pno);
		System.out.println("오냐 11");
		int userNo = 0;
		PreparedStatement pstmt = null;
		System.out.println("오냐 22");
		ResultSet rset = null;
		String sql = prop.getProperty("getReportedUserNo");
		System.out.println("오냐 33");
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("오냐44");
         pstmt.setInt(1, pno);
         rset = pstmt.executeQuery();
			
			if(rset.next()) {
            userNo = rset.getInt("seller_no");
         	}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
      return userNo;
		
	}		
	
	public String checkYn(Connection conn, int uno, int pno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String yn = "";
		String sql = prop.getProperty("checkYn");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, uno);
			pstmt.setInt(2, pno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				yn = rset.getString("like_status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return yn;
	}
	
	public int updateYesLike(Connection conn, int uno, int pno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateYesLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, uno);
			pstmt.setInt(2, pno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateNoLike(Connection conn, int uno, int pno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateNoLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, uno);
			pstmt.setInt(2, pno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<DeliveryAddress> getAddressList(Connection conn, int uno) {
		ArrayList<DeliveryAddress> addressList = new ArrayList<DeliveryAddress>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getAddressList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DeliveryAddress da = new DeliveryAddress();
				da.setDeliveryAddressNo(rset.getInt("delivery_address_no"));
				da.setUserNo(rset.getInt("user_no"));
				da.setAddress(rset.getString("address"));
				da.setDetail(rset.getString("detail"));
				da.setPostcode(rset.getString("postcode"));
				da.setTitle(rset.getString("title"));
				da.setName(rset.getString("name"));
				da.setPhone(rset.getString("phone"));
				da.setMainYn(rset.getString("main_yn"));
				
				addressList.add(da);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return addressList;
		
	}
	
	public BankAccount getBankList(Connection conn, int uno) {
		BankAccount bankList = new BankAccount();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getBankList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				bankList.setBankaccountNo(rset.getInt("bankaccount_no"));
				bankList.setUserNo(rset.getInt("user_no"));
				bankList.setBank(rset.getString("bank"));
				bankList.setAccount(rset.getString("account"));
				bankList.setUserName(rset.getString("user_name"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return bankList;
	}
	
	public int insertTrade(Connection conn, int pno, int buyerNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertTrade");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			pstmt.setInt(2, buyerNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updatePoStatus(Connection conn, int pno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePoStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int endTrade (Connection conn, int pno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("endTrade");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int rollbackTrade (Connection conn, int pno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("rollbackTrade");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int reduceCount(Connection conn, int pno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("reduceCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
		
	}
}