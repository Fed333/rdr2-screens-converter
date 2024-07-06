package com.romanko.rdr2.screens.converter.app.component.input.params;

import lombok.*;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
public class ExtractedParams {

    private final Map<String, String> paramsMap;

    private final List<String> paramsList;

    public String getParam(int i) {
        if (paramsList.size() > i) {
            return paramsList.get(i);
        }
        return null;
    }

    public String getParam(String name) {
        return paramsMap.get(name);
    }

}
