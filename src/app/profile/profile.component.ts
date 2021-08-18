import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../_services/token-storage.service";
import {NavbarService} from "../_services/navbar.service";
import {FooterService} from "../_services/footer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: any;
  id = 0;
  username = '';
  email = '';
  win = '';
  lost = '';
  ratio = 0.0;
  score = 0;

  constructor(private tokenStorageService: TokenStorageService,
              private navbarService: NavbarService,
              private footerService: FooterService,
              public router: Router) {
    this.user = this.tokenStorageService.getUser();
    console.log(this.user);
    navbarService.show();
    footerService.show();

    this.id = this.user.id;
    this.username = this.user.username;
    this.email = this.user.email;
    this.win = this.user.win;
    this.lost = this.user.lost;
    this.ratio = this.user.ratio;
    this.score = this.user.score;
  }


  ngOnInit(): void {

  }

}
