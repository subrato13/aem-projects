/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.aem.subrato.core.servlets;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@SuppressWarnings("serial")
@SlingServlet(paths = "/bin/myForexServlet",methods = "GET",metatype=true)
public class ForexServlet extends SlingSafeMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ForexServlet.class);
    private static final String ENDPOINT = "http://api.fixer.io/latest?symbols=USD,GBP,INR";
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        //Create an HTTPClient object
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(ENDPOINT);
        String responseBody= StringUtils.EMPTY;
        try {
            int statusCode =  client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                LOG.debug("HTTP Method failed: {}" , method.getStatusLine());
            }
            responseBody = method.getResponseBodyAsString();
            response.getWriter().write(responseBody);
        }catch ( Exception e){
            LOG.error("responseBody : {}",responseBody);
        }
    }
}
