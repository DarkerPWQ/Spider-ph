package com.pwq.phmain.processor;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.pwq.phmain.Cachs.CacheContainer;
import com.pwq.phmain.base.Constants;
import com.pwq.phmain.utils.DownPic;
import com.pwq.phmain.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


/**
 * @Author：WenqiangPu
 * @Description
 * @Date：Created in 17:33 2018/1/11
 * @Modified By：
 */
@Component
@Slf4j
public class MmProcessor extends AbstructProcessor{
    Logger logger = LoggerFactory.getLogger(MmProcessor.class);

    private static final String BASE_URL = "http://www.mmjpg.com/";
    ArrayBlockingQueue arrayBlockingQueue  = new ArrayBlockingQueue(50);
    private ExecutorService executorService = new ThreadPoolExecutor(50,70,30,TimeUnit.SECONDS,arrayBlockingQueue);
    /**
     * @Author: WenqiangPu
     * @Description: 获取所有图片类的url，存入缓存
     * @param
     * @return:
     * @Date: 19:42 2018/1/15
     */
    @Test
    public void getAllPicUrl(){
        WebClient webClient = new WebClient();
        webClient = initWebClient(webClient);
        Map<String,String> header =new HashMap<>();
        CacheContainer cach = new CacheContainer();
        String logFalg = "[%s] mmjpg 主页请求";
        String url = BASE_URL;
        header.put("Host","www.mmjpg.com");
        Page page = getPage(webClient, url, HttpMethod.GET, null, Constants.MAX_SENDMSG_TIIMES, logFalg, header);
        String allNum = RegexUtils.matchValue("><em class=\"info\">共(\\d+)页</em", page.getWebResponse().getContentAsString());
        //
        List<List<String>> picClassUrl = RegexUtils.matchesMutiValue("title\"><a href=\"(.*?)\" target=\"_blank\">(.*?)</a><",page.getWebResponse().getContentAsString());
        for(List everyPic:picClassUrl){
            String num = pageNum(webClient,(String)everyPic.get(1),(String)everyPic.get(0));
            List<String> list = getAllUrlByNum(num);
            cach.putStrings((String)everyPic.get(1),list);//所有url准备就绪
        }
        downPic(cach);
        while(true){}
    }


    /**
     * @Author: WenqiangPu
     * @Description: 将类图片进行url组装
     * @param
     * @return:
     * @Date: 19:44 2018/1/15
     */

    public void getAllPage(String num){



    }

    /**
     * @Author: WenqiangPu
     * @Description: 获取图类一共的张数
     * @param url
     * @return:
     * @Date: 19:09 2018/1/16
     */
    public String pageNum(WebClient webClient,String item,String url){
        String logFlag = String.format("[%s] 获取图片张数", item);
        Map<String,String> header =new HashMap<>();
        header.put("Host","www.mmjpg.com");
        Page page = getPage(webClient, url, HttpMethod.GET, null, Constants.MAX_SENDMSG_TIIMES, logFlag, header);
        return         RegexUtils.matchValue("<img src=\"(.*?)1.jpg\" alt=\""+item+"\"",page.getWebResponse().getContentAsString())+
        RegexUtils.matchValue(">(\\d+)</a><em class=\"ch all",page.getWebResponse().getContentAsString());
    }
    /**
     * @Author: WenqiangPu
     * @Description: 根据url以及共张数返回url列表
     * @param url
     * @param
     * @return:
     * @Date: 19:17 2018/1/16
     */
    public List<String> getAllUrlByNum(String url){
        List<String> list =new ArrayList<>();
        int num = Integer.parseInt(url.substring(url.lastIndexOf("/")+1,url.length()));
        String preUrl = url.substring(0,url.lastIndexOf("/")+1);
        for(int i=1;i<=num;i++){
            list.add(preUrl+i+".jpg");
        }
        return list;


    }

    public void downPic(CacheContainer cacheContainer){
        Map<String,List<String>> urlMap= cacheContainer.getStringsMap();
        for(final Map.Entry<String,List<String>> entry:urlMap.entrySet()){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    DownPic.downloadPicture(entry.getKey(),entry.getValue());
                }
            });
        }




    }
    @Override
    protected Logger getLogger() {
        return logger;
    }







}
