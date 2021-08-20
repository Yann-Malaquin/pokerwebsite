import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {TokenStorageService} from "./token-storage.service";
import {StoreService} from "./store.service";

@Injectable({
  providedIn: 'root'
})
export class DatasharingService {

  constructor(private tokenStorage: TokenStorageService,
              private store: StoreService) {
  }

  public usernameUpdate: BehaviorSubject<string> = new BehaviorSubject<string>(this.tokenStorage.getUser().username);

  public isLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.store.isLoggedIn());
}
