import {Component, Input, OnInit} from '@angular/core';
import {TokenStorageService} from "../../_services/token-storage.service";
import {Router} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";
import {UserService} from "../../_services/user.service";

@Component({
  selector: 'app-modify',
  templateUrl: './modify.component.html',
  styleUrls: ['./modify.component.css']
})
export class ModifyComponent implements OnInit {

  user: any;
  isSuccessful = false;
  isModifyFailed = false;
  errorMessage = '';
  isSubmitted = false;

  modifyForm = this.fb.group({
    username: [""]
  });

  @Input() id = 0;
  @Input() username = '';
  @Input() email = '';
  @Input() win = '';
  @Input() lost = '';
  @Input() ratio = 0.0;
  @Input() score = 0;

  constructor(private tokenStorageService: TokenStorageService,
              public router: Router,
              private fb: FormBuilder,
              private userService: UserService) {

    this.user = this.tokenStorageService.getUser();
    this.modifyForm = this.fb.group({
      username: ["", [Validators.required, Validators.minLength(3), Validators.maxLength(20)]]
    });


  }

  ngOnInit(): void {
    this.modifyForm.setValue({
      username: this.username
    });
  }

  onSubmit() {
    this.isSubmitted = true;
    if (!this.modifyForm.valid) {
      console.log("Pas valide");
    } else {
      this.userService.updateProfile(
        this.id,
        this.modifyForm.get('username')?.value).subscribe(
        data => {
          console.log(data);
          this.isSuccessful = true;
          this.isModifyFailed = false;
          this.router.navigate(['/profil']);
        },
        err => {
          this.errorMessage = err.error.message;
          this.isModifyFailed = true;
        }
      )
    }
  }

}
