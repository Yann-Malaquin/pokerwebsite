import {Component, OnInit} from '@angular/core';
import {NavbarService} from "../_services/navbar.service";
import {FooterService} from "../_services/footer.service";
import {UserService} from "../_services/user.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  users: any;
  errorMessage = '';

  constructor(public navbarService: NavbarService,
              public footerService: FooterService,
              public userService: UserService) {
  }

  ngOnInit(): void {
    this.navbarService.show();
    this.footerService.show();

    this.userService.getUsers().subscribe(data => {
        this.users = data
      },
      err => {
        this.errorMessage = err.error.message;
      })
  }


}
