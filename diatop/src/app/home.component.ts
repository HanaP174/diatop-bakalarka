import {Component} from "@angular/core";
import {AppService} from "./app.service";
import {HttpClient} from "@angular/common/http";

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
    http.get<Message>('/resource/test')
      .subscribe(response => this.greeting = response);
    http.get<Message>('/ui/resource')
      .subscribe(response => this.greeting = response);
  }

  authenticated() { return this.app.authenticated; }

}
