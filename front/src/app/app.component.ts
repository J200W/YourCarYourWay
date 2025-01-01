import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  standalone: false,
})
export class AppComponent implements OnInit {
  title = 'ycyw';
  private router: Router;

  constructor(
    router: Router,
  ) {
    this.router = router;
  }

  ngOnInit() {
    this.returnTologIn();
  }

  returnTologIn() {
    this.router.navigate(['/login']);
  }
}
