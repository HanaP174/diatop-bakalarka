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

export class Document {
  uid: string = '';
  name: string = '';
  encodedFile: string = '';
}

export class User {
  id: number = 1;
  password: string = '';
  birthNumber: string = '';
  email: string = '';
  firstName: string = '';
  lastName: string = '';
}