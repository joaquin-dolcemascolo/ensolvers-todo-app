import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Folder } from 'src/app/interfaces/Folder.interface';
import { FoldersService } from 'src/app/services/folders.service';
import { CRUDComponent } from 'src/app/templates/crud.component';

@Component({
  selector: 'app-folders',
  templateUrl: './folders.component.html',
  styleUrls: ['./folders.component.scss']
})
export class FoldersComponent extends CRUDComponent implements OnInit, OnDestroy {

  records: Folder[] = []

  constructor(private foldersService: FoldersService) { super(foldersService) }

  ngOnInit(): void {
    super.ngOnInit();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }
}
