package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class ListProductAction extends Action {

	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	SearchVO searchVO=new SearchVO();
	
	int page=1;
	if(request.getParameter("page") != null)
	page=Integer.parseInt(request.getParameter("page"));

	searchVO.setPage(page);
	searchVO.setSearchCondition(request.getParameter("searchCondition"));
	searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
	
	
	String pageUnit=getServletContext().getInitParameter("pageSize");
	searchVO.setPageUnit(Integer.parseInt(pageUnit));
	
	System.out.println("여기까지가 끝인가요? 5"); //디 
	System.out.println(Integer.parseInt(pageUnit));//버깅

	ProductService service= new ProductServiceImpl();
	HashMap<String,Object> map=service.getProductList(searchVO);//이 부분부터 연결 안됨
	
	System.out.println("여기까지가 끝인가요? 6");
	request.setAttribute("map", map);
	request.setAttribute("searchVO", searchVO);
	
	return "forward:/product/listProduct.jsp";
	}
}
