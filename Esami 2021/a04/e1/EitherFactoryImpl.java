package a04.e1;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EitherFactoryImpl implements EitherFactory{

    @Override
    public <A, B> Either<A, B> success(B b) {
        return new Either<A,B>() {

            @Override
            public boolean isFailure() {
                return false;
            }

            @Override
            public boolean isSuccess() {
                return true;
            }

            @Override
            public Optional<A> getFailure() {
                return Optional.empty();
            }

            @Override
            public Optional<B> getSuccess() {
                return Optional.of(b);
            }

            @Override
            public B orElse(B other) {
                return getSuccess().get();
            }

            @Override
            public <B1> Either<A, B1> map(Function<B, B1> function) {
                return success(function.apply(b));
            }

            @Override
            public <B1> Either<A, B1> flatMap(Function<B, Either<A, B1>> function) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public <A1> Either<A1, B> filterOrElse(Predicate<B> predicate, A1 failure) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public <C> C fold(Function<A, C> funFailure, Function<B, C> funSuccess) {
                // TODO Auto-generated method stub
                return null;
            }
            
        };
    }

    @Override
    public <A, B> Either<A, B> failure(A a) {
        return success(a);
    }

    @Override
    public <A> Either<Exception, A> of(Supplier<A> computation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <A, B, C> Either<A, List<C>> traverse(List<B> list, Function<B, Either<A, C>> function) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
