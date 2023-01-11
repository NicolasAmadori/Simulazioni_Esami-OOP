package a05.e1;

import java.util.Iterator;
import java.util.function.Function;

public class StateFactoryImpl implements StateFactory{

    @Override
    public <S, A> State<S, A> fromFunction(Function<S, Pair<S, A>> fun) {
        return new State<S,A>() {

            @Override
            public S nextState(S s) {
                return fun.apply(s).get1();
            }

            @Override
            public A value(S s) {
                return fun.apply(s).get2();
            }

            @Override
            public <B> State<S, B> map(Function<A, B> function) {
                return fromFunction(s -> new Pair<>(nextState(s), function.apply(value(s))));
            }

            @Override
            public Iterator<A> iterator(S s0) {
                return new Iterator<A>() {

                    @Override
                    public boolean hasNext() {
                        return true;
                    }

                    @Override
                    public A next() {
                        return value(s0);
                    }
                    
                };
            }
            
        };
    }

    @Override
    public <S, A, B> State<S, B> compose(State<S, A> state1, State<S, B> state2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public State<Integer, String> incSquareHalve() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public State<Integer, Integer> counterOp(CounterOp op) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
