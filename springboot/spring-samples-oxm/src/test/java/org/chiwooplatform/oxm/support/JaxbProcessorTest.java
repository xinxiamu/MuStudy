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
    public void ut1001_toXml()
        throws Exception {
        System.out.println( "processor: " + processor );
        WechatMessage msg = new WechatMessage();
        msg.setToUserName( "ToUserName" );
        msg.setContent( "aaa" );
        processor.toXml( msg );
        System.out.println( "processor.toXml( msg ): " + processor.toXml( msg ) );
    }

    @Test
    public void ut1002_toBean()
        throws Exception {
        String xml = "<xml><aaa>sdfsdfsd</aaa><ToUserName><![CDATA[gh_37b9b1054c21]]></ToUserName><FromUserName><![CDATA[oAZcVxDhjGIIyC_iJ23zHpoGsorE]]></FromUserName>"
            + "<CreateTime>1498547497</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[테스트]]></Content><MsgId>6436212491545076337</MsgId></xml>";
        WechatMessage message = processor.toBean( xml, WechatMessage.class );
        System.out.println( "message: " + message );
    }
}
