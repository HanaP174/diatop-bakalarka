import {Component} from "@angular/core";
import {AppService} from "../app.service";

@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent {

  credentials = {username: '', password: ''};

  constructor(private app: AppService) {}

  login() {
    this.app.authenticate(this.credentials, () => {
      this.app.isAdmin ? window.location.href = '/admin/' : window.location.href = '/ui/';
    });
    return false;
  }

  isAuthenticated() { return this.app.authenticated; }

}
