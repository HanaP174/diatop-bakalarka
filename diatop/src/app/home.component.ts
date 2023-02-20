import {Component} from "@angular/core";
import {AppService} from "./app.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";

export class Greeting extends Object {
  id: string = '';
  content: string = '';
}

interface TokenResponse {
  token: string;
}
@Component({
  templateUrl: './home.component.html'
})
export class HomeComponent {

  title = 'Demo';
  greeting: Object = new Greeting();

  // todo musi mat spravnu header, tuto nema na autentifikaciu, preco on nepotrebuje a ja ano?
  constructor(private app: AppService, private http: HttpClient) {
    // const headers = new HttpHeaders({authorization: 'Basic' + btoa('user:pass')});
    http.get<TokenResponse>('/token').subscribe({
      next: value => {
        const token = value.token;
        http.get('http://localhost:9000/', {headers : new HttpHeaders().set('X-Auth-Token', token)})
          .subscribe(response => this.greeting = response);
      }
    });
  }

  authenticated() { return this.app.authenticated; }

}
