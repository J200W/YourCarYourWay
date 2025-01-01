import { Component } from '@angular/core';
import { SessionService } from '../core/services/session.service';
import { AuthService } from '../core/services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginRequest } from '../core/payload/login-request.interface';
import { Router } from '@angular/router';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from '../core/interceptors/jwt.interceptor';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss',
  imports: [CommonModule, FormsModule],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
})
export class AuthComponent {
  constructor(
    private sessionService: SessionService,
    private authService: AuthService,
    private router: Router
  ) {}
  public emailOrUsername: string = '';
  public password: string = '';

  login(): void {
    const loginRequest: LoginRequest = {
      emailOrUsername: this.emailOrUsername,
      password: this.password,
    };
    this.authService.login(loginRequest).subscribe((res) => {
      if (res.statusCode === 200) {
        this.sessionService.logIn();
        localStorage.setItem('token', res.jwtToken);
        localStorage.setItem('email', res.email);
        localStorage.setItem('nom', res.username);
        this.router.navigate(['/chat']);
      }
    });
  }
}
