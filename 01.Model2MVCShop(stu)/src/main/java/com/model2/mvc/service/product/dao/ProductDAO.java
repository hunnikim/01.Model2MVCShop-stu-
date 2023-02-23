package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.product.*;

public class ProductDAO {
	
	public ProductDAO(){
	}

	public void insertProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into Product values (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, productVO.getProdName()); //제품명
		stmt.setString(2, productVO.getProdDetail());// 상품상세정보
		//파싱된값을  넣어준다 2번에 
		stmt.setString(3, productVO.getManuDate().replaceAll("-","")); //제조일자	
		stmt.setInt(4, productVO.getPrice()); //가격
		stmt.setString(5, productVO.getFileName()); //상품이미지


//		stmt.setInt(6, productVO.getProdNo());
//		stmt.setDate(7, productVO.getRegDate());
		stmt.executeUpdate();
		
		con.close();
	}

	public ProductVO findProduct(int product) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "select * from PRODUCT where prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, product);

		ResultSet rs = stmt.executeQuery();

		ProductVO productVO = null;
		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
			productVO.setProdNo(rs.getInt("PROD_NO"));
			
		}
		System.out.println("----------상품찾기 완료");
		con.close();

		return productVO;
	}

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from PRODUCT ";
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where PROD_NO='" + searchVO.getSearchKeyword()
						+ "'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " where PROD_NAME='" + searchVO.getSearchKeyword()
						+ "'";
			}
		}
		sql += " order by PROD_NO";
		
	
		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				
				vo.setFileName(rs.getString("IMAGE_FILE"));
				vo.setManuDate(rs.getString("MANUFACTURE_DAY"));
				vo.setPrice(rs.getInt("PRICE"));
				vo.setProdDetail(rs.getString("PROD_DETAIL"));
				vo.setProdName(rs.getString("PROD_NAME"));
				vo.setProdNo(rs.getInt("PROD_NO"));
				vo.setRegDate(rs.getDate("REG_DATE"));
				list.add(vo);
				
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());
		System.out.println("===============getProductList");
		con.close();
			
		return map;
	}

	public void UpdateProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update product set prod_name=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE where USER_ID=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getFileName());
		stmt.setString(2, productVO.getManuDate());
		stmt.setInt(3, productVO.getPrice());
		stmt.setString(4, productVO.getProdDetail());
		stmt.setString(5, productVO.getProdName());
		stmt.setInt(6, productVO.getProdNo());
		stmt.setDate(7, productVO.getRegDate());
		stmt.executeUpdate();
		
		con.close();
		
	}
	
}