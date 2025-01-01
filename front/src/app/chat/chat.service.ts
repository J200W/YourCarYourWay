import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  constructor(private http: HttpClient) {}

  private apiUrl = `${environment.apiBaseUrl}/api/messages`;

  sendMessage(message: any): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/send`, message);
  }

  getMessages(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/get-all`);
  }
}
