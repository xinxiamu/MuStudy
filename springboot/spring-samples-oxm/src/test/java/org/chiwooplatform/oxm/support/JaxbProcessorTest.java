package org.chiwooplatform.oxm.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import org.chiwooplatform.oxm.config.AppConfig;
import org.chiwooplatform.simple.model.WechatMessage;
import org.junit.Test;
import org.junit.runner.RunWith;

@SpringBootConfiguration
@Import({ AppConfig.class })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JaxbProcessorTest.class)
public class JaxbProcessorTest {

	@Autowired
	private JaxbProcessor processor;

	@Test
	public void toXmlTest() throws Exception {
		System.out.println("processor: " + processor);
		WechatMessage msg = new WechatMessage();
		msg.setToUserName("ToUserName");
		msg.setContent("aaa");
		String xmlStr = processor.toXml(msg);
		System.out.println(xmlStr);
	}

	@Test
	public void toBeanTest() throws Exception {
		String xml = "<xml><aaa>sdfsdfsd</aaa><ToUserName><![CDATA[mutou]]></ToUserName><FromUserName><![CDATA[yingying]]></FromUserName>"
				+ "<CreateTime>1498547497</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[加油]]></Content><MsgId>6436212491545076337</MsgId></xml>";
		WechatMessage message = processor.toBean(xml, WechatMessage.class);
		System.out.println("message: " + message);
	}
}
