package com.romanko.rdr2.screens.converter.app.runner;

import com.romanko.rdr2.screens.converter.app.component.input.params.ExtractedParams;
import com.romanko.rdr2.screens.converter.app.component.input.params.InputParamsExtractor;
import com.romanko.rdr2.screens.converter.app.service.JpgConvertedBytesScreenshotsFileWriter;
import com.romanko.rdr2.screens.converter.app.service.PRDRScreenshotsBytesConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class RDR2ScreensConverterRunner {

    private final InputParamsExtractor paramsExtractor;
    private final PRDRScreenshotsBytesConverter screenshotsConverter;
    private final JpgConvertedBytesScreenshotsFileWriter convertedBytesScreenshotsFileWriter;

    public void runScreensConversion(String... args) {
        ExtractedParams params = paramsExtractor.extractParams(args);

        String src = params.getParam("src");
        String dst = params.getParam("dst");
        String files = params.getParam("files");

        log.debug("Source folder: {}\nDestination folder: {}\nFiles: {}", src, dst, files);

        String[] fileNames = files.split(",");

        Map<String, byte[]> convertedJpgFileBytes = screenshotsConverter.convertScreenshots(src, fileNames);

        convertedBytesScreenshotsFileWriter.createConvertedJpgScreenshots(dst, convertedJpgFileBytes);

    }

}
