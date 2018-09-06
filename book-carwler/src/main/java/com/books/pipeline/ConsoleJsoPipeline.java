package com.books.pipeline;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by chenghui on 2018/9/5.
 */
public class ConsoleJsoPipeline implements Pipeline {

    private static  final Log logger= LogFactory.getLog(ConsoleJsoPipeline.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("ConsoleJsoPipeline.process,json格式解析开始");
        Request request=resultItems.getRequest();
        logger.info("ConsoleJsoPipeline.process,json格式,请求的URL:"+request.getUrl()+",请求方式为:"+request.getMethod());
        String resultJSON= JSON.toJSONString(resultItems.getAll());
        logger.info("ConsoleJsoPipeline.process,json格式,请求的数据为:"+resultJSON);
        logger.info("ConsoleJsoPipeline.process,json格式,解析完成");
    }
}
