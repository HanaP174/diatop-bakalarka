package com.diatop.resource.service;

import com.diatop.model.document.Document;
import com.diatop.model.document.DocumentDto;
import com.diatop.model.document.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@Service
public class DocumentService {

  @Autowired
  private DocumentRepository documentRepository;

  public Mono<List<DocumentDto>> getAllDocuments() {
    return documentRepository.findAll()
            .map(this::mapDocument)
            .sort(Comparator.comparing(DocumentDto::getCreationTimestamp))
            .collectList();
  }

  public void uploadDocument(DocumentDto documentDto) {
    Document document = new Document();
    document.setUid(documentDto.getUid());
    document.setName(documentDto.getName());
    document.setFile(documentDto.getFile());
    document.setCreationTimestamp(documentDto.getCreationTimestamp());
    documentRepository.save(document).subscribe();
  }

  public void deleteDocument(String uid) {
    documentRepository.deleteByUid(uid).subscribe();
  }

  private DocumentDto mapDocument(Document document) {
    return new DocumentDto(document.getUid(), document.getFile(), document.getName(), document.getCreationTimestamp());
  }
}
