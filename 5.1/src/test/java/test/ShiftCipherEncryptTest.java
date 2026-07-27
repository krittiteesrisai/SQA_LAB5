package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import main.ShiftCipher;
import static org.junit.jupiter.api.Assertions.*;

class ShiftCipherEncryptTest {

    private ShiftCipher shiftCipher;

    @BeforeEach
    void setUp() {
        shiftCipher = new ShiftCipher();
    }

    // กลุ่ม Valid (TC001 - TC003)
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "TC001, SKIBIDI, 3, VNLELGL",
            "TC002, skibidi, -3, phfyfaf",
            "TC003, SkiBiDi, 0, SkiBiDi"
    })
    void testEncryptValid(String tcId, String plainText, int key, String expected) {
        String actual = shiftCipher.encrypt(plainText, key);
        
        // ปริ้นแค่ลำดับ TC กับผลลัพธ์ที่ได้
        System.out.println(tcId + " Actual Result: " + actual);
        
        assertEquals(expected, actual);
    }

    // กลุ่ม Invalid (TC004 - TC006)
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "TC004, skibidi123, 3",
            "TC005, skibidi_toilet, 3",
            "TC006, '', 3"
    })
    void testEncryptInvalid(String tcId, String plainText, int key) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shiftCipher.encrypt(plainText, key);
        });
        
        // ปริ้นแค่ลำดับ TC กับข้อความ Error ที่โค้ดโยนออกมา
        System.out.println(tcId + " Actual Result: " + exception.getMessage());
    }
}