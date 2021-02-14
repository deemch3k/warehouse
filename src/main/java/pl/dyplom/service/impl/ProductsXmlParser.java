package pl.dyplom.service.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pl.dyplom.domain.Product;
import pl.dyplom.service.XmlParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductsXmlParser implements XmlParser {

    @Override
    @SneakyThrows
    public List<Product> parse(MultipartFile file) {
        List<Product> productList = new ArrayList<>();
        File xmlFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(xmlFile);
        fos.write(file.getBytes());
        fos.close();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();
        NodeList products = doc.getElementsByTagName("product");
        for (int i = 0; i < products.getLength(); i++) {
            Node node = products.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                Product product = new Product();
                product.setName(e.getElementsByTagName("name").item(0).getTextContent());
                product.setDescription(e.getElementsByTagName("description").item(0).getTextContent());
                product.setExpirationDate(LocalDate.parse(e.getElementsByTagName("expirationDate").item(0).getTextContent()));
                product.setTotalAmount(Integer.parseInt(e.getElementsByTagName("totalAmount").item(0).getTextContent()));

                productList.add(product);
            }
        }
        xmlFile.delete();
        return productList;
    }
}
