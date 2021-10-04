import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  private apiUrl = '/api';

  private _loginUrl = this.apiUrl + '/login';

  get loginUrl(): string {
    return this._loginUrl;
  }

  private _logoutUrl = this.apiUrl + '/logout';

  get logoutUrl(): string {
    return this._logoutUrl;
  }

  private _itemsUrl = this.apiUrl + '/items';

  get itemsUrl(): string {
    return this._itemsUrl;
  }

  private _foldersUrl = this.apiUrl + '/folders';

  get foldersUrl(): string {
    return this._foldersUrl;
  }

  private _usersUrl = this.apiUrl + '/users';

  get usersUrl(): string {
    return this._usersUrl;
  }

}
