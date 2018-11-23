package com.suma.utils;

import com.google.common.collect.Maps;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.log4j.Log4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang3.Validate;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author: luxinzong
 * @date: 2018/11/22 0022
 * @description
 */
@Log4j
public class TransFileUtils {


    public static Map<String, String> executeDriverServer(String driverUrl, Map<String, Object> param
            , String multipart, String contentType, int timeOut, String pocPath) throws Exception {
        String res = "";
        Map<String, String> map = Maps.newConcurrentMap();
        ContentType ctype = ContentType.create("content-disposition", "UTF-8");
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpResponse httpResponse = null;
        try {
            HttpPost httpPost = new HttpPost(driverUrl);

            //设置请求和传输超时时间
            //BTW 4.3版本不设置超时的话，一旦服务器没有响应，等待时间很久
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(5000).build();
            httpPost.setConfig(requestConfig);
            if (httpPost != null) {
                if ("formdata".equals(multipart)) {
                    MultipartEntityBuilder mentity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
                    Set<String> keySet = param.keySet();
                    for (String key : keySet) {
                        Object paramObj = Validate.notNull(param.get(key));
                        if (paramObj instanceof MultipartFile) {
                            mentity.addBinaryBody(key, ((MultipartFile) paramObj).getInputStream(),
                                    ctype, ((MultipartFile) paramObj).getOriginalFilename());
                        } else if (paramObj instanceof File) {
                            mentity.addBinaryBody(key, (File) paramObj);
                        } else {
                            mentity.addPart(key, new StringBody(paramObj.toString(), ctype));
                            //mentity.addTextBody(key, paramObj.toString());
                        }
                        log.info("key:::" + key);
                        log.info("paramObj:::" + paramObj.toString());
                    }
                    HttpEntity entity = mentity.build();
                    HttpUriRequest post = RequestBuilder.post().setUri(driverUrl).setEntity(entity).build();
                    httpResponse.setEntity(entity);
                    httpResponse = closeableHttpClient.execute(httpPost);
                }
                if (httpResponse == null) {
                    throw new Exception("无返回结果");
                }
                //获取返回的状态码
                int status = httpResponse.getStatusLine().getStatusCode();
                log.info("Post请求URL=" + driverUrl + ",请求的参数=" + param.toString() + ",请求的格式" + contentType + ",状态=" + status);
                if (status == HttpStatus.SC_OK) {
                    HttpEntity entity2 = httpResponse.getEntity();
                    InputStream ins = entity2.getContent();
                    res = toString(ins);
                    ins.close();
                } else {
                    InputStream fis = httpResponse.getEntity().getContent();
                    Scanner sc = new Scanner(fis);
                    log.info("Scanner :::" + sc.next());
                    log.error("Post请求URL=" + driverUrl + ",请求的参数=" + param.toString() + ",请求的格式" + contentType + ",错误Code:" + status);
                }
                map.put("code", String.valueOf(status));
                map.put("result", res);
                log.info("执行post方法请求返回的结果=" + res);
            }
        }  catch (ClientProtocolException e) {
            map.put("code","12");
            map.put("result", e.getMessage());
        } catch (UnsupportedEncodingException e) {
            map.put("code", "13");
            map.put("result", e.getMessage());
        } catch (IOException e) {
            map.put("code","14");
            map.put("result", e.getMessage());
        }finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                log.error("调用httpClient出错", e);
                throw new Exception("调用httpClient出错", e);
            }
        }
        return null;
    }

    private static String toString(InputStream in) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = in.read(b)) != -1) {
            os.write(b, 0, len);
        }
        return os.toString("UTF-8");
    }

    public static MultipartFile getMulFileByPath(String picPath) {
        FileItem fileItem = createFileItem(picPath);
        MultipartFile mfile = new CommonsMultipartFile(fileItem);
        return mfile;
    }

    private static FileItem createFileItem(String filePath) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        int num = filePath.lastIndexOf(".");
        String extFile = filePath.substring(num);
        FileItem item = factory.createItem(textFieldName, "text/plain", true, "MyFileName" + extFile);
        File newfile = new File(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[4096];
        try {
            FileInputStream fis = new FileInputStream(newfile);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }
}
