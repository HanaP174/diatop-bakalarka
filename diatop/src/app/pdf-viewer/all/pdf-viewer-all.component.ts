import {Component, Input, OnChanges, OnInit} from "@angular/core";
import {PdfDocument, PdfViewerClass} from "../pdf-viewer.class";
import {Document} from "../../model/diatop.model";

@Component({
  selector: 'my-pdf-viewer-all',
  templateUrl: './pdf-viewer-all.component.html'
})
export class PdfViewerComponentAll extends PdfViewerClass implements OnChanges {

  @Input() documents: Document[] = [];

  ngOnChanges(): void {
    this.prepareDocuments(this.documents);
  }
}