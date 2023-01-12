package a02c.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListSplitterFactoryImpl implements ListSplitterFactory{
    
    private <X>List<List<X>> genericSplit(List<X> list, int nElem, boolean saveRest) {
        List<List<X>> output = new ArrayList<>();
        List<X> sub = new ArrayList<>();
        for (X el : list) {
            if(sub.size() < nElem){
                sub.add(el);
            }
            else{
                output.add(List.copyOf(sub));
                sub.clear();
                sub.add(el);
            }
        }
        if(sub.size() == nElem || (sub.size() > 0 && saveRest)){
            output.add(sub);
        }
        return output;
    }

    @Override
    public <X> ListSplitter<X> asPairs() {
        return new ListSplitter<X>() {

            @Override
            public List<List<X>> split(List<X> list) {
                return genericSplit(list, 2, false);
            }
            
        };
    }

    @Override
    public <X> ListSplitter<X> asTriplets() {
        return new ListSplitter<X>() {

            @Override
            public List<List<X>> split(List<X> list) {
                return genericSplit(list, 3, false);
            }
        };
    }

    @Override
    public <X> ListSplitter<X> asTripletsWithRest() {
        return new ListSplitter<X>() {

            @Override
            public List<List<X>> split(List<X> list) {
                return genericSplit(list, 3, true);
            }
            
        };
    }

    @Override
    public <X> ListSplitter<X> bySeparator(X separator) {
        return new ListSplitter<X>() {

            @Override
            public List<List<X>> split(List<X> list) {
                List<List<X>> output = new ArrayList<>();
                List<X> sub = new ArrayList<>();
                for (X el : list) {
                    if(el.equals(separator)){
                        output.add(List.copyOf(sub));
                        output.add(List.of(el));
                        sub.clear();
                    }
                    else{
                        sub.add(el);
                    }
                }
                if(sub.size() > 0){
                    output.add(sub);
                }
                return output;
            }
        };
    }

    @Override
    public <X> ListSplitter<X> byPredicate(Predicate<X> predicate) {
        return new ListSplitter<X>() {

            @Override
            public List<List<X>> split(List<X> list) {
                List<List<X>> output = new ArrayList<>();
                List<X> sub = new ArrayList<>();
                boolean last = true;

                for (X el : list) {
                    if(predicate.test(el) != last){
                        last = predicate.test(el);
                        if(sub.size() != 0){
                            output.add(List.copyOf(sub));
                            sub.clear();
                            sub.add(el);
                        }
                    }
                    else{
                        sub.add(el);
                    }
                }
                if(sub.size() > 0){
                    output.add(sub);
                }
                return output;
            }
            
        };
    }
    
}
