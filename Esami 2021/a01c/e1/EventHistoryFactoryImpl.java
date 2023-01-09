package a01c.e1;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.Supplier;

public class EventHistoryFactoryImpl implements EventHistoryFactory{

    @Override
    public <E> EventHistory<E> fromMap(Map<Double, E> map) {
        return new EventHistory<E>() {
            Iterator<Entry<Double, E>> it = new TreeMap(map).entrySet().iterator();
            Entry<Double, E> current = it.next();

            @Override
            public double getTimeOfEvent() {
                return current.getKey();
            }

            @Override
            public E getEventContent() {
                return current.getValue();
            }

            @Override
            public boolean moveToNextEvent() {
                try {
                    current = it.next();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
            
        };
    }

    @Override
    public <E> EventHistory<E> fromIterators(Iterator<Double> times, Iterator<E> content) {
        return new EventHistory<E>() {
            
            Pair<Double, E> current = new Pair(times.next(), content.next());

            @Override
            public double getTimeOfEvent() {
                return current.get1();
            }

            @Override
            public E getEventContent() {
                return current.get2();
            }

            @Override
            public boolean moveToNextEvent() {
                if(times.hasNext() && content.hasNext()){
                    current = new Pair(times.next(), content.next());
                    return true;
                }
                return false;
            }
            
        };
    }

    @Override
    public <E> EventHistory<E> fromListAndDelta(List<E> content, double initial, double delta) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> EventHistory<E> fromRandomTimesAndSupplier(Supplier<E> content, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EventHistory<String> fromFile(String file) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
