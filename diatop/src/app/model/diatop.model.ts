export class Order {
  userId: number = 0;
  appointmentDate: Date = new Date();
  deliveryDate: Date = new Date();
  street: string = '';
  streetNumber: string = '';
  city: string = '';
  zipcode: string = '';
  note: string = '';
  personalDelivery: boolean = false;
}