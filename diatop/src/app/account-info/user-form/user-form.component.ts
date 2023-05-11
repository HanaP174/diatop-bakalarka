import {Component, Input, OnChanges, SimpleChanges} from "@angular/core";
import {User} from "../../model/diatop.model";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'user-form',
  templateUrl: './user-form.component.html'
})
export class UserFormComponent implements OnChanges {

  @Input() user: User = new User();

  userForm: FormGroup = new FormGroup({});
  hide = true;

  constructor(private formBuilder: FormBuilder,
              private httpClient: HttpClient,
              private _snackBar: MatSnackBar) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['user']) {
      this.createUserForm();
      this.setDefaultValues();
    }
  }

  onUserChange() {
    if (this.hasUserDataChanges()) {
      this.mapUserFromForm();
      this.httpClient.post<User>('/resource/updateUser', this.user).subscribe({
        error: error => {
          console.log('change user error', error);
        },
        next: response => {
          if (response) {
            this.openSnackBar('Vaše informácie boli úspešne zmenené');
          }
        }
      });
    }
  }

  private createUserForm() {
    this.userForm = this.formBuilder.group({
      password: new FormControl(''),
      birthNumber: new FormControl('', Validators.required),
      email: new FormControl({value: this.user.email, disabled: false}, [Validators.required, Validators.email]),
      firstName: new FormControl(this.user.firstName, Validators.required),
      lastName: new FormControl(this.user.lastName, Validators.required)
    });
  }

  private setDefaultValues() {
    window.setTimeout(() => {
      this.userForm.get('email')?.setValue(this.user.email);
      this.userForm.get('birthNumber')?.setValue(this.user.birthNumber);
      this.userForm.get('firstName')?.setValue(this.user.firstName);
      this.userForm.get('lastName')?.setValue(this.user.lastName);
    }, 0)
  }

  private hasUserDataChanges() {
    if (this.user.email != this.userForm.get('email')?.value) {
      return true;
    } else if (this.user.password != this.userForm.get('password')?.value) {
      return true;
    } else if (this.user.birthNumber != this.userForm.get('birthNumber')?.value) {
      return true;
    } else if (this.user.firstName != this.userForm.get('firstName')?.value) {
      return true;
    } else if (this.user.lastName != this.userForm.get('lastName')?.value) {
      return true;
    }
    return false;
  }

  private mapUserFromForm() {
    this.user.password = this.userForm.get('password')?.value;
    this.user.birthNumber = this.userForm.get('birthNumber')?.value;
    this.user.email = this.userForm.get('email')?.value;
    this.user.firstName = this.userForm.get('firstName')?.value;
    this.user.lastName = this.userForm.get('lastName')?.value;
  }

  private openSnackBar(message: string) {
    this._snackBar.open(message, '');
    window.setTimeout(() => {
      this._snackBar.dismiss();
    },4000);
  }
}