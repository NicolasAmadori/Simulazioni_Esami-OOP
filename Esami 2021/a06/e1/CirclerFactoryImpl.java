package a06.e1;

import java.util.ArrayList;
import java.util.List;

public class CirclerFactoryImpl implements CirclerFactory{

    @Override
    public <T> Circler<T> leftToRight() {
        return new Circler<T>() {

            private List<T> elem;
            private int counter = 0;

            @Override
            public void setSource(List<T> elements) {
                this.elem = elements;
                counter = 0;
            }

            @Override
            public T produceOne() {
                return elem.get(counter++ % elem.size());
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> output = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    output.add(produceOne());
                }
                return output;
            }
            
        };
    }

    @Override
    public <T> Circler<T> alternate() {
        return new Circler<T>() {

            private List<T> elem;
            private int counter = 0;
            private int delta = 1;

            @Override
            public void setSource(List<T> elements) {
                this.elem = elements;
                counter = -1;
            }

            @Override
            public T produceOne() {
                counter += delta;
                if(delta == 1 && counter == elem.size() - 1){
                    delta*=-1;
                }
                else if (delta == -1 && counter == 0){
                    delta*=-1;
                }
                return elem.get(counter);
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> output = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    output.add(produceOne());
                }
                return output;
            }
            
        };
    }

    @Override
    public <T> Circler<T> stayToLast() {
        return new Circler<T>() {

            private List<T> elem;
            private int counter = 0;

            @Override
            public void setSource(List<T> elements) {
                this.elem = elements;
                counter = 0;
            }

            @Override
            public T produceOne() {
                return elem.get(counter == elem.size() - 1 ? counter : counter++);
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> output = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    output.add(produceOne());
                }
                return output;
            }
            
        };
    }

    @Override
    public <T> Circler<T> leftToRightSkipOne() {
        return new Circler<T>() {

            private List<T> elem;
            private int counter = 0;

            @Override
            public void setSource(List<T> elements) {
                this.elem = elements;
                counter = 0;
            }

            @Override
            public T produceOne() {
                T e = elem.get(counter % elem.size());
                counter += 2;
                return e;
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> output = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    output.add(produceOne());
                }
                return output;
            }
            
        };
    }

    @Override
    public <T> Circler<T> alternateSkipOne() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> Circler<T> stayToLastSkipOne() {
        return new Circler<T>() {

            private List<T> elem;
            private int counter = 0;

            @Override
            public void setSource(List<T> elements) {
                this.elem = elements;
                counter = 0;
            }

            @Override
            public T produceOne() {
                T e = elem.get(counter);
                counter = counter == elem.size() - 1 ? counter : counter + 2;
                counter = counter > elem.size() - 1 ? elem.size() - 1 : counter;
                return e;
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> output = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    output.add(produceOne());
                }
                return output;
            }
            
        };
    }
    
}
