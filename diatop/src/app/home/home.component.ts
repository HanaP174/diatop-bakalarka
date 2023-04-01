import {Component} from "@angular/core";
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {OrderRequestDialogComponent} from "../order-request-dialog/order-request-dialog.component";
import {Order} from "../model/diatop.model";

@Component({
  selector: 'home',
  templateUrl: './home.component.html'
})
export class HomeComponent {

  private userId: number = 0;

  constructor(private app: AppService,
              private http: HttpClient,
              private dialog: MatDialog) {
    http.get<number>('/getUserId').subscribe(response => this.userId = response);
  }

  // todo
  logout() {
    this.http.post('/logout', {}).subscribe(() => {
      this.app.authenticated = false;
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
}