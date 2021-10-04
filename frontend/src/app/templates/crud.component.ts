import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { CRUDService } from './crud.service';

@Component({ template: '' })
export class CRUDComponent implements OnInit, OnDestroy {

  protected serviceSuscription$ = new Subscription();

  search: string = "";
  records: any = [];

  constructor(private crudService: CRUDService) { }

  ngOnInit(): void {
    // this.get();
    this.serviceSuscription$.add(this.crudService.serviceObserver$.subscribe(() => this.get()));
  }

  ngOnDestroy(): void {
    this.serviceSuscription$.unsubscribe();
  }

  add(): void {
    this.crudService.add();
  }

  edit(entity: any, event?: Event): void {
    if (event) event.stopPropagation();
    this.crudService.edit(entity);
  }

  delete(entityID: number, event?: Event): void {
    if (event) event.stopPropagation();
    this.crudService.delete(entityID);
  }

  get(): void {
    this.crudService.get().subscribe(
      resp => this.records = resp
    );
  }

}
