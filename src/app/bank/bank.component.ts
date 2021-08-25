import {Component, OnInit} from '@angular/core';
import {NavbarService} from "../_services/navbar.service";
import {FooterService} from "../_services/footer.service";
import {DatasharingService} from "../_services/datasharing.service";
import {UserService} from "../_services/user.service";
import {TokenStorageService} from "../_services/token-storage.service";
import {Router} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-bank',
  templateUrl: './bank.component.html',
  styleUrls: ['./bank.component.css']
})
export class BankComponent implements OnInit {

  addForm = this.fb.group({
    add: [0]
  });

  substractForm = this.fb.group({
    substract: [0]
  });
  errorMessage = '';
  user: any;
  isSubmittedAdd = false;
  isSubmittedSubstract = false;


  constructor(private navbarService: NavbarService,
              private footerService: FooterService,
              private dataSharingService: DatasharingService,
              private userService: UserService,
              private tokenStorageService: TokenStorageService,
              private router: Router,
              private fb: FormBuilder) {

    this.user = this.tokenStorageService.getUser();
    this.addForm = this.fb.group({
      add: [null, [Validators.required]]
    });
    this.substractForm = this.fb.group({
      substract: [null, [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.navbarService.show();
    this.footerService.show();
  }

  onAddSubmit(): void {
    this.isSubmittedAdd = true;
    if (!this.addForm.valid) {
      console.log("Pas valide");
    } else {
      this.userService.addMoney(this.user.id, this.addForm.get('add')?.value).subscribe(
        data => {
          console.log(data);
          console.log(data.accessToken);
          this.user.wallet += this.addForm.get('add')?.value;
          this.tokenStorageService.saveUser(this.user);
          this.dataSharingService.walletUpdate.next(this.user.wallet);
        },
        err => {
          this.errorMessage = err.error.message;
        }
      );
    }
  }

  onSubstractSubmit(): void {
    this.isSubmittedSubstract = true;
    if (!this.substractForm.valid) {
      console.log("Pas valide");
    } else {
      this.userService.substractMoney(this.user.id, this.substractForm.get('substract')?.value).subscribe(
        data => {
          console.log(data);
          console.log(data.accessToken);
          this.user.wallet -= this.substractForm.get('substract')?.value;
          this.tokenStorageService.saveUser(this.user);
          this.dataSharingService.walletUpdate.next(this.user.wallet);
        },
        err => {
          this.errorMessage = err.error.message;
        }
      );
    }
  }

}
