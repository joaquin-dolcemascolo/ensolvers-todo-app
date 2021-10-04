import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-edit',
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.scss']
})
export class AddEditComponent implements OnInit {

  title: string = "";
  form: FormGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.title = data.title;

    if (data.entity) {
      this.form = new FormBuilder().group({
        id: data.entity.id,
        content: [data.entity.name || data.entity.content, Validators.required]
      });
    } else {
      this.form = new FormBuilder().group({
        id: '',
        content: ['', Validators.required]
      });
    }
  }

  ngOnInit(): void { }

}
