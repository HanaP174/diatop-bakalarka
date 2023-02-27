import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class AppService {

  authenticated = false;

  constructor(private http: HttpClient) {
  }

  authenticate(credentials: { password: string; username: string }, callback: () => void) {

    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get('/ui/user', {headers: headers}).subscribe( {
      next: response => {
        this.authenticated = response.hasOwnProperty('name');
        return callback && callback();
      },
      error: error => {
        console.log(error.toString());
      }
    });

  }

}
