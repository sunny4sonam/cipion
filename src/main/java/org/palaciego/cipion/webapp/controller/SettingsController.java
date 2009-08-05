package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.model.Settings;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SettingsController implements Controller {
    private GenericManager<Settings, Long> settingsManager;

    public void setSettingsManager(GenericManager<Settings, Long> settingsManager) {
        this.settingsManager = settingsManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        return new ModelAndView().addObject(settingsManager.getAll());
    }
}
