import {Injectable, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {HomeComponent} from "./home.component";
import {FormsModule} from "@angular/forms";
import {AppService} from "./app.service";
import {LoginComponent} from "./login.component";
import {AppRoutingModule} from "./app-routing.module";
//
// @Injectable()
// export class CsrfInterceptor implements HttpInterceptor {
//   constructor(private token: string) {}
//
//   intercept(req: HttpRequest<any>, next: HttpHandler) {
//     // Add the CSRF token to the request headers
//     const csrfReq = req.clone({
//       headers: req.headers.set('X-XSRF-TOKEN', this.token)
//     });
//
//     // Pass on the modified request to the next interceptor or to the HttpHandler
//     return next.handle(csrfReq);
//   }
// }

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
    AppComponent,
    HomeComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [AppService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
