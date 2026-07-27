package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import main.ShiftCipher;
import static org.junit.jupiter.api.Assertions.*;

class ShiftCipherDecryptTest {

    private ShiftCipher shiftCipher;

    @BeforeEach
    void setUp() {
        shiftCipher = new ShiftCipher();
    }

    // กลุ่ม Valid (TC001 - TC003)
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "TC001, VNLELGL, 3, SKIBIDI",
            "TC002, phfyfaf, -3, skibidi",
            "TC003, SkiBiDi, 0, SkiBiDi"
    })
    void testDecryptValid(String tcId, String cipherText, int key, String expected) {
        String actual = shiftCipher.decrypt(cipherText, key);
        
        // ปริ้นแค่ลำดับ TC กับผลลัพธ์ที่ได้
        System.out.println(tcId + " Actual Result: " + actual);
        
        assertEquals(expected, actual);
    }

    // กลุ่ม Invalid (TC004 - TC006)
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "TC004, VNLELGL123, 3",
            "TC005, VNLELGL_toilet, 3",
            "TC006, '', 3"
    })
    void testDecryptInvalid(String tcId, String cipherText, int key) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shiftCipher.decrypt(cipherText, key);
        });
        
        // ปริ้นแค่ลำดับ TC กับข้อความ Error ที่โค้ดโยนออกมา
        System.out.println(tcId + " Actual Result: " + exception.getMessage());
    }
}