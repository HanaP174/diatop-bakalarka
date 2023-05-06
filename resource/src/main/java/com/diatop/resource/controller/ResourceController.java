package com.diatop.resource.controller;

import com.diatop.model.document.DocumentDto;
import com.diatop.model.order.OrderDto;
import com.diatop.resource.service.DocumentService;
import com.diatop.resource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
public class ResourceController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private DocumentService documentService;

    @PostMapping("/addOrder")
    public void addOrder(@RequestBody OrderDto orderDto) {
      orderService.addOrder(orderDto);
    }

    @PostMapping("/uploadDocument")
    public DocumentDto uploadDocument(@RequestBody MultipartFile file) throws IOException {
      String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
      try {
        if (fileName.contains("..")) {
          throw new IOException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        DocumentDto documentDto =
                new DocumentDto(file.getBytes(), fileName, LocalDate.now());
        documentService.uploadDocument(documentDto);
        return documentDto;
      } catch (IOException ex) {
        throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
      }
    }

  @RequestMapping("/getAllDocuments")
  @ResponseBody
  public Mono<List<DocumentDto>> getAllDocuments() {
    return documentService.getAllDocuments();
  }

  @PostMapping("/deleteDocument")
  public void deleteDocument(@RequestBody String uid) {
      documentService.deleteDocument(uid);
  }
}
