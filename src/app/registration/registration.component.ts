import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {CustomValidationService} from "../_services/custom-validation.service";
import {AuthService} from "../_services/auth.service";


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  submitted = false;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  registrationForm = this.fb.group({
    username: [""],
    email: [""],
    password: [""],
    confirmPassword: [""],
    terms: [false]
  });

  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private customValidator: CustomValidationService) {

    console.log(this.submitted);
    this.registrationForm = this.fb.group({
        username: ["", [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
        email: ["", [Validators.required, Validators.email]],
        password: ["", [Validators.required, Validators.minLength(6)]],
        confirmPassword: ["", [Validators.required, Validators.minLength(6)]],
        terms: [false, [Validators.requiredTrue]]
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
      this.authService.registration(
        this.registrationForm.get('username')?.value,
        this.registrationForm.get('email')?.value,
        this.registrationForm.get('password')?.value).subscribe(
        data => {
          console.log(data);
          this.isSuccessful = true;
          this.isSignUpFailed = false;
        },
        err => {
          this.errorMessage = err.error.message;
          this.isSignUpFailed = true;
        }
      )
    }
  }
}
