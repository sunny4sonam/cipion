package org.palaciego.cipion.webapp.view;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.springframework.web.servlet.view.AbstractView;


/**
 * Vista resolutora de xsl con fo para generaci�n de pdf.
 * @author SEJAS
 *
 */
public class XslFopView extends AbstractView
{
	/**
	 * Factoria de Fop.
	 */
	private FopFactory			fopFactory;

	public static final String XSLT="XSLT";
	public static final String BASICMAP="BASICMAP";

	/**
	 * Constructor.
	 */
	public XslFopView()
	{
		this.fopFactory = FopFactory.newInstance();
	}


	/**
	 * El servidor responde con el formato de archivo en xml.
	 * @author SEJMVR
	 * @param data Map
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception Lanzamos una excepci�n
	 */
	@SuppressWarnings("all")
	protected final void renderMergedOutputModel(Map data, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (data != null)
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Fop fop = this.fopFactory.newFop("application/pdf", out);
			TransformerFactory tf=TransformerFactory.newInstance();
			
			InputStream is=getClass().getResourceAsStream((String)data.get(XslFopView.XSLT));
			Templates temp = tf.newTemplates(new StreamSource(is));
			Transformer transformer = temp.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

			StreamResult sr = new StreamResult(response.getWriter());
			
			BasicMap basicMap=(BasicMap)data.get(BASICMAP);
			String xml = (new StringBuilder()).append("<?xml version=\"1.0\" encoding=\"").append("ISO-8859-1").append("\"?>").append(basicMap.toString()).toString();

			javax.xml.transform.Result res = new SAXResult(fop.getDefaultHandler());
			StringReader sreader = new StringReader(xml);
			javax.xml.transform.Source src = new StreamSource(sreader);
			transformer.transform(src, res); 
			
			response.setContentType("application/pdf");
			response.setContentLength(out.size());
			response.getOutputStream().write(out.toByteArray());
			response.getOutputStream().flush();
		}
	}
	


}
