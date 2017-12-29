package com.uic.oole.utility;

import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@PrepareForTest(XmlFileReader.class)
public class XmlFileReaderTest {
    /**
     * Mocks the XmlFileReader Class
     */
    XmlFileReader xmlFileReader = mock(XmlFileReader.class);
    ApplicationConstraints applicationConstraints = mock(ApplicationConstraints.class);

    /**
     * Testing the xml file reader method which reads all the elements and nodes of
     * a xml file
     */
    @Test
    public void testGetConstraints(){
        Node node = mock(Node.class);
        when(xmlFileReader.getConstraints(node)).thenReturn(applicationConstraints);
    }

    /**
     * Testing the get tag values method
     */
    @Test
    public void testGetTagValues(){
        Element element = mock(Element.class);
        when(xmlFileReader.getTagValue("String", element)).thenReturn("String");
    }
}