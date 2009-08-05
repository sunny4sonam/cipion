package org.palaciego.cipion.webapp.view;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class BinaryView extends AbstractView {

	
	public static final String DATA="DATA";
	
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		byte[] data=(byte[]) model.get(DATA);
		response.setContentType("image/jpeg");
		OutputStream os=response.getOutputStream();
		os.write(data);
		os.flush();
		os.close();
	}
	
}