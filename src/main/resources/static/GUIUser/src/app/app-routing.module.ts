import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {MainComponent} from './user/main/main.component';
import {DetailComponent} from './user/detail/detail.component';

const routes: Routes = [
  {path: 'tables', component: MainComponent},
  {path: 'tables/:t', component: DetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
