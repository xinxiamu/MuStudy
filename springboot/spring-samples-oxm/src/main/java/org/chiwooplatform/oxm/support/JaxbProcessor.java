package org.chiwooplatform.oxm.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class JaxbProcessor {

    private final Jaxb2Marshaller marshaller;

    public JaxbProcessor( Jaxb2Marshaller marshaller ) {
        super();
        this.marshaller = marshaller;
    }

    // Converts Object to XML file
    public String toXml( Object bean )
        throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            StreamResult result = new StreamResult( out );
            marshaller.marshal( bean, result );
            String xml = result.getOutputStream().toString();
            return xml.replace( "&lt;![CDATA[", "<![CDATA[" ).replace( "]]&gt;", "]]>" );
        } finally {
            out.close();
        }
    }

    // Converts XML to Java Object
    @SuppressWarnings("unchecked")
    public <T> T toBean( String xml, Class<T> clazz )
        throws IOException {
        StringReader reader = new StringReader( xml );
        try {
            StreamSource stream = new StreamSource( reader );
            return (T) this.marshaller.unmarshal( stream );
        } finally {
            reader.close();
        }
    }
}