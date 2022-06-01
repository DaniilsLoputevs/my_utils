package smallthings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
class TreeVisitor<N> {
    private final N rootNode;
    
    public static void main(String[] args) {
        
        val root = new Node<>(0, "root", "root-val", 0, List.of(
                new Node<>(1, "L1-1", "L1-value", 1, List.of(
                        new Node<>(2, "L1-1_L2-1", "L2-value", 2, null)
                )),
                new Node<>(3, "L1-2", "L1-value", 1, List.of(
                        new Node<>(4, "L1-1_L2-2", "L2-value", 2, null)
                ))
        ));
        
        Function<Node<String>, List<Node<String>>> getter = Node::getChildren;
//        Function<Node, List<Node>> getter = it -> it.getChildren();
//        new TreeVisitor<>().visitWidthValue((VisitableNode) root, System.out::println);
//        new TreeVisitor<Node>(root).visitWidth(root, it -> ((Node<String>) it).getValue(), Node::getChildren, System.out::println, Node::getValue);
//        new TreeVisitor<>(root).visitWidth(root, Node::getValue, Node::getChildren, System.out::println, Node::getValue);
    
    }
    
    
    public <V> void visitWidth(N rootNode,
                               Function<N, V> valueGetter,
                               Function<N, List<N>> childrenGetter,
                               Consumer<V> onVisit
    ) {
        if (rootNode == null) return;
        
        val nodeList = new LinkedList<N>();
        nodeList.add(rootNode);
        
        while (!nodeList.isEmpty()) {
            val currNode = nodeList.removeFirst();
            val currNodeChildren = childrenGetter.apply(currNode);
            onVisit.accept(valueGetter.apply(currNode));
            if (currNodeChildren == null) continue;
            nodeList.addAll((currNodeChildren instanceof Collection)
                    ? (Collection<? extends N>) currNodeChildren
                    : StreamSupport.stream(currNodeChildren.spliterator(), false).collect(Collectors.toList()));
//            nodeList.addAll(this.iterableToCollection(currNodeChildren));
        }
    }
//    private <V> Collection<? extends V> iterableToCollection(Iterable<V> iterable) {
//        return (currNodeChildren instanceof Collection)
//                ? currNodeChildren
//                : StreamSupport.stream(currNodeChildren.spliterator(), false).collect(Collectors.toList())
//    }

//    public void visitWidthValue(VisitableNode<T> rootNode, Consumer<T> onVisit) {
//        if (rootNode == null) return;
//
//        val nodeList = new LinkedList<VisitableNode<T>>();
//        nodeList.add(rootNode);
//
//        while (!nodeList.isEmpty()) {
//            val currNode = nodeList.removeFirst();
//            val currNodeChildren = currNode.getChildren();
//            onVisit.accept(currNode.getValue());
//            if (currNodeChildren == null) continue;
//            nodeList.addAll((currNodeChildren instanceof Collection)
//                    ? (Collection<VisitableNode<T>>) currNodeChildren
//                    : StreamSupport.stream(currNodeChildren.spliterator(), false).collect(Collectors.toList()));
//        }
//    }
}

interface VisitableNode<T> {
    T getValue();
    
    Iterable<VisitableNode<T>> getChildren();
}

@AllArgsConstructor
class Node<E>  {
    private long id;
    private String name;
    @Getter private E value;
    private int level;
    @Getter private List<Node<E>> children;
    
//    @Override public String getValue() {
//        return this.name;
//    }
//
//    @Override public Iterable<VisitableNode<String>> getChildren() {
//        return children;
//    }
}
//@AllArgsConstructor
//class Node<E> implements VisitableNode<E> {
//    private long id;
//    private String name;
//    private E value;
//    private int level;
//    private List<Node<E>> children;
//
//    @Override public E getValue() {
//        return this.value;
//    }
//
//    @Override public Iterable<VisitableNode<E>> getChildren() {
//        return (Iterable<VisitableNode<E>>) children;
//    }
//}


//class Node<E> implements VisitableNode<VisitableNode<E>> {
//    private long id;
//    private String name;
//    private int level;
//    private List<Node<E>> children;
//
//    @Override public VisitableNode<E> getValue() {
//        return (VisitableNode<E>) this;
//    }
//
//    @Override public Iterable<VisitableNode<VisitableNode<E>>> getChildren() {
//        return null;
//    }
//}
