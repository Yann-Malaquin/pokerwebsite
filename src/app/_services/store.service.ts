import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'logged-user';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  constructor() { }


  public saveLoggedIn(loggedIn: boolean): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(loggedIn));
  }

  public isLoggedIn(): any {
    const isLoggedIn = window.sessionStorage.getItem(USER_KEY);
    if (isLoggedIn) {
      return JSON.parse(isLoggedIn);
    }

    return false;
  }
}
