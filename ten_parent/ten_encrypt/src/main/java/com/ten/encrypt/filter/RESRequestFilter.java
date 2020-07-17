package com.ten.encrypt.filter;

import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.ten.encrypt.rsa.RsaKeys;
import com.ten.encrypt.service.RsaService;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClsaaName RESRequestFilter
 * Version information 1.0
 * @Date 2020/7/16 09:32
 */
@Component
public class RESRequestFilter extends ZuulFilter {
    @Autowired
    private  RsaService rsaService;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        //是否使用过滤器
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("过滤器执行了");

        RequestContext currentContext = RequestContext.getCurrentContext();

        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();

        //声明存放加密后的数据的变量
        String requestData = null;
        String qecryptData = null;

        try {
            //通过request获取inputstream
            ServletInputStream inputStream = request.getInputStream();

            //从inputstream中得到加密的数据
            requestData = StreamUtils.copyToString(inputStream, Charsets.UTF_8);

            System.out.println(requestData);

            if(!Strings.isNullOrEmpty(requestData)) {
                qecryptData = rsaService.RSADecryptDataPEM(requestData, RsaKeys.getServerPrvKeyPkcs8());
                System.out.println(qecryptData);
            }

            if (!Strings.isNullOrEmpty(qecryptData)) {
                byte[] bytes = qecryptData.getBytes();

                currentContext.setRequest(new HttpServletRequestWrapper(request){
                    @Override
                    public int getContentLength() {
                        return bytes.length;
                    }

                    @Override
                    public long getContentLengthLong() {
                        return bytes.length;
                    }

                    @Override
                    public ServletInputStream getInputStream() throws IOException {
                        return new ServletInputStreamWrapper(bytes);
                    }
                });
            }

            currentContext.addZuulRequestHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE + ";charsets=UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }




        return null;
    }
}
