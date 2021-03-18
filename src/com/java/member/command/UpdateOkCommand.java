package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class UpdateOkCommand implements Command{

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");		
		MemberDto dto = new MemberDto();
		String id = request.getParameter("id");
		String interest = request.getParameter("resultInterest");
		dto.setId(id);
		dto.setPass(request.getParameter("password"));
		dto.setEmail(request.getParameter("email"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setJob(request.getParameter("job"));
		dto.setMailing(request.getParameter("mailing"));
//		dto.setInterest(request.getParameter("interest"));
		dto.setInterest(request.getParameter("resultInterest"));	
		System.out.println(interest);
		int check = MemberDao.getInstance().updateOk(id, dto);
		
		request.setAttribute("check", check);				
		
		return "/WEB-INF/member/updateOk.jsp";
	}

}