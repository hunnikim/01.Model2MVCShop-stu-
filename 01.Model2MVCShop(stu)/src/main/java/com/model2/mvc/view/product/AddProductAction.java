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
		System.out.println(request.getParameter("fileName")); //�����
		
		product.setManuDate(request.getParameter("manuDate"));
		System.out.println(request.getParameter("manuDate")); //�����
		
		
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		System.out.println(Integer.parseInt(request.getParameter("price"))); //�����
		
		product.setProdDetail(request.getParameter("prodDetail"));
		System.out.println(request.getParameter("prodDetail"));//�����
		
		product.setProdName(request.getParameter("prodName"));
		System.out.println(request.getParameter("prodName")); //�����
		
	
		
	

		ProductService productservice=new ProductServiceImpl();
		productservice.addProduct(product);
		
//		 ���� vo�� �ҷ��ͼ� ���ָ� �ȴ�.
		request.setAttribute("pro", product);

		//object scope�� �̿��ؼ� jsp�� �̵� ���Ѿ��ϴµ� 3������ �ִ� . session , application, request
		
		
		return "forward:/product/getProductView.jsp";
		}

}



