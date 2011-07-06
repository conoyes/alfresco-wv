/*
 * Copyright (C) 2005-2011 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfresco.repo.calendar;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.query.CannedQueryFactory;
import org.alfresco.query.CannedQueryResults;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.repo.blog.cannedqueries.DraftsAndPublishedBlogPostsCannedQuery;
import org.alfresco.repo.blog.cannedqueries.DraftsAndPublishedBlogPostsCannedQueryFactory;
import org.alfresco.repo.blog.cannedqueries.GetBlogPostsCannedQuery;
import org.alfresco.repo.blog.cannedqueries.GetBlogPostsCannedQueryFactory;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.cmr.calendar.CalendarEntry;
import org.alfresco.service.cmr.calendar.CalendarEntryDTO;
import org.alfresco.service.cmr.calendar.CalendarService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.site.SiteService;
import org.alfresco.service.cmr.tagging.TaggingService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.ParameterCheck;
import org.alfresco.util.registry.NamedObjectRegistry;

/**
 * @author Nick Burch (based on existing webscript controllers in the REST API)
 * @since 4.0
 */
public class CalendarEntryImpl extends CalendarEntryDTO
{
    private NodeRef nodeRef;
    private String systemName;
    
    /**
     * Wraps an existing Calendar Entry node
     */
    protected CalendarEntryImpl(NodeRef nodeRef, String systemName)
    {
       this.nodeRef = nodeRef;
       this.systemName = systemName;
    }
    
    protected void recordStorageDetails(NodeRef nodeRef, String systemName)
    {
       this.nodeRef = nodeRef;
       this.systemName = systemName;
    }

    @Override
    public NodeRef getNodeRef() 
    {
       return nodeRef;
    }
    
    @Override
    public String getSystemName() 
    {
       return systemName;
    }
    
    /**
     * Builds up the node properties for a given Calendar Entry
     */
    protected static Map<QName,Serializable> toNodeProperties(CalendarEntry entry)
    {
       Map<QName,Serializable> properties = new HashMap<QName, Serializable>();
       // TODO
       
       return properties;
    }
    
    /**
     * Populates a Calendar Entry from the given node properties
     */
    protected static void populate(CalendarEntry entry, Map<QName,Serializable> properties)
    {
       // TODO
    }
    
    /**
     * Populates this entry from the given node properties
     */
    protected void populate(Map<QName,Serializable> properties)
    {
       populate(this, properties);
    }
}
