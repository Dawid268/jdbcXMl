import { Component, OnInit } from '@angular/core';
import {MainComponent} from '../main/main.component';
import {main} from '@angular/compiler-cli/src/main';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../user.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html'
})
export class DetailComponent implements OnInit {
  name: string;
  idd;
  fieldsName: any[] = [];
  selectedLevel;
  sql;
  selectedList = [];
  otrzymanaLista = [];
  dbName;
  constructor(private user:  UserService, private route: ActivatedRoute) { }
  ngOnInit() {
    this.idd = this.route.snapshot.paramMap.get('t');
    this.getFields();
  }
  getFields () {
    this.user.getTablesPola(this.idd).subscribe(value => {
      console.log(value);
      this.fieldsName = value;
    });
  }

  selected() {
    this.name = this.selectedLevel;
    console.log(this.name);
      console.log('Tu mam jedno pole i je pobieram ');
      this.sql =  'SELECT ' + this.selectedLevel + ' FROM ' + this.dbName;
      this.user.getData(this.idd, this.sql).subscribe(value => {
        console.log(value);
      });
  }
  getAll () {
    this.sql =  'SELECT * FROM '+this.dbName;
    this.user.getSomeData(this.idd, this.sql).subscribe(value => {
      console.log(value);
    });
  }
  addSelectedList(selectedLevel) {
  this.selectedList.push(selectedLevel);
  }
  sendMultipleFields() {

    if (this.selectedLevel != null) {
      console.log('Tu mam wiele pól i je pobieram ');
      this.sql = 'SELECT ' + this.selectedList + ' FROM '  + this.dbName;
      console.log(this.sql);
      this.user.getSomeData(this.idd, this.sql).subscribe(value => {
        console.log('Powinienem pobrac kilka pól na raz: ');
        this.otrzymanaLista = value;
        console.log(this.otrzymanaLista);
      });
    } else {
      alert('Musisz wybrać jakieś pole!!');
    }
  }
}
