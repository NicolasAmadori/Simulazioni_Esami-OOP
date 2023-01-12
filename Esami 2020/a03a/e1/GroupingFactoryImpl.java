package a03a.e1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class GroupingFactoryImpl implements GroupingFactory{

    @Override
    public <G, V> Grouping<G, V> fromPairs(Iterable<Pair<G, V>> values) {
        return new Grouping<G,V>() {

            @Override
            public Set<V> getValuesOfGroup(G group) {
                Set<V> output = new HashSet<>();
                for (Pair<G,V> pair : values) {
                    if(pair.getX().equals(group)){
                        output.add(pair.getY());
                    }
                }
                return output;
            }

            @Override
            public Set<G> getGroups() {
                Set<G> output = new HashSet<>();
                for (Pair<G,V> pair : values) {
                    if(!output.contains(pair.getX())){
                        output.add(pair.getX());
                    }
                }
                return output;
            }

            @Override
            public Optional<G> getGroupOf(V data) {
                for (Pair<G,V> pair : values) {
                    if(pair.getY().equals(data)){
                        return Optional.of(pair.getX());
                    }
                }
                return Optional.empty();
            }

            @Override
            public Map<G, Set<V>> asMap() {
                Map<G, Set<V>> output = new HashMap<>();
                for (Pair<G,V> pair : values) {
                    if(output.containsKey(pair.getX())){
                        output.get(pair.getX()).add(pair.getY());
                    }
                    else{
                        Set<V> newSet = new HashSet<>();
                        newSet.add(pair.getY());
                        output.put(pair.getX(), newSet);
                    }
                }
                return output;
            }

            @Override
            public Grouping<G, V> combineGroups(G initial1, G initial2, G result) {
                // TODO Auto-generated method stub
                return null;
            }
            
        };
    }

    @Override
    public <V> Grouping<V, V> singletons(Set<V> values) {
        return withChampion(values, (i,j) -> i == j, i-> true);
    }

    @Override
    public <V> Grouping<V, V> withChampion(Set<V> values, BiPredicate<V, V> sameGroup, Predicate<V> champion) {
        return new Grouping<V,V>() {

            @Override
            public Set<V> getValuesOfGroup(V group) {
                Set<V> output = new HashSet<>();
                for (V el : values) {
                    if(sameGroup.test(group, el)){
                        output.add(el);
                    }
                }
                return output;
            }

            @Override
            public Set<V> getGroups() {
                Set<V> output = new HashSet<>();
                for (V el : values) {
                    if(champion.test(el) && (output.isEmpty() || !output.contains(el))){
                        output.add(el);
                    }
                }
                return output;
            }

            @Override
            public Optional<V> getGroupOf(V data) {
                if(values.contains(data)){
                    for (V v : values) {
                        if(champion.test(v) && sameGroup.test(data, v)){
                            return Optional.of(v);
                        }
                    }
                }

                return Optional.empty();
            }

            @Override
            public Map<V, Set<V>> asMap() {
                Map<V, Set<V>> output = new HashMap<>();
                for (V group : getGroups()) {
                    output.put(group, getValuesOfGroup(group));
                }
                return output;
            }

            @Override
            public Grouping<V, V> combineGroups(V initial1, V initial2, V result) {
                // TODO Auto-generated method stub
                return null;
            }
            
        };
    }

    @Override
    public <G, V> Grouping<G, V> fromFunction(Set<V> values, Function<V, G> mapper) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
