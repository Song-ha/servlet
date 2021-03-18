package com.java.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final Logger logger = Logger.getLogger(FrontController.class.getName());
	public static final String logMsg = "logMsg~~~~~~";

	private HashMap<String, Object> commandmap = new HashMap<String, Object>();

	public FrontController() {
		super();
	}
	//ServletConfig: 범위는 한개의 servlet으로 한정
	public void init(ServletConfig config) throws ServletException {
		String configFile = config.getInitParameter("configFile");
		//ServletContext(공유자원): 웹 어플리케이션(프로젝트) 모든 영역에서 사용할 공동의 자원을 미리 할당 해 모든 서블릿들이 이를 사용할 수 있게 하는데
		//톰캣이 실행되면 웹어플리케이션당 1개의 컨텍스트가 생성된다.>>>웹어플리케이션 전체의 범위
		String path = config.getServletContext().getRealPath(configFile); //getRealPath:실제경로 불러오기
		logger.info(logMsg + path);

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		Properties pro = new Properties();

		try {
			fis = new FileInputStream(path); //파일
			bis = new BufferedInputStream(fis, 1024); //버퍼단위로 쪼개기
			pro.load(bis); //쪼개서 로드함(버퍼단위의 정보를 읽을 수 있음)

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		// 자바의 컬렉션에 저장되어있는 요소를 읽어오는 표준 인터페이스
		Iterator<Object> keyIter = pro.keySet().iterator(); //인터페이스는 객체가 혼자 구현이 안되서 keyset().iterator()로 객체를 구현해준다.
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String className = pro.getProperty(command);
			logger.info(command + "\t" + className);

			try {
				//객체에 대한 정보를 담는 handlerClass생성
				Class<?> handlerClass = Class.forName(className);
				//정보를 이용하여 new를 통한 객체 생성
				Object handlerInstance = handlerClass.getDeclaredConstructor().newInstance();
				
				commandmap.put(command, handlerInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getServletPath();
		logger.info(cmd);
		String viewPage = null;
		try {
			Command com = (Command) commandmap.get(cmd);
			viewPage = com.proRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (viewPage != null) {
			RequestDispatcher rd = request.getRequestDispatcher(viewPage); //RequestDispatcher 생성
			rd.forward(request, response); //viewPage url로 넘겨주기
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
