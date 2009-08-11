package org.palaciego.cipion.webapp.controller;


import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Country;
import org.palaciego.cipion.model.GenericPropertyEditor;	
import org.palaciego.cipion.model.ImageUtil;
import org.palaciego.cipion.webapp.controller.BaseFormController;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class CountryFormController extends BaseFormController {
    private GenericManager<Country, Long> countryManager = null;

    public void setCountryManager(GenericManager<Country, Long> countryManager) {
        this.countryManager = countryManager;
    }
    
    // Obtain Related managers 
	// End related managers    
    

    public CountryFormController() {
        setCommandClass(Country.class);
        setCommandName("country");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sid = request.getParameter("sid");

        if (!StringUtils.isBlank(sid)) {
            return countryManager.get(new Long(sid));
        }

        return new Country();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
    	
//    	//meto los paises
//    	System.out.println("meto los pa�ses!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//    	List<countrydata> lista=parseContent(new String[4]);
//    	for(int i=0;i<lista.size();i++)
//    	{
//        	System.out.println("meto 1 PAIS");
//    		countrydata cd=lista.get(i);
//    		byte[] data=readFlag(cd.code);
//    		Country c=new Country();
//    		c.setName(cd.name);
//    		c.setFlag(data);
//    		countryManager.save(c);
//    	}
//    	System.out.println("FIN!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	
        log.debug("entering 'onSubmit' method...");

        Country country = (Country) command;
        boolean isNew = (country.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            countryManager.remove(country.getSid());
            saveMessage(request, getText("country.deleted", locale));
        } else {
        	//me aseguro de que la foto tiene dimensiones correctas
        	country.setFlag(ImageUtil.resizeImage(country.getFlag(), 200, 200));
        	
            countryManager.save(country);
            String key = (isNew) ? "country.added" : "country.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:countryform.html?sid=" + country.getSid() + "&edit=false";
            }
        }

        return new ModelAndView(success);
    }
    
    public byte[] readFlag(String code) throws Exception
    {
    	String fileName="J:\\instalaciondgp\\verificador\\Recursos\\images\\banderas\\"+code+".png";
    	File f=new File(fileName);
    	if(f.exists())
    	{
    		FileInputStream fis=new FileInputStream(f);
    		byte[] buffer=new byte[1024];
    		int read;
    		ByteArrayOutputStream baos=new ByteArrayOutputStream();
    		
    		read=fis.read(buffer);
    		while(read>=0)
    		{
    			baos.write(buffer,0,read);
        		read=fis.read(buffer);
    		}
    		
    		return baos.toByteArray();
    	}
    	else
    	{
    		System.out.println("NO EXISTE EL FICHERO:"+fileName);
    	}
    	return null;
    }
    
	public static List<countrydata> parseContent(String[] args) throws Exception{
		String content=readFileAsString("c:\\Desarrollo\\proyectos\\cipion\\database\\icao-countries");
		String[] tokens=content.split("\n");
		ArrayList<countrydata> list=new ArrayList<countrydata>();
		for(int i=0;i<tokens.length-1;i=i+2)
		{
			countrydata cd=new countrydata();
			cd.name=tokens[i].trim();
			cd.code=tokens[i+1].trim();
			list.add(cd);
		}
		return list;
	}
	
    /** @param filePath the name of the file to open. Not sure if it can accept URLs or just filenames. Path handling could be better, and buffer sizes are hardcoded
	    */ 
	    private static String readFileAsString(String filePath)
	    throws java.io.IOException{
	        StringBuffer fileData = new StringBuffer(1000);
	        BufferedReader reader = new BufferedReader(
	                new FileReader(filePath));
	        char[] buf = new char[1024];
	        int numRead=0;
	        while((numRead=reader.read(buf)) != -1){
	            String readData = String.valueOf(buf, 0, numRead);
	            fileData.append(readData);
	            buf = new char[1024];
	        }
	        reader.close();
	        return fileData.toString();
	    }

    
    // Loads dynamically related data
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
		Map allReferenceData = super.referenceData(request);
		
		if(allReferenceData == null) {
			allReferenceData = new HashMap();
		}
		
		// #START isManyToOne fields
	 	// Q: How to reference data? -> A: Using <field.name>Manager
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}

class countrydata
{
	public String name;
	public String code;
}
