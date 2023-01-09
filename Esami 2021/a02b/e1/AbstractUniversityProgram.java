package a02b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class AbstractUniversityProgram implements UniversityProgram{

    private List<Pair<String, Pair<Sector, Integer>>> courses = new ArrayList<>();
    
    protected int getCreditsSum(Set<String> courseNames){
        return courses.stream()
        .filter(c -> courseNames.contains(c.get1()))
        .map(p -> p.get2().get2())
        .reduce((a,b) -> a + b)
        .get();
    }
    
    protected int getSectorCredits(Sector s, Set<String> courseNames){
        return courses.stream()
            .filter(c -> courseNames.contains(c.get1()))
            .filter(p -> p.get2().get1().equals(s))
            .map(p -> p.get2().get2())
            .reduce((a,b) -> a + b)
            .get();
    }

    @Override
    public void addCourse(String name, Sector sector, int credits) {
        courses.add(new Pair<>(name, new Pair<>(sector, credits)));
    }
}
