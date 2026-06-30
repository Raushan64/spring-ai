package com.kumar.spring_ai.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentLoader {

    private final VectorStore vectorStore;

    public DocumentLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadDocuments() {

        TextReader reader = new TextReader(new ClassPathResource("rag-data/company-policy.txt"));

        List<Document> documents = reader.get();

        vectorStore.add(documents);

        System.out.println("Company documents loaded Successfully.");
    }

}