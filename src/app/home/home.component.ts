import { Component, OnInit } from '@angular/core';
import {NavbarService} from "../_services/navbar.service";
import {FooterService} from "../_services/footer.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(public navbarService: NavbarService,
              public footerService: FooterService,) { }

  ngOnInit(): void {
    this.navbarService.show();
    this.footerService.show();
  }

}
