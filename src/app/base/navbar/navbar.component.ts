import {Component, OnInit} from '@angular/core';
import {NavbarService} from "../../_services/navbar.service";
import {TokenStorageService} from "../../_services/token-storage.service";
import {DatasharingService} from "../../_services/datasharing.service";
import {StoreService} from "../../_services/store.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public isLoggedIn = false;
  public username = '';
  private user: any;

  constructor(private tokenStorageService: TokenStorageService,
              public navbarService: NavbarService,
              private dataSharing: DatasharingService,
              private store: StoreService,
              private router: Router) {

    this.user = this.tokenStorageService.getUser();
    this.dataSharing.isLoggedIn.subscribe(value => {
      this.isLoggedIn = value;
    });

    this.dataSharing.usernameUpdate.subscribe(value => {
      this.username = value;
    });

    this.username = this.user.username;

  }

  ngOnInit() {
    console.log(this.isLoggedIn);
  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.dataSharing.isLoggedIn.next(false);
    this.store.saveLoggedIn(false);
    this.router.navigate(['']);
  }


}
