import {Component, Input, OnInit} from "@angular/core";
import {Order} from "../../model/diatop.model";
import {MatTableDataSource} from "@angular/material/table";

export class OrderToDisplay extends Order {
  position: number = 1;
}

@Component({
  selector: 'orders',
  templateUrl: './orders.component.html'
})
export class OrdersComponent implements OnInit {

  @Input() orders: Order[] = [];

  ordersToDisplay = new MatTableDataSource<OrderToDisplay>([]);
  readonly displayedColumns: string[] = ['position', 'appointmentDate', 'deliveryDate', 'street', 'streetNumber', 'city',
    'zipcode', 'note', 'personalDelivery'];

  ngOnInit(): void {
    this.initOrdersToDisplay();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.ordersToDisplay.filter = filterValue.trim().toLowerCase();
  }

  private initOrdersToDisplay() {
    this.ordersToDisplay.data = this.orders.map((order, index) => this.mapOrder(index, order));
  }

  private mapOrder(index: number, order: Order) {
    const orderToDisplay = new OrderToDisplay();
    orderToDisplay.position = index + 1;
    orderToDisplay.appointmentDate = order.appointmentDate;
    orderToDisplay.deliveryDate = order.deliveryDate;
    orderToDisplay.street = order.street;
    orderToDisplay.streetNumber = order.streetNumber;
    orderToDisplay.city = order.city;
    orderToDisplay.zipcode = order.zipcode;
    orderToDisplay.note = order.note;
    orderToDisplay.personalDelivery = order.personalDelivery;
    return orderToDisplay;
  }
}
