/*
 * Clase para leer y convertir xml a objetos
 */
package ec.fin.be.tienda.utils;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

/**
 *
 * @author
 */
public class Strings {

    public Strings() {
        //Constructor
    }

    /**
     * Converit xml a Objetos
     *
     * @param <I>
     * @param object
     * @param contentType
     * @return
     * @throws JAXBException
     */
    public static <I> String marshal(I object, String contentType) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext jbc = JAXBContext.newInstance(object.getClass());
        Marshaller m = jbc.createMarshaller();
        m.setProperty(MarshallerProperties.MEDIA_TYPE, contentType);
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(object, writer);
        return writer.toString();
    }

    /**
     *
     * @param <E>
     * @param object
     * @param cls
     * @param contentType
     * @return
     * @throws JAXBException
     */
    public static <E> E unmarshal(String object, Class<E> cls, String contentType) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(cls);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, contentType);
        StreamSource xml = new StreamSource(new StringReader(object));
        return unmarshaller.unmarshal(xml, cls).getValue();
    }

}
