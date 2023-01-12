package a02b.e1;

import java.util.ArrayList;
import java.util.List;

public class PatternExtractorFactoryImpl implements PatternExtractorFactory{

    @Override
    public PatternExtractor<Integer, Integer> countConsecutiveZeros() {
        return new PatternExtractor<Integer,Integer>() {

            @Override
            public List<Integer> extract(List<Integer> input) {
                List<Integer> consecutiveZeroSequencesLength = new ArrayList<>();
                int c = 0;
                for (Integer i : input) {
                    if(i == 0){
                        c++;
                    }
                    else{
                        if(c != 0){
                            consecutiveZeroSequencesLength.add(c);
                            c = 0;
                        }
                    }
                }
                return consecutiveZeroSequencesLength;
            }
            
        };
    }

    @Override
    public PatternExtractor<Double, Double> averageConsecutiveInRange(double min, double max) {
        return new PatternExtractor<Double, Double>() {

            @Override
            public List<Double> extract(List<Double> input) {
                List<Double> avgs = new ArrayList<>();
                List<Double> numbers = new ArrayList<>();
                boolean isSumming = true;

                for (Double i : input) {
                    if(i >= min && i < max && !isSumming){
                        isSumming = true;
                        numbers.clear();
                        numbers.add(i);
                    }
                    else if ((i < min || i > max) && isSumming){
                        isSumming = false;
                        avgs.add(numbers.stream()
                                        .reduce((a,b) -> a + b)
                                        .get() / numbers.size());
                    }
                    else if(isSumming){
                        numbers.add(i);
                    }

                }
                return avgs;
            }
            
        };
    }

    @Override
    public PatternExtractor<String, String> concatenateBySeparator(String separator) {
        return new PatternExtractor<String,String>() {

            @Override
            public List<String> extract(List<String> input) {
                List<String> output = new ArrayList<>();
                String concat = "";
                for (String s : input) {
                    if(s == separator && concat != ""){
                        output.add(concat);
                        concat = "";
                    }
                    else if(s != separator){
                        concat+=s;
                    }
                }
                if(concat != ""){
                    output.add(concat);
                }
                return output;
            }
            
        };
    }

    @Override
    public PatternExtractor<String, Double> sumNumericStrings() {
        return new PatternExtractor<String,Double>() {

            @Override
            public List<Double> extract(List<String> input) {
                List<Double> output = new ArrayList<>();
                Double sum = 0.0;

                for (String s : input) {
                    Double d;
                    try {
                        d = Double.parseDouble(s);
                        sum += d;
                    } catch (Exception e) {
                        output.add(sum);
                        sum = 0.0;
                    }
                }
                if(sum != 0.0){
                    output.add(sum);
                }
                return output;
            }
        };
    }
    
}
