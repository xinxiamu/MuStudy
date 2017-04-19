package co.jufeng.util;

import java.util.*;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import co.jufeng.json.util.StringUtil;

@SuppressWarnings("unchecked")
public class XmlUtil {
	private XmlUtil() {
	}
	public static String map2xml(Map<String, Object> vo, String rootElement) {
		org.dom4j.Document doc = DocumentHelper.createDocument();
		Element body = DocumentHelper.createElement(rootElement);
		doc.add(body);
		buildMap2xmlBody(body, vo);
		return doc.asXML();
	}

	private static void buildMap2xmlBody(Element body, Map<String, Object> vo) {
		if (vo != null) {
			Iterator<String> it = vo.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if (!StringUtil.isEmpty(key)) {
					Object obj = vo.get(key);
					Element element = DocumentHelper.createElement(key);
					if (obj != null) {
						if (obj instanceof java.lang.String) {
							element.setText((String) obj);
						} else {
							if (obj instanceof java.lang.Character
									|| obj instanceof java.lang.Boolean
									|| obj instanceof java.lang.Number
									|| obj instanceof java.math.BigInteger
									|| obj instanceof java.math.BigDecimal) {
								org.dom4j.Attribute attr = DocumentHelper
										.createAttribute(element, "type", obj
												.getClass().getCanonicalName());
								element.add(attr);
								element.setText(String.valueOf(obj));
							} else if (obj instanceof java.util.Map) {
								org.dom4j.Attribute attr = DocumentHelper
										.createAttribute(element, "type", null);
								element.add(attr);
								buildMap2xmlBody(element,
										(Map<String, Object>) obj);
							} else {
							}
						}
					}
					body.add(element);
				}
			}
		}
	}
   
	//Document doc = DocumentHelper.parseText(xml);
	public static Object xml2map(Element element) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Element> elements = element.elements();
		if (elements.size() == 0) {
			map.put(element.getName(), element.getText());
			if (!element.isRootElement()) {
				return element.getText();
			}
		} else if (elements.size() == 1) {
			map.put(elements.get(0).getName(), xml2map(elements.get(0)));
		} else if (elements.size() > 1) {
			// 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
			// 构造一个map用来去重
			Map<String, Element> tempMap = new HashMap<String, Element>();
			for (Element ele : elements) {
				tempMap.put(ele.getName(), ele);
			}
			Set<String> keySet = tempMap.keySet();
			for (String string : keySet) {
				Namespace namespace = tempMap.get(string).getNamespace();
				List<Element> elements2 = element.elements(new QName(string, namespace));
				// 如果同名的数目大于1则表示要构建list
				if (elements2.size() > 1) {
					List<Object> list = new ArrayList<Object>();
					for (Element ele : elements2) {
						list.add(xml2map(ele));
					}
					map.put(string, list);
				} else {
					// 同名的数量不大于1则直接递归去
					map.put(string, xml2map(elements2.get(0)));
				}
			}
		}

		return map;
	}

}