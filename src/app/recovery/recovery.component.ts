import {Component, OnInit} from '@angular/core';
import {UserService} from "../_services/user.service";
import {FormBuilder, Validators} from "@angular/forms";
import {CustomValidationService} from "../_services/custom-validation.service";

@Component({
  selector: 'app-recovery',
  templateUrl: './recovery.component.html',
  styleUrls: ['./recovery.component.css']
})
export class RecoveryComponent implements OnInit {
  submitted = false;
  isSuccessful = false;
  isRecoveryFailed = false;
  errorMessage = '';

  registrationForm = this.fb.group({
    email: [""],
    password: [""],
    confirmPassword: [""],
    terms: [false]
  });

  constructor(private userService: UserService,
              private fb: FormBuilder,
              private customValidator: CustomValidationService) {

    console.log(this.submitted);
    this.registrationForm = this.fb.group({
        email: ["", [Validators.required, Validators.email]],
        password: ["", [Validators.required, Validators.minLength(6)]],
        confirmPassword: ["", [Validators.required, Validators.minLength(6)]]
      },
      {
        validator: this.customValidator.passwordMatchValidator(
          "password",
          "confirmPassword"
        )
      });
  }

  ngOnInit() {

  }


  onSubmit() {
    this.submitted = true;
    if (!this.registrationForm.valid) {
      console.log("Pas valide");
    } else {
      this.userService.recovery(
        this.registrationForm.get('email')?.value,
        this.registrationForm.get('password')?.value).subscribe(
        data => {
          console.log(data);
          this.isSuccessful = true;
          this.isRecoveryFailed = false;
        },
        err => {
          this.errorMessage = err.error.message;
          this.isRecoveryFailed = true;
        }
      )
    }
  }

}
