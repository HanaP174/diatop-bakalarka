import {Component, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {OrderRequestDialogComponent} from "../order-request-dialog/order-request-dialog.component";
import {Document, Order} from "../model/diatop.model";
import {Router} from "@angular/router";

@Component({
  selector: 'home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  documents: Document[] = [];
  allPdf = false;
  private userId: number = 0;

  constructor(private http: HttpClient,
              private dialog: MatDialog,
              private router: Router) {
    http.get<number>('/getUserId').subscribe(response => this.userId = response);
  }

  ngOnInit() {
    this.http.get<Document[]>('/resource/getAllDocuments').subscribe(response => {
      this.documents = response;
    })
  }

  // todo
  logout() {
    this.http.post('/logout', {}).subscribe(() => {
      this.router.navigateByUrl('/');
    });
  }

  openOrderRequestDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = this.userId;

    const dialogOutput = this.dialog.open(OrderRequestDialogComponent, dialogConfig);

    dialogOutput.afterClosed().subscribe(order => this.http.post<Order>("/resource/addOrder", order)
        .subscribe({
          error: error => {
            console.log('Order error', error);
          }
        }));
  }

  openAccountInfo() {
    this.router.navigateByUrl('/account');
  }

  changePdfView(index: number) {
    this.allPdf = index === 1;
  }
}