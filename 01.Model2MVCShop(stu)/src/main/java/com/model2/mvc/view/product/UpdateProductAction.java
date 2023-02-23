package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdateProductAction extends Action {

	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	String prodNo=(String)request.getParameter("PROD_NO");
	
	ProductVO VO=new ProductVO();
	VO.setProdName(request.getParameter("prodName"));
	VO.setProdDetail(request.getParameter("PROD_DETAIL"));
	VO.setManuDate(request.getParameter("MANUFACTURE_DAY"));
	VO.setPrice(Integer.parseInt(request.getParameter("price")));
	VO.setFileName(request.getParameter("IMAGE_FILE"));
	VO.setProdNo(Integer.parseInt(request.getParameter("ProdNo")));
	
	ProductService service=new ProductServiceImpl();
	service.updateProduct(VO);
	
	HttpSession session=request.getSession();
	String sessionName=((ProductVO)session.getAttribute("product")).getProdName();
	
	if(sessionName.equals(prodNo)){
		session.setAttribute("Product", VO);
	}
	
	return "redirect:/getProduct.do?prodNo="+prodNo;
	}

}
