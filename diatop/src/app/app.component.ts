import {Component} from "@angular/core";
import {AppService} from "./app.service";
import {HttpClient} from "@angular/common/http";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {OrderRequestDialogComponent} from "./order-request-dialog/order-request-dialog.component";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  greetingUI = {id: '', content: ''};
  greetingResource = {id: '', content: ''};

  constructor(private app: AppService,
              private http: HttpClient,
              private dialog: MatDialog) {
    http.get<{id: '', content: ''}>('/resource/test')
        .subscribe(response => this.greetingResource = response);
    http.get<{id: '', content: ''}>('/ui/resource')
        .subscribe(response => this.greetingUI = response);
  }

  // todo
  logout() {
    this.http.post('/logout', {}).subscribe(() => {
      this.app.authenticated = false;
    });
  }

  openRequestDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogOutput = this.dialog.open(OrderRequestDialogComponent, dialogConfig);

    dialogOutput.afterClosed().subscribe(data => console.log(data));
  }
}
