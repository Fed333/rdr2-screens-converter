package com.romanko.rdr2.screens.converter.app.runner;

import com.romanko.rdr2.screens.converter.app.component.input.params.ExtractedParams;
import com.romanko.rdr2.screens.converter.app.component.input.params.InputParamsExtractor;
import com.romanko.rdr2.screens.converter.app.service.PRDRScreenshotsConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class RDR2ScreensConverterRunner {

    private final InputParamsExtractor paramsExtractor;
    private final PRDRScreenshotsConverter screenshotsConverter;

    public void runScreensConversion(String... args) {
        ExtractedParams params = paramsExtractor.extractParams(args);

        String src = params.getParam("src");
        String dst = params.getParam("dst");
        String files = params.getParam("files");

        log.debug("Source folder: {}\nDestination folder: {}\nFiles: {}", src, dst, files);

        String[] fileNames = files.split(",");

        Map<String, byte[]> convertedJpgFileBytes = screenshotsConverter.convertScreenshots(src, fileNames);

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
