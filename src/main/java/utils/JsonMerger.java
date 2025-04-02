package utils;

import java.io.*;


public class JsonMerger {

    public static void main(String[] args) {
        String inputDirectory = "target/";  // Change this path
        String outputFilePath = "merged_result.json";

        try {
            mergeJsonFiles(inputDirectory, outputFilePath);
            System.out.println("Merged JSON file created: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergeJsonFiles(String directoryPath, String outputFilePath) throws IOException {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (listOfFiles == null || listOfFiles.length == 0) {
            System.out.println("No JSON files found in directory.");
            return;
        }

        StringBuilder mergedJson = new StringBuilder("[");  // Start of JSON array
        boolean first = true;

        for (File file : listOfFiles) {
            String content = readFile(file);
            content = content.trim();

            // Remove wrapping brackets if it's a JSON array
            if (content.startsWith("[")) {
                content = content.substring(1, content.length() - 1);
            }

            if (!first) {
                mergedJson.append(",");
            } else {
                first = false;
            }

            mergedJson.append(content);
        }

        mergedJson.append("]"); // End of JSON array

        // Write the merged JSON to a file
        writeFile(outputFilePath, mergedJson.toString());
    }

    private static String readFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        }
    }
}
