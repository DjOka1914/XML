package com.firma;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidacijaSema {

	public static boolean validirajSemu(Object object,String imeFajla) {
		File file = new File(imeFajla + ".xml");
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(object.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(object, file);
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema;
			try {
				schema = factory.newSchema(new StreamSource("src/main/resources/" + imeFajla + ".xsd"));
				Validator validator = schema.newValidator();
				try {
					validator.validate(new StreamSource(file));
					return true;
				} catch (IOException e) {
					return false;
				}

			} catch (SAXException e) {
				e.printStackTrace();
				return false;
			}
		} catch (JAXBException e) {
			return false;
		}
	}

}
