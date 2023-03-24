import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {RegistrationComponent} from "./registration/registration.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  // todo routing to login /login with nice url pattern
  { path: '', pathMatch: 'full', redirectTo: '/login'},
  { path: 'login', component: LoginComponent},
  { path: 'registration', component: RegistrationComponent},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
