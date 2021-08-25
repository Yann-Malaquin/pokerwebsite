import {Component, OnInit} from '@angular/core';
import {AuthService} from "../_services/auth.service";
import {TokenStorageService} from "../_services/token-storage.service";
import {Router} from "@angular/router";
import {NavbarService} from "../_services/navbar.service";
import {FooterService} from "../_services/footer.service";
import {DatasharingService} from "../_services/datasharing.service";
import {StoreService} from "../_services/store.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  };

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private route: Router,
              private navbarService: NavbarService,
              private footerService: FooterService,
              private dataSharing: DatasharingService,
              private store: StoreService) {
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
    this.navbarService.hide();
    this.footerService.hide();

  }

  onSubmit(): void {
    const {username, password} = this.form;

    this.authService.login(username, password).subscribe(
      data => {
        console.log(data);
        console.log(data.accessToken);
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.dataSharing.isLoggedIn.next(true);
        this.dataSharing.usernameUpdate.next(username);
        this.dataSharing.walletUpdate.next(this.tokenStorage.getUser().wallet);
        this.store.saveLoggedIn(true);
        this.route.navigate(['/']);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

}
