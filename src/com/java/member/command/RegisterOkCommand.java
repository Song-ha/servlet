package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class RegisterOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		//파라미터를 통해 넘어온 데이터를 MemberDto에 저장
		MemberDto memberDto = new MemberDto();
		memberDto.setId(request.getParameter("id")); //memberDto에 id값 담기
		memberDto.setPass(request.getParameter("password"));
		memberDto.setName(request.getParameter("name"));
		memberDto.setJumin1(request.getParameter("jumin1"));
		memberDto.setJumin2(request.getParameter("jumin2"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setZipcode(request.getParameter("zipcode"));
		memberDto.setAddress(request.getParameter("address"));
		memberDto.setJob(request.getParameter("job"));
		memberDto.setMailing(request.getParameter("mailing"));
		memberDto.setInterest(request.getParameter("resultInterest"));
		memberDto.setMemberLevel("MA");
		
		logger.info(logMsg+memberDto.toString());
		
		//DAO insert가 되었는지 체크
		int check = MemberDao.getInstance().insert(memberDto);
		
		request.setAttribute("check", check);
			
		return "/WEB-INF/member/registerOk.jsp";
		
		
	}

}
