package a01b.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

public class EventSequenceProducerHelpersImpl implements EventSequenceProducerHelpers{

    @Override
    public <E> EventSequenceProducer<E> fromIterator(Iterator<Pair<Double, E>> iterator) {
        return new EventSequenceProducer<E>() {
            public Pair<Double, E> getNext() throws NoSuchElementException{
                return iterator.next();//Exception already thrown by the next method
            }
        };
    }

    @Override
    public <E> List<E> window(EventSequenceProducer<E> sequence, double fromTime, double toTime) {
        List<E> l = new ArrayList<>();
        try {
            while(true){
                var next = sequence.getNext();
                if(next.get1() >= fromTime && next.get1() <= toTime){
                    l.add(next.get2());
                }
            }
                
        } catch (Exception e) {}
        return l;
    }

    @Override
    public <E> Iterable<E> asEventContentIterable(EventSequenceProducer<E> sequence) {
        return new Iterable<E>() {

            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    E nextValue = null;

                    @Override
                    public boolean hasNext() {
                        try {
                            nextValue = sequence.getNext().get2();
                            return true;
                        } catch (Exception e) {
                            return false;
                        }
                    }

                    @Override
                    public E next() {
                        if(nextValue != null){
                            E copy = nextValue;
                            nextValue = null;
                            return copy;
                        }
                        return sequence.getNext().get2();
                    }
                    
                };
            }
            
        };
    }

    @Override
    public <E> Optional<Pair<Double, E>> nextAt(EventSequenceProducer<E> sequence, double time) {
        try {
            while(true){
                var next = sequence.getNext();
                if(next.get1() >= time){
                    return Optional.of(next);
                }
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public <E> EventSequenceProducer<E> filter(EventSequenceProducer<E> sequence, Predicate<E> predicate) {
        return new EventSequenceProducer<E>() {

            @Override
            public Pair<Double, E> getNext() throws NoSuchElementException {
                Pair<Double, E> next = sequence.getNext();
                if(predicate.test(next.get2())){
                    return next;
                }
                else{
                    getNext();
                    return null;//Questo null non viene mai restituito, perchè il metodo chiama se stesso ricorsivamente finchè non trova o un next adatto al filtro, o restituisce un eccezione perchè sono finiti
                }
            }
        };
    }
    
}
