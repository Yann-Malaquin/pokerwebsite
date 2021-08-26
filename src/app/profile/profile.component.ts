import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../_services/token-storage.service";
import {NavbarService} from "../_services/navbar.service";
import {FooterService} from "../_services/footer.service";
import {Router} from "@angular/router";
import {DatasharingService} from "../_services/datasharing.service";

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
              public router: Router,
              private dataSharing: DatasharingService) {

    this.user = this.tokenStorageService.getUser();
    this.dataSharing.usernameUpdate.subscribe(value => {
      this.user.username = value
    })

    navbarService.show();
    footerService.show();

    this.id = this.user.id;
    this.username = this.user.username;
    this.email = this.user.email;
    this.win = this.user.scoreboard.win;
    this.lost = this.user.scoreboard.lost;
    this.ratio = this.user.scoreboard.ratio;
    this.score = this.user.scoreboard.score;
  }


  ngOnInit(): void {
    this.user = this.tokenStorageService.getUser();
    console.error(this.user);
  }


}
