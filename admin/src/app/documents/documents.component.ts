import {Component, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Document} from "../model/admin.model";
import {MatTableDataSource} from "@angular/material/table";

export class DocumentToDisplay extends Document {
  position: number = 1;
}

@Component({
  selector: 'documents',
  templateUrl: './documents.component.html'
})
export class DocumentsComponent implements OnInit {

  documentsToDisplay = new MatTableDataSource<DocumentToDisplay>([]);
  readonly displayedColumns: string[] = ['position', 'name', 'creationTimestamp', 'action'];

  constructor(private httpClient: HttpClient,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.initDocuments();
  }

  onFileUpload(event: Event) {
    const element = event.currentTarget as HTMLInputElement;
    let files: FileList | null = element.files;

    if (files != null) {
      const file: File = files[0];
      if (file) {
        const formData = new FormData();
        formData.append('file', file);
        this.httpClient.post<Document>('/resource/uploadDocument', formData).subscribe({
          error: error => {
            this.openSnackBar(error);
          },
          next: response => {
            this.documentsToDisplay.data.push(this.mapDocument(this.documentsToDisplay.data.length, response));
            this.documentsToDisplay.data = this.documentsToDisplay.data;
            this.openSnackBar('Súbor ' + response.name + ' bol úspešne uložený.')
          }
        });
      }
    }
  }

  onDeleteDocument(doc: DocumentToDisplay) {
    this.httpClient.post('/resource/deleteDocument', doc.uid).subscribe({
      error: error => {
        this.openSnackBar(error);
      },
        complete: () => {
          this.documentsToDisplay.data.splice(this.documentsToDisplay.data.indexOf(doc), 1)
          this.documentsToDisplay.data = this.documentsToDisplay.data;
          this.openSnackBar('Súbor s názvom ' + doc.name + ' bol úspešne vymazaný.')
      }
    })
  }

  private initDocuments() {
    this.httpClient.get<Document[]>('/resource/getAllDocuments').subscribe(response => {
      this.documentsToDisplay.data = response.map((document: Document, index) => this.mapDocument(index, document));
    })
  }

  private mapDocument(index: number, document: Document) {
    const doc = new DocumentToDisplay();
    doc.position = index + 1;
    doc.uid = document.uid;
    doc.name = document.name;
    doc.creationTimestamp = document.creationTimestamp;
    return doc;
  }

  private openSnackBar(message: string) {
    this._snackBar.open(message, '');
    window.setTimeout(() => {
      this._snackBar.dismiss();
    },4000);
  }
}
