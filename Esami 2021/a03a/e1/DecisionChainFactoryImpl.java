package a03a.e1;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

public class DecisionChainFactoryImpl implements DecisionChainFactory{

    @Override
    public <A, B> DecisionChain<A, B> oneResult(B b) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                return Optional.of(b);
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                throw new NoSuchElementException();
            }
            
        };
    }

    @Override
    public <A, B> DecisionChain<A, B> simpleTwoWay(Predicate<A> predicate, B positive, B negative) {
        return twoWay(predicate, oneResult(positive), oneResult(negative));
    }

    @Override
    public <A, B> DecisionChain<A, B> enumerationLike(List<Pair<A, B>> mapList, B defaultReply) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                return Optional.ofNullable(mapList.size() == 0 ? defaultReply : mapList.get(0).get1() == a ? mapList.get(0).get2() : null);
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                return enumerationLike(mapList.subList(mapList.size() >= 2 ? 1 : mapList.size(), mapList.size()), defaultReply);
            }
            
        };
    }

    @Override
    public <A, B> DecisionChain<A, B> twoWay(Predicate<A> predicate, DecisionChain<A, B> positive, DecisionChain<A, B> negative) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                return Optional.empty();
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                return predicate.test(a) ? positive : negative;
            }
            
        };
    }

    @Override
    public <A, B> DecisionChain<A, B> switchChain(List<Pair<Predicate<A>, B>> cases, B defaultReply) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                return Optional.ofNullable(cases.size() == 0 ? defaultReply : cases.get(0).get1().test(a) ? cases.get(0).get2() : null);
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                return switchChain(cases.subList(cases.size() >= 2 ? 1 : cases.size(), cases.size()), defaultReply);
            }
            
        };
    }
    
}
