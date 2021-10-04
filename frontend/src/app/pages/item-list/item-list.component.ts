import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/interfaces/Item.interface';
import { ItemList } from 'src/app/interfaces/ItemList.interface';
import { ListsService } from 'src/app/services/lists.service';
import { CRUDComponent } from 'src/app/templates/crud.component';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent extends CRUDComponent implements OnInit, OnDestroy {

  folderTitle: string = "";

  constructor(private listsService: ListsService, private route: ActivatedRoute) {
    super(listsService);
    this.folderTitle = this.route.snapshot.paramMap.get('list')!;
  }

  ngOnInit(): void { super.ngOnInit(); }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  add() {
    this.listsService.add(this.folderTitle);
  }

  get(): void {
    this.listsService.get(this.route.snapshot.paramMap.get('list')!).subscribe(
      resp => this.orderItems(resp)
    );
  }

  orderItems(resp: Item[]): void {
    this.itemLists[0].items = resp.filter(item => !item.checked);
    this.itemLists[1].items = resp.filter(item => item.checked);
  }

  checkItem(item: Item): void {
    this.listsService.checkItem(item);
  }

  itemLists: ItemList[] = [
    {
      title: "Pending",
      items: []
    },
    {
      title: "Done",
      items: []
    },
  ]

}
