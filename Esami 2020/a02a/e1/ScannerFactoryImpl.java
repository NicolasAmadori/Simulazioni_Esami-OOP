package a02a.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class ScannerFactoryImpl implements ScannerFactory{

    @Override
    public <X, Y> Scanner<X, List<Y>> collect(Predicate<X> filter, Function<X, Y> mapper) {
        return new Scanner<X, List<Y>>() {

            @Override
            public List<Y> scan(Iterator<X> input) {
                List<Y> l = new ArrayList<>();
                while(input.hasNext()){
                    X next = input.next();
                    if(filter.test(next)){
                        l.add(mapper.apply(next));
                    }
                }
                return l;
            }
            
        };
    }

    @Override
    public <X> Scanner<X, Optional<X>> findFirst(Predicate<X> filter) {
        return new Scanner<X, Optional<X>>() {

            @Override
            public Optional<X> scan(Iterator<X> input) {
                while(input.hasNext()){
                    X next = input.next();
                    if(filter.test(next)){
                        return Optional.of(next);
                    }
                }
                return Optional.empty();
            }
        };
    }

    @Override
    public Scanner<Integer, Optional<Integer>> maximalPrefix() {
        return new Scanner<Integer,Optional<Integer>>() {

            @Override
            public Optional<Integer> scan(Iterator<Integer> input) {
                if(!input.hasNext()){
                    return Optional.empty();
                }
                int max = input.next(), last = max, maxSequenceLength = 1, sequenceCounter = 1;

                while(input.hasNext()){
                    int next = input.next();
                    if(next >= last){
                        sequenceCounter++;
                        if(sequenceCounter >= maxSequenceLength && next >= max){
                            max = next;
                            maxSequenceLength = sequenceCounter;
                        }
                    }
                    else{
                        sequenceCounter = 0;
                    }
                    last = next;
                }
                if(sequenceCounter - 1 >= maxSequenceLength && last > max){
                    max = last;
                    sequenceCounter = 1;
                }
                return Optional.of(max);
            }
            
        };
    }

    @Override
    public Scanner<Integer, List<Integer>> cumulativeSum() {
        return new Scanner<Integer,List<Integer>>() {
            
            @Override
            public List<Integer> scan(Iterator<Integer> input) {
                List<Integer> output = new ArrayList<>();
                int sum = 0;
                while(input.hasNext()){
                    sum += input.next();
                    output.add(sum);
                }

                return output;
            }
            
        };
    }
    
}
