package pl.dyplom.dyplom.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface XmlParser {

    List<?> parse(MultipartFile file);
}
