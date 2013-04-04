/**
 * 
 */
package com.trungkent.androidviewcollection.parse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.trungkent.androidviewcollection.model.ViewItem;

/**
 * @author Trung Doan
 * 
 */
public class DataParser {

	public static DataParser mDataParser;

	public static DataParser getInstance() {
		if (mDataParser == null) {
			mDataParser = new DataParser();
		}
		return mDataParser;
	}

	/**
	 * Gets the input stream.
	 * 
	 * @param data
	 *            the data
	 * @return the input stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	protected InputSource getInputStream(String data) throws IOException,
			UnsupportedEncodingException {
		InputStream is = null;

		try {
			is = new ByteArrayInputStream(data.getBytes("UTF-8"));

		} catch (UnsupportedEncodingException ue) {
			ue.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return new InputSource(is);
	}

	/**
	 * Gets the xML reader.
	 * 
	 * @return the xML reader
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the sAX exception
	 */
	protected XMLReader getXMLReader() throws ParserConfigurationException,
			SAXException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		return xr;
	}

	/**
	 * @param dataXml
	 * @param handler
	 * @throws DoximityException
	 */
	protected void parser(InputStream dataXml, DefaultHandler handler) {
		try {
			XMLReader xr = getXMLReader();
			InputSource is = new InputSource(dataXml);
			xr.setContentHandler(handler);
			xr.parse(is);
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		} catch (SAXException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public List<ViewItem> getDataList(InputStream dataXml) {
		ViewParser handler = new ViewParser();
		parser(dataXml, handler);

		return handler.getDataList();
	}
}
