import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Observable, Subject } from "rxjs";
import { AddEditComponent } from "../components/add-edit/add-edit.component";
import { ApiService } from "../services/api.service";

export class CRUDService {

  protected serviceSubject$ = new Subject<void>();
  public readonly serviceObserver$ = this.serviceSubject$.asObservable();

  entityName: string = "";
  apiUrl: string = "/api";
  controllerUrl: string = "";

  successMessage: string = "";
  errorMessage: string = "";

  successEditMessage: string = "";
  errorEditMessage: string = "";

  constructor(protected dialogService: MatDialog, protected apiService: ApiService, protected snackBar: MatSnackBar) { }

  add(value?: any): void {
    const dialogRef = this.openDialog(`Add a new ${this.entityName}`);
    dialogRef.afterClosed().subscribe(resp => {
      if (resp) this.apiService.post(this.controllerUrl, this.getBody(resp, value)).subscribe(
        success => { this.serviceSubject$.next(); this.snackBar.open(this.successMessage, "Ok") },
        error => this.snackBar.open(this.errorMessage, "Ok")
      );
    });
  }

  edit(entity: any): void {
    this.openDialog(`Edit ${this.entityName}`, entity).afterClosed().subscribe(resp => {
      if (resp) this.apiService.put(this.controllerUrl, this.getBody(resp)).subscribe(
        success => { this.serviceSubject$.next(); this.snackBar.open(this.successEditMessage, "Ok") },
        error => this.snackBar.open(this.errorEditMessage, "Ok")
      );
    });
  }

  delete(entityID: any): void {
    this.apiService.delete(`${this.controllerUrl}/${entityID}`).subscribe(() => this.serviceSubject$.next());
  }

  get(value?: any): Observable<any> {
    return this.apiService.get(this.controllerUrl);
  }

  getBody(resp: any, value?: any): Object {
    return null!;
  }

  protected openDialog(title: string, entity?: any): MatDialogRef<AddEditComponent> {
    const dialogRef = this.dialogService.open(
      AddEditComponent,
      { minWidth: "20rem", width: "40rem", autoFocus: true, disableClose: true, data: { title: title, entity: entity } }
    );

    return dialogRef;
  }
}
