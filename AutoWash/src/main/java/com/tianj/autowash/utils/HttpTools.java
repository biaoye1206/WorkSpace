package com.tianj.autowash.utils;

import okhttp3.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 * @version v1.0
 * @update 2019-01-22 16:47
 */
@Component
public final class HttpTools {
    public String http(String url, HttpMethod method, Map body, Map<String, String> header) throws IOException {
        Request.Builder builder = new Request.Builder();
        Request request = null;
        switch (method) {
            case POST:
                String xml = createXmlByMap(body);
                RequestBody reqBody = RequestBody
                        .create(MediaType.parse("application/*; charset=utf-8"), xml);
                request =  builder
                        .url(url)
                        .post(reqBody).build();
                break;
            case GET:
                // 处理请求头信息
                addHeader(header, builder);
                StringBuffer buffer = new StringBuffer(url + "?");
                Iterator iterator;
                // 处理参数信息
                Set set = body.keySet();
                for (Object key : set) {
                    String value = (String) body.get(key);
                    buffer.append(key)
                            .append("=")
                            .append(value)
                            .append("&");
                }
                String httUrl = buffer.substring(0, buffer.length() - 1);
               request = builder.url(httUrl).build();
               break;
            default:
        }
        OkHttpClient okHttpClient = new OkHttpClient();

        Response execute = okHttpClient.newCall(request)
                .execute();
        return execute.body().string();

    }

    private void addHeader(Map<String, String> header, Request.Builder builder) {
        Set set = header.keySet();
        for (Object key : set) {
            String value = header.get(key);
            builder.addHeader((String) key, value);
        }
    }

    /**
     * 通过map对象创建xml
     *
     * @param body
     * @return
     */
    private String createXmlByMap(Map<String, String> body) {
        Document doc = DocumentHelper.createDocument();
        Element xml = doc.addElement("xml");
        Set<String> set = body.keySet();
        for (String key : set) {
            Element element = xml.addElement(key);
            element.addCDATA(body.get(key));
        }
        StringWriter writer = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(writer);
        try {
            xmlWriter.write(doc);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return writer.toString();
    }
}
