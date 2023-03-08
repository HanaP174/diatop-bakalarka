import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserPrincipal} from "./model/gateway.model";

@Injectable()
export class AppService {

  authenticated = false;
  isAdmin = false;

  constructor(private http: HttpClient) {
  }

  authenticate(credentials: { password: string; username: string }, callback: () => void) {

    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get<UserPrincipal>('/user', {headers: headers}).subscribe( {
      next: response => {
        this.authenticated = response && response.name != null;
        this.isAdmin = this.authenticated && response.roles.length > 0 && response.roles.indexOf('ADMIN') > -1;
        return callback && callback();
      },
      error: error => {
        console.log(error.toString());
      }
    });

  }

}
