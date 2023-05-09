import {Document} from "../model/diatop.model";
import {Directive, Injectable, Input} from "@angular/core";

export class PdfDocument {
  name: string = '';
  file: string = '';
}

@Directive()
export abstract class PdfViewerClass {

  noPdfToDisplay = false;
  pdfDocumentToDisplay: PdfDocument = new PdfDocument();
  pdfDocuments: PdfDocument[] = [];

  public prepareDocuments(documents: Document[]) {
    if (documents != null && documents.length > 0) {
      this.pdfDocuments = documents.map(doc => this.mapDocument(doc));
      this.pdfDocumentToDisplay = this.pdfDocuments[0];
      this.noPdfToDisplay = false;
    } else {
      this.noPdfToDisplay = true;
    }
  }

  public mapDocument(document: Document) {
    const pdfDoc = new PdfDocument();
    pdfDoc.file = document.encodedFile as string;
    pdfDoc.name = document.name;
    return pdfDoc;
  }
}