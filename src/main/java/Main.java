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
//        parseXML("src/main/resources/data.xml");
        for (Employee employee : list) {
            System.out.println(employee);
        }
//        System.out.println(list.size());
//        for (Employee employee : list) {
//            System.out.println(employee);
//        }
    }


    public static List<Employee> parseXML(String fileWay) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileWay));

        Node root = document.getDocumentElement();
        System.out.println("Корневой узел - " + root.getNodeName());
        List<Employee> employeesList = new ArrayList<>();
        //Просматриваем все элементы корневого - т.е. сотрудников
        NodeList employees = root.getChildNodes();
        for (int i = 0; i < employees.getLength(); i++) {
            Node employee = employees.item(i);
            //Если нода не текст, то это книга - заходим внутрь
            if (employee.getNodeType() != Node.TEXT_NODE) {
                NodeList employeeStaffs = employee.getChildNodes();
                Employee employee1 = new Employee();
                for (int j = 0; j < employeeStaffs.getLength(); j++) {
                    Node employeeStaff = employeeStaffs.item(j);
                    //Если нода не текст, то это один из параметров
//                    if(employeeStaff.getNodeType()!=Node.TEXT_NODE) {
//                        System.out.println(employeeStaff.getNodeName() + ":" + employeeStaff.getChildNodes().item(0).getTextContent());
//                    }

                    if (employeeStaff.getNodeType() != Node.TEXT_NODE) {
                        if (employeeStaff.getNodeName().equals("id"))
                            employee1.setId(Long.parseLong(employeeStaff.getChildNodes().item(0).getTextContent()));
                        if (employeeStaff.getNodeName().equals("firstName"))
                            employee1.setFirstName((employeeStaff.getChildNodes().item(0).getTextContent()));
                        if (employeeStaff.getNodeName().equals("lastName"))
                            employee1.setLastName((employeeStaff.getChildNodes().item(0).getTextContent()));
                        if (employeeStaff.getNodeName().equals("country"))
                            employee1.setCountry((employeeStaff.getChildNodes().item(0).getTextContent()));
                        if (employeeStaff.getNodeName().equals("age"))
                            employee1.setAge(Integer.parseInt(employeeStaff.getChildNodes().item(0).getTextContent()));

                    }
                }
//                System.out.println("=========>>>>>>>>>");
                employeesList.add(employee1);
//                System.out.println(employeesList);
            }
        }
        return employeesList;
    }
}



