package com.mychart.mychart.controller;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mychart.mychart.vo.ChartVO;

@Controller
public class ChartController {

	@GetMapping({ "/", "/melon" })
	public String melon(Model model) throws Exception {
		// 멜론 사이트에서 Jsoup 파싱
		Document doc = Jsoup.connect("https://www.melon.com/chart/index.htm").get();

		// 제목 부분 파싱
		Elements titleinfo = doc.select("div.rank01 span a");
		List<String> titleList = titleinfo.eachText();
		// 가수 부분 파싱
		Elements artistinfo = doc.select("div.rank02 span");
		List<String> artistList = artistinfo.eachText();
		// 앨범아트 파싱
		Elements albuminfo = doc.select("a.image_typeAll img");
		List<String> albumList = albuminfo.eachAttr("src");
		
		List<ChartVO> chartList = new ArrayList<ChartVO>();
		for (int i = 0; i < titleList.size(); i++) {
			ChartVO chart = new ChartVO();
			chart.setRank(Integer.toString(i + 1));
			chart.setTitle(titleList.get(i));
			chart.setArtist(artistList.get(i));
			chart.setSrc(albumList.get(i));
			chartList.add(chart);

		}

		model.addAttribute("chartList", chartList);
		return "melon";
	}

	@GetMapping("/bugs")
	public String bugs(Model model) throws Exception {
		// 벅스 사이트에서 Jsoup 파싱
		Document doc = Jsoup.connect("https://music.bugs.co.kr/chart").get();

		// 제목 부분 파싱
		Elements titleinfo = doc.select("th p.title a");
		List<String> titleList = titleinfo.eachText();
		// 가수 부분 파싱
		Elements artistinfo = doc.select("td.left p.artist a:first-child");
		List<String> artistList = artistinfo.eachText();
		// 앨범아트 파싱
		Elements albuminfo = doc.select("td a.thumbnail img");
		List<String> albumList = albuminfo.eachAttr("src");

		List<ChartVO> chartList = new ArrayList<ChartVO>();
		for (int i = 0; i < titleList.size(); i++) {
			ChartVO chart = new ChartVO();
			chart.setRank(Integer.toString(i + 1));
			chart.setTitle(titleList.get(i));
			chart.setArtist(artistList.get(i));
			chart.setSrc(albumList.get(i));
			chartList.add(chart);
		}

		model.addAttribute("chartList", chartList);
		return "bugs";
	}

	@GetMapping("/genie")
	public String genie(Model model) throws Exception {
		// 지니 사이트에서 Jsoup 파싱
		Document top50 = Jsoup.connect("https://www.genie.co.kr/chart/top200").get();
		Document top100 = Jsoup.connect("https://www.genie.co.kr/chart/top200?ditc=D&ymd=20200602&hh=03&rtm=Y&pg=2")
				.get();

		// 제목 부분 파싱
		Elements titleinfoTop50 = top50.select("td.info > a:first-child");
		Elements titleinfoTop100 = top100.select("td.info > a:first-child");
		List<String> titleList = titleinfoTop50.eachText();
		titleList.addAll(titleinfoTop100.eachText());

		// 가수 부분 파싱
		Elements artistinfoTop50 = top50.select("td.info > a:nth-child(2)");
		Elements artistinfoTop100 = top100.select("td.info > a:nth-child(2)");
		List<String> artistList = artistinfoTop50.eachText();
		artistList.addAll(artistinfoTop100.eachText());

		// 앨범아트 파싱
		Elements albuminfoTop50 = top50.select("a.cover img");
		Elements albuminfoTop100 = top100.select("a.cover img");
		List<String> albumList = albuminfoTop50.eachAttr("src");
		albumList.addAll(albuminfoTop100.eachAttr("src"));

		List<ChartVO> chartList = new ArrayList<ChartVO>();
		for (int i = 0; i < titleList.size(); i++) {
			ChartVO chart = new ChartVO();
			chart.setRank(Integer.toString(i + 1));
			chart.setTitle(titleList.get(i));
			chart.setArtist(artistList.get(i));
			chart.setSrc(albumList.get(i));
			chartList.add(chart);
		}

		model.addAttribute("chartList", chartList);
		return "genie";
	}

}
