package JPAApp.service;

import JPAApp.entity.Product;
import JPAApp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileService {
    private final ProductRepository productRepository;
    private final String baseDir = System.getProperty("user.dir")+ "/files/";
    private final String fileName = "csvWithProducts.csv";
    private final String fullPath = baseDir+fileName;

    public void writeProductsToFile() throws IOException {
        if (!Files.exists(Paths.get(baseDir))) {
            Files.createDirectory(Paths.get(baseDir));
        }
        File csvOutputFile = new File(baseDir+fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            productRepository.findAll().stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        };
    }

    public String convertToCSV(Product product) {
        return Stream.of(product.productToArray())
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public byte[] getFileData() throws IOException {
        System.out.println(fullPath);
        return Files.readAllBytes(Paths.get(fullPath));
    }
}
