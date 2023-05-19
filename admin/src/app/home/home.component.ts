import {Component} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'home',
  templateUrl: './home.component.html'
})
export class HomeComponent {

  constructor(private http: HttpClient) {}

  logout() {
    this.http.post('/logout', {}).subscribe(() => {
      window.location.href = '/';
    });
  }
}
