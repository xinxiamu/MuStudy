package org.chiwooplatform.simple.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.chiwooplatform.oxm.support.CDataAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WechatMessage {

    @XmlElement(name = "ToUserName")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String ToUserName;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String FromUserName;

    @XmlElement
    private String CreateTime;

    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String MsgType;

    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String Content;

    @XmlElement
    private String MsgId;

    @XmlElement(required = false)
    private String MsgAdded;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "WechatMessage [ToUserName=" );
        builder.append( ToUserName );
        builder.append( ", FromUserName=" );
        builder.append( FromUserName );
        builder.append( ", CreateTime=" );
        builder.append( CreateTime );
        builder.append( ", MsgType=" );
        builder.append( MsgType );
        builder.append( ", Content=" );
        builder.append( Content );
        builder.append( ", MsgId=" );
        builder.append( MsgId );
        builder.append( "]" );
        return builder.toString();
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName( String toUserName ) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName( String fromUserName ) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime( String createTime ) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType( String msgType ) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent( String content ) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId( String msgId ) {
        MsgId = msgId;
    }

    public String getMsgAdded() {
        return MsgAdded;
    }

    public void setMsgAdded( String msgAdded ) {
        MsgAdded = msgAdded;
    }
}
