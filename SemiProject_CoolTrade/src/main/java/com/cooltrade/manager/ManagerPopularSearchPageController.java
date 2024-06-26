package com.cooltrade.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooltrade.product.model.service.ProductService;
import com.cooltrade.product.model.vo.Search;

/**
 * Servlet implementation class ManagerPopularSearchPageController
 */
@WebServlet("/search.in")
public class ManagerPopularSearchPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerPopularSearchPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Search> popularWordList = new ProductService().selectPopularWord();
		
		int totalResults = new ProductService().countPopularWord();
		int pageSize = 10; // 페이지당 결과 수
		int totalPages = (int) Math.ceil((double) totalResults / pageSize); 
		
		request.setAttribute("popularWordList", popularWordList);
		request.setAttribute("endValue", totalPages);
		
		request.getRequestDispatcher("views/manager/managerPopularSearch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
