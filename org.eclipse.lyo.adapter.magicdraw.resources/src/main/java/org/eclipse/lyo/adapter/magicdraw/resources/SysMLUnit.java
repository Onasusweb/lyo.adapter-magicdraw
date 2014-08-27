/*********************************************************************************************
 * Copyright (c) 2014 Model-Based Systems Engineering Center, Georgia Institute of Technology.
 *                         http://www.mbse.gatech.edu/
 *                  http://www.mbsec.gatech.edu/research/oslc
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
 *	   Axel Reichwein, Koneksys (axel.reichwein@koneksys.com)		
 *******************************************************************************************/
package org.eclipse.lyo.adapter.magicdraw.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.lyo.oslc4j.core.annotation.OslcDescription;
import org.eclipse.lyo.oslc4j.core.annotation.OslcName;
import org.eclipse.lyo.oslc4j.core.annotation.OslcOccurs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcNamespace;
import org.eclipse.lyo.oslc4j.core.annotation.OslcReadOnly;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRange;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRepresentation;
import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcTitle;
import org.eclipse.lyo.oslc4j.core.annotation.OslcValueType;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.Representation;
import org.eclipse.lyo.oslc4j.core.model.ValueType;
import org.eclipse.lyo.oslc4j.core.model.Link;

@OslcNamespace(Constants.SYSML_NAMESPACE)
@OslcName("Unit")
@OslcResourceShape(title = "Unit Resource Shape", describes = Constants.TYPE_SYSML_UNIT)
public class SysMLUnit extends AbstractResource{

	public SysMLUnit() throws URISyntaxException {
		super();
	}
	public SysMLUnit(URI about) throws URISyntaxException {
		super(about);
	}

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@OslcDescription("Description of NamedElement::name TBD")
	@OslcName("name")
	@OslcOccurs(Occurs.ZeroOrOne)
	@OslcPropertyDefinition("http://omg.org/sysml/rdf#NamedElement/name")
	@OslcTitle("name")
	@OslcValueType(ValueType.XMLLiteral)
	public String getName() {
		 return name;
	}
	private String symbol;

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@OslcDescription("Description of Unit::symbol TBD")
	@OslcName("symbol")
	@OslcOccurs(Occurs.ZeroOrOne)
	@OslcPropertyDefinition("http://omg.org/sysml/rdf#Unit/symbol")
	@OslcTitle("symbol")
	@OslcValueType(ValueType.XMLLiteral)
	public String getSymbol() {
		 return symbol;
	}
	private String description;

	public void setDescription(String description) {
		this.description = description;
	}

	@OslcDescription("Description of Unit::description TBD")
	@OslcName("description")
	@OslcOccurs(Occurs.ZeroOrOne)
	@OslcPropertyDefinition("http://omg.org/sysml/rdf#Unit/description")
	@OslcTitle("description")
	@OslcValueType(ValueType.XMLLiteral)
	public String getDescription() {
		 return description;
	}
	private String definitionURI;

	public void setDefinitionURI(String definitionURI) {
		this.definitionURI = definitionURI;
	}

	@OslcDescription("Description of Unit::definitionURI TBD")
	@OslcName("definitionURI")
	@OslcOccurs(Occurs.ZeroOrOne)
	@OslcPropertyDefinition("http://omg.org/sysml/rdf#Unit/definitionURI")
	@OslcTitle("definitionURI")
	@OslcValueType(ValueType.XMLLiteral)
	public String getDefinitionURI() {
		 return definitionURI;
	}
	// ********* quantityKind *********
	private URI quantityKind;

	public void setQuantityKind(final URI quantityKind) {
		this.quantityKind = quantityKind;
	}

	@OslcDescription("Description of Unit::quantityKind TBD")
	@OslcName("quantityKind")
	@OslcPropertyDefinition("http://omg.org/sysml/rdf#Unit/quantityKind")
	@OslcTitle("quantityKind")
	@OslcRange("http://omg.org/sysml/rdf#QuantityKind")
	public URI  getQuantityKind() {
		 return quantityKind;
	}

	private URI      serviceProvider;

	public void setServiceProvider(final URI serviceProvider)
	{		this.serviceProvider = serviceProvider;
	}

	@OslcDescription("The scope of a resource is a URI for the resource's OSLC Service Provider.")
	@OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "serviceProvider")
	@OslcRange(OslcConstants.TYPE_SERVICE_PROVIDER)
	@OslcTitle("Service Provider")	
	public URI getServiceProvider()
	{
		return serviceProvider;
	}

}