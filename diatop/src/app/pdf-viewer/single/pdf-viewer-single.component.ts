import {Component, Input, OnChanges, OnInit, SimpleChanges} from "@angular/core";
import {PdfViewerClass} from "../pdf-viewer.class";
import {Document} from "../../model/diatop.model";

@Component({
  selector: 'my-pdf-viewer-single',
  templateUrl: './pdf-viewer-single.component.html'
})
export class PdfViewerComponentSingle extends PdfViewerClass implements OnChanges {

  @Input() documents: Document[] = [];

  ngOnChanges(): void {
    this.prepareDocuments(this.documents);
  }
}