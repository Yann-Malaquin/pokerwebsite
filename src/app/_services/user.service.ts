import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

const HTTPOPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  recovery(email: string, password: string): Observable<any> {
    return this.http.put(`${environment.url}/recovery`, {
      email,
      password
    }, HTTPOPTIONS);
  }

  updateProfile(id: number, username: string): Observable<any> {
    return this.http.put(`${environment.url}/${id}/updateProfile`, {
      username
    }, HTTPOPTIONS);
  }

  addMoney(id: number, amount: number): Observable<any> {
    return this.http.put(`${environment.url}/${id}/add_money`, {
      amount
    }, HTTPOPTIONS);
  }

  substractMoney(id: number, amount: number): Observable<any> {
    return this.http.put(`${environment.url}/${id}/substract_money`, {
      amount
    }, HTTPOPTIONS);
  }

  getUsers(): Observable<any> {
    return this.http.get(`${environment.url}/users`, HTTPOPTIONS);
  }
}
