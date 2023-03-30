export class Order {
  appointmentDate: Date = new Date();
  deliveryDate: Date = new Date();
  street: string = '';
  streetNumber: string = '';
  city: string = '';
  zipcode: string = '';
  note: string = '';
  personalDelivery: boolean = false;
}