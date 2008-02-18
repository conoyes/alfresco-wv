/*
 * Copyright (C) 2005-2007 Alfresco Software Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.

 * As a special exception to the terms and conditions of version 2.0 of 
 * the GPL, you may redistribute this Program in connection with Free/Libre 
 * and Open Source Software ("FLOSS") applications as described in Alfresco's 
 * FLOSS exception.  You should have recieved a copy of the text describing 
 * the FLOSS exception, and it is also available here: 
 * http://www.alfresco.com/legal/licensing
 */
package org.alfresco.web.app.servlet.command;

import java.util.Map;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.util.ParameterCheck;
import org.alfresco.web.app.servlet.BaseServlet;
import org.alfresco.web.app.servlet.FacesHelper;
import org.alfresco.web.bean.BrowseBean;
import org.alfresco.web.bean.repository.Node;

/**
 * Command to execute the Edit Content Properties dialog via url.
 * <p>
 * Arguments: noderef - of the document to show the edit props dialog for
 * 
 * @author Kevin Roast
 */
public class EditContentPropertiesCommand extends BaseUIActionCommand
{
   public static final String PROP_NODEREF = "noderef";
   
   private static final String[] PROPERTIES = new String[] {
      PROP_SERVLETCONTEXT, PROP_REQUEST, PROP_RESPONSE, PROP_NODEREF};
   
   /**
    * @see org.alfresco.web.app.servlet.command.Command#execute(org.alfresco.service.ServiceRegistry, java.util.Map)
    */
   public Object execute(ServiceRegistry serviceRegistry, Map<String, Object> properties)
   {
      ServletContext sc = (ServletContext)properties.get(PROP_SERVLETCONTEXT);
      ServletRequest req = (ServletRequest)properties.get(PROP_REQUEST);
      ServletResponse res = (ServletResponse)properties.get(PROP_RESPONSE);
      FacesContext fc = FacesHelper.getFacesContext(req, res, sc, "/jsp/return.jsp");
      BrowseBean browseBean = (BrowseBean)FacesHelper.getManagedBean(fc, BrowseBean.BEAN_NAME);
      
      // setup context from url args in properties map
      String strNodeRef = (String)properties.get(PROP_NODEREF);
      ParameterCheck.mandatoryString(PROP_NODEREF, strNodeRef);
      browseBean.setDocument(new Node(new NodeRef(strNodeRef)));
      
      NavigationHandler navigationHandler = fc.getApplication().getNavigationHandler();
      navigationHandler.handleNavigation(fc, null, "dialog:editContentProperties");
      String viewId = fc.getViewRoot().getViewId();
      try
      {
         sc.getRequestDispatcher(BaseServlet.FACES_SERVLET + viewId).forward(req, res);
      }
      catch (Exception e)
      {
         throw new AlfrescoRuntimeException("Unable to forward to viewId: " + viewId, e);
      }
      
      return null;
   }

   /**
    * @see org.alfresco.web.app.servlet.command.Command#getPropertyNames()
    */
   public String[] getPropertyNames()
   {
      return PROPERTIES;
   }
}
