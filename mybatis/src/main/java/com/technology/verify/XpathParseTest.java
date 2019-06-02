package com.technology.verify;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.parsing.XPathParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

public class XpathParseTest {
    public static void main(String[] args) throws Exception {
//        xpathParse();
        mybatisXpathParse();
    }

    private static void mybatisXpathParse() {
        XPathParser pathParser;
    }

    private static void xpathParse() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        // 通过实例化DocumentBuilderFactory对象,获取DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);//开启验证
        factory.setNamespaceAware(false);
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(false);
        factory.setCoalescing(false);
        factory.setExpandEntityReferences(true);
        DocumentBuilder bulider = factory.newDocumentBuilder();
        bulider.setErrorHandler(new ErrorHandler() {
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println("warning:" + exception.getMessage());
            }

            public void error(SAXParseException exception) throws SAXException {
                System.out.println("error:" + exception.getMessage());
            }

            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println("fatalError:" + exception.getMessage());
            }
        }); // 异常处理对象
        // 2.将文档加载
        Document doc = bulider.parse(Resources.getResourceAsStream("xml/inventory.xml"));
        // 3.创建XpathFactory获取Xpath对象
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        // 4.编译Xpath表达式
        XPathExpression expression = xPath.compile("//book[title='mybatis']/isbn/text()");
        // 5.指定查询的上下文节点及表达式类型 执行获取结果
        Object result = expression.evaluate(doc, XPathConstants.NODESET);
        System.out.println(result);
        System.out.println(result.getClass().getName());
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            System.out.println(item.getNodeName() + ":" + item.getNodeValue());
        }
//        nodes = (NodeList)xPath.evaluate("",doc,XPathConstants.NODESET);  xpath只使用一次

    }
}
