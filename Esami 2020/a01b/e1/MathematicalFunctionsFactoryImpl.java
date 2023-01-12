package a01b.e1;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class MathematicalFunctionsFactoryImpl implements MathematicalFunctionsFactory{

    @Override
    public <A, B> MathematicalFunction<A, B> constant(Predicate<A> domainPredicate, B value) {
        return fromFunction(domainPredicate, i -> value);
    }

    @Override
    public <A, B> MathematicalFunction<A, A> identity(Predicate<A> domainPredicate) {
        return fromFunction(domainPredicate, i -> i);
    }

    @Override
    public <A, B> MathematicalFunction<A, B> fromFunction(Predicate<A> domainPredicate, Function<A, B> function) {
        return new MathematicalFunction<A,B>() {

            @Override
            public Optional<B> apply(A a) {
                return Optional.ofNullable(inDomain(a) ? function.apply(a) : null);
            }

            @Override
            public boolean inDomain(A a) {
                return domainPredicate.test(a);
            }

            @Override
            public <C> MathematicalFunction<A, C> composeWith(MathematicalFunction<B, C> f) {
                return fromFunction(domainPredicate, el -> f.apply(this.apply(el).get()).get());
            }

            @Override
            public MathematicalFunction<A, B> withUpdatedValue(A domainValue, B codomainValue) {
                return fromFunction(val -> domainPredicate.test(val) || val.equals(domainValue), el -> el.equals(domainValue) ? codomainValue : function.apply(el));
            }

            @Override
            public MathematicalFunction<A, B> restrict(Set<A> subDomain) {
                return fromFunction(el -> subDomain.contains(el), function);
            }
            
        };
    }

    @Override
    public <A, B> MathematicalFunction<A, B> fromMap(Map<A, B> map) {
        MathematicalFunction<A, B> func = null;
        for (var entry : map.entrySet()) {
            if(func == null){
                func = constant(val -> val.equals(entry.getKey()), entry.getValue());
            }
            else{
                func = func.withUpdatedValue(entry.getKey(), entry.getValue());
            }
        }
        return func;
    }
    
}
