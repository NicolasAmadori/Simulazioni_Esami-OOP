package a01a.e1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeFactoryImpl implements TreeFactory{

    @Override
    public <E> Tree<E> singleValue(E root) {
        return new Tree<E>() {

            @Override
            public E getRoot() {
                return root;
            }

            @Override
            public List<Tree<E>> getChildren() {
                return List.of();
            }

            @Override
            public Set<E> getLeafs() {
                return Set.of(root);
            }

            @Override
            public Set<E> getAll() {
                return Set.of(root);
            }
            
        };
    }

    @Override
    public <E> Tree<E> twoChildren(E root, Tree<E> child1, Tree<E> child2) {
        return new Tree<E>() {

            @Override
            public E getRoot() {
                return root;
            }

            @Override
            public List<Tree<E>> getChildren() {
                return List.of(child1, child2);
            }

            @Override
            public Set<E> getLeafs() {
                Set<E> sum = Set.copyOf(child1.getLeafs());
                sum.addAll(Set.copyOf(child2.getLeafs()));
                return sum;
            }

            @Override
            public Set<E> getAll() {
                Set<E> sum = new HashSet<>();
                sum.add(root);
                sum.addAll(child1.getAll());
                sum.addAll(child1.getAll());
                return sum;
            }
            
        };
    }

    @Override
    public <E> Tree<E> oneLevel(E root, List<E> children) {
        return new Tree<E>() {

            @Override
            public E getRoot() {
                return root;
            }

            @Override
            public List<Tree<E>> getChildren() {
                List<Tree<E>> list = new ArrayList<Tree<E>>();
                for (E e : children) {
                    list.add(singleValue(e));
                }
                return list;
            }

            @Override
            public Set<E> getLeafs() {
                return Set.copyOf(children);
            }

            @Override
            public Set<E> getAll() {
                Set<E> sum = new HashSet<>();
                sum.add(root);
                sum.addAll(getLeafs());
                return sum;
            }
            
        };
    }

    @Override
    public <E> Tree<E> chain(E root, List<E> list) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
