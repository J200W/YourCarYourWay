import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subscription } from 'rxjs';
import { Utilisateur } from '../interfaces/utilisateur.interface';
import { AuthService } from './auth.service';

@Injectable({
    providedIn: 'root',
})
/**
 * Service de session
 * @class
 */
export class SessionService {
    /**
     * Indique si l'utilisateur est connecté
     * @type {BehaviorSubject<boolean>}
     */
    public isLoggedSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.getLocalStorageIsLogged());
    
    /**
     * Utilisateur connecté
     * @type {User | undefined}
     */
    public user: Utilisateur | undefined;

    private sessionSubscription: Subscription = new Subscription();
    
    /**
     * Constructeur du service
     * @param authService 
     */
    constructor(
        private authService: AuthService,
    ) {

    }

    /**
     * Observable de l'état de connexion (qui est un BehaviorSubject à l'initialisation)
     * @returns {Observable<boolean>}
     */
    public $isLogged(): Observable<boolean> {
        const isLogged = this.getLocalStorageIsLogged();
        this.isLoggedSubject.next(isLogged);
        return this.isLoggedSubject.asObservable();
    }

    /**
     * Connecte l'utilisateur
     */
    public logIn(): void {
        this.setLocalStorageIsLogged(true);
        this.isLoggedSubject.next(true);
    }

    /**
     * Déconnecte l'utilisateur
     */
    public logOut(): void {
        this.setLocalStorageIsLogged(false);
        this.isLoggedSubject.next(false);
        localStorage.clear();
    }

    /**
     * Enregistre l'état de connexion dans le localStorage
     * avant le chargement d'une page
     * @param {boolean} isLogged
     */
    private setLocalStorageIsLogged(isLogged: boolean): void {
        localStorage.setItem('isLogged', JSON.stringify(isLogged));
    }

    /**
     * Récupère l'état de connexion dans le localStorage
     * après le chargement d'une page
     * @returns {boolean}
     */
    private getLocalStorageIsLogged(): boolean {
        const isLogged = localStorage.getItem('isLogged');
        return isLogged ? JSON.parse(isLogged) : false;
    }

    /**
     * Connecte automatiquement l'utilisateur
     */
    public autoLogin(): void {
        this.sessionSubscription = this.authService.isLogged().subscribe((isLogged: boolean) => {
            if (isLogged) {
                this.logIn();
            } else {
                this.logOut();
            }
        });
    }
}