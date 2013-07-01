package com.booteak.basf.advmat.datagen;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import io.searchbox.core.Index;

import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;

import com.booteak.basf.advmat.common.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AdvancedMaterialsData {

	private static final String SEARCHBOX_ELASTIC_SERVER_URL =
			"http://api.searchbox.io/api-key/4b2b15abc74453f926fa588e084f82ba/";


	
	public static void main(String[] args) {
		assert args.length > 0;
		int numProducts = Integer.valueOf(args[0]);
		assert numProducts >= 0;
		Gson gson = new GsonBuilder()
	    .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE)
	    .create();

		 // Configuration
		 ClientConfig clientConfig = new ClientConfig();
		 LinkedHashSet<String> servers = new LinkedHashSet<String>();
		 servers.add(SEARCHBOX_ELASTIC_SERVER_URL);
		 clientConfig.getProperties().put(ClientConstants.SERVER_LIST, servers);
		 clientConfig.getProperties().put(ClientConstants.IS_MULTI_THREADED, true);

		 // Construct a new Jest client according to configuration via factory
		 JestClientFactory factory = new JestClientFactory();
		 factory.setClientConfig(clientConfig);
		 JestClient client = factory.getObject();
						
		for(int i = 0; i < numProducts; i++) {
			Product p = Product.newInstance();
			String prodJson = gson.toJson(p);

			try {
				Index index = new Index.Builder(p)
					.index("basftest")
					.type("product")
					.id(Integer.toString(p.getId()))
					.build();
				JestResult jr = client.execute(index);
				System.out.println(jr.getJsonString());
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println(prodJson);
			}
		}
	}

}
