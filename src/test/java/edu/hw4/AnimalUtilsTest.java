package edu.hw4;

import edu.hw4.Animal.Sex;
import edu.hw4.Animal.Type;
import edu.hw4.ValidationError.ErrorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class AnimalUtilsTest {

    private final List<Animal> animals = new ArrayList<>();

    @BeforeEach
    public void clearList() {
        animals.clear();
    }

    @Test
    @DisplayName("#sortByHeight test")
    public void sortByHeight_shouldReturnSortedAnimalsListByHeight() {
        animals.add(Animal.builder().height(28).build());
        animals.add(Animal.builder().height(22).build());
        animals.add(Animal.builder().height(13).build());
        animals.add(Animal.builder().height(48).build());
        List<Integer> actual =
            AnimalUtils.sortByHeight(animals).stream().map(Animal::height).collect(Collectors.toList());
        assertThat(actual).containsExactly(
            13, 22, 28, 48
        );
    }

    @Test
    @DisplayName("#sortByWeightDesc test")
    public void sortByWeightDesc_shouldReturnDescSortedAnimalsListByWeight() {
        animals.add(Animal.builder().weight(28).build());
        animals.add(Animal.builder().weight(22).build());
        animals.add(Animal.builder().weight(13).build());
        animals.add(Animal.builder().weight(48).build());
        List<Integer> actual =
            AnimalUtils.sortByWeightDesc(animals, 3).stream().map(Animal::weight).collect(Collectors.toList());
        assertThat(actual).containsExactly(
            48, 28, 22
        );
    }

    @Test
    @DisplayName("#countAnimalTypes test")
    public void countAnimalTypes_shouldReturnMapOfAnimalTypesAndItsNumbers() {
        animals.add(Animal.builder().type(Type.DOG).build());
        animals.add(Animal.builder().type(Type.DOG).build());
        animals.add(Animal.builder().type(Type.CAT).build());
        animals.add(Animal.builder().type(Type.FISH).build());
        Map<Type, Long> actual = AnimalUtils.countAnimalTypes(animals);
        assertThat(actual).contains(entry(Type.FISH, 1L), entry(Type.DOG, 2L), entry(Type.CAT, 1L));
    }

    @Test
    @DisplayName("#getAnimalWithLongestName test")
    public void getAnimalWithLongestName_shouldReturnAnimalWithLongestName() {
        animals.add(Animal.builder().name("Rex").build());
        animals.add(Animal.builder().name("Murzik").build());
        animals.add(Animal.builder().name("Druzhok").build());
        animals.add(Animal.builder().name("Kesha").build());
        Animal actual = AnimalUtils.getAnimalWithLongestName(animals);
        assertThat(actual.name()).isEqualTo("Druzhok");
    }

    @Test
    @DisplayName("#getMostPopularSex test")
    public void getMostPopularSex_shouldReturnMostPopularSexOfAnimals() {
        animals.add(Animal.builder().sex(Sex.M).build());
        animals.add(Animal.builder().sex(Sex.M).build());
        animals.add(Animal.builder().sex(Sex.F).build());
        animals.add(Animal.builder().sex(Sex.M).build());
        Sex actual = AnimalUtils.getMostPopularSex(animals);
        assertThat(actual).isEqualTo(Sex.M);
    }

    @Test
    @DisplayName("#getHeaviestAnimalOfEachType test")
    public void getHeaviestAnimalOfEachType_shouldReturnMapWithHeaviestAnimalsOfEachType() {
        animals.add(Animal.builder().type(Type.DOG).weight(200).build());
        animals.add(Animal.builder().type(Type.DOG).weight(300).build());
        animals.add(Animal.builder().type(Type.DOG).weight(100).build());
        animals.add(Animal.builder().type(Type.CAT).weight(50).build());
        Map<Type, Animal> actual = AnimalUtils.getHeaviestAnimalOfEachType(animals);
        assertThat(actual).contains(
            entry(Type.DOG, Animal.builder().type(Type.DOG).weight(300).build()),
            entry(Type.CAT, Animal.builder().type(Type.CAT).weight(50).build())
        );
    }

    @Test
    @DisplayName("#getTheKthOldestAnimal test")
    public void getTheKthOldestAnimal_shouldReturnTheKthOldestAnimal() {
        animals.add(Animal.builder().age(10).build());
        animals.add(Animal.builder().age(7).build());
        animals.add(Animal.builder().age(2).build());
        animals.add(Animal.builder().age(15).build());
        Animal actual = AnimalUtils.getTheKthOldestAnimal(animals, 3);
        assertThat(actual.age()).isEqualTo(7);
    }

    @Test
    @DisplayName("#getTheHeaviestAnimalUnderGivenHeight test")
    public void getTheHeaviestAnimalUnderGivenHeight_shouldReturnOptionalHeaviestAnimalUnderGivenHeight() {
        animals.add(Animal.builder().height(120).weight(200).build());
        animals.add(Animal.builder().height(110).weight(150).build());
        animals.add(Animal.builder().height(100).weight(200).build());
        animals.add(Animal.builder().height(90).weight(30).build());
        Optional<Animal> actual = AnimalUtils.getTheHeaviestAnimalUnderGivenHeight(animals, 115);
        assertThat(actual.get()).isEqualTo(Animal.builder().height(100).weight(200).build());
    }

    @Test
    @DisplayName("#countPaws test")
    public void countPaws_shouldReturnTotalAmountOfPaws() {
        animals.add(Animal.builder().type(Type.SPIDER).build());
        animals.add(Animal.builder().type(Type.DOG).build());
        animals.add(Animal.builder().type(Type.CAT).build());
        Integer actual = AnimalUtils.countPaws(animals);
        assertThat(actual).isEqualTo(16);
    }

    @Test
    @DisplayName("#getAnimalsWhichAgeDoesntEqualTheirPaws test")
    public void getAnimalsWhichAgeDoesntEqualTheirPaws_shouldReturnListOfRightAnimals() {
        animals.add(Animal.builder().type(Type.CAT).age(4).build());
        animals.add(Animal.builder().type(Type.CAT).age(5).build());
        animals.add(Animal.builder().type(Type.FISH).age(4).build());
        animals.add(Animal.builder().type(Type.FISH).age(0).build());
        List<Animal> actual = AnimalUtils.getAnimalsWhichAgeDoesntEqualTheirPaws(animals);
        assertThat(actual.stream().map(Animal::age).collect(Collectors.toList())).containsExactly(5, 4);
    }

    @Test
    @DisplayName("#getBitingAnimals test")
    public void getBitingAnimals_shouldReturnListOfBitingAnimals() {
        animals.add(Animal.builder().bites(true).height(110).build());
        animals.add(Animal.builder().bites(true).height(90).build());
        animals.add(Animal.builder().bites(false).height(120).build());
        List<Animal> actual = AnimalUtils.getBitingAnimals(animals);
        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("#getNumberOfAnimalsWhichWeightGreaterThanHeight test")
    public void getNumberOfAnimalsWhichWeightGreaterThanHeight_shouldReturnNumberOfTheseAnimals() {
        animals.add(Animal.builder().weight(120).height(100).build());
        animals.add(Animal.builder().weight(120).height(120).build());
        animals.add(Animal.builder().weight(90).height(100).build());
        animals.add(Animal.builder().weight(130).height(100).build());
        Long actual = AnimalUtils.getNumberOfAnimalsWhichWeightGreaterThanHeight(animals);
        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("#getAnimalsWhichNameConsistsOfTwoWords test")
    public void getAnimalsWhichNameConsistsOfTwoWords_shouldReturnListOfTheseAnimals() {
        animals.add(Animal.builder().name("Michael Bury").build());
        animals.add(Animal.builder().name("John").build());
        animals.add(Animal.builder().name("Mike Tyson").build());
        animals.add(Animal.builder().name("Ryan").build());
        List<Animal> actual = AnimalUtils.getAnimalsWhichNameConsistsOfTwoWords(animals);
        assertThat(actual.stream().map(Animal::name).collect(Collectors.toList())).containsExactly(
            "Michael Bury",
            "Mike Tyson"
        );
    }

    @Test
    @DisplayName("#doesListContainDogWithHeightGreaterThanNumber test")
    public void doesListContainDogWithHeightGreaterThanNumber_shouldReturnTrueWhenDogWithHeightInList() {
        animals.add(Animal.builder().type(Type.DOG).height(100).build());
        animals.add(Animal.builder().type(Type.DOG).height(80).build());
        animals.add(Animal.builder().type(Type.CAT).height(100).build());
        boolean actual = AnimalUtils.doesListContainDogWithHeightGreaterThanNumber(animals, 90);
        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("#getTotalWeightOfAnimalsWithAgeInGivenRange test")
    public void getTotalWeightOfAnimalsWithAgeInGivenRange_shouldReturnTotalSumm() {
        animals.add(Animal.builder().weight(120).age(10).build());
        animals.add(Animal.builder().weight(120).age(8).build());
        animals.add(Animal.builder().weight(100).age(13).build());
        animals.add(Animal.builder().weight(300).age(11).build());
        int actual = AnimalUtils.getTotalWeightOfAnimalsWithAgeInGivenRange(animals, 10, 13);
        assertThat(actual).isEqualTo(520);
    }

    @Test
    @DisplayName("#sortAnimalsByTypeSexName test")
    public void sortAnimalsByTypeSexName_shouldReturnSortedList() {
        animals.add(Animal.builder().type(Type.BIRD).sex(Sex.F).name("Bird1").build());
        animals.add(Animal.builder().type(Type.BIRD).sex(Sex.M).name("Bird3").build());
        animals.add(Animal.builder().type(Type.BIRD).sex(Sex.F).name("Bird2").build());
        animals.add(Animal.builder().type(Type.CAT).sex(Sex.F).name("Cat").build());
        List<Animal> actual = AnimalUtils.sortAnimalsByTypeSexName(animals);
        assertThat(actual).containsExactly(
            Animal.builder().type(Type.CAT).sex(Sex.F).name("Cat").build(),
            Animal.builder().type(Type.BIRD).sex(Sex.M).name("Bird3").build(),
            Animal.builder().type(Type.BIRD).sex(Sex.F).name("Bird1").build(),
            Animal.builder().type(Type.BIRD).sex(Sex.F).name("Bird2").build()
        );
    }

    @Test
    @DisplayName("#isSpidersBiteMoreThanDogs test")
    public void isSpidersBiteMoreThanDogs_shouldReturnTrueIfSpidersBiteMoreThanDogs() {
        animals.add(Animal.builder().type(Type.SPIDER).bites(true).build());
        animals.add(Animal.builder().type(Type.SPIDER).bites(true).build());
        animals.add(Animal.builder().type(Type.DOG).bites(true).build());
        boolean actual = AnimalUtils.isSpidersBiteMoreThanDogs(animals);
        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("#getTheHeaviestFish test")
    public void getTheHeaviestFish_shouldReturnTheHeaviestFishInTwoOrMoreLists() {
        animals.add(Animal.builder().type(Type.FISH).weight(100).build());
        animals.add(Animal.builder().type(Type.DOG).weight(100).build());
        List<Animal> animals2 = new ArrayList<>();
        animals2.add(Animal.builder().type(Type.CAT).weight(200).build());
        List<Animal> animals3 = new ArrayList<>();
        animals3.add(Animal.builder().type(Type.FISH).weight(300).build());

        Animal actual = AnimalUtils.getTheHeaviestFish(animals, animals2, animals3);
        assertThat(actual).isEqualTo(animals3.get(0));
    }

    @Test
    @DisplayName("#getAnimalsErrorMap test")
    public void getAnimalsErrorMap_shouldReturnMapOfAnimalErrors() {
        animals.add(Animal.builder().weight(-1).height(-1).age(0).name("Bobik").build());
        animals.add(Animal.builder().weight(-1).height(12).age(0).name("Grizlich").build());
        animals.add(Animal.builder().weight(12).height(13).age(10).name("Gavgavich").build());
        Map<String, Set<ValidationError>> actual = AnimalUtils.getAnimalsErrorMap(animals);
        assertThat(actual)
            .containsExactly(
                entry(
                    "Bobik",
                    Set.of(
                        new ValidationError(ErrorType.AGE, ErrorType.AGE.getErrorMessage()),
                        new ValidationError(ErrorType.HEIGHT, ErrorType.HEIGHT.getErrorMessage()),
                        new ValidationError(ErrorType.WEIGHT, ErrorType.WEIGHT.getErrorMessage())
                    )
                ),
                entry(
                    "Grizlich",
                    Set.of(
                        new ValidationError(ErrorType.WEIGHT, ErrorType.WEIGHT.getErrorMessage()),
                        new ValidationError(ErrorType.AGE, ErrorType.AGE.getErrorMessage())
                    )
                )
            );
    }

    @Test
    @DisplayName("#getReadbleAnimalsErrorMap test")
    public void getReadableAnimalsErrorMap_shouldReturnMapOfStringsWithNameAndErrorTypes() {
        animals.add(Animal.builder().name("Bobik").age(-1).weight(120).height(-20).build());
        animals.add(Animal.builder().name("Sobaken").age(1).weight(120).height(20).build());
        Map<String, String> actual = AnimalUtils.getReadableAnimalsErrorMap(animals);
        assertThat(actual)
            .hasEntrySatisfying("Bobik", val -> assertThat(val).isIn("AGE, HEIGHT", "HEIGHT, AGE"));
    }
}

