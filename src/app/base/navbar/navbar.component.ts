import {Component, OnInit} from '@angular/core';
import {NavbarService} from "../../_services/navbar.service";
import {TokenStorageService} from "../../_services/token-storage.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public isLoggedIn = false;

  constructor(private tokenStorageService: TokenStorageService, public navbarService: NavbarService) {

  }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }


}
