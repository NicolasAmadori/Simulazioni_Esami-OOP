package a03c.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeterministicParserFactoryImpl implements DeterministicParserFactory{

    @Override
    public DeterministicParser oneSymbol(String s) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                List<String> mutableList = new ArrayList<>(tokens);
                return Optional.ofNullable(mutableList.remove(s) ? mutableList : null);
            }
            
        };
    }

    @Override
    public DeterministicParser twoSymbols(String s1, String s2) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                return oneSymbol(s1).accepts(oneSymbol(s2).accepts(tokens).orElse(List.of()));
            }
            
        };
    }

    @Override
    public DeterministicParser possiblyEmptyIncreasingSequenceOfPositiveNumbers() {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                for(int n = 10; oneSymbol(Integer.toString(n)).accepts(tokens).isPresent(); n+=10){
                    tokens = oneSymbol(Integer.toString(n)).accepts(tokens).get();
                }
                return Optional.ofNullable(tokens.size() != 0 ? tokens : null);
            }
            
        };
    }

    @Override
    public DeterministicParser sequenceOfParsersWithDelimiter(String start, String stop, String delimiter, DeterministicParser element) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                int startIndex = tokens.indexOf(start), stopIndex = tokens.indexOf(stop);
                if(startIndex < 0 || stopIndex < 0 || startIndex < stopIndex){
                    return Optional.empty();
                }
                tokens = new ArrayList<>(tokens);
                List<String> before = tokens.subList(0, startIndex), 
                            after = tokens.subList(stopIndex + 1, tokens.size()),
                            center = tokens.subList(startIndex + 1, stopIndex);
                System.out.println(before);
                System.out.println(center);
                System.out.println(after);
                while(center.size() >= 2 || sequence(element, oneSymbol(delimiter)).accepts(center).isPresent()){
                    if(sequence(element, oneSymbol(delimiter)).accepts(center).isEmpty()){
                        return Optional.empty();
                    }
                    center = sequence(element, oneSymbol(delimiter)).accepts(center).get();
                }
                before.addAll(after);
                return Optional.ofNullable(center.size() != 0 ? null : before);
            }
            
        };
    }

    @Override
    public DeterministicParser sequence(DeterministicParser first, DeterministicParser second) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                return second.accepts(first.accepts(tokens).orElse(null));
            }
            
        };
    }
    
}
