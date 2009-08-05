package org.palaciego.cipion.model;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.palaciego.cipion.service.GenericManager;


public class GenericPropertyEditor<T extends BaseObject, PK extends Serializable>
		extends PropertyEditorSupport {

	protected static final transient Log log = LogFactory
			.getLog(GenericPropertyEditor.class);

	private GenericManager<T, PK> manager = null;
	private T baseObjectExample = null;

	public GenericManager<T, PK> getManager() {
		return manager;
	}

	public void setManager(GenericManager<T, PK> manager) {
		this.manager = manager;
	}

	public void setBaseObjectExample(T baseObjectExample) {
		this.baseObjectExample = baseObjectExample;
	}

	public T getBaseObjectExample() {
		return baseObjectExample;
	}

	@Override
	public String getAsText() {
		T object = (T) getValue();
		if (object != null) {
			String str = object.getStringForPK();
			if (str != null) {
				return str;
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.trim().equals("")) {
			PK id = (PK) baseObjectExample.getPKForString(text);
			try {
				T value = manager.get(id);
				setValue(value);
			} catch (Exception e) {
				log.error("Manager error finding element id: " + id);
			}
		} else {
			setValue(null);
		}
	}
}