package com.cooltrade.chatting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooltrade.chatting.controller.model.Service.ChatService;
import com.cooltrade.chatting.controller.model.vo.ChatRoom;
import com.cooltrade.product.model.vo.Images;

/**
 * Servlet implementation class ChatRoomListController
 */
@WebServlet("/chatRoom.list")
public class ChatRoomListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatRoomListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ChatRoom> list = new ChatService().getChatRoomList();
		
		for(ChatRoom c : list) {
			Images img = new ChatService().getBuyerInfo(c.getUserId());
			Images imgs = new ChatService().getSellerInfo(c.getSellerId());
			c.setBuyerNickname(img.getBuyerNickname());
			c.setBuyerTitleImg(img.getBuyerTitleImg());
			c.setSellerNickname(imgs.getSellerNickname());
			c.setSellerTitleImg(imgs.getSellerTitleImg());
		}
		//System.out.println(list);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/chat/chatRoom.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
