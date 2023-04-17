package ua.lviv.iot.algo.part1.lab2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChainsawWriterTest {
    private ChainsawWriter chainsawWriter;
    private List<Chainsaw> chainsaws;

    @BeforeEach
    void setUp() {
        chainsaws = new ArrayList<>();
        chainsaws.add(new Chainsaw("Model1", 14.0, 5.5));
        chainsaws.add(new Chainsaw("Model2", 18.0, 7.0));
        chainsaws.add(new Chainsaw("Model3", 20.0, 8.5));
        chainsawWriter = new ChainsawWriter(chainsaws, "Acme");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of("chainsaw_models.csv"));
    }

    @Test
    void testWriteModelsToFile() throws IOException {
        // Виконуємо тест з попереднього завдання
        testWriteModelsToFileWithExistingFile();
    }

    @Test
    void testWriteModelsToFileWithExistingFile() throws IOException {
        // додатковий тест, перевіряємо чи файл був створений та заповнений даними
        chainsawWriter.writeModelsToFile();

        Path filePath = Path.of("chainsaw_models.csv");
        assertTrue(Files.exists(filePath));

        List<String> fileContent = Files.readAllLines(filePath);
        assertEquals("Model,Bar Length,Weight", fileContent.get(0));
        assertEquals("Model1,14.0,5.5", fileContent.get(1));
        assertEquals("Model2,18.0,7.0", fileContent.get(2));
        assertEquals("Model3,20.0,8.5", fileContent.get(3));

        Files.deleteIfExists(filePath);
    }

    @Test
    void testWriteModelsToFileWithEmptyList() throws IOException {
        // тестуємо, що файл не створюється, коли список порожній
        chainsaws.clear();
        chainsawWriter = new ChainsawWriter(chainsaws, "Acme");
        chainsawWriter.writeModelsToFile();

        Path filePath = Path.of("chainsaw_models.csv");
        assertFalse(Files.exists(filePath));
    }
}

