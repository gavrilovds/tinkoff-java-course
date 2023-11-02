package edu.hw4;

import edu.hw4.Animal.Sex;
import edu.hw4.Animal.Type;
import edu.hw4.ValidationError.ErrorType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class AnimalUtils {

    private AnimalUtils() {

    }

    public static List<Animal> sortByHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

    public static List<Animal> sortByWeightDesc(List<Animal> animals, int limit) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(limit)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Long> countAnimalTypes(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt(o -> o.name().length())).orElse(null);
    }

    public static Sex getMostPopularSex(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Comparator.comparingLong(Entry::getValue))
            .map(Entry::getKey)
            .orElse(null);
    }

    public static Map<Animal.Type, Animal> getHeaviestAnimalOfEachType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                (animal1, animal2) -> animal1.weight() >= animal2.weight() ? animal1 : animal2
            ));
    }

    public static Animal getTheKthOldestAnimal(List<Animal> animals, int number) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .limit(number)
            .min(Comparator.comparingInt(Animal::age)).orElse(null);
    }

    public static Optional<Animal> getTheHeaviestAnimalUnderGivenHeight(List<Animal> animals, int height) {
        return animals.stream()
            .filter(o -> o.height() < height)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer countPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> getAnimalsWhichAgeDoesntEqualTheirPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .collect(Collectors.toList());
    }

    public static List<Animal> getBitingAnimals(List<Animal> animals) {
        final int minHeight = 100;
        return animals.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > minHeight)
            .collect(Collectors.toList());
    }

    public static Long getNumberOfAnimalsWhichWeightGreaterThanHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> getAnimalsWhichNameConsistsOfTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length == 2)
            .collect(Collectors.toList());
    }

    public static boolean doesListContainDogWithHeightGreaterThanNumber(List<Animal> animals, int number) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Type.DOG && animal.height() > number);
    }

    public static Integer getTotalWeightOfAnimalsWithAgeInGivenRange(List<Animal> animals, int start, int end) {
        return animals.stream()
            .filter(animal -> animal.age() >= start && animal.age() <= end)
            .mapToInt(Animal::weight)
            .sum();
    }

    public static List<Animal> sortAnimalsByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    public static boolean isSpidersBiteMoreThanDogs(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> (animal.type() == Type.SPIDER || animal.type() == Type.DOG) && animal.bites())
            .collect(
                Collectors.collectingAndThen(
                    Collectors.groupingBy(Animal::type, Collectors.counting()),
                    result -> result.get(Type.SPIDER) > result.get(Type.DOG)
                )
            );
    }

    @SafeVarargs
    public static Animal getTheHeaviestFish(List<Animal>... animals) {
        return Arrays.stream(animals).flatMap(List::stream)
            .filter(animal -> animal.type() == Type.FISH)
            .max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }

    public static Map<String, Set<ValidationError>> getAnimalsErrorMap(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(Animal::name, AnimalUtils::validateAnimal),
                map -> {
                    map.values().removeIf(Set::isEmpty);
                    return map;
                }
            ));
    }

    public static Map<String, String> getReadableAnimalsErrorMap(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(
                    Animal::name,
                    animal -> validateAnimal(animal).stream().map(el -> el.errorType().toString())
                        .collect(Collectors.joining(", "))
                ),
                map -> {
                    map.values().removeIf(String::isEmpty);
                    return map;
                }
            ));
    }

    private static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.age() <= 0) {
            errors.add(new ValidationError(ErrorType.AGE));
        }
        if (animal.height() <= 0) {
            errors.add(new ValidationError(ErrorType.HEIGHT));
        }
        if (animal.weight() <= 0) {
            errors.add(new ValidationError(ErrorType.WEIGHT));
        }
        return errors;
    }
}
