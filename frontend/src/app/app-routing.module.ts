import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { FoldersComponent } from './pages/folders/folders.component';
import { ItemListComponent } from './pages/item-list/item-list.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "",
    component: LayoutComponent,
    children: [
      {
        path: "folders",
        component: FoldersComponent
      },
      {
        path: "folder/:list",
        component: ItemListComponent
      },
      {
        path: "",
        pathMatch: "full",
        redirectTo: "folders"
      },
    ]
  },
  {
    path: "**",
    pathMatch: "full",
    redirectTo: "login"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }