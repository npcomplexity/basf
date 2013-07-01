package com.booteak.basf.advmat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.booteak.basf.advmat.common.BASFProcess;
import com.booteak.basf.advmat.common.FlexuralStrength;
import com.booteak.basf.advmat.common.FractureEnergy;
import com.booteak.basf.advmat.common.FractureToughness;
import com.booteak.basf.advmat.common.GelTime;
import com.booteak.basf.advmat.common.JSONToCollectionUtil;
import com.booteak.basf.advmat.common.MixViscosity;
import com.booteak.basf.advmat.common.PotLife;
import com.booteak.basf.advmat.common.ProductCompact;
import com.booteak.basf.advmat.common.ProductFilterOptions;

public class ElasticsearchClient {
	
	private static final String SEARCH_TYPE_QUERY_THEN_FETCH = "search_type=query_then_fetch";

	private static final String SEARCH_TYPE_COUNT = "search_type=count";

	enum Mode {DEVELOPMENT, PRODUCTION};
	
	private static final String LOCAL_ELASTIC_SERVER_URL =
			"http://localhost:9200/basftest/product/";
	
	private static final String SEARCHBOX_ELASTIC_SERVER_URL =
			"http://api.searchbox.io/api-key/4b2b15abc74453f926fa588e084f82ba/basftest/product/";

	private static final String SEARCH_OPERATION = "_search?";

	private static final String PROCESS_FACETS_QUERY = "{\"query\" : {\"match_all\" : { }},\"facets\" : {\"processes\" : {\"terms\" : {\"field\" : \"process\"}}}}";

	private static final String PRODUCT_FILTERS_FACET_QUERY_PREFIX = "{\"query\":{\"term\":{\"process\":";

	private static final String PRODUCT_FILTERS_FACET_QUERY_SUFFIX = "}},\"facets\":{\"potLife\":{\"statistical\":{\"fields\":[\"potLife.min\",\"potLife.max\"]}},\"gelTime\":{\"statistical\":{\"fields\":[\"gelTime.min\",\"gelTime.max\"]}},\"mixViscosity\":{\"statistical\":{\"fields\":[\"mixViscosity.min\",\"mixViscosity.max\"]}},\"flexuralStrength\":{\"statistical\":{\"fields\":[\"flexuralStrength.min\",\"flexuralStrength.max\"]}},\"fractureEnergy\":{\"statistical\":{\"fields\":[\"fractureEnergy.min\",\"fractureEnergy.max\"]}},\"fractureToughness\":{\"statistical\":{\"fields\":[\"fractureToughness.min\",\"fractureToughness.max\"]}}}}";

	private static final ElasticsearchClient ESC = new ElasticsearchClient();

	
	private Mode mode;

	private ElasticsearchClient() {

		mode = Mode.PRODUCTION;
	}
	
	private String getElasticServerUrl() {
		return (mode == Mode.DEVELOPMENT) ? LOCAL_ELASTIC_SERVER_URL : SEARCHBOX_ELASTIC_SERVER_URL;
	}

	public static ElasticsearchClient getInstance() {
		return ESC;
	}

	public List<BASFProcess> getProcesses() {
		List<BASFProcess> procs = new ArrayList<BASFProcess>();

		
		Resty r = new Resty();
		try {
			JSONResource topLevel = r
					.json(getElasticServerUrl() + SEARCH_OPERATION + SEARCH_TYPE_COUNT,
							Resty.form(PROCESS_FACETS_QUERY));
			JSONArray o = (JSONArray) topLevel.get("facets.processes.terms");
			for (int i = 0; i < o.length(); i++) {
				BASFProcess bp = BASFProcess.getNameToProcessMap().get(
						o.getJSONObject(i).getString("term"));
				bp.setCount(o.getJSONObject(i).getInt("count"));
				procs.add(bp);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return procs;
	}

	public List<ProductFilterOptions> getProductFilterOptions(int processId) {
		BASFProcess bp = BASFProcess.getIdToProcessMap().get(processId);

		List<ProductFilterOptions> pfos = new ArrayList<ProductFilterOptions>();

		Resty r = new Resty();
		try {
			JSONResource topLevel = r
					.json(getElasticServerUrl() + SEARCH_OPERATION + SEARCH_TYPE_COUNT,
							Resty.form(PRODUCT_FILTERS_FACET_QUERY_PREFIX
									+ "\"" + bp.getName() + "\""
									+ PRODUCT_FILTERS_FACET_QUERY_SUFFIX));

			pfos.add(new ProductFilterOptions(PotLife.POTLIFE,
					PotLife.POTLIFE_FACET,
					((Double)topLevel.get("facets." + PotLife.MIN)).intValue(),
					((Double)topLevel.get("facets." + PotLife.MAX)).intValue()));
					
			pfos.add(new ProductFilterOptions(GelTime.GELTIME,
					GelTime.GELTIME_FACET,
					((Double)topLevel.get("facets." + GelTime.MIN)).intValue(),
					((Double)topLevel.get("facets." + GelTime.MAX)).intValue()));

			pfos.add(new ProductFilterOptions(MixViscosity.MIXVISCOSITY,
					MixViscosity.MIXVISCOSITY_FACET, 
					((Double)topLevel.get("facets." + MixViscosity.MIN)).intValue(),
					((Double)topLevel.get("facets." + MixViscosity.MAX)).intValue()));

			pfos.add(new ProductFilterOptions(
					FlexuralStrength.FLEXURALSTRENGTH,
					FlexuralStrength.FLEXURALSTRENGTH_FACET, 
					((Double)topLevel.get("facets." + FlexuralStrength.MIN)).intValue(),
					((Double)topLevel.get("facets." + FlexuralStrength.MAX)).intValue()));

			pfos.add(new ProductFilterOptions(
					FractureToughness.FRACTURETOUGHNESS,
					FractureToughness.FRACTURETOUGHNESS_FACET,
					((Double)topLevel.get("facets." + FractureToughness.MIN)).doubleValue(),
					((Double)topLevel.get("facets." + FractureToughness.MAX)).doubleValue()));

			pfos.add(new ProductFilterOptions(FractureEnergy.FRACTUREENERGY,
					FractureEnergy.FRACTUREENERGY_FACET, 
					((Double)topLevel.get("facets." + FractureEnergy.MIN)).intValue(),
					((Double)topLevel.get("facets." + FractureEnergy.MAX)).intValue()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pfos;
	}

	public List<ProductCompact> getCompactProductInfo(BASFProcess process,
			Map<String, String[]> options, int from, int size) {

		FilterBuilder fb = FilterBuilders.andFilter(
				FilterBuilders.rangeFilter(PotLife.MIN).gte(
						options.get(PotLife.MIN)[0]),
				FilterBuilders.rangeFilter(PotLife.MAX).lte(
						options.get(PotLife.MAX)[0]),
				FilterBuilders.rangeFilter(GelTime.MIN).gte(
						options.get(GelTime.MIN)[0]),
				FilterBuilders.rangeFilter(GelTime.MAX).lte(
						options.get(GelTime.MAX)[0]),
				FilterBuilders.rangeFilter(MixViscosity.MIN).gte(
						options.get(MixViscosity.MIN)[0]),
				FilterBuilders.rangeFilter(MixViscosity.MAX).lte(
						options.get(MixViscosity.MAX)[0]),
				FilterBuilders.rangeFilter(FlexuralStrength.MIN).gte(
						options.get(FlexuralStrength.MIN)[0]),
				FilterBuilders.rangeFilter(FlexuralStrength.MAX).lte(
						options.get(FlexuralStrength.MAX)[0]),
				FilterBuilders.rangeFilter(FractureToughness.MIN).gte(
						options.get(FractureToughness.MIN)[0]),
				FilterBuilders.rangeFilter(FractureToughness.MAX).lte(
						options.get(FractureToughness.MAX)[0]),
				FilterBuilders.rangeFilter(FractureEnergy.MIN).gte(
						options.get(FractureEnergy.MIN)[0]),
				FilterBuilders.rangeFilter(FractureEnergy.MAX).lte(
						options.get(FractureEnergy.MAX)[0]));
		
		SearchRequestBuilder srb = new SearchRequestBuilder(null).addFields("name", "sku").setFilter(fb).setFrom(from).setSize(size);
		
	
		Resty r = new Resty();
		List<ProductCompact> pis = new ArrayList<ProductCompact>();
		try {
			JSONResource topLevel = r
					.json(getElasticServerUrl() + SEARCH_OPERATION + SEARCH_TYPE_QUERY_THEN_FETCH,
							Resty.form(srb.toString()));

			JSONArray o = (JSONArray) topLevel.get("hits.hits");
			for (int i = 0; i < o.length(); i++) {
				pis.add(new ProductCompact(
						o.getJSONObject(i).getJSONObject("fields").getString("name"),
						o.getJSONObject(i).getJSONObject("fields").getString("sku"),
						o.getJSONObject(i).getInt("_id")));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pis;
	}

	public Map<String, Object> getProduct(String id) {
		Resty r = new Resty();
		Map<String, Object> m = null;
		try {
			JSONResource topLevel = r
					.json(getElasticServerUrl() + id);
			JSONObject o = (JSONObject)topLevel.get("_source");
			m = JSONToCollectionUtil.toMap(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	public static void main(String[] args) {
		ElasticsearchClient esc = getInstance();

		// List<BASFProcess> procs = esc.getProcesses();
		// System.out.println(procs);
		List<ProductFilterOptions> pfos = esc.getProductFilterOptions(0);
		System.out.println(pfos);

		
/*		 Map<String, String> options = ImmutableMap.<String, String>builder()
				 .put(PotLife.MIN,new String[] {15}).build();
		 List<ProductCompact> pcs = esc.getCompactProductInfo(procs.get(0), options, 0, 10);*/
		 
	}
}
