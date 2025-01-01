import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Utilisateur } from '../interfaces/utilisateur.interface'; 
import { environment } from '../../environments/environment'; 
import { LoginRequest } from '../payload/login-request.interface';
import { ResponseAPI } from '../payload/response-api.interface';


@Injectable({
    providedIn: 'root',
})
export class AuthService {
    /**
     * Chemin vers le service
     * @type {string}
     * @memberof AuthService
     * @default api/auth
     * @private
     */
    private pathService: string = `${environment.apiBaseUrl}/api/auth`;

    constructor(private httpClient: HttpClient) {
    }

    /**
     * Connecte un utilisateur
     * @param {LoginRequest} loginRequest
     * @returns {Observable<ResponseAPI>}
     * @memberof AuthService
     * @public
     */

    public login(loginRequest: LoginRequest): Observable<any> {
        return this.httpClient.post<ResponseAPI>(
            `${this.pathService}/login`,
            loginRequest
        );
    }

    /**
     * Déconnecte l'utilisateur
     * @returns {Observable<void>}
     * @memberof AuthService
     * @public
     */
    public logout(): Observable<void> {
        return this.httpClient.get<void>(`${this.pathService}/logout`);
    }

    /**
     * Vérifie si l'utilisateur est connecté
     * @returns {Observable<boolean>}
     * @memberof AuthService
     * @public
     */
    public isLogged(): Observable<boolean> {
        return this.httpClient.get<boolean>(`${this.pathService}/is-logged`);
    }
}