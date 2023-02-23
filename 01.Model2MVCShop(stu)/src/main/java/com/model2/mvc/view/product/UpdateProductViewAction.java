package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class UpdateProductViewAction extends Action {

	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int prod_no=Integer.parseInt(request.getParameter("prod_No")); //������

		
		ProductService service=new ProductServiceImpl();
		System.out.println("----------------prod_no updatepAC"+prod_no);
		ProductVO VO=service.getProduct(prod_no); //���� ����/.././
		System.out.println("n32j1k12n3j21k3nk1"+service.getProduct(prod_no));
		
		
		request.setAttribute("ProductVO", VO);
		
		
		
		return "forward:/product/updateProductView.jsp";
}

}
