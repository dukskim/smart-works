package com.dhkim.sworks.test.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhkim.sworks.sql.domain.Test;
import com.dhkim.sworks.test.service.TestService;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TestService testService;
	
	@RequestMapping("/test/test.do")
	public ModelAndView test() throws Exception {
		logger.debug(">>>>>>>>>>>>>>>> test.do");
		ModelAndView mv = new ModelAndView();
		Test test = new Test();
		test.setAaa(1);
		test.setBbb("b");
		test = testService.getTest(test);
		logger.debug("-------------->>> " + test.getAaa() + ", " + test.getBbb());
		mv.setViewName("/test/test.tiles");
		return mv;
	}
	@RequestMapping("/test/tranTest.do")
	//Controller에서는 적용안되네...
	//@Transactional(rollbackFor={SQLException.class}, propagation = Propagation.REQUIRED)
	public ModelAndView tranTest() throws Exception {
		logger.debug(">>>>>>>>>>>>>>>> TranTest.do");
		ModelAndView mv = new ModelAndView();

		for (int i = 0; i < 10; i++) {
			
			if (i == 6) {
				throw new SQLException();
			} else {
				Test test = new Test();
				test.setAaa(i);
				test.setBbb("b"+i);
				int res = testService.addTest(test);
				logger.debug("res -------------->>> " + res);
			}
		}
		
		mv.setViewName("/test/test.tiles");
		return mv;
	}
	
	/*
	 * 테이블 생성작업
	 */
	@RequestMapping("/test/makeTable.do")
	public ModelAndView makeTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		testService.makeTable("RT_BOARD_JAVA");
		return new ModelAndView("/test/test.tiles");
	}
	
}
