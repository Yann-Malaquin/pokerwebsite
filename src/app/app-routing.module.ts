import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from './home/home.component';
import {LoginComponent} from "./login/login.component";
import {RegistrationComponent} from "./registration/registration.component";
import {RecoveryComponent} from "./recovery/recovery.component";
import {ProfileComponent} from "./profile/profile.component";
import {ModifyComponent} from "./profile/modify/modify.component";
import {BankComponent} from "./bank/bank.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'connexion', component: LoginComponent},
  {path: 'inscription', component: RegistrationComponent},
  {path: 'recovery', component: RecoveryComponent},
  {
    path: 'profil', component: ProfileComponent, children: [
      {
        path: 'modification',
        component: ModifyComponent
      }
    ]
  },
  {path: 'depot', component: BankComponent}
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
