package org.ax;

public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        String pathToFile = "C:\\Users\\Alex\\IdeaProjects\\SortFilesByMerge\\src\\main\\resources\\test.txt";
        String pathToFile2 = "C:\\Users\\Alex\\IdeaProjects\\SortFilesByMerge\\src\\main\\resources\\test2.txt";
        String pathToFile3 = "C:\\Users\\Alex\\IdeaProjects\\SortFilesByMerge\\src\\main\\resources\\test3.txt";
        fileService.readIntFromFile(pathToFile, pathToFile3, pathToFile2);
    }
}