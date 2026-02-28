package com.nav.spring_boot.ai.rr.utils;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class TextExtractor {

    public String extractText(MultipartFile file) throws IOException, TikaException {
        Tika tika = new Tika();
        return tika.parseToString(file.getInputStream());
    }
}
