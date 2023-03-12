import {Injectable, NgModule} from "@angular/core";
import { BrowserModule } from '@angular/platform-browser';
import { LoginComponent } from './login.component';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {RouterLink, RouterLinkActive} from "@angular/router";
import {LoginService} from "./login.service";
import {FormsModule} from "@angular/forms";

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterLinkActive,
    RouterLink
  ],
  providers: [LoginService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [LoginComponent]
})
export class LoginModule { }
