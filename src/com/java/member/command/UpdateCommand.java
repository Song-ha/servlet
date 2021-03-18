package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class UpdateCommand implements Command{

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println(id);
		MemberDto memberInfo = MemberDao.getInstance().update(id);
		System.out.println(memberInfo);
		request.setAttribute("memberDto", memberInfo);
		
//		response.sendRedirect("update.jsp");
		
		return "/WEB-INF/member/update.jsp";
	}

}