import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private apiUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}

  sendMessage(message: any): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}send`, message);
  }

  getMessages(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}messages`);
  }
}
