package jaxb;

import java.io.File;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * The Class JaxbBinderSample.
 * 
 * @author Subash
 */
public class JaxbBinderSample {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        try
        {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilderFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            File file = new File("result.xml");
            Document doc = docBuilder.parse(file);
            
            // Retrieve the document element from the Document
            Element documentElement = doc.getDocumentElement();

            // Create JAXB instance
            JAXBContext jaxbContext = JAXBContext.newInstance(Country.class);
            Binder<Node> binder = jaxbContext.createBinder();
            
            // Unmarshall and get the object
            Country country = (Country)binder.unmarshal(documentElement);
            ZipCode zipCode = country.getCode();
            
            // Update specific portion of the code in object
            zipCode.setPart1(1111);

            binder.updateXML(zipCode);

            // Create TransformerFactory instance
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			
			// Increase the indent amount based on your requirement
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
            DOMSource source = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(source, streamResult);
        }
        catch(Exception excep)
        {
            excep.printStackTrace();
        }
    }
}
