import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Item } from '../interfaces/Item.interface';
import { CRUDService } from '../templates/crud.service';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class ListsService extends CRUDService {

  entityName = "item"
  controllerUrl = this.apiUrl + "/items"

  successMessage: string = "Item successfully created";
  errorMessage: string = "There was an error creating the item";

  successEditMessage: string = "Item edited correctly";
  errorEditMessage: string = "There was an error editing the item";

  constructor(protected dialogService: MatDialog, protected apiService: ApiService, protected snackBar: MatSnackBar, private route: ActivatedRoute) {
    super(dialogService, apiService, snackBar)
  }

  get(listName: string): Observable<any> {
    return this.apiService.get(this.controllerUrl + "/fromFolder/" + listName);
  }

  checkItem(item: Item): void {
    this.apiService.put(this.controllerUrl, item);
    this.serviceSubject$.next();
  }

  getBody(resp: any, value?: any): object {
    return {
      content: resp.content,
      checked: false,
      folder: {
        name: value
      }
    }
  }

}
