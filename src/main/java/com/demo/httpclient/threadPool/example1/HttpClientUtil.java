package com.demo.httpclient.threadPool.example1;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 带有连接池的Http客户端工具类。具有如下特点：
 * <ol>
 * <li>基于apache的高性能Http客户端{@link org.apache.http.client.HttpClient}；</li>
 * <li>连接池的最大连接数默认是20，可通过{@link #init(int, int)}、或者系统变量-Dzzarch.common.http.max.total=200指定；</li>
 * <li>连接池的每个路由的最大连接数默认是2，可通过{@link #init(int, int)}、或者系统变量-Dzzarch.common.http.max.per.route=10指定；</li>
 * <li>可设置超时，通过{@link HttpOptions}进行设置；</li>
 * <li>可重试，通过{@link HttpOptions}进行设置；</li>
 * </ol>
 *
 * @author duyunjie
 * @date 2019-09-18 16:33
 */
public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * HttpClient 连接池
     */
    private static PoolingHttpClientConnectionManager CONNECTION_MANAGER = buildPoolingHttpClientConnectionManager(null, null);

    /**
     * @param maxTotal    连接池的最大连接数,默认为20。
     * @param maxPerRoute 连接池的每个路由的最大连接数，默认为2。
     */
    public static void init(int maxTotal, int maxPerRoute) {
        CONNECTION_MANAGER = buildPoolingHttpClientConnectionManager(maxTotal, maxPerRoute);
    }


    public static HttpResponse httpGet(HttpRequest httpRequest) throws Exception {
        return httpGet(httpRequest, null);
    }

    /**
     * 发送 HTTP GET请求
     *
     * @param httpRequest 请求参数，如url，header等。
     * @param httpOptions 配置参数，如重试次数、超时时间等。
     * @return
     * @throws Exception
     */
    public static HttpResponse httpGet(HttpRequest httpRequest, HttpOptions httpOptions) throws Exception {
        // 装载请求地址和参数
        URIBuilder ub = new URIBuilder(httpRequest.getUrl());

        // 转换请求参数
        List<NameValuePair> pairs = convertParams2NVPS(httpRequest.getParams());
        if (!pairs.isEmpty()) {
            ub.setParameters(pairs);
        }
        HttpGet httpGet = new HttpGet(ub.build());

        // 设置请求头
        if (Objects.nonNull(httpRequest.getHeaders())) {
            for (Map.Entry<String, String> header : httpRequest.getHeaders().entrySet()) {
                httpGet.addHeader(header.getKey(), String.valueOf(header.getValue()));
            }
        }

        return doHttp(httpGet, httpOptions);
    }


    public static HttpResponse httpPost(HttpRequest httpRequest) throws Exception {
        return httpPost(httpRequest, null);
    }


    /**
     * 发送 HTTP POST请求
     *
     * @param httpRequest 请求参数
     * @param httpOptions 配置参数
     * @return
     * @throws Exception
     */
    public static HttpResponse httpPost(HttpRequest httpRequest, HttpOptions httpOptions) throws Exception {
        HttpPost httpPost = new HttpPost(httpRequest.getUrl());

        // 转换请求参数
        List<NameValuePair> pairs = convertParams2NVPS(httpRequest.getParams());
        if (!pairs.isEmpty()) {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8.name()));
        }

        // 设置请求头
        if (Objects.nonNull(httpRequest.getHeaders())) {
            for (Map.Entry<String, String> header : httpRequest.getHeaders().entrySet()) {
                httpPost.addHeader(header.getKey(), String.valueOf(header.getValue()));
            }
        }

        return doHttp(httpPost, httpOptions);
    }


    /**
     * 发送 HTTP POST请求，参数格式JSON
     * <p>请求参数是JSON格式，数据编码是UTF-8</p>
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static HttpResponse httpPostJson(String url, String param, HttpOptions httpOptions) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");

        // 设置请求参数
        httpPost.setEntity(new StringEntity(param, StandardCharsets.UTF_8.name()));

        return doHttp(httpPost, httpOptions);
    }

    /**
     * 发送 HTTP POST请求，参数格式XML
     * <p>请求参数是XML格式，数据编码是UTF-8</p>
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static HttpResponse httpPostXml(String url, String param, HttpOptions httpOptions) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        httpPost.addHeader("Content-Type", "application/xml; charset=UTF-8");

        // 设置请求参数
        httpPost.setEntity(new StringEntity(param, StandardCharsets.UTF_8.name()));

        return doHttp(httpPost, httpOptions);
    }

    /**
     * 通过post发送multipart信息。
     *
     * @param url
     * @param multiparts
     * @param httpOptions
     * @return
     * @throws Exception
     */
    /*public static HttpResponse httpPostMultipart(String url, Map<String, ContentBody> multiparts, HttpOptions httpOptions) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        // 设置Multipart
        if (Objects.nonNull(multiparts)) {
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            for (Map.Entry<String, ContentBody> multipartEntry : multiparts.entrySet()) {
                multipartEntityBuilder.addPart(multipartEntry.getKey(), multipartEntry.getValue());
            }

            httpPost.setEntity(multipartEntityBuilder.build());
        }

        return doHttp(httpPost, httpOptions);
    }*/


    /**
     * 转换请求参数，将Map键值对拼接成QueryString字符串
     *
     * @param params
     * @return
     */
    public static String convertParams2QueryStr(Map<String, ?> params) {
        List<NameValuePair> pairs = convertParams2NVPS(params);

        return URLEncodedUtils.format(pairs, StandardCharsets.UTF_8.name());
    }

    /**
     * 转换请求参数
     *
     * @param params
     * @return
     */
    public static List<NameValuePair> convertParams2NVPS(Map<String, ?> params) {
        if (Objects.isNull(params)) {
            return new ArrayList<>();
        }

        return params.entrySet().stream().map(param -> new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue()))).collect(Collectors.toList());
    }

    /**
     * 发送 HTTP 请求
     *
     * @param request
     * @return
     * @throws Exception
     */
    private static HttpResponse doHttp(HttpRequestBase request, HttpOptions httpOptions) throws Exception {
        if (Objects.isNull(httpOptions)) {//如果为空，则用默认的。
            httpOptions = HttpOptions.DEFAULT_HTTP_OPTION;
        }
        // 设置超时时间
        if (Objects.nonNull(httpOptions.getTimeoutMs())) {
            request.setConfig(RequestConfig.custom().setSocketTimeout(httpOptions.getTimeoutMs()).build());
        }

        //设置重试策略
        HttpRequestRetryHandler httpRequestRetryHandler = null;
        if (Objects.nonNull(httpOptions.getRetryCount())) {
            httpRequestRetryHandler = new HttpRequestRetryHandler(httpOptions.getRetryCount());
        }

        // 通过连接池获取连接对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(CONNECTION_MANAGER).setRetryHandler(httpRequestRetryHandler).build();
        return doRequest(httpClient, request);

    }

    /**
     * 处理Http/Https请求，并返回请求结果
     * <p>注：默认请求编码方式 UTF-8</p>
     *
     * @param httpClient
     * @param request
     * @return
     * @throws Exception
     */
    private static HttpResponse doRequest(CloseableHttpClient httpClient, HttpRequestBase request) throws Exception {
        HttpResponse res = new HttpResponse();
        CloseableHttpResponse response = null;
        long start = System.currentTimeMillis();

        try {
            // 获取请求结果
            response = httpClient.execute(request);

            // 解析请求结果
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, StandardCharsets.UTF_8.name()); // 转换结果
            EntityUtils.consume(entity); // 关闭IO流

            //解析返回header
            Map<String, String> headers = new HashMap<>(response.getAllHeaders().length);
            for (Header header : response.getAllHeaders()) {
                headers.put(header.getName(), header.getValue());
            }

            res.setStatusCode(response.getStatusLine().getStatusCode()).setResult(result).setHeaders(headers);
        } finally {
            if (Objects.nonNull(response)) {
                response.close();
            }
        }

        long elapsed = System.currentTimeMillis() - start;
        logger.debug("op=end_doRequest,request={},res={},elapsed={}", request, res, elapsed);
        return res;
    }


    /**
     * 初始化连接池
     *
     * @return
     */
    private static PoolingHttpClientConnectionManager buildPoolingHttpClientConnectionManager(Integer maxTotal, Integer maxPerRoute) {
        // 初始化连接池，可用于请求HTTP/HTTPS（信任所有证书）
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(getRegistry());

        // 整个连接池的最大连接数
        String maxTotalProperty = null;
        if (Objects.nonNull(maxTotal)) { //首先看有没有在参数中显式指定
            connectionManager.setMaxTotal(maxTotal);
        } else { //如果没有在参数中显式指定，则再看有没有在系统变量中指定
            maxTotalProperty = System.getProperty(Constants.SYSTEM_PROPERTY_KEY_HTTP_MAX_TOTAL);
            if (Objects.nonNull(maxTotalProperty)) {
                connectionManager.setMaxTotal(Integer.valueOf(maxTotalProperty));
            }
        }

        // 每个路由的最大连接数
        String maxPerRouteProperty = null;
        if (Objects.nonNull(maxPerRoute)) { //首先看有没有在参数中显式指定
            connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        } else { //如果没有在参数中显式指定，则再看有没有在系统变量中指定
            maxPerRouteProperty = System.getProperty(Constants.SYSTEM_PROPERTY_KEY_HTTP_MAX_PER_ROUTE);
            if (Objects.nonNull(maxPerRouteProperty)) {
                connectionManager.setDefaultMaxPerRoute(Integer.valueOf(maxPerRouteProperty));
            }
        }

        logger.info("[ZZARCH_COMMON_SUCCESS_buildPoolingHttpClientConnectionManager]maxTotal={},maxPerRoute={},maxTotalProperty={},maxPerRouteProperty={}", maxTotal, maxPerRoute, maxTotalProperty, maxPerRouteProperty);
        return connectionManager;
    }


    /**
     * 获取 HTTPClient注册器
     *
     * @return
     * @throws Exception
     */
    private static Registry<ConnectionSocketFactory> getRegistry() {
        try {
            return RegistryBuilder.<ConnectionSocketFactory>create().register("http", new PlainConnectionSocketFactory()).register("https", new SSLConnectionSocketFactory(SSLContext.getDefault())).build();
        } catch (Exception e) {
            logger.error("[ERROR_getRegistry]", e);
        }

        return null;
    }
}
