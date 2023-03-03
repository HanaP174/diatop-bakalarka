import {Component} from "@angular/core";
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent {

  credentials = {username: '', password: ''};

  constructor(private app: AppService) {}

  login() {
    this.app.authenticate(this.credentials, () => {
      window.location.href = '/ui/';
    });
    return false;
  }

  isAuthenticated() { return this.app.authenticated; }

}
