import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CRUDService } from '../templates/crud.service';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class FoldersService extends CRUDService {

  entityName = "folder"
  controllerUrl = this.apiUrl + "/folders"

  successMessage: string = "Folder successfully created";
  errorMessage: string = "There was an error creating the folder";

  successEditMessage: string = "Folder edited correctly";
  errorEditMessage: string = "There was an error editing the folder";

  constructor(protected dialogService: MatDialog, protected apiService: ApiService, protected snackBar: MatSnackBar) { super(dialogService, apiService, snackBar) }

  getBody(resp: any, value?: any): object {
    return {
      id: resp.id,
      name: resp.content
    }
  }
}
