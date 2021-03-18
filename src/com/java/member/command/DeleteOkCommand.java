package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class DeleteOkCommand implements Command{

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		int check = MemberDao.getInstance().delet(id);
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/member/deletOk.jsp";
	}

}
