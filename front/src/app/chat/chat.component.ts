import { Component, OnInit } from '@angular/core';
import { ChatService } from './chat.service';
import { SessionService } from '../core/services/session.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from '../core/interceptors/jwt.interceptor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
  imports: [
    CommonModule,  // Assurez-vous que CommonModule est inclus pour les directives de base comme *ngIf, *ngFor
    FormsModule,   // Inclure FormsModule pour ngModel
  ],
  providers: [
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ]
})

export class ChatComponent implements OnInit{

  public email: string = '';
  public nom: string = '';
  constructor(
    private chatService: ChatService,
    private sessionService: SessionService,
    private router: Router,
  ) { 
    this.email = localStorage.getItem('email') || '';
    this.nom = localStorage.getItem('nom') || '';
  }

  logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['/login']);
  }

  ngOnInit(): void {
    this.chatService.getMessages().subscribe((messages) => {
      this.messages = messages;
    },
    (error) => {
      this.logout();
    });
  }

  selectedMode: string = 'chat'; // Valeur par défaut
  messageContent: string = '';
  messages:any = [];

  sendMessage(): void {
    if (this.messageContent.trim()) {
      const message = {
        email: this.email,
        contenu: this.messageContent,
      };
      this.messageContent = '';
      this.chatService.sendMessage(message).subscribe((response) => {
        location.reload();
      });
    }
  }
}
