package com.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description:根据网页地址转换成图片
 * @Author: admin
 * @CreateDate: 2018年6月22日
 */
public class PhantomTools {
    private static String tempPath = "D:/temp/img";// 图片保存目录
    private static String BLANK = " ";
    // 下面内容可以在配置文件中配置
    private static String binPath = "D:/tooles/phantomjs/phantomjs-2.1.1-windows/bin/phantomjs.exe";// 插件引入地址
    private static String jsPath = "D:/tooles/phantomjs/phantomjs-2.1.1-windows/examples/rasterize.js";// js引入地址

    // 执行cmd命令
    public static String cmd(String imgagePath, String url) {
        return binPath + BLANK + jsPath + BLANK + url + BLANK + imgagePath;
    }
    //关闭命令
    public static void close(Process process, BufferedReader bufferedReader) throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (process != null) {
            process.destroy();
            process = null;
        }
    }
    /**
     *
     * @param url
     * @throws IOException
     */
    public static void printUrlScreen2jpg(String url) throws IOException{
        String imgagePath = tempPath+"/"+System.currentTimeMillis()+".png";//图片路径
        //Java中使用Runtime和Process类运行外部程序
        Process process = Runtime.getRuntime().exec(cmd(imgagePath,url));
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String tmp = "";
      /* while ((tmp = reader.readLine()) != null) {
            close(process,reader);
        }*/
        System.out.println("success");
    }

    public static void main(String[] args) throws IOException {
        String url = "https://www.baidu.com/s?wd=%E7%88%B1%E5%A5%87%E8%89%BA&rsv_spt=1&rsv_iqid=0xa443335300041baa&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=02003390_3_hao_pg&rsv_enter=0&rsv_dl=tb&oq=%25E7%2588%25B1%25E5%25A5%2587%25E8%2589%25BA&rsv_btype=t&rsv_t=d760YK%2BnSiocw0XkrIJeXtrr6VPcOlwIB%2ByOeYmigY2XimNRnJsOkoBChBkpg3jxivBIW4GND8c&rsv_pq=c10593a9000726e7";//以百度网站首页为例
        PhantomTools.printUrlScreen2jpg(url);
    }
}