package com.qunincey.wiremock;


import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-16 17:20
 **/

public class MockServer {


    public static void main(String[] args) throws IOException {
        WireMock.configureFor(9999);
        WireMock.removeAllMappings();

        mock("/user/1","1");
        mock("/user/2","2");


    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("response/"+file+".txt");
        String content = FileUtils.readFileToString(resource.getFile(),"utf-8");
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
                .willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
