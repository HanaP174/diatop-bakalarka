import {Component} from "@angular/core";
import {AppService} from "./app.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";

export class Message {
  id: string = '';
  content: string = '';
}
@Component({
  templateUrl: './home.component.html'
})
export class HomeComponent {

  title = 'Demo';
  greeting = new Message();

  constructor(private app: AppService, private http: HttpClient) {
    http.get<{token: string, value: string}>('/ui/token').subscribe({
      next: value => {
        const token = value.token;
        http.get<Message>('/resource/test', {headers : new HttpHeaders().set('X-Auth-Token', token)})
          .subscribe(response => this.greeting = response);
      }
    });
  }

  authenticated() { return this.app.authenticated; }

}
