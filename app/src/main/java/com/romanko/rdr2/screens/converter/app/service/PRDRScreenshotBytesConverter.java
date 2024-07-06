package com.romanko.rdr2.screens.converter.app.service;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class PRDRScreenshotBytesConverter {

    public byte[] convertToJPG(byte[] prdrBytes) {

        log.debug("Converting bytes...");
        int soi = -1;
        for (int i = 1; i < prdrBytes.length; i++) {
            byte ff = (byte) 255, d8 = (byte) 216;
            if (prdrBytes[i - 1] == ff && prdrBytes[i] == d8) {
                soi = i - 1;
                break;
            }
        }
        log.debug("SOI index: {}", soi);
        if (soi == -1) {
            throw new IllegalArgumentException("Given file is not a valid prdr file. JPG SOI was not found.");
        }
        byte[] jpgBytes = Arrays.copyOfRange(prdrBytes, soi, prdrBytes.length);
        log.debug("Bytes have been successfully converted to jpg.");
        return jpgBytes;
    }

}
