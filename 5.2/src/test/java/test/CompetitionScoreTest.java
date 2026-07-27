package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import main.CompetitionScore;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionScoreTest {

    private CompetitionScore compScore;

    @BeforeEach
    void setUp() {
        compScore = new CompetitionScore();
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "TC001, 100, 250, 200, 250"
    })
    void testValidCases(String tcId, int s1, int s2, int s3, int expected) {
        int actual3Params = compScore.findMaxScore(s1, s2, s3);
        int actualArray = compScore.findMaxScore(new int[]{s1, s2, s3});
        System.out.println(tcId + " Actual Result: " + actual3Params);
        assertEquals(expected, actual3Params);
        assertEquals(expected, actualArray);
    }
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "TC002, 250, 250, -1",    "TC003, 250, 250, 501",
            "TC004, 250, -1, 250",    "TC005, 250, -1, -1",     "TC006, 250, -1, 501",
            "TC007, 250, 501, 250",   "TC008, 250, 501, -1",    "TC009, 250, 501, 501",
            "TC010, -1, 250, 250",    "TC011, -1, 250, -1",     "TC012, -1, 250, 501",
            "TC013, -1, -1, 250",     "TC014, -1, -1, -1",      "TC015, -1, -1, 501",
            "TC016, -1, 501, 250",    "TC017, -1, 501, -1",     "TC018, -1, 501, 501",
            "TC019, 501, 250, 250",   "TC020, 501, 250, -1",    "TC021, 501, 250, 501",
            "TC022, 501, -1, 250",    "TC023, 501, -1, -1",     "TC024, 501, -1, 501",
            "TC025, 501, 501, 250",   "TC026, 501, 501, -1",    "TC027, 501, 501, 501"
    })
    void testInvalidCases(String tcId, int s1, int s2, int s3) {
        Exception ex3Params = assertThrows(IllegalArgumentException.class, () -> {
            compScore.findMaxScore(s1, s2, s3);
        });
        Exception exArray = assertThrows(IllegalArgumentException.class, () -> {
            compScore.findMaxScore(new int[]{s1, s2, s3});
        });       
        System.out.println(tcId + " Actual Result: " + ex3Params.getMessage());
    }
}