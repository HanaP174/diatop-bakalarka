import {Component, Inject, OnInit} from "@angular/core";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Order} from "../model/diatop.model";

@Component({
    selector: 'order-request-dialog',
    templateUrl: './order-request-dialog.component.html'
})
export class OrderRequestDialogComponent implements OnInit {

  orderRequestForm: FormGroup = new FormGroup({});
  differentAddress = false;
  personalDelivery = false;
  minDeliveryDate = new Date();

  private order = new Order();
  private readonly userId: number = 0;

  constructor(private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<OrderRequestDialogComponent>,
              @Inject(MAT_DIALOG_DATA) data: number) {
    this.userId = data;
  }

  ngOnInit(): void {
    this.minDeliveryDate.setDate(new Date().getDate() + 1);
    this.orderRequestForm = this.formBuilder.group({
        appointmentDate: new FormControl(null, Validators.required),
        deliveryDate: new FormControl(null, Validators.required),
        street: new FormControl(null, null),
        streetNumber: new FormControl(null, null),
        city: new FormControl(null, null),
        zipcode: new FormControl(null, null),
        note: new FormControl(null, null)
    });
  }

  weekendsDatesFilter = (d: Date | null): boolean => {
    if (d) {
      const day = d.getDay();

      /* Prevent Saturday and Sunday for select. */
      return day !== 0 && day !== 6 ;
    }
    return true;
  }

  close() {
    this.dialogRef.close();
  }

  save() {
    this.mapFormToModel();
    this.dialogRef.close(this.order);
  }

  onDifferentAddress(isDifferent: boolean) {
    this.differentAddress = isDifferent;
    if (this.differentAddress) {
      this.orderRequestForm.get('street')?.addValidators(Validators.required);
      this.orderRequestForm.get('streetNumber')?.addValidators(Validators.required);
      this.orderRequestForm.get('city')?.addValidators(Validators.required);
      this.orderRequestForm.get('zipcode')?.addValidators(Validators.required);
    } else {
      this.orderRequestForm.get('street')?.removeValidators(Validators.required);
      this.orderRequestForm.get('streetNumber')?.removeValidators(Validators.required);
      this.orderRequestForm.get('city')?.removeValidators(Validators.required);
      this.orderRequestForm.get('zipcode')?.removeValidators(Validators.required);
    }
  }

  private mapFormToModel() {
    this.order.userId = this.userId;
    this.order.appointmentDate = this.orderRequestForm.get('appointmentDate')?.value;
    this.order.deliveryDate = this.orderRequestForm.get('deliveryDate')?.value;
    this.order.street = this.orderRequestForm.get('street')?.value;
    this.order.streetNumber = this.orderRequestForm.get('streetNumber')?.value;
    this.order.city = this.orderRequestForm.get('city')?.value;
    this.order.zipcode = this.orderRequestForm.get('zipcode')?.value;
    this.order.note = this.orderRequestForm.get('note')?.value;
    this.order.personalDelivery = this.personalDelivery;
  }
}
