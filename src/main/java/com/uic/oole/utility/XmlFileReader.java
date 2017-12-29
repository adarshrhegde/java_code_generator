package com.uic.oole.utility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Xml File Reader Class
 */
public class XmlFileReader {
    public static ApplicationConstraints ac;

    public XmlFileReader(ApplicationConstraints _applicationConstraints){
        this.ac = _applicationConstraints;
        this.readXmlFile();
    }

 	/**
     * The read xml file method takes in the xml file as the input and sets the state for the application
     * constraints class where with the help of getters and setters all the constraints as specified in
     * description can be set and can be called where ever necessary
     */
    public void readXmlFile() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        }
        Document doc = null;
        try {
            doc = dBuilder.parse(new File("constraints.xml"));
            doc.getDocumentElement().normalize();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
        NodeList nList = doc.getElementsByTagName("ProgGen");
        try{
            for(int i = 0; i <= nList.getLength(); i++){
                Element element = (Element) nList.item(i);
                if(element != null){
                    getConstraints(element);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * The get constraints methods takes in a node that it picked up when iterating through
     * all the elements and their nodes in the xnl file
     * @param node
     * @return
     */
    public ApplicationConstraints getConstraints(Node node){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            ac.setNoOfInterfaces(Integer.parseInt(getTagValue("noOfInterfaces",element)));
            ac.setMinInheritanceDepth(Integer.parseInt(getTagValue("minInheritanceDepth",element)));
            ac.setNoOfClasses(Integer.parseInt(getTagValue("noOfClasses", element)));
            ac.setAllowArray(getTagValue("allowArray", element));
            ac.setAllowIndirectRecursion(getTagValue("allowIndirectRecursion", element));
            ac.setClassNamePrefix(getTagValue("classNamePrefix", element));
            ac.setIntMaxValue(Integer.parseInt(getTagValue("intMaxValue", element)));
            ac.setMaxAllowedMethodCalls(Integer.parseInt(getTagValue("maxAllowedMethodCalls", element)));
            ac.setMaxInheritanceDepth(Integer.parseInt(getTagValue("maxInheritanceDepth", element)));
            ac.setMaxInterfacesToImplement(Integer.parseInt(getTagValue("maxInterfacesToImplement", element)));
            ac.setMaxNestedIfs(Integer.parseInt(getTagValue("maxNestedIfs", element)));
            ac.setMaxNoOfClassFields(Integer.parseInt(getTagValue("maxNoOfClassFields", element)));
            ac.setMaxNoOfMethodsPerClass(Integer.parseInt(getTagValue("maxNoOfMethodsPerClass", element)));
            ac.setMaxNoOfMethodsPerInterface(Integer.parseInt(getTagValue("maxNoOfMethodsPerInterface", element)));
            ac.setMaxNoOfParametersPerMethod(Integer.parseInt(getTagValue("maxNoOfParametersPerMethod", element)));
            ac.setMaxRecursionDepth(Integer.parseInt(getTagValue("maxRecursionDepth", element)));
            ac.setRecursionProbability(Double.parseDouble(getTagValue("recursionProbability", element)));
            ac.setTotalLOC(Integer.parseInt(getTagValue("totalLOC", element)));
            ac.setTypeByte(getTagValue("byte", element));
            ac.setTypeChar(getTagValue("char", element));
            ac.setTypeDouble(getTagValue("double", element));
            ac.setTypeFloat(getTagValue("float", element));
            ac.setTypeInt(getTagValue("int", element));
            ac.setTypeLong(getTagValue("long", element));
            ac.setTypeObject(getTagValue("Object", element));
            ac.setTypeShort(getTagValue("short", element));
            ac.setTypeString(getTagValue("String", element));
        }
        return ac;
    }

    /**
     * The get String method takes in a name of the node that needs to be called and an element object
     * and returns the corresponding values from the nodes list.
     * @param tag
     * @param element
     * @return
     */
    public String getTagValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
