package jaxb.binder;

import java.io.File;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * The Class JAXBBinderExample.
 * 
 * @author Subash Janarthanan
 */
public class JAXBBinderExample {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			
			// Create a new instance of document builder factory 
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
			File file = new File("Employee.xml");
			Document document = docBuilder.parse(file);

			// create JAXBContext
			JAXBContext jaxBContext = JAXBContext.newInstance(Employee.class);

			// Create a binder from the JAXBContext
			Binder<Node> jaxbBinder = jaxBContext.createBinder();
			jaxbBinder.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			Node xmlNode = document.getDocumentElement();

			// Return the updated JAXB object(Employee)
			Employee employee = (Employee) jaxbBinder.updateJAXB(xmlNode);

			// Set the updated information to the Employee object
			employee.setName("hari");
			employee.setId("158");
			employee.setBusinessUnit("Testing");

			// update xml node with new data
			xmlNode = jaxbBinder.updateXML(employee);

			// get the node value and set it to the document
			String nodeValue = xmlNode.getNodeValue();
			document.setNodeValue(nodeValue);

			// Create a TransformerFactory instance and set the output property for formatted output
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			
			// Increase the indent amount based on your requirement
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            DOMSource source = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(source, streamResult);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
