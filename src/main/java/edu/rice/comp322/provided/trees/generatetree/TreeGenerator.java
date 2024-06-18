package edu.rice.comp322.provided.trees.generatetree;

import edu.rice.comp322.provided.trees.*;

import static edu.rice.comp322.provided.trees.Tree.makeNode;

public class TreeGenerator {

    /**
     * Generate a relatively balanced test int tree.
     */
    public static Tree<Integer> generateIntTreeOne() {
        var n1 = makeNode(5, GList.empty());
        var n2 = makeNode(17, GList.empty());
        var n3 = makeNode(23, GList.empty());
        var t1 = makeNode(2, GList.of(n1, n2, n3));
        var t2 = makeNode(4, GList.of(t1, n1, n2));
        var t3 = makeNode(13, GList.of(t1, t2, n3));
        return t3;
    }

    /**
     * Generate a int tree of a single element.
     */
    public static Tree<Integer> generateIntTreeTwo() {
        return makeNode(2, GList.empty());
    }

    /**
     * Generate a deep int tree.
     */
    public static Tree<Integer> generateIntTreeThree() {
        var n1 = makeNode(5, GList.empty());
        var n2 = makeNode(17, GList.empty());
        var n3 = makeNode(23, GList.empty());
        var n4 = makeNode(15, GList.empty());
        var t1 = makeNode(4, GList.of(n1));
        var t2 = makeNode(2, GList.of(t1, n2));
        var t3 = makeNode(9, GList.of(t2, t1, n3));
        var t4 = makeNode(0, GList.of(t3, t2, t1, n4));
        return t4;
    }
}
