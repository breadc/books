package com.books.crawer;

import com.books.pipeline.ConsoleJsoPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class BookPageProcessor implements PageProcessor {

	private static final String LIST = "https://www\\.biquge5\\.com/18_18221/\\w+\\.html";

	private Site site = Site
			.me()
			.setSleepTime(3000)
			.setDomain("www.biquge5.com")
			.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31(KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	@Override
	public void process(Page page) {
			Html  html=page.getHtml();
			Selectable kui=page.getHtml().xpath("//ul[@class='_chapter']");
			page.putField("title", kui);		
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new BookPageProcessor())
				.addUrl("https://www.biquge5.com/18_18221/")
				.addPipeline(new ConsoleJsoPipeline()).run();
	}
}
