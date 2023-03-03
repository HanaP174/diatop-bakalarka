import { Component } from '@angular/core';
import {AppService} from "./app.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';

  constructor(private appService: AppService,
              private router: Router) {
    if (!this.appService.authenticated) {
      router.navigateByUrl('/login');
    } else {
      window.location.href = '/ui/';
    }
  }

  isAuthenticated() {
    return this.appService.authenticated;
  }
}
