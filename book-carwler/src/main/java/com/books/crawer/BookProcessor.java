package com.books.crawer;

import com.books.util.CrawerContans;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by chenghui on 2018/9/4.
 */
public class BookProcessor implements PageProcessor {

    private  static  final String  TITLE_LIST="https://www\\.biquge5\\.com/18_18221/\\d+\\.html";

    private final Site site = Site.me().
            setTimeOut(3000)
            .setSleepTime(3000)
            .setCharset("UTF-8")
            .setDomain("www.biquge5.com")
            .setUserAgent(CrawerContans.AENT);

    @Override
    public void process(Page page) {
        if(page.getUrl().regex(TITLE_LIST).match()){
            page.putField("url_tile",page.getHtml().xpath("<li></li>"));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BookProcessor()).addUrl("https://www.biquge5.com/18_18221/").run();
    }
}
