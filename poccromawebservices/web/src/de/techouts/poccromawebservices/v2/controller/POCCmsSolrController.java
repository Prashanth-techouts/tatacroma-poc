/**
 *
 */
package de.techouts.poccromawebservices.v2.controller;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.request.mapping.annotation.ApiVersion;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.techouts.poccromawebservices.facades.impl.CmsSolrDataFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
 * @author D1
 *
 */
@Controller
@RequestMapping(value = "/{baseSiteId}/poccmssolr")
@ApiVersion("v2")
@Api(tags = "PocCmsSolr")
public class POCCmsSolrController extends BaseController
{
	@Autowired
	private CmsSolrDataFacade cmsPageDataFacade;

	private final String pageLabelOrId = null;

	private static Logger LOG = LoggerFactory.getLogger(POCCmsSolrController.class);

	@RequestMapping(value = "/pages", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiBaseSiteIdParam
	public List<ProductData> getPageAndSolrData(
			@ApiParam(value = "page type", defaultValue = "CategoryPage")
			@RequestParam(required = true)
			final String pageType,
			@ApiParam(value = "pageType is CategoryPage, code should be category code")
			@RequestParam(required = false)
			final String code,
			@ApiParam(value = "Response configuration (list of fields, which should be returned in response)", allowableValues = "BASIC, DEFAULT, FULL")
			@RequestParam(defaultValue = "DEFAULT")
			final String fields) throws CMSItemNotFoundException
	{
		final List<ProductData> list = new ArrayList<ProductData>();
		//list.addAll(cmsPageDataFacade.getPageAndSolrData(pageType, pageLabelOrId, code, fields));

		return list;

	}

}
