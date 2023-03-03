import {Component} from "@angular/core";
import {AppService} from "./app.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  greetingUI = {id: '', content: ''};
  greetingResource = {id: '', content: ''};

  constructor(private app: AppService,
              private http: HttpClient) {
    http.get<{id: '', content: ''}>('/resource/test')
        .subscribe(response => this.greetingResource = response);
    http.get<{id: '', content: ''}>('/ui/resource')
        .subscribe(response => this.greetingUI = response);
  }

  logout() {
    this.http.post('/logout', {}).subscribe(() => {
      this.app.authenticated = false;
    });
  }
}
