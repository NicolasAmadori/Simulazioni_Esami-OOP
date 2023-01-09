package a02b.e1;

import java.util.Set;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory{

    @Override
    public UniversityProgram flexible() {
        return new AbstractUniversityProgram() {
            private final int MUST_HAVE_CREDITS = 60;

            @Override
            public boolean isValid(Set<String> courseNames) {
                return getCreditsSum() == MUST_HAVE_CREDITS;
            }
        };
    }

    @Override
    public UniversityProgram scientific() {
        return new AbstractUniversityProgram() {
            private final int MUST_HAVE_CREDITS = 60;
            private final int MATHEMATICS_LOWER_BOUND = 12;
            private final int COMPUTER_SCIENCE_LOWER_BOUND = 12;
            private final int PHYSICS_LOWER_BOUND = 12;

            @Override
            public boolean isValid(Set<String> courseNames) {
                return getCreditsSum() == MUST_HAVE_CREDITS
                    && getSectorCredits(Sector.MATHEMATICS) >= MATHEMATICS_LOWER_BOUND
                    && getSectorCredits(Sector.COMPUTER_SCIENCE) >= COMPUTER_SCIENCE_LOWER_BOUND
                    && getSectorCredits(Sector.PHYSICS) >= PHYSICS_LOWER_BOUND;
            }
        };
    }

    @Override
    public UniversityProgram shortComputerScience() {
        return new AbstractUniversityProgram() {
            private final int CREDITS_LOWER_BOUND = 48;
            private final int COMPUTER_SCIENCE_AND_COMPUTER_ENGINEERING_LOWER_BOUND = 30;

            @Override
            public boolean isValid(Set<String> courseNames) {
                return getCreditsSum() >= CREDITS_LOWER_BOUND
                    && getSectorCredits(Sector.COMPUTER_SCIENCE) + getSectorCredits(Sector.COMPUTER_ENGINEERING) >= COMPUTER_SCIENCE_AND_COMPUTER_ENGINEERING_LOWER_BOUND;
            }
        };
    }

    @Override
    public UniversityProgram realistic() {
        return new AbstractUniversityProgram() {
            private final int CREDITS_LOWER_BOUND = 120;
            private final int COMPUTER_SCIENCE_AND_COMPUTER_ENGINEERING_LOWER_BOUND = 60;
            private final int MATH_AND_PHYSICS_LOWER_BOUND = 18;
            private final int THESIS_CREDITS = 24;

            @Override
            public boolean isValid(Set<String> courseNames) {
                return getCreditsSum() >= CREDITS_LOWER_BOUND
                    && getSectorCredits(Sector.COMPUTER_SCIENCE) + getSectorCredits(Sector.COMPUTER_ENGINEERING) >= COMPUTER_SCIENCE_AND_COMPUTER_ENGINEERING_LOWER_BOUND
                    && getSectorCredits(Sector.MATHEMATICS) + getSectorCredits(Sector.PHYSICS) >= MATH_AND_PHYSICS_LOWER_BOUND
                    && getSectorCredits(Sector.THESIS) == THESIS_CREDITS;
            }
        };
    }
    
}
