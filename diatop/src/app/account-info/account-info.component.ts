import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Document, Order, User} from "../model/diatop.model";

@Component({
  selector: 'account-info',
  templateUrl: './account-info.component.html'
})
export class AccountInfoComponent implements OnInit {

  user: User = new User();
  orders: Order[] = [];

  constructor(private router: Router,
              private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.initUser();
  }

  onHomeClick() {
    this.router.navigateByUrl('/home');
  }

  private initUser() {
    this.httpClient.get<User>('/getUser').subscribe({
      next: response => {
        this.user = response;
        this.initOrders(response.id);
      },
    })
  }

  private initOrders(userId: number) {
    const params = new HttpParams()
        .set('userId', userId);
    this.httpClient.get<Order[]>('/resource/getUsersOrders', {params: params}).subscribe(response => {
      this.orders = response;
    })
  }
}