package org.palaciego.cipion.webapp.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.webapp.view.BinaryView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.sun.media.sound.Toolkit;

public class GetImageController extends AbstractController{

	/**
	 * Imagen de no picture, cacheada.
	 */
	private byte[] nopicture;
	/**
	 * Ruta a la imagen.
	 */
	private String noImagePath;

	/**
	 * getter.
	 * @return
	 */
	public String getNoImagePath() {
		return noImagePath;
	}

	/**
	 * setter.
	 * @param noImagePath
	 */
	public void setNoImagePath(String noImagePath) {
		this.noImagePath = noImagePath;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv=new ModelAndView();
		
		String sid=request.getParameter("sid");
		String manager=request.getParameter("manager");
		String pojo=request.getParameter("pojo");
		String field=request.getParameter("field");
//		System.out.println("sid="+sid);
//		System.out.println("manager="+manager);
//		System.out.println("pojo="+pojo);
//		System.out.println("field="+field);
		
		GenericManager gm=(GenericManager) getApplicationContext().getBean(manager);
		String hql="Select " + field + " from " + pojo + " where sid="+sid;
//		System.out.println("hql="+hql);
		List<?> lista=gm.findHQL(hql);
		byte[] datos=null;
		if(lista.size()>0)
		{
			datos=((byte [])lista.get(0));
			if(datos==null || datos.length==0)
			{
				datos=getNoPicture();
			}
		}
		else
		{
			datos=getNoPicture();
		}
		
		mv.setView(new BinaryView());
		mv.addObject(BinaryView.DATA,datos);
		return mv;
	}

	/**
	 * Funci�n que devuelve el byte[] de la imagen NO PICTURE.
	 * @return
	 */
	private byte[] getNoPicture()
	{
		if(this.nopicture==null)
		{
			try
			{
				System.out.println("ruta: "+ this.noImagePath);
				//InputStream is=this.getClass().getResourceAsStream(this.noImagePath);
				InputStream is=getApplicationContext().getResource(this.noImagePath).getInputStream();
				byte[] buffer=new byte[1024];
				int read=0;
				ByteArrayOutputStream baos=new ByteArrayOutputStream();
				
				read=is.read(buffer);
				while(read>=0)
				{
					baos.write(buffer,0,read);
					read=is.read(buffer);
				}
				
				this.nopicture=baos.toByteArray();
			}catch(Exception e)
			{
				System.out.println("FALLO!!!!!!!!!!!!!!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		
		return this.nopicture;
	}

}
