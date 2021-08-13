import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from './home/home.component';
import {LoginComponent} from "./login/login.component";
import {RegistrationComponent} from "./registration/registration.component";
import {RecoveryComponent} from "./recovery/recovery.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'connexion', component: LoginComponent},
  {path: 'inscription', component: RegistrationComponent},
  {path: 'recovery', component: RecoveryComponent}
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
