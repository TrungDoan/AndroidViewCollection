/**
 * 
 */
package com.trungkent.androidviewcollection.parse;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Trung Doan
 * 
 */
public abstract class BaseDataParser extends DefaultHandler {
	protected StringBuilder sb;

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		sb.append(ch, start, length);
	}
	
}
