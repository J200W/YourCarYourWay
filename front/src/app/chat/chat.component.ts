import { Component, OnInit } from '@angular/core';
import { ChatService } from './chat.service';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
  imports: [FormsModule, BrowserModule],
})
export class ChatComponent implements OnInit {

  constructor(private chatService: ChatService) { }

  ngOnInit(): void {
    this.chatService.getMessages().subscribe((messages) => {
      this.messages = messages;
    });
  }

  selectedMode: string = 'chat'; // Valeur par défaut
  messageContent: string = '';
  messages:any = [];

  sendMessage(): void {
    if (this.messageContent.trim()) {
      const message = {
        utilisateur: { id: 2 },
        contenu: this.messageContent,
        type: this.selectedMode
      };
      this.messageContent = '';
      console.log('Message envoyé :', message);
      this.chatService.sendMessage(message).subscribe((response) => {
        alert('Message envoyé avec succès !');
        location.reload();
      });
    }
  }

  handleFormSubmit(): void {

    // Process the form data
    console.log('Mode de communication :', this.selectedMode);
    console.log('Messages envoyés :', this.messages);
    alert('Formulaire soumis avec succès !');
  }
}
