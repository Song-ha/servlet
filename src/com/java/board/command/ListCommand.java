package com.java.board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class ListCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null) {
			pageNumber = "1";
		}

		int currentPage = Integer.parseInt(pageNumber);

		int boardSize = 4;
		int startRow = (currentPage - 1)*boardSize + 1;
		int endRow = currentPage * boardSize;
		
		int count = BoardDao.getInstance().getCount();

		ArrayList<BoardDto> boardList = null;
		if (count>0) {
			boardList = BoardDao.getInstance().getBoardList(startRow,endRow);
		}
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("boardSize", boardSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("count", count);
		
		
		return "/WEB-INF/board/list.jsp";
	}

}
