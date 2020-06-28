package a.data_driven;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class DDTest {
    @ParameterizedTest(name = "print palindrome")
    @ValueSource (strings = {"racecar", "radar", "madam"}) // откуда берутся данные, если не используем файл, то такая аанотация ValueSource с типами данных внутри. Тест будет знать что надо брать по-одному
    void test(String word){
        System.out.println(word);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ddt.csv", numLinesToSkip = 1) // path к файлу(Mark directory as => Test Resources Root), параметр numLinesToSkip позволяет показать сколько Сверху пропустить
    void test2(int num, String name, String expected) {

        System.out.println(num + name + expected);
    }
}