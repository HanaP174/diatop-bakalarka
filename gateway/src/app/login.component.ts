import { Component } from '@angular/core';
import {LoginService} from "./login.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  // styleUrls: ['./app.component.css']
})
export class LoginComponent {
  title = 'client';

  credentials = {username: '', password: ''};

  constructor(private app: LoginService,
              private http: HttpClient) {
    if (app.authenticated) {
      this.app.isAdmin ? window.location.href = '/admin/' : window.location.href = '/ui/';
    }
  }

  login() {
    // this.http.post('/login', this.credentials, {withCredentials: true});
    this.app.authenticate(this.credentials, () => {
      this.app.isAdmin ? window.location.href = '/admin/' : window.location.href = '/ui/';
    });
    return false;
  }

  isAuthenticated() { return this.app.authenticated; }

}
