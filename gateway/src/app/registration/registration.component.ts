import {Component, OnInit} from "@angular/core";
import {UserDto} from "../model/gateway.model";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'registration',
  templateUrl: './registration.component.html'
})
export class RegistrationComponent implements OnInit {

  user: UserDto = new UserDto();
  formRegistration: FormGroup = new FormGroup({});
  userExists = false;

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private router: Router) {
  }

  ngOnInit(): void {
    this.formRegistration = this.formBuilder.group({
      password: new FormControl(null, Validators.required),
      birthNumber: new FormControl(null, Validators.required),
      email: new FormControl(null, Validators.required),
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required)
    });
  }

  register() {
    this.userExists = false;
    this.mapUserFromForm();
    this.http.post<UserDto>('/addUser', this.user).subscribe({
      error: error => {
        console.log('Error:', error);
      },
      next: response => {
        if (response) {
          this.router.navigateByUrl('/login');
        } else {
          this.userExists = true;
        }
      }
    });
  }

  private mapUserFromForm() {
    this.user.password = this.formRegistration.get('password')?.value;
    this.user.birthNumber = this.formRegistration.get('birthNumber')?.value;
    this.user.email = this.formRegistration.get('email')?.value;
    this.user.firstName = this.formRegistration.get('firstName')?.value;
    this.user.lastName = this.formRegistration.get('lastName')?.value;
  }
}
