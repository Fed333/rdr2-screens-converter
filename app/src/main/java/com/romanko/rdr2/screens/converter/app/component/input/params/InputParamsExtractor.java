package com.romanko.rdr2.screens.converter.app.component.input.params;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

/**
 * Class designed for extracting argument params.
 * @author Roman_Kovalchuk
 * */
public class InputParamsExtractor {

    public ExtractedParams extractParams(String[] args) {
        Map<String, String> paramsMap = Arrays.stream(args)
                .map(String::trim)
                .filter(not(String::isEmpty))
                .map(param -> param.split("=", 2))
                .collect(Collectors.toMap(p -> p[0], p -> p.length == 2 ? trimQuotes(p[1]) : p[0]));

        return new ExtractedParams(
                paramsMap,
                Arrays.stream(args).map(this::trimParam).collect(Collectors.toList())
        );
    }

    private String trimParam(String param) {
        return trimQuotes(param.trim());
    }

    private String trimQuotes(String str) {
        return str.replaceAll("^\"|\"$", "");
    }
}