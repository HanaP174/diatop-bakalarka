import {Component, OnInit} from '@angular/core';
import {Credentials, LoginService} from "./login.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  title = 'client';
  formLogin: FormGroup = new FormGroup({});
  credentials: Credentials = new Credentials();
  hide = true;

  constructor(private app: LoginService,
              private formBuilder: FormBuilder) {
    if (app.authenticated) {
      this.app.isAdmin ? window.location.href = '/admin/' : window.location.href = '/ui/';
    }
  }

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      username: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    });
  }


  login() {
    if (this.formLogin.invalid) {
      return;
    }
    this.credentials.username = this.formLogin.get('username')?.value;
    this.credentials.password = this.formLogin.get('password')?.value;
    this.app.authenticate(this.credentials, () => {
      this.app.isAdmin ? window.location.href = '/admin/' : window.location.href = '/ui/';
    });
    return false;
  }

  isAuthenticated() { return this.app.authenticated; }

}
