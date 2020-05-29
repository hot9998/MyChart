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
public class MelonController {

	@GetMapping({ "/", "/melon" })
	public String melon(Model model) throws Exception {
		// 멜론 사이트에서 Jsoup 파싱
		Document doc = Jsoup.connect("https://www.melon.com/chart/index.htm").get();

		// 순위 부분 파싱
//			String rank = doc.select("span.rank").text();
//			String rankArr[] = rank.split(" ");
		// 1~100 순위 배열에서 "순위" 항목 제거를 위해 리스트로 변환해서 제거
//			List<String> rankList = new ArrayList<String>(Arrays.asList(rankArr));
//			rankList.remove("순위");

		// 제목 부분 파싱
		Elements titleinfo = doc.select("div.rank01 span a");
		List<String> titleList = titleinfo.eachAttr("title");
		// 가수 부분 파싱
		Elements artistinfo = doc.select("div.rank02 span a");		
		List<String> artistList = artistinfo.eachAttr("title");
		// 앨범아트 파싱
		Elements albuminfo = doc.select("a.image_typeAll img");
		List<String> albumList = albuminfo.eachAttr("src");
		
		List<ChartVO> chartList = new ArrayList<ChartVO>();
		for (int i = 0; i < titleList.size(); i++) {
			ChartVO chart = new ChartVO();
			// 제목 리스트에서 제목부분 하나씩 가져옴
			String chartStr = titleList.get(i);
			String artistStr = artistList.get(i);

//			System.out.print((i + 1) + "\t");
//			System.out.println(str.substring(0, str.length() - 3) + "\t");

			chart.setRank(Integer.toString((i + 1)));
			// 제목,가수명 뒤에 붙은 부분을 제거 후 chartVO에 추가
			chart.setTitle(chartStr.substring(0, chartStr.length() - 3));
			chart.setArtist(artistStr.substring(0, artistStr.length() - 9));
			chart.setSrc(albumList.get(i));
			chartList.add(chart);
						
//			System.out.println(chart.getRank());
//			System.out.println(chart.getArtist());			
//			System.out.println(chart.getTitle());
		}

		model.addAttribute("chartList", chartList);
		return "melon";

	}

}
