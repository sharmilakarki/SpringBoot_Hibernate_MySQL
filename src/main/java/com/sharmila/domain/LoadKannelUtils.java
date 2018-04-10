package com.sharmila.domain;


import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadKannelUtils {

	private static final Logger logger=LoggerFactory.getLogger(LoadKannelUtils.class);
	
	@Value("${kannel-username}")
	private String username;
	
	@Value("${kannel-password}")
	private String password;
	
	@Value("${kannel-url}")
	private String url;
	
	@PostConstruct
	public void start(){
		
		//connectTokannel(username, password, url, new SmsLog());
	}
	
	
	public SmsLog sendFakeSms(SmsLog smsLog){
		 String body=null;
		logger.info(" username "+username);
		
		CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url);

		StringEntity stringEntity=null;
		
		String messageXML = "<message><submit><da><number>" + smsLog.getRecieptent() + "</number></da><oa><number>" + smsLog.getSender()
				+ "</number></oa><ud>" + smsLog.getText() + "</ud><from><username>" + username + "</username><password>"
				+ password + "</password></from><to>" + "send" + "</to></submit></message>";
		InputStream inputStream;
		stringEntity=new StringEntity(messageXML, ContentType.create("text/xml", Consts.UTF_8));
		
		stringEntity.setChunked(true);
		
		httpPost=new HttpPost(url);
		httpPost.setEntity(stringEntity);
		SmsLog sms=new SmsLog();
		try {
			CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
			
			inputStream=response.getEntity().getContent();
             body = IOUtils.toString(inputStream);
            System.out.println(body);
            if(body.equalsIgnoreCase("3: Queued for later delivery")){
            	logger.info("Queuing processs");
            	sms=smsLog;
            }
            EntityUtils.consume(stringEntity);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sms;
	}
}
