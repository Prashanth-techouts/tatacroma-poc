/**
 *
 */
package de.techouts.poccromawebservices.services.impl;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cmsfacades.data.AbstractCMSComponentData;
import de.hybris.platform.cmsfacades.data.AbstractPageData;
import de.hybris.platform.cmsfacades.exception.ValidationException;
import de.hybris.platform.cmsfacades.pages.PageFacade;
import de.hybris.platform.cmsoccaddon.data.CMSPageWsDTO;
import de.hybris.platform.cmsoccaddon.data.ComponentListWsDTO;
import de.hybris.platform.cmsoccaddon.data.ComponentWsDTO;
import de.hybris.platform.cmsoccaddon.data.ContentSlotListWsDTO;
import de.hybris.platform.cmsoccaddon.data.ContentSlotWsDTO;
import de.hybris.platform.webservicescommons.errors.exceptions.WebserviceValidationException;
import de.hybris.platform.webservicescommons.mapping.DataMapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author tech
 *
 */
public class CmsPageDataService
{

	@Resource(name = "cmsPageFacade")
	private PageFacade pageFacade;

	@Resource(name = "cmsDataMapper")
	protected DataMapper dataMapper;

	private static Logger LOGGER = LoggerFactory.getLogger(CmsPageDataService.class);



	public CMSPageWsDTO getPageData(final String pageType, final String pageLabelOrId, final String code,
			final String fields) throws CMSItemNotFoundException
	{
		try
		{
			final AbstractPageData pageData = getPageFacade().getPageData(pageType, pageLabelOrId, code);

			final ContentSlotListWsDTO slotList = new ContentSlotListWsDTO();
			slotList.setContentSlot(getContentSlotsWsDTOs(pageData, fields));

			final CMSPageWsDTO pageWsDTO = new CMSPageWsDTO();
			dataMapper.map(pageData, pageWsDTO, fields);
			pageWsDTO.setContentSlots(slotList);

			return pageWsDTO;
		}
		catch (final ValidationException e)
		{
			LOGGER.info("Validation exception", e);
			throw new WebserviceValidationException(e.getValidationObject());
		}

	}



	protected List<ContentSlotWsDTO> getContentSlotsWsDTOs(final AbstractPageData pageData, final String fields)
	{
		return pageData.getContentSlots().stream().map(slot -> {
			final ContentSlotWsDTO contentSlotDTO = new ContentSlotWsDTO();
			dataMapper.map(slot, contentSlotDTO, fields);
			final ComponentListWsDTO componentList = new ComponentListWsDTO();
			componentList.setComponent(getComponentWsDTOs(slot.getComponents(), fields));
			contentSlotDTO.setComponents(componentList);
			return contentSlotDTO;
		}).collect(Collectors.toList());
	}


	protected List<ComponentWsDTO> getComponentWsDTOs(final List<AbstractCMSComponentData> components, final String fields)
	{
		return components.stream().map(componentData -> {
			final ComponentWsDTO componentDTO = new ComponentWsDTO();
			dataMapper.map(componentData, componentDTO, fields);
			return componentDTO;
		}).collect(Collectors.toList());
	}

	protected PageFacade getPageFacade()
	{
		return pageFacade;
	}

	public void setPageFacade(final PageFacade pageFacade)
	{
		this.pageFacade = pageFacade;
	}


}
