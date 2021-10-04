import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted: boolean = false;
  hide: boolean = true;

  constructor(private authService: AuthService, private router: Router, private snackBar: MatSnackBar) {
    this.loginForm = new FormBuilder().group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])]
    });
  }

  ngOnInit(): void { }

  onSubmit() {
    this.submitted = true;
    this.authService.login(this.loginForm.value)
      .subscribe(() => {
        this.submitted = false;
        this.router.navigate(['/folders']);
      },
        () => {
          this.submitted = false;
          this.snackBar.open("User or password invalid. Please try again", "Ok");
        });
  }

}
