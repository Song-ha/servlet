package com.java.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;



public class WriteCommand implements Command{

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		//부모글 일 경우
		int boardNumber = 0; // 부모글 일 경우 최초글쓰기 상위 글번호가 존개하지 않는다.
		int groupNumber = 1;
		int sequenceNumber = 0;
		int sequenceLevel = 0;
		
		//답글 일 경우 부모글의 글번호, 그룹번호, 글순서, 글레벨
		if (request.getParameter("boardNumber")!=null) {
			
		}
		
		request.setAttribute("boardNumber", boardNumber);
		request.setAttribute("groupNumber", groupNumber);
		request.setAttribute("sequenceNumber", sequenceNumber);
		request.setAttribute("sequenceLevel", sequenceLevel);
		
		
		
		return "/WEB-INF/board/write.jsp";
	}
	
}
