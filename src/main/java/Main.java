import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
//        List<Employee> list = parseXML("src/main/resources/data.xml");
        parseXML("src/main/resources/data.xml");
    }


    public static void parseXML(String fileWay) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileWay));

        Node root = document.getDocumentElement();
        System.out.println("Корневой узел - " + root.getNodeName());
        read(root);
    }

    private static void read(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                if (node_.getNodeName().equals("id"))
                    System.out.println(node_.getNodeName());
            }
//             List<String> staff = new ArrayList<>();
//             staff.add(node_.getTextContent());
//                System.out.println(staff);


            read(node_);
        }
    }
}

