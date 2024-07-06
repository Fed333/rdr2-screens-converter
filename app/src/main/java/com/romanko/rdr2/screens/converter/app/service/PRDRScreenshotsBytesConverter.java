package com.romanko.rdr2.screens.converter.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class PRDRScreenshotsBytesConverter {

    private final PRDRScreenshotBytesConverter screenshotConverter;

    public Map<String, byte[]> convertScreenshots(String sourcePath, String[] fileNames) {
        Map<String, byte[]> convertedFileBytesMap = new HashMap<>();

        for (String fileName : fileNames) {
            try (FileInputStream prdrFile = new FileInputStream(sourcePath + "\\" + fileName) ) {
                byte[] prdrBytes = prdrFile.readAllBytes();
                byte[] jpgBytes = screenshotConverter.convertToJPG(prdrBytes);

                convertedFileBytesMap.put(fileName, jpgBytes);
            } catch (IOException e) {
                log.error("Can't operate file: {}", fileName, e);
                throw new RuntimeException(e);
            }
        }

        return convertedFileBytesMap;
    }

}
