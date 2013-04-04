/**
 * 
 */
package com.trungkent.androidviewcollection.parse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.trungkent.androidviewcollection.model.ViewItem;

/**
 * @author Trung Doan
 * 
 */
public class ViewParser extends BaseDataParser {

	private List<ViewItem> mListData;
	private ViewItem mItem;

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		mListData = new ArrayList<ViewItem>();
		sb = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);

		if ("item".equalsIgnoreCase(localName)) {
			mItem = new ViewItem();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);

		if ("image1".equalsIgnoreCase(localName)) {
			mItem.imgPreview1 = sb.toString().trim();
		} else if ("image2".equalsIgnoreCase(localName)) {
			mItem.imgPreview2 = sb.toString().trim();
		} else if ("image3".equalsIgnoreCase(localName)) {
			mItem.imgPreview3 = sb.toString().trim();
		} else if ("view_name".equalsIgnoreCase(localName)) {
			mItem.viewName = sb.toString().trim();
		} else if ("author".equalsIgnoreCase(localName)) {
			mItem.author = sb.toString().trim();
		} else if ("description".equalsIgnoreCase(localName)) {
			mItem.description = sb.toString().trim();
		} else if ("link".equalsIgnoreCase(localName)) {
			mItem.link = sb.toString().trim();
		} else if ("class_name".equalsIgnoreCase(localName)) {
			mItem.className = sb.toString().trim();
		} else if ("license".equalsIgnoreCase(localName)) {
			mItem.license = sb.toString().trim();
		} else if ("os_level".equalsIgnoreCase(localName)) {
			mItem.osLevel = sb.toString().trim();
		} else if ("item".equalsIgnoreCase(localName)) {
			mListData.add(mItem);
		}

		sb.setLength(0);

	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	public List<ViewItem> getDataList() {
		return mListData;
	}
}
