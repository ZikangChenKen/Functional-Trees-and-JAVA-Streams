package edu.rice.comp322.solutions;

import edu.rice.comp322.provided.trees.GList;
import edu.rice.comp322.provided.trees.Tree;

public class TreeSolutions {

    // @TODO Implement tree sum
    /**
     * Recursive sum function to calculate the sum of the values in all of the nodes in a Tree<Integer>.
     * @param tree the tree object representing the input tree
     * @return the integer sum of the values in all nodes
     */
    public static Integer problemOne(Tree<Integer> tree) {
        // if the tree has no children, simply return the value
        if (tree.children().isEmpty()){
            return tree.value();
        }
        else{
            // build another tree with the head node of the children being the new root
            Integer rootVal = tree.children().head().value();
            // adding the head's children to the existing children
            Tree.Node<Integer> root = new Tree.Node<>(rootVal, tree.children().tail().appendAll(
                    tree.children().head().children()));
            return tree.value() + problemOne(root);
        }
    }

    // @TODO Implement tree sum using higher order list functions

    /**
     * Calculate the same sum using the higher order GList functions map, fold, and filter.
     * @param tree the tree object representing the input tree
     * @return the integer sum of the values in all nodes
     */
    public static Integer problemTwo(Tree<Integer> tree) {
        // add children's sum to the root node
        return tree.value() + tree.children().map(x -> problemTwo(x)).fold(0, Integer::sum);
    }

    /*
     * Problem 3's solution should be written in the Tree.java class at line 118.
     */

    // @TODO Calculate the sum of the elements of the tree using tree fold

    /**
     * Use the implementation of fold above to calculate the sum of the tree’s nodes.
     * @param tree the tree object representing the input tree
     * @return the integer sum of the values in all nodes
     */
    public static Integer problemFour(Tree<Integer> tree) {
        // directly using tree fold
        return tree.fold(0, Integer::sum);
    }

}
