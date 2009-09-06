package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.webapp.view.BasicMap;
import org.palaciego.cipion.webapp.view.XslFopView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ShowReportController extends AbstractController{


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv=new ModelAndView();
		
		String report=request.getParameter("report");

		mv.setView(new XslFopView());
		mv.addObject(XslFopView.XSLT,"/xslt/"+report+"-fop.xslt");
		BasicMap bm=new BasicMap();
		bm.put("prueba", "esto es una prueba");
		mv.addObject(XslFopView.BASICMAP,bm);
		return mv;
	}


}
