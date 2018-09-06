package com.books.crawer;

import com.books.pipeline.ConsoleJsoPipeline;
import com.books.util.CrawerContans;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class BookNameProcess implements PageProcessor{
	private Site site = Site
			.me()
			.setSleepTime(3000)
			.setDomain("www.biquge5.com")
			.setUserAgent(CrawerContans.AENT);
	@Override
	public void process(Page page) {
		page.putField("title", page.getHtml().xpath("//ul[@class='list-group list-top']"));
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new BookNameProcess())
				.addUrl("https://www.biquge5.com/shuku/5/default-0-1.html")
				.addPipeline(new ConsoleJsoPipeline()).run();
	}
}
