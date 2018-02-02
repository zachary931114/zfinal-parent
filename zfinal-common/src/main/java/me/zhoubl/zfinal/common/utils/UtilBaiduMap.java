package me.zhoubl.zfinal.common.utils;

import me.zhoubl.zfinal.common.CommonConfig;
import me.zhoubl.zfinal.common.utils.serialization.FastJsonSerializer;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhoubl on 2017/6/7.
 */
public class UtilBaiduMap {

	public static Map<String, BigDecimal> getLatAndLngByAddress(String addr) throws Exception {
		String lat = "";
		String lng = "";

		String url = "http://api.map.baidu.com/geocoder/v2/?ak=" + CommonConfig.commonMap.get("BaiduAK")
				+ "&output=json&address=" + addr;
		ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(new URI(url), HttpMethod.POST);
		request.getHeaders().set("Accept", "application/json");
		ClientHttpResponse response = request.execute();
		InputStream is = response.getBody();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String data = null;
		while ((data = br.readLine()) != null) {
			lat = data.substring(data.indexOf("\"lat\":") + ("\"lat\":").length(), data.indexOf("},\"precise\""));
			lng = data.substring(data.indexOf("\"lng\":") + ("\"lng\":").length(), data.indexOf(",\"lat\""));
		}

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("lat", new BigDecimal(lat));
		map.put("lng", new BigDecimal(lng));

		return map;
	}

	public static Map geoconv(String coords, String from, String to) throws Exception {
		String url = "http://api.map.baidu.com/geoconv/v1/?ak=" + CommonConfig.commonMap.get("BaiduAK") + "&coords="
				+ coords + "&from=" + from + "&to=" + to;
		ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(new URI(url), HttpMethod.POST);
		request.getHeaders().set("Accept", "application/json");
		ClientHttpResponse response = request.execute();
		InputStream is = response.getBody();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String data = null;
		StringBuilder sb = new StringBuilder();
		while ((data = br.readLine()) != null) {
			sb.append(data);
		}
		Map result = FastJsonSerializer.stringToCollect(sb.toString());
		return result;
	}
}
