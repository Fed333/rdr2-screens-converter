package com.romanko.rdr2.screens.converter.app.service;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class JpgConvertedBytesScreenshotsFileWriter {

    public void createConvertedJpgScreenshots(String dst, Map<String, byte[]> convertedJpgFileBytes) {
        convertedJpgFileBytes.forEach((fileName, jpgBytes) -> {
            String dstFilename = dst + "\\" + fileName + ".jpg";
            File dstFile = new File(dstFilename);
            try {
                if (dstFile.createNewFile()) {
                    try (FileOutputStream dstFos = new FileOutputStream(dstFile)) {
                        dstFos.write(jpgBytes);
                        log.info("PRDR file {} has been succesfully converted.", fileName);
                    }
                } else {
                    log.info("File: {} already exist and will not be rewritten. Skipping conversion.", dstFile);
                }

            } catch (IOException e) {
                log.error("Can't write jpg converted bytes to a {} file", dstFilename, e);
            }
        });
    }

}
