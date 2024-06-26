package com.cooltrade.chatting.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooltrade.chatting.controller.model.Service.ChatService;
import com.cooltrade.chatting.controller.model.vo.ChatRoom;
import com.cooltrade.product.model.vo.Images;

/**
 * Servlet implementation class ChatInsertMessageController
 */
@WebServlet("/message.insert")
public class ChatInsertMessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatInsertMessageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String message = request.getParameter("message");
		int chatRoomNo = Integer.parseInt(request.getParameter("chatRoomNo"));
		
		int result = new ChatService().insertMessage(userId,message,chatRoomNo);
		
		if(result>0) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print(message);
		}else {
		 request.setAttribute("errorMsg", "실패했습니다");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
