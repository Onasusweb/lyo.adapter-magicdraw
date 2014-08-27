/*********************************************************************************************
 * Copyright (c) 2014 Model-Based Systems Engineering Center, Georgia Institute of Technology.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 *  
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Eclipse Distribution License is available at
 *  http://www.eclipse.org/org/documents/edl-v10.php.
 *  
 *  Contributors:
 *  
 *	   Axel Reichwein (axel.reichwein@koneksys.com)		- initial implementation       
 *******************************************************************************************/
package org.eclipse.lyo.adapter.magicdraw.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.eclipse.lyo.adapter.magicdraw.application.MagicDrawManager;
import org.eclipse.lyo.adapter.magicdraw.resources.Constants;
import org.eclipse.lyo.adapter.magicdraw.resources.SysMLAssociationBlock;
import org.eclipse.lyo.adapter.magicdraw.resources.SysMLRequirement;
import org.eclipse.lyo.oslc4j.core.annotation.OslcQueryCapability;
import org.eclipse.lyo.oslc4j.core.annotation.OslcService;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType;

/**
 * This servlet contains the implementation of OSLC RESTful web services for 
 * <li> returning specific SysMLAssociationBlock resources in HTML and other formats </li>
 * <li> returning all SysMLAssociationBlock resources within a specific MagicDraw project
 *  in HTML and other formats </li>
 *
 * @author Axel Reichwein (axel.reichwein@koneksys.com)
 */
@OslcService(Constants.SYSML_ASSOCIATIONBLOCK_DOMAIN)
@Path("{projectId}/associationblocks")
public class SysMLAssociationBlockService {

	@Context
	private HttpServletRequest httpServletRequest;
	@Context
	private HttpServletResponse httpServletResponse;
	@Context
	private UriInfo uriInfo;

	@OslcQueryCapability(title = "SysML Association Block Query Capability", label = "SysML AssociationBlock Catalog Query", resourceShape = OslcConstants.PATH_RESOURCE_SHAPES
			+ "/" + Constants.PATH_SYSML_ASSOCIATIONBLOCK, resourceTypes = { Constants.TYPE_SYSML_ASSOCIATIONBLOCK }, usages = { OslcConstants.OSLC_USAGE_DEFAULT })
	
	@GET
	@Produces({ OslcMediaType.APPLICATION_RDF_XML,
			OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON })
	public List<org.eclipse.lyo.adapter.magicdraw.resources.SysMLAssociationBlock> getAssociationBlocks(
			@PathParam("projectId") final String projectId,
			@QueryParam("oslc.where") final String where,
			@QueryParam("oslc.select") final String select,
			@QueryParam("oslc.prefix") final String prefix,
			@QueryParam("page") final String pageString,
			@QueryParam("oslc.orderBy") final String orderBy,
			@QueryParam("oslc.searchTerms") final String searchTerms,
			@QueryParam("oslc.paging") final String paging,
			@QueryParam("oslc.pageSize") final String pageSize)
			throws IOException, ServletException {
		MagicDrawManager.loadSysMLProject(projectId);
		return MagicDrawManager.getAssociationBlocks(projectId);
	}

	@GET
	@Path("{blockQualifiedName}")
	@Produces({ OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_JSON })
	public org.eclipse.lyo.adapter.magicdraw.resources.SysMLAssociationBlock getAssociationBlock(
			@PathParam("projectId") final String projectId,
			@PathParam("blockQualifiedName") final String blockQualifiedName)
			throws URISyntaxException {
		MagicDrawManager.loadSysMLProject(projectId);
		org.eclipse.lyo.adapter.magicdraw.resources.SysMLAssociationBlock sysmlAssociationBlock = MagicDrawManager
				.getAssociationBlockByQualifiedName(projectId + "/associationblocks/" + blockQualifiedName);
		return sysmlAssociationBlock;
	}
	
	@GET
    @Produces(MediaType.TEXT_HTML)
    public void getHtmlAssociationBlocks(@PathParam("projectId") final String projectId)
    {				
		MagicDrawManager.loadSysMLProject(projectId);
		List<SysMLAssociationBlock> sysmlAssociationBlocks = MagicDrawManager.getAssociationBlocks(projectId);		
		String requestURL = httpServletRequest.getRequestURL().toString();
    	if (sysmlAssociationBlocks !=null )
    	{	        
	        httpServletRequest.setAttribute("elements", sysmlAssociationBlocks);	        
	        httpServletRequest.setAttribute("requestURL",requestURL);
	        httpServletRequest.setAttribute("projectId",projectId);	        
	        RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/sysml/sysml_associationblocks_html.jsp");
    		try {
				rd.forward(httpServletRequest, httpServletResponse);
			} catch (Exception e) {				
				e.printStackTrace();
				throw new WebApplicationException(e);
			} 
    	}
    }

	
	@GET
	@Path("{blockQualifiedName}")
	@Produces(MediaType.TEXT_HTML)
	public void getHtmlAssociationBlock(
			@PathParam("projectId") final String projectId,
			@PathParam("blockQualifiedName") final String blockQualifiedName,
			@QueryParam("oslc.properties") final String propertiesString,
			@QueryParam("oslc.prefix") final String prefix)
			throws URISyntaxException, IOException {
		MagicDrawManager.loadSysMLProject(projectId);
		org.eclipse.lyo.adapter.magicdraw.resources.SysMLAssociationBlock sysmlAssociationBlock = MagicDrawManager.getAssociationBlockByQualifiedName(projectId + "/associationblocks/" + blockQualifiedName);
	
		String requestURL = httpServletRequest.getRequestURL().toString();
		if (sysmlAssociationBlock !=null )
    	{       
	        httpServletRequest.setAttribute("associationblock", sysmlAssociationBlock);
	        httpServletRequest.setAttribute("requestURL",requestURL);
	        RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/sysml/sysml_associationblock_html.jsp");
    		try {
				rd.forward(httpServletRequest, httpServletResponse);
			} catch (Exception e) {				
				e.printStackTrace();
				throw new WebApplicationException(e);
			} 
    	}	
	}
}
