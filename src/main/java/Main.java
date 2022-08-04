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
        List<Employee> list = parseXML("src/main/resources/data.xml");
        System.out.println(list.size());
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }


    public static List<Employee> parseXML(String fileWay) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileWay));

        Node root = document.getDocumentElement();
        System.out.println("Корневой узел - " + root.getNodeName());
        return read(root);

    }

    private static List<Employee> read(Node node) {
        List<Employee> employees = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                Employee employee = new Employee();

                if (node_.getNodeName().equals("id")) {
                    System.out.println(node_.getNodeName() + " - " + node_.getTextContent());
                    employee.id = Long.parseLong(node_.getTextContent());
                } else if (node_.getNodeName().equals("firstName")) {
                    System.out.println(node_.getNodeName() + " - " + node_.getTextContent());
                    employee.firstName = node_.getTextContent();

                } else if (node_.getNodeName().equals("lastName")) {
                    System.out.println(node_.getNodeName() + " - " + node_.getTextContent());
                    employee.lastName = node_.getTextContent();

                } else if (node_.getNodeName().equals("country")) {
                    System.out.println(node_.getNodeName() + " - " + node_.getTextContent());
                    employee.country = node_.getTextContent();

                } else if (node_.getNodeName().equals("age")) {
                    System.out.println(node_.getNodeName() + " - " + node_.getTextContent());
                    employee.age = Integer.parseInt(node_.getTextContent());
                }
                employees.add(employee);
            }
            read(node_);
//             List<String> staff = new ArrayList<>();
//             staff.add(node_.getTextContent());
//                System.out.println(staff);
        }
        return employees;
    }

}



