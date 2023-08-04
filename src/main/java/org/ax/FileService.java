package org.ax;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {
    public void readIntFromFile(String... filePaths) {
        Iterator<String> iterator = Arrays.stream(filePaths).iterator();
        List<Integer> integerList = new ArrayList<>();
        try {
            while (iterator.hasNext()) {
                String path = iterator.next();
                BufferedReader reader = new BufferedReader(new FileReader(path));
                while (reader.ready()) {
                    int item = Integer.parseInt(reader.readLine());
                    integerList.add(item);
                }
                reader.close();
            }
            int[] intArray = integerList.stream().mapToInt(integer -> integer).toArray();
            int[] sorted = mergeSort(intArray, 0, intArray.length - 1);
            String result = Arrays.stream(sorted).mapToObj(j -> j + "\n").collect(Collectors.joining());
            BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"));
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int[] mergeSort(int[] actual, int start, int end) {
        int length = end - start + 1;
        if (length == 1) {
            return new int[]{actual[start]};
        }
        if (length == 2) {
            if (actual[start] < actual[end]) {
                return new int[]{actual[start], actual[end]};
            }
            {
                return new int[]{actual[end], actual[start]};
            }
        }
        int half = length / 2;
        int[] buffer1 = mergeSort(actual, start, start + half - 1);
        int[] buffer2 = mergeSort(actual, start + half, end);
        return merge(buffer1, buffer1.length - 1, buffer2, buffer2.length - 1);
    }

    private int[] merge(int[] buffer1, int endBuf1, int[] buffer2, int endBuf2) {
        int lengthBuf1 = endBuf1 + 1;
        int lengthBuf2 = endBuf2 + 1;
        int[] result = new int[lengthBuf1 + lengthBuf2];
        int indexBuf1 = 0;
        int indexBuf2 = 0;
        for (int i = 0; i < result.length; i++) {
            if (indexBuf1 >= lengthBuf1) {
                result[i] = buffer2[indexBuf2++];
                continue;
            }
            if (indexBuf2 >= lengthBuf2) {
                result[i] = buffer1[indexBuf1++];
                continue;
            }
            if (buffer1[indexBuf1] < buffer2[indexBuf2]) {
                result[i] = buffer1[indexBuf1++];
            } else {
                result[i] = buffer2[indexBuf2++];
            }
        }
        return result;
    }
}