package com.mychart.mychart.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class YouTubeUtil {

	public String getYoutube(String search) throws Exception {
		search = "아이유-에잇(Prod.&Feat. SUGA of BTS)";

		String apiurl = "https://www.googleapis.com/youtube/v3/search";
		apiurl += "?key=AIzaSyCXQpmMdSqA9Rkx0FL2VPkBgo4fOebbFzs";
		apiurl += "&part=snippet&type=video&maxResults=1&videoEmbeddable=true";
		apiurl += "&q=" + URLEncoder.encode(search, "UTF-8");

		URL url = new URL(apiurl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();		
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		
		ObjectMapper mapper = new ObjectMapper();
		JSONObject obj = mapper.readValue(url, JSONObject.class);
		JSONObject items = (JSONObject) obj.get("itmes");
		System.out.println(items.toString());
		
		
		return response.toString();
	}
}
