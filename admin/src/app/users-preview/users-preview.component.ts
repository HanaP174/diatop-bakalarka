import {Component, OnInit} from "@angular/core";
import {User} from "../model/admin.model";
import {MatTableDataSource} from "@angular/material/table";
import {HttpClient} from "@angular/common/http";

export class UserToDisplay extends User {
  position: number = 0;
}

@Component({
  selector: 'users-preview',
  templateUrl: './users-preview.component.html'
})
export class UsersPreviewComponent implements OnInit {

  usersToDisplay = new MatTableDataSource<UserToDisplay>([]);
  readonly displayedColumns: string[] = ['position', 'firstName', 'lastName', 'email', 'birthNumber'];

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.httpClient.get<User[]>('/resource/getAllUsers').subscribe(response => {
      this.usersToDisplay.data = response.map((user, index) => this.mapUser(index, user));
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.usersToDisplay.filter = filterValue.trim().toLowerCase();
  }

  private mapUser(index: number, user: User) {
    const userToDisplay = new UserToDisplay();
    userToDisplay.position = index + 1;
    userToDisplay.birthNumber = user.birthNumber;
    userToDisplay.firstName = user.firstName;
    userToDisplay.lastName = user.lastName;
    userToDisplay.email = user.email;
    return userToDisplay;
  }
}
