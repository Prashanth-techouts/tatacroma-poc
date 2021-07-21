/**
 *
 */
package de.techouts.poccromawebservices.facades.impl;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.techouts.pocchroma.facades.DTO.CmsSolrResponseDTO;
import de.techouts.poccromawebservices.services.impl.CmsPageDataService;
import de.techouts.poccromawebservices.solr.CromaCategorySolrDataService;


/**
 * @author tech
 *
 */
public class CmsSolrDataFacade
{


	private CmsPageDataService cmsPageDataService;
	private CromaCategorySolrDataService cromaCategorySolrDataService;


	private static Logger LOGGER = LoggerFactory.getLogger(CmsSolrDataFacade.class);


	public List<ProductData> getPageAndSolrData(final String pageType,
			final String pageLabelOrId, final String code,
			final String fields) throws CMSItemNotFoundException
	{

		final List<ProductData> list = new ArrayList<ProductData>();

		final CmsSolrResponseDTO cmsSolrResponseDto = new CmsSolrResponseDTO();


		cmsSolrResponseDto.setCmsPageWsDTO(getCmsPageDataService().getPageData(pageType, pageLabelOrId, code, fields));
		final ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData> searchPageData = getCromaCategorySolrDataService()
				.getSolrDataforCategory(code);
		cmsSolrResponseDto.setProductCategorySearchPageData(searchPageData);
		LOGGER.info(" ^^^^^^^^^^" + searchPageData.getResults().size() + " ^^^^^^^^^^^");

		for (int i = 0; i < searchPageData.getResults().size(); i++)
		{
			final ProductData data = new ProductData();

			data.setAddToCartDisabled(searchPageData.getResults().get(i).getAddToCartDisabled());
			data.setAddToCartDisabledMessage(searchPageData.getResults().get(i).getAddToCartDisabledMessage());
			data.setAvailableForPickup(searchPageData.getResults().get(i).getAvailableForPickup());
			data.setAverageRating(searchPageData.getResults().get(i).getAverageRating());
			data.setBaseOptions(searchPageData.getResults().get(i).getBaseOptions());
			data.setBaseProduct(searchPageData.getResults().get(i).getBaseProduct());
			data.setBundleTemplates(searchPageData.getResults().get(i).getBundleTemplates());
			data.setCategories(searchPageData.getResults().get(i).getCategories());
			data.setClassifications(searchPageData.getResults().get(i).getClassifications());
			data.setCode(searchPageData.getResults().get(i).getCode());
			data.setConfigurable(searchPageData.getResults().get(i).getConfigurable());
			data.setDescription(searchPageData.getResults().get(i).getDescription());
			data.setFirstCategoryNameList(searchPageData.getResults().get(i).getFirstCategoryNameList());
			data.setFutureStocks(searchPageData.getResults().get(i).getFutureStocks());
			data.setGenders(searchPageData.getResults().get(i).getGenders());
			data.setImages(searchPageData.getResults().get(i).getImages());
			data.setKeywords(searchPageData.getResults().get(i).getKeywords());
			data.setLowestBundlePrice(searchPageData.getResults().get(i).getLowestBundlePrice());
			data.setManufacturer(searchPageData.getResults().get(i).getManufacturer());
			data.setMultidimensional(searchPageData.getResults().get(i).getMultidimensional());
			data.setName(searchPageData.getResults().get(i).getName());
			data.setNumberOfReviews(searchPageData.getResults().get(i).getNumberOfReviews());
			data.setPotentialPromotions(searchPageData.getResults().get(i).getPotentialPromotions());
			data.setPrice(searchPageData.getResults().get(i).getPrice());
			data.setPriceRange(searchPageData.getResults().get(i).getPriceRange());
			data.setProductReferences(searchPageData.getResults().get(i).getProductReferences());
			data.setPurchasable(searchPageData.getResults().get(i).getPurchasable());
			data.setReviews(searchPageData.getResults().get(i).getReviews());
			data.setStock(searchPageData.getResults().get(i).getStock());
			data.setSummary(searchPageData.getResults().get(i).getSummary());
			data.setUrl(searchPageData.getResults().get(i).getUrl());
			data.setVariantMatrix(searchPageData.getResults().get(i).getVariantMatrix());
			data.setVariantOptions(searchPageData.getResults().get(i).getVariantOptions());
			data.setVariantType(searchPageData.getResults().get(i).getVariantType());
			data.setVolumePrices(searchPageData.getResults().get(i).getVolumePrices());
			data.setVolumePricesFlag(searchPageData.getResults().get(i).getVolumePricesFlag());
			data.setDisabled(false);
			data.setSoldIndividually(false);

			list.add(data);

		}
		LOGGER.info("********" + list.size());
		return list;

	}

	public CmsPageDataService getCmsPageDataService()
	{
		return cmsPageDataService;
	}


	public void setCmsPageDataService(final CmsPageDataService cmsPageDataService)
	{
		this.cmsPageDataService = cmsPageDataService;
	}


	public CromaCategorySolrDataService getCromaCategorySolrDataService()
	{
		return cromaCategorySolrDataService;
	}


	public void setCromaCategorySolrDataService(final CromaCategorySolrDataService cromaCategorySolrDataService)
	{
		this.cromaCategorySolrDataService = cromaCategorySolrDataService;
	}



}
