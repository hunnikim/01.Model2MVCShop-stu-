package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class AddProductAction extends Action{

	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ProductVO product=new ProductVO();
		product.setFileName(request.getParameter("fileName"));
		System.out.println(request.getParameter("fileName")); //디버깅
		
		product.setManuDate(request.getParameter("manuDate"));
		System.out.println(request.getParameter("manuDate")); //디버깅
		
		
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		System.out.println(Integer.parseInt(request.getParameter("price"))); //디버깅
		
		product.setProdDetail(request.getParameter("prodDetail"));
		System.out.println(request.getParameter("prodDetail"));//디버깅
		
		product.setProdName(request.getParameter("prodName"));
		System.out.println(request.getParameter("prodName")); //디버깅
		
	
		
	

		ProductService productservice=new ProductServiceImpl();
		productservice.addProduct(product);
		
//		 유저 vo를 불러와서 쏴주면 된다.
		request.setAttribute("pro", product);

		//object scope를 이용해서 jsp로 이동 시켜야하는데 3가지가 있다 . session , application, request
		
		
		return "forward:/product/getProductView.jsp";
		}

}



