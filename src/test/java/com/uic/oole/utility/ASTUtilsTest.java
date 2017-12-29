package com.uic.oole.utility;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Method;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import java.util.List;
import static org.mockito.Mockito.mock;

/**
 * ASTUtil Test Class
 */
@PrepareForTest(ASTUtils.class)
public class ASTUtilsTest {

    Node node = mock(Node.class);

    /**
     * Testing getParentNode which returns an Enum
     * and it asserts to an Enum based on a condition
     */
    @Test
    public void TestGetParentNodeType(){
        ASTUtils.NodeType  nodeType = ASTUtils.getParentNodeType(node);
        if(nodeType == ASTUtils.NodeType.method){
            Assert.assertEquals(nodeType, ASTUtils.NodeType.method);
        }else if(nodeType == ASTUtils.NodeType.classOrInterface){
            Assert.assertEquals(nodeType, ASTUtils.NodeType.classOrInterface);
        }else{
            Assert.assertEquals(nodeType, ASTUtils.NodeType.unknown);
        }
    }

    @Test
    public void TestCheckInitializer(){
        CClass cClass = mock(CClass.class);
        Method method = mock(Method.class);
        Expression expression = mock(Expression.class);
        Pair<Boolean, String> getPair = ASTUtils.checkInitializer(cClass, method, expression);
        Assert.assertFalse(getPair.toString(), false);
    }

    @Test
    public void TestGetAllNodesOfType(){
        Node node = mock(Node.class);
        List<Node> str = ASTUtils.getAllChildNodesOfType(node, String.class);
        Assert.assertFalse(str.toString(), false);
    }
}